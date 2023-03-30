package dev.toma.pubgmc.client;

import dev.toma.pubgmc.ClientHooks;
import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.animation.AnimationDispatcher;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.client.animation.interfaces.HandAnimate;
import dev.toma.pubgmc.client.gui.animator.GuiAnimator;
import dev.toma.pubgmc.client.gui.hands.GuiHandPlacer;
import dev.toma.pubgmc.client.gui.menu.GuiGunConfig;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.util.KeyBinds;
import dev.toma.pubgmc.common.capability.player.*;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.entity.controllable.IControllable;
import dev.toma.pubgmc.common.items.ItemAmmo;
import dev.toma.pubgmc.common.items.ItemFuelCan;
import dev.toma.pubgmc.common.items.armor.ArmorBase;
import dev.toma.pubgmc.common.items.attachment.AttachmentType;
import dev.toma.pubgmc.common.items.attachment.ItemGrip;
import dev.toma.pubgmc.common.items.attachment.ItemMuzzle;
import dev.toma.pubgmc.common.items.attachment.ScopeData;
import dev.toma.pubgmc.common.items.guns.AmmoType;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import dev.toma.pubgmc.common.items.heal.ItemHealing;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFGEnumOverlayStyle;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.server.*;
import dev.toma.pubgmc.util.handlers.GuiHandler;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;

public class ClientEvents {

    private static final ResourceLocation NV = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nv.png");
    private static final ResourceLocation BOOST = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_empty.png");
    private static final ResourceLocation BOOST_FULL = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_full.png");
    private static final ResourceLocation BOOST_OVERLAY = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_overlay.png");
    private static final ResourceLocation[] BACKPACK_OVERLAY = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack1.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack2.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack3.png")};
    private static final ResourceLocation[] NIGHT_VISION_OVERLAY = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nightvision_off.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nightvision_on.png")};
    private static final ResourceLocation VEHICLE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vehicle.png");

    private final WeaponCooldownTracker tracker = new WeaponCooldownTracker();
    private int shotsFired;
    private boolean shooting;
    private int shootingTimer;

    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {
        if(ConfigPMC.client.customMainMenu.get() && event.getGui() instanceof GuiMainMenu) {
            event.setGui(new GuiMenu());
        }
    }

    @SubscribeEvent
    public void renderHands(RenderSpecificHandEvent event) {
        ItemStack stack = event.getItemStack();
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        float partial = event.getPartialTicks();
        EnumHand hand = event.getHand();
        if (hand == EnumHand.MAIN_HAND) {
            if(stack.getItem() instanceof HandAnimate) {
                HandAnimate animate = (HandAnimate) stack.getItem();
                event.setCanceled(true);
                float pitch = DevUtil.lerp(player.rotationPitch, player.prevRotationPitch, partial);
                float swing = event.getSwingProgress();
                AnimationProcessor processor = AnimationProcessor.instance();
                GlStateManager.pushMatrix();
                {
                    processor.process(AnimationElement.ITEM_AND_HANDS);
                    GlStateManager.pushMatrix();
                    {
                        processor.process(AnimationElement.HANDS);
                        GlStateManager.pushMatrix();
                        {
                            GlStateManager.disableCull();
                            GlStateManager.pushMatrix();
                            {
                                processor.process(AnimationElement.RIGHT_HAND);
                                animate.animate(EnumHandSide.RIGHT);
                            }
                            GlStateManager.popMatrix();
                            GlStateManager.pushMatrix();
                            {
                                processor.process(AnimationElement.LEFT_HAND);
                                animate.animate(EnumHandSide.LEFT);
                            }
                            GlStateManager.popMatrix();
                            GlStateManager.enableCull();
                        }
                        GlStateManager.popMatrix();
                    }
                    GlStateManager.popMatrix();
                    if(!processor.isItemRenderBlocked()) {
                        processor.process(AnimationElement.ITEM);
                        mc.getItemRenderer().renderItemInFirstPerson(player, partial, pitch, event.getHand(), swing, stack, 0.0F);
                    }
                }
                GlStateManager.popMatrix();
            }
        } else if (hand == EnumHand.OFF_HAND) {
            ItemStack mainHandItemStack = player.getHeldItemMainhand();
            if (mainHandItemStack.getItem() instanceof HandAnimate) {
                if (((HandAnimate) mainHandItemStack.getItem()).cancelShieldRender()) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void renderOverlayPost(RenderGameOverlayEvent.Post e) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP sp = mc.player;
        ScaledResolution res = new ScaledResolution(mc);

        //Get the player capability to use the stored data
        IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        //e.getType() == ElementType.TEXT - this is very important otherwise it will mess all fonts used in mc
        if (!e.isCancelable() && e.getType() == ElementType.TEXT && !sp.capabilities.isCreativeMode && !sp.isSpectator() && ConfigPMC.client.overlays.imageBoostOverlay.get() == CFGEnumOverlayStyle.TEXT && !data.getBoostStats().isEmpty()) {
            mc.entityRenderer.setupOverlayRendering();
            int width = res.getScaledWidth();
            int height = res.getScaledHeight();
            int left = width / 2 + 45;
            int top = height - 49;

            int color;
            int boostLevel = data.getBoostStats().getLevel();
            if (boostLevel >= 10) {
                color = 14651904;
            } else {
                color = 14664960;
            }
            if (boostLevel > 9) {
                left -= 5;
            }
            mc.fontRenderer.drawStringWithShadow(boostLevel + " / 20", left + ConfigPMC.client.overlays.textBoostOverlayPos.getX(), top + ConfigPMC.client.overlays.textBoostOverlayPos.getY(), color);
        }

        //Ammo and Firemode info rendering
        if (!e.isCancelable() && e.getType() == ElementType.TEXT && sp.getHeldItemMainhand().getItem() instanceof GunBase) {
            ItemStack weaponStack = sp.getHeldItemMainhand();
            GunBase gun = (GunBase) weaponStack.getItem();
            mc.entityRenderer.setupOverlayRendering();
            int x = 15;
            int y = e.getResolution().getScaledHeight() - 15;
            int totalCount = 0;
            for (int i = 0; i < sp.inventory.getSizeInventory(); i++) {
                ItemStack stack = sp.inventory.getStackInSlot(i);
                if (stack.getItem() instanceof ItemAmmo) {
                    ItemAmmo ammo = (ItemAmmo) stack.getItem();
                    if (ammo.type == gun.getAmmoType()) {
                        totalCount += stack.getCount();
                    }
                }
            }
            if (weaponStack.hasTagCompound()) {
                int ammo = gun.getAmmo(weaponStack);
                mc.fontRenderer.drawStringWithShadow(gun.getItemStackDisplayName(weaponStack) + ": " + gun.getFiremode(weaponStack), x, y - 9, 16777215);
                mc.fontRenderer.drawStringWithShadow(TextFormatting.BOLD.toString() + ammo + TextFormatting.RESET + " | " + totalCount, x, y, 16777215);
            }
        }
        renderVehicleOverlay(sp, mc, res, e);
    }

    @SubscribeEvent
    public void renderOverlayPre(RenderGameOverlayEvent.Pre e) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP sp = mc.player;
        ScaledResolution res = new ScaledResolution(mc);
        ItemStack stack = sp.getHeldItemMainhand();
        IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();

        if (e.getType() == ElementType.CROSSHAIRS) {
            if (!ConfigPMC.developerMode.get() && stack.getItem() instanceof GunBase) {
                e.setCanceled(true);
            }
        }

        if (ConfigPMC.client.overlays.imageBoostOverlay.get() == CFGEnumOverlayStyle.IMAGE) {
            if (e.getType() == ElementType.EXPERIENCE) {
                if (ConfigPMC.client.overlays.imgBoostOverlayPos.getX() == 0 && ConfigPMC.client.overlays.imgBoostOverlayPos.getY() == 0 && !data.getBoostStats().isEmpty()) {
                    e.setCanceled(true);
                }
            }
        }

        if (e.getType() == ElementType.ALL) {
            if (data.isUsingNV()) {
                ImageUtil.drawFullScreenImage(mc, res, NV, true);
            }

            if (!sp.capabilities.isCreativeMode && !sp.isSpectator() && !data.getBoostStats().isEmpty()) {
                renderBoost(data.getBoostStats());
            }

            if (ConfigPMC.client.overlays.renderArmorIcons.get() && !sp.isSpectator())
                renderArmorIcons(e, sp, res, mc, data);

            if (stack.getItem() instanceof ItemHealing || stack.getItem() instanceof ItemFuelCan) {
                if (sp.isHandActive()) {
                    drawItemUseOverlay(sp, mc, res, e, stack);
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        EntityPlayerSP sp = Minecraft.getMinecraft().player;
        if(ConfigPMC.developerMode.get()) {
            Minecraft mc = Minecraft.getMinecraft();
            if(Keyboard.isKeyDown(Keyboard.KEY_O)) {
                mc.displayGuiScreen(new GuiGunConfig());
            } else if(Keyboard.isKeyDown(Keyboard.KEY_M)) {
                mc.displayGuiScreen(new GuiAnimator());
            } else if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
                mc.displayGuiScreen(new GuiHandPlacer());
            }
        }
        if (KeyBinds.PRONE.isPressed()) {
            IPlayerData data = PlayerData.get(sp);
            if (data != null) {
                data.setProning(!data.isProning());
                if(data.getAimInfo().isAiming()) this.setAiming(data, false);
                if(data.isReloading()) {
                    data.getReloadInfo().interrupt(data);
                    PacketHandler.sendToServer(new SPacketSetProperty(false, SPacketSetProperty.Action.RELOAD));
                }
                PacketHandler.sendToServer(new PacketProne(data.isProning()));
            }
        }
        IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        if (KeyBinds.ATTACHMENT.isPressed()) {
            if(sp.getHeldItemMainhand().getItem() instanceof GunBase) {
                PacketHandler.INSTANCE.sendToServer(new PacketOpenGui(GuiHandler.GUI_ATTACHMENTS));
            } else {
                sp.sendStatusMessage(new TextComponentString(TextFormatting.RED + "You must hold gun in your hand!"), true);
            }
        }
        if (KeyBinds.RELOAD.isPressed()) {
            ItemStack stack = sp.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                if(data.isReloading()) {
                    IReloader reloader = gun.getReloader();
                    if(reloader.canInterrupt(gun, stack)) {
                        ReloadInfo info = data.getReloadInfo();
                        info.setReloading(false);
                        PacketHandler.INSTANCE.sendToServer(new SPacketSetProperty(false, SPacketSetProperty.Action.RELOAD));
                        AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                        return;
                    }
                }
                if (stack.hasTagCompound()) {
                    int ammo = gun.getAmmo(stack);
                    AmmoType type = gun.getAmmoType();
                    if (ammo < gun.getWeaponAmmoLimit(stack)) {
                        boolean hasAmmo = false;
                        for (int i = 0; i < sp.inventory.getSizeInventory(); i++) {
                            ItemStack ammoStack = sp.inventory.getStackInSlot(i);
                            if(!ammoStack.isEmpty() && ammoStack.getItem() == type.ammo()) {
                                hasAmmo = true;
                                break;
                            }
                        }
                        if(hasAmmo) {
                            AnimationDispatcher.dispatchReloadAnimation(gun, stack, sp);
                            ReloadInfo info = data.getReloadInfo();
                            info.startReload(sp, gun, stack);
                            PacketHandler.INSTANCE.sendToServer(new SPacketSetProperty(true, SPacketSetProperty.Action.RELOAD));
                        }
                    }
                }
            }
        }

        //Same as above, we just send packet to server and everything else will be done in the rendering method above
        if (KeyBinds.NV.isPressed()) {
            if (data.getEquippedNV()) {
                PacketHandler.sendToServer(new SPacketSetProperty(!data.isUsingNV(), SPacketSetProperty.Action.NIGHT_VISION));
            }
        }

        //Switch firemode
        if (KeyBinds.FIREMODE.isPressed()) {
            ItemStack stack = sp.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                PacketHandler.sendToServer(new SPacketFiremode());
            }
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gs = mc.gameSettings;
        EntityPlayerSP player = mc.player;
        if (player != null && !player.isSpectator()) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
                if(isEquipAnimationDone(mc)) {
                    if (gs.keyBindAttack.isPressed()) {
                        if (gun.getFiremode(stack) == GunBase.Firemode.SINGLE) {
                            if (!isReloading(player, data, gun, stack) && !tracker.isOnCooldown(gun)) {
                                if (gun.hasAmmo(stack)) {
                                    PacketHandler.INSTANCE.sendToServer(new PacketShoot());
                                    tracker.add(gun);
                                    if(gun.getAction() != null) {
                                        Pubgmc.proxy.playMCDelayedSound(gun.getAction().get(), player.posX, player.posY, player.posZ, 1.0F, 20);
                                        if(gun.getGunType() == GunBase.GunType.SR) {
                                            setAiming(data, false);
                                        }
                                    }
                                    applyRecoil(player, stack, gun, data.getAimInfo().isAiming());
                                } else {
                                    player.playSound(PMCSounds.gun_noammo, 4f, 1f);
                                }
                            }
                        } else if (gun.getFiremode(stack) == GunBase.Firemode.BURST) {
                            if (!shooting && !isReloading(player, data, gun, stack) && !tracker.isOnCooldown(gun)) {
                                if (gun.hasAmmo(stack)) {
                                    shooting = true;
                                    shotsFired = 0;
                                } else {
                                    player.playSound(PMCSounds.gun_noammo, 4f, 1f);
                                }
                            }
                        }
                    }
                }

                //Aiming on RMB press
                if (gs.keyBindUseItem.isPressed() && isEquipAnimationDone(mc)) {
                    if (!data.getAimInfo().isAiming()) {
                        player.setSprinting(false);
                        RenderHandler.fovBackup = gs.fovSetting;
                        RenderHandler.sensBackup = gs.mouseSensitivity;
                        ScopeData scopeData = gun.getScopeData(stack);
                        if(scopeData != null && scopeData.getMouseSens() < 1.0F) {
                            gs.mouseSensitivity *= scopeData.getMouseSens();
                        }
                        PacketHandler.sendToServer(new SPacketSetProperty(true, SPacketSetProperty.Action.AIM));
                        AnimationDispatcher.dispatchAimAnimation(gun, stack);
                    } else {
                        gs.fovSetting = RenderHandler.fovBackup;
                        gs.mouseSensitivity = RenderHandler.sensBackup;
                        PacketHandler.sendToServer(new SPacketSetProperty(false, SPacketSetProperty.Action.AIM));
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onClientTick(TickEvent.ClientTickEvent ev) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        GameSettings gs = mc.gameSettings;
        if(ev.phase == Phase.END) {
            if(mc.currentScreen instanceof ITickable) {
                ((ITickable) mc.currentScreen).update();
            }
            AnimationProcessor.instance().processTick();
        }

        if (player != null && ev.phase == Phase.END && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            tracker.tick(mc.isGamePaused());
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            if(player.getRidingEntity() instanceof IControllable && player.getRidingEntity().getControllingPassenger() == player) {
                IControllable controllable = (IControllable) player.getRidingEntity();
                int inputs = controllable.encode(gs);
                controllable.handle((byte) inputs);
                PacketHandler.sendToServer(new SPacketControllableInput((Entity & IControllable) player.getRidingEntity(), inputs));
            }
            if (gs.keyBindAttack.isKeyDown() && isEquipAnimationDone(mc)) {
                ItemStack stack = player.getHeldItemMainhand();
                if (!player.isSpectator() && stack.getItem() instanceof GunBase) {
                    GunBase gun = (GunBase) stack.getItem();
                    if (gun.getFiremode(stack) == GunBase.Firemode.AUTO && !isReloading(player, data, gun, stack) && !tracker.isOnCooldown(gun)) {
                        if (gun.hasAmmo(stack)) {
                            PacketHandler.INSTANCE.sendToServer(new PacketShoot());
                            this.applyRecoil(player, stack, gun, data.getAimInfo().isAiming());
                            tracker.add(gun);
                        } else {
                            player.playSound(PMCSounds.gun_noammo, 4f, 1f);
                        }
                    }
                }
            }

            if (player.getHeldItemMainhand().getItem() instanceof GunBase && !player.isSpectator()) {
                ItemStack stack = player.getHeldItemMainhand();
                GunBase gun = (GunBase) player.getHeldItemMainhand().getItem();
                int maxRounds = gun.getBurstAmount();
                if (stack.hasTagCompound() && gun.hasAmmo(stack)) {
                    if (shooting && gun.getFiremode(stack) == GunBase.Firemode.BURST) {
                        shootingTimer++;
                        if (shootingTimer >= gun.getFireRate() && shotsFired < maxRounds) {
                            PacketHandler.INSTANCE.sendToServer(new PacketShoot());
                            applyRecoil(player, stack, gun, data.getAimInfo().isAiming());
                            shotsFired++;
                            shootingTimer = 0;
                        }

                        if (shotsFired >= maxRounds || !gun.hasAmmo(stack)) {
                            shooting = false;
                            shootingTimer = 0;
                            shotsFired = 0;
                        }
                    } else {
                        shooting = false;
                        shootingTimer = 0;
                        shotsFired = 0;
                    }
                } else {
                    if (shooting) {
                        shooting = false;
                        shootingTimer = 0;
                        shotsFired = 0;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        ClientHooks.setRenderTickTime(event.renderTickTime);
        if(event.phase == Phase.START) {
            AnimationProcessor.instance().processFrame(event.renderTickTime);
        }
    }

    private boolean isReloading(EntityPlayer player, IPlayerData data, GunBase gun, ItemStack stack) {
        IReloader reloader = gun.getReloader();
        if(data.isReloading()) {
            if(reloader.canInterrupt(gun, stack)) {
                ReloadInfo info = data.getReloadInfo();
                info.setReloading(false);
                PacketHandler.sendToServer(new SPacketSetProperty(false, SPacketSetProperty.Action.RELOAD));
                AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                return false;
            }
            return true;
        }
        return false;
    }

    private void applyRecoil(EntityPlayer player, ItemStack stack, GunBase gun, boolean aiming) {
        ItemMuzzle muzzle = gun.getAttachment(AttachmentType.MUZZLE, stack);
        ItemGrip grip = gun.getAttachment(AttachmentType.GRIP, stack);
        float vertical = 1.0F;
        float horizontal = 1.0F;
        IPlayerData data = PlayerData.get(player);
        if(data.isProning()) {
            vertical *= 0.3F;
            horizontal *= 0.3F;
        } else if(player.isSneaking()) {
            vertical *= 0.85F;
            horizontal *= 0.85F;
        }
        if(muzzle != null) {
            vertical = muzzle.applyVerticalRecoilMultiplier(vertical);
            horizontal = muzzle.applyHorizontalRecoilMultiplier(horizontal);
        }
        if(grip != null) {
            vertical = grip.applyVerticalRecoilMultiplier(vertical);
            horizontal = grip.applyHorizontalRecoilMultiplier(horizontal);
        }
        float v = gun.getVerticalRecoil() * vertical;
        float h = Pubgmc.rng().nextBoolean() ? -gun.getHorizontalRecoil() * horizontal : gun.getHorizontalRecoil() * horizontal;
        player.rotationPitch -= v;
        player.rotationYaw -= h;
        AnimationDispatcher.dispatchRecoilAnimationDefault(h, v, aiming);
        AnimationDispatcher.dispatchShootAnimation(gun);
    }

    private void setAiming(IPlayerData data, boolean aim) {
        PacketHandler.sendToServer(new SPacketSetProperty(aim, SPacketSetProperty.Action.AIM));
    }

    /**
     * Method for rendering the textured boost overlays
     * TODO improve
     */
    private static void renderBoost(BoostStats stats) {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();

        if (ConfigPMC.client.overlays.imageBoostOverlay.get() == CFGEnumOverlayStyle.IMAGE) {
            int left = width / 2 - 91;
            int top = height - 32 + 3;
            short barWidth = 182;

            //Actual drawing code
            ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST, left + ConfigPMC.client.overlays.imgBoostOverlayPos.getX(), top + ConfigPMC.client.overlays.imgBoostOverlayPos.getY(), barWidth, 5, false);
            int boost = stats.getLevel();
            if (boost > 0) {
                double sizeX = ((182.0D / 20.0D) * (boost + stats.getSaturation()));
                ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST_FULL, left + ConfigPMC.client.overlays.imgBoostOverlayPos.getX(), top + ConfigPMC.client.overlays.imgBoostOverlayPos.getY(), sizeX, 5, false);
            }

            //This will render after these 2 above to make sure this will always be on the top
            ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST_OVERLAY, left + ConfigPMC.client.overlays.imgBoostOverlayPos.getX(), top + ConfigPMC.client.overlays.imgBoostOverlayPos.getY(), barWidth, 5, true);
        }
    }

    private static void renderArmorIcons(RenderGameOverlayEvent.Pre e, EntityPlayer player, ScaledResolution res, Minecraft mc, IPlayerData data) {
        ItemStack head = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack body = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int left = width / 2 + 93;
        int top = height - 19;
        int offset = 0;

        if (!head.isEmpty() && head.getItem() instanceof ArmorBase) {
            ArmorBase armor = (ArmorBase) head.getItem();
            int level = armor.armorLevel().getArmorLevel();
            ResourceLocation img = armor.armorLevel().getIcon(true, level, getDamageLevel(body));
            ImageUtil.drawCustomSizedImage(mc, img, left + offset, top, 16, 16, true);
            offset += 17;
        }

        if (!body.isEmpty() && body.getItem() instanceof ArmorBase) {
            ArmorBase armor = (ArmorBase) body.getItem();
            int level = armor.armorLevel().getArmorLevel();
            ResourceLocation img = armor.armorLevel().getIcon(false, level, getDamageLevel(body));
            ImageUtil.drawCustomSizedImage(mc, img, left + offset, top, 16, 16, true);
            offset += 17;
        }

        if (data.getBackpackLevel() > 0) {
            int level = data.getBackpackLevel() - 1;
            ImageUtil.drawCustomSizedImage(mc, BACKPACK_OVERLAY[level], left + offset, top, 16, 16, true);
            offset += 17;
        }

        if (data.getEquippedNV()) {
            int i = data.isUsingNV() ? 1 : 0;
            ImageUtil.drawCustomSizedImage(mc, NIGHT_VISION_OVERLAY[i], left + offset, top, 16, 16, true);
        }
    }

    /**
     * @deprecated render tinted texture instead
     */
    @Deprecated
    private static int getDamageLevel(ItemStack stack) {
        final double val = (double) stack.getItemDamage() / (double) stack.getMaxDamage();
        return val < 0.2d ? 0 : val > 0.7d ? 2 : 1;
    }

    private static void renderVehicleOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Post e) {
        if (e.getType() == ElementType.TEXT && player.getRidingEntity() instanceof EntityVehicle) {
            EntityVehicle car = (EntityVehicle) player.getRidingEntity();
            double speed = car.getSpeed() * 20;
            mc.fontRenderer.drawStringWithShadow("Speed: " + (int)(speed * 3.6) + "km/h", 15, res.getScaledHeight() - 60, 16777215);
        } else if (e.getType() == ElementType.ALL && player.getRidingEntity() instanceof EntityVehicle) {
            EntityVehicle car = (EntityVehicle) player.getRidingEntity();
            double health = car.health / car.getVehicleConfiguration().maxHealth.getAsFloat() * 100;
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, car.fuel * 1.2, 5, 0.0, 0.25, 1.0, 0.375, false);
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, 120, 5, 0.0, 0.375, 1.0, 0.5, true);
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 50, 120, 5, 0.0, 0.125, 1.0, 0.25, false);
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 50, health * 1.2, 5, 0.0, 0.0, 1.0, 0.125, false);
        }
    }

    private static void drawItemUseOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Pre e, ItemStack stack) {
        final DecimalFormat f = new DecimalFormat("#,#0.0");
        final float useTime = (float) player.getItemInUseCount();
        FontRenderer font = mc.fontRenderer;
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int left = width / 2;
        int top = height / 2;
        font.drawStringWithShadow(f.format(useTime / 20), left - 6, top + 3, 0xFFFFFF);
    }

    private static boolean isEquipAnimationDone(Minecraft mc) {
        ItemRenderer renderer = mc.getItemRenderer();
        return renderer.equippedProgressMainHand == 1.0F;
    }
}
