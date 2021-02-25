package dev.toma.pubgmc.common.items.guns;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.client.models.ModelGun;
import dev.toma.pubgmc.common.capability.player.IPlayerData;
import dev.toma.pubgmc.common.capability.player.PlayerData;
import dev.toma.pubgmc.common.entity.EntityBullet;
import dev.toma.pubgmc.common.items.ItemAmmo;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.common.items.guns.attachments.ItemAttachment;
import dev.toma.pubgmc.config.common.CFGWeapon;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.PacketFiremode;
import dev.toma.pubgmc.network.sp.PacketCreateNBT;
import dev.toma.pubgmc.network.sp.PacketDelayedSound;
import dev.toma.pubgmc.network.sp.PacketReloadingSP;
import dev.toma.pubgmc.util.FirerateCooldownTracker;
import dev.toma.pubgmc.util.PUBGMCUtil;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Toma1O6
 * This is the core class for all guns
 */
public class GunBase extends PMCItem {
    public static final List<GunBase> GUNS = new ArrayList<>();
    public boolean airdropWeapon = false;
    protected Supplier<SoundEvent> action = null;
    private CFGWeapon wepStats;
    private float horizontal_recoil = 0f;
    private float vertical_recoil = 0f;
    private double reloadTime = 100;
    private int rate = 10;
    private int maxAmmo, exMaxAmmo;
    private AmmoType ammotype;
    private Firemode firemode;
    private Firemode[] validFiremodes = new Firemode[]{Firemode.SINGLE};
    private ReloadType reloadType;
    private GunType gunType;
    private boolean hasTwoRoundBurst = false;
    @SideOnly(Side.CLIENT)
    private ModelGun gunModel;

    //Attachments
    private ItemAttachment[]
            barrel = new ItemAttachment[0],
            grip = new ItemAttachment[0],
            magazine = new ItemAttachment[0],
            stock = new ItemAttachment[0],
            scope = new ItemAttachment[0];

    //SOUNDS
    private SoundEvent gun_shoot, gun_silenced, gun_reload;
    private float gun_volume, gun_volume_s;

    private ItemAmmo ammoItem;
    private int ammoCount = 0;

    protected GunBase(String name) {
        super(name);
        setCreativeTab(PMCTabs.TAB_GUNS);
        setMaxStackSize(1);
        GUNS.add(this);
    }

    public static boolean canAttachAttachment(GunBase gun, ItemAttachment attachment) {
        switch (attachment.getType()) {
            case BARREL:
                return DevUtil.containsD(gun.getBarrelAttachments(), attachment);
            case GRIP:
                return DevUtil.containsD(gun.getGripAttachments(), attachment);
            case MAGAZINE:
                return DevUtil.containsD(gun.getMagazineAttachments(), attachment);
            case SCOPE:
                return DevUtil.containsD(gun.getScopeAttachments(), attachment);
            case STOCK:
                return DevUtil.containsD(gun.getStockAttachments(), attachment);
            default:
                return false;
        }
    }

    public SoundEvent getWeaponReloadSound() {
        return this.gun_reload;
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
        int cooldown = FirerateCooldownTracker.getValue(player.getUniqueID());
        if ((this.hasAmmo(stack) || player.capabilities.isCreativeMode) && !data.isReloading() && cooldown <= 0) {
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
                FirerateCooldownTracker.set(player.getUniqueID(), this.getFireRate());
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

    //Set the firemode on both client and server
    public void switchFiremode(EntityPlayer player) {
        switch (this.getFiremode()) {
            case SINGLE: {
                if (canGunBurstFire()) {
                    setFiremode(Firemode.BURST);
                    break;
                } else {
                    setFiremode(Firemode.AUTO);
                    break;
                }
            }

            case BURST: {
                if (canGunAutofire()) {
                    setFiremode(Firemode.AUTO);
                    break;
                } else {
                    setFiremode(Firemode.SINGLE);
                    break;
                }
            }

            case AUTO: {
                setFiremode(Firemode.SINGLE);
                break;
            }
        }
        PacketHandler.INSTANCE.sendToServer(new PacketFiremode(firemode));
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
        tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firemode") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + getFiremode().translatedName());

        if (GuiScreen.isShiftKeyDown() && stack.hasTagCompound()) {
            DecimalFormat f = new DecimalFormat("###.##");
            DecimalFormat g = new DecimalFormat("###.###");

            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.damage") + ": " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + this.wepStats.damage);
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.reloadtime") + ": " + TextFormatting.RESET + "" + TextFormatting.GREEN + g.format(getReloadTime(stack) / 20) + " " + I18n.format("gun.reloadtime.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.velocity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.velocity.get() * 5.5) + " " + I18n.format("gun.velocity.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.gravity") + ": " + TextFormatting.RESET + "" + TextFormatting.BLUE + f.format(wepStats.gravityModifier.get() * 20) + " " + I18n.format("gun.gravity.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.firerate") + ": " + TextFormatting.RESET + "" + TextFormatting.AQUA + g.format(20.00 / this.getFireRate()) + " " + I18n.format("gun.firerate.info"));
            tooltip.add(TextFormatting.BOLD + I18n.format("gun.desc.ammotype") + ": " + TextFormatting.BLUE + ammotype.translatedName());
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

//-------------------------------------------------
// Setters and getters
//-------------------------------------------------

    public Supplier<SoundEvent> getAction() {
        return action;
    }

    @SideOnly(Side.CLIENT)
    public void setGunModel(ModelGun model) {
        this.gunModel = model;
    }

    @SideOnly(Side.CLIENT)
    public ModelGun getWeaponModel() {
        return gunModel;
    }

    public void setMaxAmmo(int maxAmmo, int exMaxAmmo) {
        this.maxAmmo = maxAmmo;
        this.exMaxAmmo = exMaxAmmo;
    }

    public void setReloadSound(SoundEvent reload) {
        this.gun_reload = reload;
    }

    public ItemAttachment[] getBarrelAttachments() {
        return barrel;
    }

    public void setBarrelAttachments(ItemAttachment... attachments) {
        this.barrel = attachments;
    }

    public ItemAttachment[] getGripAttachments() {
        return grip;
    }

    public void setGripAttachments(ItemAttachment... attachments) {
        this.grip = attachments;
    }

    public ItemAttachment[] getMagazineAttachments() {
        return magazine;
    }

    public void setMagazineAttachments(ItemAttachment... attachments) {
        this.magazine = attachments;
    }

    public ItemAttachment[] getStockAttachments() {
        return stock;
    }

    public void setStockAttachments(ItemAttachment... attachments) {
        this.stock = attachments;
    }

    public ItemAttachment[] getScopeAttachments() {
        return scope;
    }

    public void setScopeAttachments(ItemAttachment... attachments) {
        this.scope = attachments;
    }

    public Firemode[] getValidFiremodes() {
        return validFiremodes;
    }

    public void setValidFiremodes(Firemode... firemodes) {
        this.validFiremodes = firemodes;
    }

    public boolean canGunBurstFire() {
        for (Firemode f : validFiremodes) {
            if (f.equals(Firemode.BURST))
                return true;
        }
        return false;
    }

    public boolean canGunAutofire() {
        for (Firemode f : validFiremodes)
            if (f.equals(Firemode.AUTO))
                return true;

        return false;
    }

    public void setStats(CFGWeapon cfgStats) {
        this.wepStats = cfgStats;
    }

    public void setReloadTime(double time) {
        this.reloadTime = time;
    }

    public void setHorizontalRecoil(float recoil) {
        this.horizontal_recoil = recoil;
    }

    public void setVerticalRecoil(float recoil) {
        this.vertical_recoil = recoil;
    }

    public void setGunSoundVolume(float volume) {
        this.gun_volume = volume;
    }

    //set this after the first sound volume
    public void setGunSilencedSoundVolume(float volume) {
        this.gun_volume_s = volume;
    }

    public GunBase getGun() {
        return this;
    }

    public CFGWeapon getConfigurableStats() {
        return wepStats;
    }

    public double getReloadTime(ItemStack stack) {
        if (stack.hasTagCompound() && (stack.getTagCompound().getInteger("magazine") == 1 || stack.getTagCompound().getInteger("magazine") == 3) || stack.getTagCompound().getInteger("stock") == 1) {
            return reloadTime * 0.7;
        } else return reloadTime;
    }

    public AmmoType getAmmoType() {
        return ammotype;
    }

    public void setAmmoType(AmmoType type) {
        this.ammotype = type;
    }

    public SoundEvent getGunSound() {
        return gun_shoot;
    }

    public void setGunSound(SoundEvent shootSound) {
        this.gun_shoot = shootSound;
    }

    public SoundEvent getGunSilencedSound() {
        return gun_silenced;
    }

    public void setGunSilencedSound(SoundEvent silenced) {
        this.gun_silenced = silenced;
    }

    public float getGunVolume() {
        return gun_volume;
    }

    public float getGunSilencedVolume() {
        return gun_volume_s;
    }

    public Firemode getFiremode() {
        return firemode;
    }

    public void setFiremode(Firemode type) {
        this.firemode = type;
    }

    public boolean getCanSwitchFiremode() {
        return validFiremodes.length > 1;
    }

    public ReloadType getReloadType() {
        return reloadType;
    }

    public void setReloadType(ReloadType type) {
        this.reloadType = type;
    }

    public int getFireRate() {
        return rate;
    }

    public void setFireRate(int firerate) {
        this.rate = firerate;
    }

    public GunType getGunType() {
        return gunType;
    }

    //For testing attachment types for different guns
    public void setGunType(GunType type) {
        this.gunType = type;
    }

    public float getHorizontalRecoil(ItemStack stack) {
        return horizontal_recoil;
    }

    public float getVerticalRecoil(ItemStack stack) {
        return vertical_recoil;
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

    public boolean isHasTwoRoundBurst() {
        return hasTwoRoundBurst;
    }

    public void setHasTwoRoundBurst(boolean hasTwoRoundBurst) {
        this.hasTwoRoundBurst = hasTwoRoundBurst;
    }

    public enum Firemode {
        SINGLE("gun.firemode.single"),
        BURST("gun.firemode.burst"),
        AUTO("gun.firemode.auto");

        private String name;

        Firemode(String name) {
            this.name = name;
        }

        public static Firemode[] all() {
            return new Firemode[]{Firemode.SINGLE, Firemode.BURST, Firemode.AUTO};
        }

        public static Firemode[] noBurst() {
            return new Firemode[]{Firemode.SINGLE, Firemode.AUTO};
        }

        public static Firemode[] noAuto() {
            return new Firemode[]{Firemode.SINGLE, Firemode.BURST};
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
                data.setAiming(false);
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
        LMG(40), PISTOL(100), SHOTGUN(80), SMG(70), AR(50), DMR(30), SR(10);

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
