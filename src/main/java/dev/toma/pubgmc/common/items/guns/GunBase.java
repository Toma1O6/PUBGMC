package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.common.capability.player.AimInfo;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.entity.EntityBullet;
import dev.toma.pubgmc.common.items.ItemAmmo;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import dev.toma.pubgmc.config.common.CFGWeapon;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.sp.PacketCreateNBT;
import dev.toma.pubgmc.network.sp.PacketDelayedSound;
import dev.toma.pubgmc.network.sp.PacketReloadingSP;
import dev.toma.pubgmc.util.PUBGMCUtil;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This is the core class for all guns
 * @author Toma
 */
public class GunBase extends PMCItem {

    public static final List<GunBase> GUNS = new ArrayList<>();
    protected final Supplier<SoundEvent> action;
    private final CFGWeapon wepStats;
    private final float horizontalRecoil;
    private final float verticalRecoil;
    private final int reloadTime;
    private final int firerate;
    private final int maxAmmo, exMaxAmmo;
    private final int burstAmount;
    private final AmmoType ammoType;
    private final Firemode firemode;
    private final Function<Firemode, Firemode> firemodeSwitch;
    private final ReloadType reloadType;
    private final GunType gunType;
    private final SoundEvent shootSound, silentShootSound, reloadSound;
    private final float gunVolume, silentGunVolume;
    private final GunAttachments attachments;

    // TODO delete all below
    @SideOnly(Side.CLIENT)
    private ModelGun gunModel;
    private ItemAttachment[]
            barrel = new ItemAttachment[0],
            grip = new ItemAttachment[0],
            magazine = new ItemAttachment[0],
            stock = new ItemAttachment[0],
            scope = new ItemAttachment[0];
    private ItemAmmo ammoItem;
    private int ammoCount = 0;

    protected GunBase(GunBuilder builder) {
        super(builder.name);
        setCreativeTab(PMCTabs.TAB_GUNS);
        setMaxStackSize(1);
        GUNS.add(this);
        this.action = builder.action;
        this.gunType = builder.weaponType;
        this.wepStats = builder.cfgStats;
        this.verticalRecoil = builder.vertical;
        this.horizontalRecoil = builder.horizontal;
        this.reloadType = builder.reloadType;
        this.reloadTime = builder.reloadTime;
        this.firerate = builder.firerate;
        this.ammoType = builder.ammoType;
        this.maxAmmo = builder.maxAmmo;
        this.exMaxAmmo = builder.exMaxAmmo;
        this.firemode = builder.defFiremode;
        this.firemodeSwitch = builder.firemodeSwitchFunc;
        this.burstAmount = builder.burstAmount;
        this.shootSound = builder.shootNormal;
        this.silentShootSound = builder.shootSilenced;
        this.reloadSound = builder.reloadSound;
        this.gunVolume = builder.volumeNormal;
        this.silentGunVolume = builder.volumeSilenced;
        this.attachments = builder.attachments;
    }

    public static boolean canAttachAttachment(GunBase gun, ItemAttachment attachment) {
        switch (attachment.getType()) {
            case BARREL:
                return DevUtil.contains(gun.getBarrelAttachments(), attachment);
            case GRIP:
                return DevUtil.contains(gun.getGripAttachments(), attachment);
            case MAGAZINE:
                return DevUtil.contains(gun.getMagazineAttachments(), attachment);
            case SCOPE:
                return DevUtil.contains(gun.getScopeAttachments(), attachment);
            case STOCK:
                return DevUtil.contains(gun.getStockAttachments(), attachment);
            default:
                return false;
        }
    }

    public SoundEvent getWeaponReloadSound() {
        return this.reloadSound;
    }

    /**
     * Gets maximum possible amount of bullet gun can have loaded
     *
     * @param stack - the gun itemstack
     * @return the weapon ammo limit
     */
    public int getWeaponAmmoLimit(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getInteger("magazine") > 1 ? exMaxAmmo : maxAmmo;
    }

    /**
     * Used to spawn bullet entity, called from packet
     */
    public void shoot(World world, EntityPlayer player, ItemStack stack) {
        IPlayerData data = PlayerData.get(player);
        CooldownTracker tracker = player.getCooldownTracker();
        if ((this.hasAmmo(stack) || player.capabilities.isCreativeMode) && !data.isReloading() && !tracker.hasCooldown(stack.getItem())) {
            if (!world.isRemote) {
                if (!gunType.equals(GunType.SHOTGUN)) {
                    EntityBullet bullet = new EntityBullet(world, player, this);
                    world.spawnEntity(bullet);
                } else {
                    for (int i = 0; i < 8; i++) {
                        EntityBullet bullet = new EntityBullet(world, player, this);
                        world.spawnEntity(bullet);
                    }
                }

                if (!player.capabilities.isCreativeMode) {
                    stack.getTagCompound().setInteger("ammo", stack.getTagCompound().getInteger("ammo") - 1);
                }
                tracker.setCooldown(stack.getItem(), getFireRate());
                PacketHandler.sendToClientsAround(new PacketDelayedSound(playWeaponSound(stack), playWeaponSoundVolume(stack), player.posX, player.posY, player.posZ), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 150));
            }
        }
    }

    /**
     * Function for getting the right volume of the weapon
     *
     * @param stack - the weapon
     * @return volume
     */
    public float playWeaponSoundVolume(ItemStack stack) {
        float volume = 1f;

        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("barrel") == 1) {
                volume = getGunSilencedVolume();
            } else volume = getGunVolume();
        } else PUBGMCUtil.createNBT(stack);
        return volume;
    }

    /**
     * Function for playing the right weapon sound
     * If silencer is equipped the silenced sound will be played
     *
     * @param stack - the itemstack which is shooting
     * @return the soundEvent
     */
    public SoundEvent playWeaponSound(ItemStack stack) {
        SoundEvent event = SoundEvents.BLOCK_LEVER_CLICK;
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().getInteger("barrel") == 1) {
                if (getGunSilencedSound() != null) {
                    event = getGunSilencedSound();
                }
            } else {
                event = getGunSound();
            }
        } else {
            PUBGMCUtil.createNBT(stack);
            event = getGunSound();
        }

        return event;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof EntityPlayer && !worldIn.isRemote) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (!player.capabilities.isCreativeMode) {
                if (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("isValidWeapon")) {
                    PUBGMCUtil.createWeaponNBT(stack, 0);
                    PacketHandler.INSTANCE.sendTo(new PacketCreateNBT(0), (EntityPlayerMP) player);
                }
            } else {
                if (!stack.hasTagCompound() || !stack.getTagCompound().getBoolean("isValidWeapon")) {
                    PUBGMCUtil.createWeaponNBT(stack, getWeaponAmmoLimit(stack));
                    PacketHandler.INSTANCE.sendTo(new PacketCreateNBT(getWeaponAmmoLimit(stack)), (EntityPlayerMP) player);
                }
            }
        }
    }

    public boolean hasAmmo(ItemStack itemStack) {
        return itemStack.getTagCompound().getInteger("ammo") > 0;
    }

    public boolean hasPlayerAmmoForGun(EntityPlayer player, GunBase gun) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);

            if (stack.getItem() instanceof ItemAmmo) {
                if (((ItemAmmo) stack.getItem()).type == gun.getAmmoType()) {
                    ammoCount = stack.getCount();
                    ammoItem = (ItemAmmo) stack.getItem();
                    return true;
                }
            }
        }

        return player.capabilities.isCreativeMode;
    }

    //Here we add all info which will be displayed on the stack
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String barrel = "default";
        String grip = "none";
        String magazine = "default";
        String stock = "default";
        String scope = "ironsight";
        
        if (stack.hasTagCompound()) {
            NBTTagCompound c = stack.getTagCompound();

            if (c.getInteger("barrel") == 1) barrel = "suppressor";
            if (c.getInteger("barrel") == 2) barrel = "compensator";
            if (c.getInteger("scope") == 1) scope = "red dot sight";
            if (c.getInteger("scope") == 2) scope = "holographic";
            if (c.getInteger("scope") == 3) scope = "2X";
            if (c.getInteger("scope") == 4) scope = "4X";
            if (c.getInteger("scope") == 5) scope = "8X";
            if (c.getInteger("scope") == 6) scope = "15X";
            if (c.getInteger("grip") == 1) grip = "vertical grip";
            if (c.getInteger("grip") == 2) grip = "angled grip";
            if (c.getInteger("magazine") == 1) magazine = "quickdraw";
            if (c.getInteger("magazine") == 2) magazine = "extended";
            if (c.getInteger("magazine") == 3) magazine = "extended quickdraw";
            if (c.getInteger("stock") == 2) stock = "cheekpad";
            if (c.getInteger("stock") == 1) stock = "bullet loops";
        }

        tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammo") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + getAmmo(stack));
        tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firemode") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + getFiremode(stack).translatedName());

        if (GuiScreen.isShiftKeyDown() && stack.hasTagCompound()) {
            DecimalFormat f = new DecimalFormat("###.##");
            DecimalFormat g = new DecimalFormat("###.###");
            // TODO this is just temp fix, this needs to be rewritten completely
            if(wepStats.damage == null)
                return;
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.damage") + ": " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + this.wepStats.damage.get());
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.reloadtime") + ": " + TextFormatting.RESET + "" + TextFormatting.GREEN + g.format(getReloadTime(stack) / 20) + " " + I18n.format("gun.reloadtime.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.velocity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.velocity.get() * 5.5) + " " + I18n.format("gun.velocity.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.gravity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.gravityModifier.get() * 20) + " " + I18n.format("gun.gravity.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firerate") + ": " + TextFormatting.RESET + "" + TextFormatting.AQUA + g.format(20.00 / this.getFireRate()) + " " + I18n.format("gun.firerate.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammotype") + ": " + TextFormatting.BLUE + ammoType.translatedName());
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.maxammo") + ": " + TextFormatting.RESET + "" + TextFormatting.RED + getWeaponAmmoLimit(stack));
        } else if (GuiScreen.isCtrlKeyDown()) {
            if (stack.hasTagCompound()) {
                tooltip.add(TextFormatting.BOLD + "Attachments:");
                tooltip.add(TextFormatting.BOLD + "Scope:" + TextFormatting.RESET + " " + TextFormatting.GREEN + scope);
                tooltip.add(TextFormatting.BOLD + "Barrel:" + TextFormatting.RESET + "" + TextFormatting.GREEN + " " + barrel);
                tooltip.add(TextFormatting.BOLD + "Grip:" + TextFormatting.RESET + " " + TextFormatting.GREEN + grip);
                tooltip.add(TextFormatting.BOLD + "Magazine:" + TextFormatting.RESET + " " + TextFormatting.GREEN + magazine);
                tooltip.add(TextFormatting.BOLD + "Stock:" + TextFormatting.RESET + " " + TextFormatting.GREEN + stock);
            }
        } else {
            tooltip.add(TextFormatting.YELLOW + I18n.format("gun.desc.moreinfo"));
            tooltip.add(TextFormatting.YELLOW + I18n.format("gun.desc.moreinfo2"));
        }
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }

    public int getBurstAmount() {
        return burstAmount;
    }

    public Firemode getFiremode(ItemStack stack) {
        NBTTagCompound nbt = getOrCreateGunData(stack);
        int i = nbt.getInteger("firemode");
        return Firemode.fromID(i);
    }

    public void setFiremode(ItemStack stack, Firemode firemode) {
        NBTTagCompound nbt = getOrCreateGunData(stack);
        nbt.setInteger("firemode", firemode.ordinal());
    }

    public Firemode switchFiremode(ItemStack stack) {
        Firemode current = getFiremode(stack);
        return firemodeSwitch.apply(current);
    }

    public NBTTagCompound getOrCreateGunData(ItemStack stack) {
        if(!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }

    public Supplier<SoundEvent> getAction() {
        return action;
    }

    @SideOnly(Side.CLIENT)
    @Deprecated
    public void setGunModel(ModelGun model) {
        this.gunModel = model;
    }

    @SideOnly(Side.CLIENT)
    @Deprecated
    public ModelGun getWeaponModel() {
        return gunModel;
    }

    @Deprecated
    public ItemAttachment[] getBarrelAttachments() {
        return barrel;
    }

    @Deprecated
    public ItemAttachment[] getGripAttachments() {
        return grip;
    }

    @Deprecated
    public ItemAttachment[] getMagazineAttachments() {
        return magazine;
    }

    @Deprecated
    public ItemAttachment[] getStockAttachments() {
        return stock;
    }

    @Deprecated
    public ItemAttachment[] getScopeAttachments() {
        return scope;
    }

    public CFGWeapon getConfigurableStats() {
        return wepStats;
    }

    public double getReloadTime(ItemStack stack) {
        if (stack.hasTagCompound() && (stack.getTagCompound().getInteger("magazine") == 1 || stack.getTagCompound().getInteger("magazine") == 3) || stack.getTagCompound().getInteger("stock") == 1) {
            return reloadTime * 0.7;
        } else return reloadTime;
    }

    public void registerToGlobalLootPool(boolean airdropOnly) {
        LootManager.register(LootType.GUN, new LootManager.LootEntry(this, gunType.getWeight(), airdropOnly));
    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public SoundEvent getGunSound() {
        return shootSound;
    }

    public SoundEvent getGunSilencedSound() {
        return silentShootSound;
    }

    public float getGunVolume() {
        return gunVolume;
    }

    public float getGunSilencedVolume() {
        return silentGunVolume;
    }

    public ReloadType getReloadType() {
        return reloadType;
    }

    public int getFireRate() {
        return firerate;
    }

    public GunType getGunType() {
        return gunType;
    }

    public float getHorizontalRecoil(ItemStack stack) {
        return horizontalRecoil;
    }

    public float getVerticalRecoil(ItemStack stack) {
        return verticalRecoil;
    }

    public int getAmmo(ItemStack stack) {
        int a = 0;
        if (stack.hasTagCompound()) {
            a = stack.getTagCompound().getInteger("ammo");
        }

        return a;
    }

    public boolean containsAttachment(ItemAttachment[] group, ItemAttachment toCheck) {
        for (ItemAttachment a : group) {
            if (a == toCheck) {
                return true;
            }
        }

        return false;
    }

    public enum Firemode {
        SINGLE("gun.firemode.single"),
        BURST("gun.firemode.burst"),
        AUTO("gun.firemode.auto");

        private String name;

        Firemode(String name) {
            this.name = name;
        }

        public static Firemode fromID(int id) {
            return values()[DevUtil.wrap(id, 0, 2)];
        }

        public static Firemode ignoreAuto(Firemode current) {
            return current == SINGLE ? BURST : SINGLE;
        }

        public static Firemode ignoreBurst(Firemode current) {
            return current == SINGLE ? AUTO : SINGLE;
        }

        public static Firemode cycleAll(Firemode current) {
            int i = current.ordinal();
            int j = i + 1;
            if(j > 2)
                j = 0;
            return values()[j];
        }

        @SideOnly(Side.CLIENT)
        public String translatedName() {
            return I18n.format(name);
        }
    }

    public enum ReloadType {
        MAGAZINE,
        SINGLE,
        KAR98K;

        // TODO: clean
        public void handleReload(EntityPlayer player) {
            IPlayerData data = PlayerData.get(player);
            ItemStack heldItem = player.getHeldItemMainhand();

            if (heldItem.getItem() instanceof GunBase) {
                data.getAimInfo().setAiming(false, AimInfo.STOP_AIMING_SPEED);
                GunBase gun = (GunBase) heldItem.getItem();

                if ((heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem) || !gun.hasPlayerAmmoForGun(player, gun)) && data.isReloading()) {
                    data.setReloading(false);
                    PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP) player);
                }

                if (heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem)) {
                    if (gun.getReloadType() == ReloadType.MAGAZINE) {
                        while ((gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem)) {
                            int ammoInGun = heldItem.getTagCompound().getInteger("ammo");
                            int ammoToFill = gun.getWeaponAmmoLimit(heldItem) - ammoInGun;
                            int ammoInInventory = gun.ammoCount;

                            if (ammoToFill > ammoInInventory) ammoToFill = ammoInInventory;

                            if (!player.capabilities.isCreativeMode) {
                                ItemAmmo ammo = (ItemAmmo) gun.ammoItem.getAmmoItem();
                                player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, ammoToFill, null);
                            }

                            heldItem.getTagCompound().setInteger("ammo", ammoInGun + ammoToFill);
                        }

                        data.setReloading(false);
                        PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP) player);
                    } else if (gun.getReloadType() == ReloadType.SINGLE) {
                        if (gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) {
                            //If the gun is already full and player still atempts to reload, cancel it
                            if (heldItem.getTagCompound().getInteger("ammo") == gun.getWeaponAmmoLimit(heldItem)) {
                                data.setReloading(false);
                                PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP) player);
                            }

                            if (heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem)) {
                                ItemAmmo ammo = (ItemAmmo) gun.ammoItem.getAmmoItem();

                                if (!player.capabilities.isCreativeMode) {
                                    player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
                                }

                                //Increase ammo count by 1
                                heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
                            }
                        }
                    } else if (gun.getReloadType() == ReloadType.KAR98K) {
                        if (!gun.hasAmmo(heldItem)) {
                            while ((gun.hasPlayerAmmoForGun(player, gun) || player.capabilities.isCreativeMode) && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem)) {
                                ItemAmmo ammo = (ItemAmmo) gun.ammoItem.getAmmoItem();

                                if (!player.capabilities.isCreativeMode) {
                                    player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
                                }

                                heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
                            }

                            data.setReloading(false);
                            PacketHandler.INSTANCE.sendTo(new PacketReloadingSP(false), (EntityPlayerMP) player);
                        } else if (heldItem.getTagCompound().getInteger("ammo") > 0 && heldItem.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(heldItem)) {
                            ItemAmmo ammo = (ItemAmmo) gun.ammoItem.getAmmoItem();

                            if (!player.capabilities.isCreativeMode) {
                                player.inventory.clearMatchingItems(ammo.getAmmoItem(), 0, 1, null);
                            }

                            heldItem.getTagCompound().setInteger("ammo", heldItem.getTagCompound().getInteger("ammo") + 1);
                        }
                    } else {
                        throw new IllegalArgumentException("Unknown reload type. Report this to mod author!");
                    }
                }
            }
        }
    }

    public enum GunType {
        LMG(40),
        PISTOL(100),
        SHOTGUN(80),
        SMG(70),
        AR(50),
        DMR(30),
        SR(10);

        private int weight;

        GunType(int weight) {
            this.weight = weight;
        }

        public static GunType getTypeFromName(String name) {
            for (GunType type : values()) {
                if (type.name().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }

        public static List<GunType> toCollection() {
            List<GunType> list = new ArrayList<>(values().length);
            for (int i = 0; i < values().length; i++) {
                list.add(values()[i]);
            }
            list = list.stream().filter(type -> type != LMG).collect(Collectors.toList());
            return list;
        }

        public int getWeight() {
            return weight;
        }
    }
}
