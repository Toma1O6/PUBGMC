package dev.toma.pubgmc.client;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.*;
import dev.toma.pubgmc.api.entity.IControllable;
import dev.toma.pubgmc.api.game.playzone.PlayzoneDeliveryVehicle;
import dev.toma.pubgmc.api.item.Backpack;
import dev.toma.pubgmc.api.item.BulletproofArmor;
import dev.toma.pubgmc.api.item.Consumable;
import dev.toma.pubgmc.api.item.NightVisionGoggles;
import dev.toma.pubgmc.MixinHooks;
import dev.toma.pubgmc.client.animation.AnimationDispatcher;
import dev.toma.pubgmc.client.animation.AnimationElement;
import dev.toma.pubgmc.client.animation.AnimationProcessor;
import dev.toma.pubgmc.client.animation.AnimationType;
import dev.toma.pubgmc.client.animation.interfaces.HandAnimate;
import dev.toma.pubgmc.client.games.GameRendererManager;
import dev.toma.pubgmc.client.games.MapPointRendererManager;
import dev.toma.pubgmc.client.gui.GuiAttachmentSelector;
import dev.toma.pubgmc.client.gui.animator.GuiAnimator;
import dev.toma.pubgmc.client.gui.hands.GuiGunConfig;
import dev.toma.pubgmc.client.gui.hands.GuiHandPlacer;
import dev.toma.pubgmc.client.gui.menu.GuiMenu;
import dev.toma.pubgmc.client.gui.widget.EquipmentInventoryButton;
import dev.toma.pubgmc.client.util.KeyBinds;
import dev.toma.pubgmc.common.container.ContainerPlayerEquipment;
import dev.toma.pubgmc.common.entity.controllable.EntityVehicle;
import dev.toma.pubgmc.common.items.attachment.*;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.common.items.guns.IReloader;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.config.client.CFG2DCoords;
import dev.toma.pubgmc.config.client.CFG2DRatio;
import dev.toma.pubgmc.config.client.CFGEnumOverlayStyle;
import dev.toma.pubgmc.config.common.CFGWeapons;
import dev.toma.pubgmc.init.PMCSounds;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.c2s.*;
import dev.toma.pubgmc.util.helper.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ClientEvents {

    private static final ResourceLocation VEHICLE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vehicle.png");
    private static final DecimalFormat CONSUMABLE_USE_FORMAT = new DecimalFormat("#,#0.0");
    private static final EntityEquipmentSlot[] ARMOR_SLOTS = {
            EntityEquipmentSlot.HEAD,
            EntityEquipmentSlot.CHEST
    };

    private final WeaponCooldownTracker cooldownTracker = new WeaponCooldownTracker();
    private int shotsFired;
    private boolean shooting;
    private int shootingTimer;

    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {
        if (ConfigPMC.client.customMainMenu.get() && event.getGui() instanceof GuiMainMenu) {
            event.setGui(new GuiMenu());
        }
    }

    @SubscribeEvent
    public void addAdditionalGuiButtons(GuiScreenEvent.InitGuiEvent.Post event) {
        Gui gui = event.getGui();
        if (gui instanceof GuiInventory) {
            GuiInventory inventoryGui = (GuiInventory) gui;
            CFG2DCoords offsets = ConfigPMC.overlays().equipmentInventoryButtonPos;
            event.getButtonList().add(new EquipmentInventoryButton(inventoryGui.getGuiLeft() + 66 + offsets.getX(), inventoryGui.getGuiTop() + 9 + offsets.getY(), "+"));
        }
    }

    @SubscribeEvent
    public void handleAddedButtonActions(GuiScreenEvent.ActionPerformedEvent.Post event) {
        Gui gui = event.getGui();
        if (gui instanceof GuiInventory) {
            int eventId = event.getButton().id;
            if (eventId == EquipmentInventoryButton.SPECIAL_INVENTORY_BUTTON_ID) {
                PacketHandler.sendToServer(new C2S_PacketOpenPlayerEquipment());
            }
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
            if (stack.getItem() instanceof HandAnimate) {
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
                    if (!processor.isItemRenderBlocked()) {
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

        //Ammo and Firemode info rendering
        if (!e.isCancelable() && e.getType() == ElementType.TEXT && sp.getHeldItemMainhand().getItem() instanceof GunBase) {
            ItemStack weaponStack = sp.getHeldItemMainhand();
            GunBase gun = (GunBase) weaponStack.getItem();
            mc.entityRenderer.setupOverlayRendering();

            int screenWidth = res.getScaledWidth();
            int screenHeight = res.getScaledHeight();
            float centerX = screenWidth / 2f;
            float halfWidth = centerX;
            float centerY = screenHeight / 2f;
            float halfHeight = centerY;

            CFG2DRatio gunInfoPos = ConfigPMC.client.overlays.gunInfoPos;
            float gunInfoPosX = centerX + halfWidth * (gunInfoPos.getX() - 0.95f);
            float gunInfoPosY = centerY + halfHeight * (gunInfoPos.getY() + 0.87f);
            int totalCount = 0;
            boolean isFreeAmmo = IReloader.isFreeReload(mc.player);
            String infinity = I18n.format("label.pubgmc.infinite");
            if (!isFreeAmmo) {
                Item ammoItem =  gun.getAmmoType().ammo();
                totalCount = DevUtil.getItemCount(ammoItem, sp.inventory);
            }
            if (weaponStack.hasTagCompound()) {
                int ammoInGun = gun.getAmmo(weaponStack);
                GunBase.Firemode firemode = gun.getFiremode(weaponStack);
                int ammoInGunColor = ammoInGun > 0 ? 16777215 : 16711680;
                mc.fontRenderer.drawStringWithShadow(gun.getItemStackDisplayName(weaponStack) + ": " + firemode.translatedName(), gunInfoPosX, gunInfoPosY - 9, 16777215);
                mc.fontRenderer.drawStringWithShadow(TextFormatting.BOLD.toString() + ammoInGun, gunInfoPosX, gunInfoPosY, ammoInGunColor);
                if (totalCount > 0 || isFreeAmmo) {
                    mc.fontRenderer.drawStringWithShadow("     | " + (isFreeAmmo ? infinity : totalCount), gunInfoPosX, gunInfoPosY, 16777215);
                }
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
        boolean isFirstPersonView = mc.gameSettings.thirdPersonView == 0;
        // Crosshairs
        if (e.getType() == ElementType.CROSSHAIRS && isFirstPersonView) {
            if (!ConfigPMC.client.overlays.renderGunCrosshairs.get() || data.getAimInfo().isAiming()) {
                e.setCanceled(true);
            }
        }
        // Experience and Food bar
        // if (ConfigPMC.client.overlays.imageBoostOverlay.get() == CFGEnumOverlayStyle.IMAGE)
        if (e.getType() == ElementType.EXPERIENCE || e.getType() == ElementType.FOOD) {
            if (!ConfigPMC.client.overlays.renderStatusBars.get()) { // The game will automatically replenish saturation and no experience bar required
                // if (ConfigPMC.client.overlays.imgBoostOverlayPos.getX() == 0 && ConfigPMC.client.overlays.imgBoostOverlayPos.getY() == 0 && !data.getBoostStats().isEmpty())
                e.setCanceled(true);
            } else if (ConfigPMC.client.overlays.renderNewHealthBar.get() && ConfigPMC.client.overlays.newHealthBarLengthRatio.getAsFloat() > 0.5f) {
                e.setCanceled(true);
            }
        }
        // Health and armor bar
        if (e.getType() == ElementType.HEALTH || e.getType() == ElementType.ARMOR) {
            if (ConfigPMC.client.overlays.renderNewHealthBar.get()) {
                e.setCanceled(true);
                renderNewHealthBar();
            }
        }

        if (e.getType() == ElementType.ALL) {
            if (data.isNightVisionActive()) {
                ItemStack nightVisionItem = data.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION);
                if (nightVisionItem.getItem() instanceof NightVisionGoggles) {
                    NightVisionGoggles goggles = (NightVisionGoggles) nightVisionItem.getItem();
                    World world = mc.world;
                    BlockPos playerPosition = mc.player.getPosition();
                    float sunBrightness = Math.max(world.getSunBrightness(1.0F) - 0.2F, 0.0F);
                    int rawSkylight = world.getLightFor(EnumSkyBlock.SKY, playerPosition);
                    int light = (int) (rawSkylight * sunBrightness);
                    int blockLight = world.getLightFor(EnumSkyBlock.BLOCK, playerPosition);
                    float exposure = Math.max(light, blockLight) / 15.0F * goggles.getLightExposureSensitivity();
                    if (exposure > 0.0F) {
                        ImageUtil.drawShape(0, 0, res.getScaledWidth(), res.getScaledWidth(), 0.3F, 1.0F, 0.3F, exposure);
                    }
                    ImageUtil.drawFullScreenImage(mc, res, goggles.getOverlayTexture(), true);
                }
            }

            if (!sp.isSpectator() && ConfigPMC.client.overlays.renderBoost.get()) {
                renderBoost(data.getBoostStats());
            }

            if (ConfigPMC.client.overlays.renderArmorIcons.get() && !sp.isSpectator())
                renderArmorIcons(e, sp, res, mc, data);

            if (stack.getItem() instanceof Consumable) {
                if (sp.isHandActive()) {
                    drawItemUseOverlay(sp, mc, res, e, stack);
                }
            }

            GameRendererManager.INSTANCE.renderCurrentGameHUDOverlay(e);
        }
    }

    @SubscribeEvent
    public void renderWorld(RenderWorldLastEvent event) {
        float partialTicks = event.getPartialTicks();
        GameRendererManager.INSTANCE.renderWorldOverlay(partialTicks);
        MapPointRendererManager.INSTANCE.renderInWorld(partialTicks);
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

        //Same as above, we just send packet to server and everything else will be done in the rendering method above
        if (KeyBinds.NV.isPressed()) {
            if (!data.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION).isEmpty()) {
                boolean status = !data.isNightVisionActive();
                data.setNightVisionActive(status);
                PacketHandler.sendToServer(new C2S_PacketSetProperty(status, C2S_PacketSetProperty.Action.NIGHT_VISION));
            }
        }

        if (player.isSpectator() || data == null) {
            return; // No other key processing for spectator game mode
        }

        // Dev keybinds
        if (ConfigPMC.developerMode.get()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
                mc.displayGuiScreen(new GuiGunConfig());
            } else if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
                mc.displayGuiScreen(new GuiAnimator());
            } else if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
                mc.displayGuiScreen(new GuiHandPlacer());
            }
        }
        // Prone
        if (KeyBinds.PRONE.isPressed()) {
            if (!player.onGround)
                return;
            data.setProne(!data.isProne(), false);
            ReloadInfo reloadInfo = data.getReloadInfo();
            // if (data.getAimInfo().isAiming()) this.setAiming(data, false);
//            if (reloadInfo.isReloading()) {
//                reloadInfo.interrupt();
//                PacketHandler.sendToServer(new C2S_PacketSetProperty(false, C2S_PacketSetProperty.Action.RELOAD));
//            }
            PacketHandler.sendToServer(new C2S_PacketProneStatus(data.isProne()));
        }
        // Attachment menu
        if (KeyBinds.ATTACHMENT.isPressed()) {
            if (player.getHeldItemMainhand().getItem() instanceof GunBase) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.displayGuiScreen(new GuiAttachmentSelector());
            } else {
                ITextComponent component = new TextComponentTranslation("label.pubgmc.must_hold_weapon");
                component.getStyle().setColor(TextFormatting.RED);
                player.sendStatusMessage(component, true);
            }
        }
        // Reloading
        if (KeyBinds.RELOAD.isPressed()) {
            ItemStack stack = player.getHeldItemMainhand();
            ReloadInfo reloadInfo = data.getReloadInfo();
            if (stack.getItem() instanceof GunBase) {
                GunBase gun = (GunBase) stack.getItem();
                IReloader reloader = gun.getReloader();
                if (reloadInfo.isReloading()) {
                    if (!reloader.canInterrupt(gun, stack)) {
                        return;
                    }
                    reloadInfo.setReloading(false);
                    PacketHandler.INSTANCE.sendToServer(new C2S_PacketSetProperty(false, C2S_PacketSetProperty.Action.RELOAD));
                    AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
                    return;
                } else if (stack.hasTagCompound() && reloader.canReload(player, gun, stack)) {
                    AnimationDispatcher.dispatchReloadAnimation(gun, stack, player);
                    reloadInfo.startReload(player, gun, stack);
                    PacketHandler.INSTANCE.sendToServer(new C2S_PacketSetProperty(true, C2S_PacketSetProperty.Action.RELOAD));
                }
            }
        }

        //Switch firemode
        if (KeyBinds.FIREMODE.isPressed()) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof GunBase) {
                PacketHandler.sendToServer(new C2S_PacketFiremode());
            }
        }

        // Request game GUI - TODO
        /*if (KeyBinds.GAME_MENU.isPressed()) {
            PacketHandler.sendToServer(new C2S_RequestGameMenuGUI());
        }*/

        // Equipment inventory
        if (KeyBinds.EQUIPMENT_INVENTORY.isPressed()) {
            PacketHandler.sendToServer(new C2S_PacketOpenPlayerEquipment());
        }
    }

    @SubscribeEvent
    public void handleMouseEvent(MouseEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        EntityPlayer player = minecraft.player;
        if (player == null || player.isSpectator())
            return;
        int wheelAmount = Integer.signum(event.getDwheel());
        if (wheelAmount == 0 || !GuiScreen.isAltKeyDown())
            return;
        ItemStack itemStack = player.getHeldItemMainhand();
        if (!(itemStack.getItem() instanceof GunBase))
            return;
        ScopeZoom scopeZoom = ((GunBase) itemStack.getItem()).getScopeData(itemStack);
        if (scopeZoom == null || !scopeZoom.hasMouseScrollOverrides())
            return;

        if (wheelAmount > 0) {
            scopeZoom.zoomIn(itemStack.getItem());
        } else {
            scopeZoom.zoomOut(itemStack.getItem());
        }
        RenderHandler.restore();
        gameSettings.mouseSensitivity *= scopeZoom.getSensitivity(itemStack.getItem());
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gs = mc.gameSettings;
        EntityPlayerSP player = mc.player;
        if (player == null || player.isSpectator()) {
            return;
        }
        ItemStack stack = player.getHeldItemMainhand();
        if (!(stack.getItem() instanceof GunBase)) {
            return;
        }
        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        if (!isEquipAnimationDone(mc)) {
            return;
        }
        GunBase gun = (GunBase) stack.getItem();
        if (gs.keyBindAttack.isPressed()) {
            if (interruptCurrentReloading(player, data, gun, stack) || cooldownTracker.isOnCooldown(gun)) {
                return;
            }
            if (gun.getFiremode(stack) == GunBase.Firemode.SINGLE) {
                if (gun.hasAmmo(stack)) {
                    PacketHandler.INSTANCE.sendToServer(new C2S_PacketShoot());
                    cooldownTracker.add(gun);
                    if (gun.getAction() != null) { //currently only has bolt action
                        Pubgmc.proxy.playMCDelayedSound(gun.getAction().get(), player.posX, player.posY, player.posZ, 1.0F, 10);
                        if (gun.getGunType() == GunBase.GunType.SR) {
                            setAiming(data, false); //TODO add to firemode or holding aiming button
                        }
                    }
                    applyRecoil(player, stack, gun, data.getAimInfo().isAiming());
                } else {
                    player.playSound(PMCSounds.gun_noammo, 4f, 1f);
                }
            } else if (gun.getFiremode(stack) == GunBase.Firemode.BURST) {
                if (shooting) {
                    return;
                }
                if (gun.hasAmmo(stack)) {
                    shooting = true;
                    shotsFired = 0;
                } else {
                    player.playSound(PMCSounds.gun_noammo, 4f, 1f);
                }
            }
        }
        //Aiming on RMB press
        else if (gs.keyBindUseItem.isPressed()) {
            if (data.getAimInfo().isAiming()) {
                // cancel aiming
                RenderHandler.restore();
                PacketHandler.sendToServer(new C2S_PacketSetProperty(false, C2S_PacketSetProperty.Action.AIM));
                return;
            }
            // aiming
            player.setSprinting(false);
            RenderHandler.saveCurrentOptions();
            gs.thirdPersonView = 0; // Switch to first person view when aiming
            ScopeZoom scopeData = gun.getScopeData(stack);
            if (scopeData != null && scopeData.getSensitivity(gun) < 1.0F) {
                gs.mouseSensitivity *= scopeData.getSensitivity(gun);
            }
            PacketHandler.sendToServer(new C2S_PacketSetProperty(true, C2S_PacketSetProperty.Action.AIM));
            AnimationDispatcher.dispatchAimAnimation(gun, stack);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onClientTick(TickEvent.ClientTickEvent ev) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        GameSettings gs = mc.gameSettings;
        if (ev.phase == Phase.END) {
            if (mc.currentScreen instanceof ITickable) {
                ((ITickable) mc.currentScreen).update();
            }
            AnimationProcessor.instance().processTick();
            ScrollableScopeZoom.tick();
        }

        if (player != null && ev.phase == Phase.END && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null)) {
            cooldownTracker.tick(mc.isGamePaused());
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            if (player.getRidingEntity() instanceof IControllable && player.getRidingEntity().getControllingPassenger() == player) {
                IControllable controllable = (IControllable) player.getRidingEntity();
                int inputs = controllable.encode(gs);
                controllable.handle((byte) inputs);
                PacketHandler.sendToServer(new C2S_PacketControllableInput((Entity & IControllable) player.getRidingEntity(), inputs));
            }
            if (gs.keyBindAttack.isKeyDown() && isEquipAnimationDone(mc)) {
                ItemStack stack = player.getHeldItemMainhand();
                if (!player.isSpectator() && stack.getItem() instanceof GunBase) {
                    GunBase gun = (GunBase) stack.getItem();
                    if (gun.getFiremode(stack) == GunBase.Firemode.AUTO && !interruptCurrentReloading(player, data, gun, stack) && !cooldownTracker.isOnCooldown(gun)) {
                        if (gun.hasAmmo(stack)) {
                            PacketHandler.INSTANCE.sendToServer(new C2S_PacketShoot());
                            this.applyRecoil(player, stack, gun, data.getAimInfo().isAiming());
                            cooldownTracker.add(gun);
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
                            PacketHandler.INSTANCE.sendToServer(new C2S_PacketShoot());
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
        MixinClientHooks.setRenderTickTime(event.renderTickTime);
        if (event.phase == Phase.START) {
            AnimationProcessor.instance().processFrame(event.renderTickTime);
        }
    }

    @SubscribeEvent
    public void stitchTextures(TextureStitchEvent.Pre event) {
        TextureMap map = event.getMap();
        map.registerSprite(MixinHooks.LOCKED_SLOT_ICON);
        map.registerSprite(ContainerPlayerEquipment.SLOT_NIGHT_VISION);
        map.registerSprite(ContainerPlayerEquipment.SLOT_BACKPACK);
        map.registerSprite(ContainerPlayerEquipment.SLOT_GHILLIE);
    }

    @SubscribeEvent
    public void adjustCameraOffset(EntityViewRenderEvent.CameraSetup event) {
        Entity entity = event.getEntity();
        Entity vehicle = entity.getRidingEntity();
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (settings.thirdPersonView > 0 && vehicle instanceof PlayzoneDeliveryVehicle) {
            double offset = ((PlayzoneDeliveryVehicle) vehicle).getCameraOffset();
            if (offset > 0) {
                int i = settings.thirdPersonView == 1 ? -1 : 1;
                GlStateManager.translate(0, 0, offset * i);
            }
        }
    }

    private boolean interruptCurrentReloading(EntityPlayer player, IPlayerData data, GunBase gun, ItemStack stack) {
        IReloader reloader = gun.getReloader();
        ReloadInfo info = data.getReloadInfo();
        if (!info.isReloading()) {
            return false;
        }
        if (!reloader.canInterrupt(gun, stack)) {
            return true;
        }
        info.setReloading(false);
        PacketHandler.sendToServer(new C2S_PacketSetProperty(false, C2S_PacketSetProperty.Action.RELOAD));
        AnimationProcessor.instance().stop(AnimationType.RELOAD_ANIMATION_TYPE);
        return false;
    }

    private void applyRecoil(EntityPlayer player, ItemStack stack, GunBase gun, boolean aiming) {
        //TODO improve
        ItemMuzzle muzzle = gun.getAttachment(AttachmentType.MUZZLE, stack);
        ItemGrip grip = gun.getAttachment(AttachmentType.GRIP, stack);
        CFGWeapons config = ConfigPMC.guns();
        float vertical = config.globalVerticalRecoil.getAsFloat();
        float horizontal = config.globalHorizontalRecoil.getAsFloat();
        IPlayerData data = PlayerDataProvider.get(player);
        if (data.isProne()) {
            float multiplier = config.proneRecoilScale.getAsFloat();
            vertical *= multiplier;
            horizontal *= multiplier;
        } else if (player.isSneaking()) {
            float multiplier = config.crouchRecoilScale.getAsFloat();
            vertical *= multiplier;
            horizontal *= multiplier;
        }
        if (muzzle != null) {
            vertical = muzzle.applyVerticalRecoilMultiplier(vertical);
            horizontal = muzzle.applyHorizontalRecoilMultiplier(horizontal);
        }
        if (grip != null) {
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
        PacketHandler.sendToServer(new C2S_PacketSetProperty(aim, C2S_PacketSetProperty.Action.AIM));
    }

    private static void renderBoost(BoostStats stats) {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int boost = stats.getBoost();
        int boostLevel = stats.getBoostLevel();
        int boostLimit = stats.getBoostLimit();
        float percentage = boostLimit != 0f ? (float)boost / boostLimit : 0f;

        // Image style
        if (ConfigPMC.client.overlays.imageBoostOverlay.get() == CFGEnumOverlayStyle.IMAGE) {
            CFG2DCoords overlayPos = ConfigPMC.client.overlays.imgBoostOverlayPos;
            short barWidth = 182;
            short barHight = 3;
            float singleWidth = barWidth/100.0f;
            int leftPos = width / 2 - barWidth / 2 + overlayPos.getX();
            int topPos = height - 31 + barHight + overlayPos.getY();

            float barLevel1End = barWidth * stats.getLevelPercentage(1);
            float barLevel1Limit = barLevel1End - singleWidth;
            float barLevel2End = barWidth * stats.getLevelPercentage(2);
            float barLevel2Limit = barLevel2End - singleWidth;
            float barLevel3End = barWidth * stats.getLevelPercentage(3);
            float barLevel3Limit = barLevel3End - singleWidth;
            float barLevel4End = barWidth * stats.getLevelPercentage(4);
            float barLevel4Limit = barLevel4End;

            // Transparent background
            ImageUtil.drawShape(leftPos, topPos, leftPos + barLevel1Limit, topPos + barHight, 0.577F, 0.577F, 0.577F, 0.3F); // #939393 147,147,147
            ImageUtil.drawShape(leftPos + barLevel1End, topPos, leftPos + barLevel2Limit, topPos + barHight, 0.526F, 0.526F, 0.526F, 0.3F); // #868686 134,134,134
            ImageUtil.drawShape(leftPos + barLevel2End, topPos, leftPos + barLevel3Limit, topPos + barHight, 0.491F, 0.491F, 0.491F, 0.3F); // #7d7d7d 125,125,125
            ImageUtil.drawShape(leftPos + barLevel3End, topPos, leftPos + barLevel4Limit, topPos + barHight, 0.455F, 0.455F, 0.455F, 0.3F); // #747474 116,116,116

            if (boostLevel <= 0) return;
            // level 1
            float boost1 = Math.min(percentage * barWidth, barLevel1Limit);
            ImageUtil.drawShape(leftPos, topPos, leftPos + boost1, topPos+barHight, 0.91F, 0.78F, 0.146F, 0.8F); // #e8c625 232,198,37
            if (boostLevel <= 1) return;
            // level 2
            float boost2 = Math.min(percentage * barWidth, barLevel2Limit);
            ImageUtil.drawShape(leftPos + barLevel1End, topPos, leftPos + boost2, topPos+barHight, 0.883F, 0.64F, 0.11F, 0.8F); // #e1a31c 225,163,28
            if (boostLevel <= 2) return;
            // level 3
            float boost3 = Math.min(percentage * barWidth, barLevel3Limit);
            ImageUtil.drawShape(leftPos + barLevel2End, topPos, leftPos + boost3, topPos+barHight, 0.844F, 0.514F, 0.12F, 0.8F); // #d7831e 215,131,30
            if (boostLevel <= 3) return;
            // level 4
            float boost4 = Math.min(percentage * barWidth, barLevel4Limit);
            ImageUtil.drawShape(leftPos + barLevel3End, topPos, leftPos + boost4, topPos+barHight, 0.832F, 0.436F, 0.087F, 0.8F); // #d46f16 212,111,22
        }
        // Text style
        else {
            Minecraft mc = Minecraft.getMinecraft();
            mc.entityRenderer.setupOverlayRendering();

            CFG2DCoords overlayPos = ConfigPMC.client.overlays.textBoostOverlayPos;
            int leftPos = width / 2 + 40 + overlayPos.getX();
            int topPos = height - 49 + overlayPos.getY();
            int color;

            switch (boostLevel) {
                case 1: {
                    color = 15208997; break; // #e8c625 232,198,37
                }
                case 2: {
                    color = 14749468; break; // #e1a31c 225,163,28
                }
                case 3: {
                    color = 14107422; break; // #d7831e 215,131,30
                }
                case 4: {
                    color = 13909782; break; // #d46f16 212,111,22
                }
                default: {
                    color = 12566463; break; // #bfbfbf 191,191,191
                }
            }
            int percentageInt = (int)(percentage * 100);
            if (percentageInt > 99) {
                leftPos -= 5;
            }
            if (percentageInt > 9) {
                leftPos -= 5;
            }
            mc.fontRenderer.drawStringWithShadow(percentageInt + " / 100", leftPos, topPos, color);
        }
    }

    private static void renderNewHealthBar() {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        CFG2DCoords overlayPos = ConfigPMC.client.overlays.imgNewHealthBarOverlayPos;
        int barWidth = Math.round(182 * ConfigPMC.client.overlays.newHealthBarLengthRatio.getAsFloat());
        short barHeight = 9;
        int leftPos = width / 2 - 91 + overlayPos.getX(); // Hard coded 91 (182/2) to fix the lower left corner, otherwise change newHealthBarLength requires modifying imgNewHealthBarOverlayPos as well
        int topPos = height - 50 + barHeight + overlayPos.getY();

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP sp = mc.player;
        float lineLimit = ConfigPMC.client.overlays.imgNewHealthBarLimit.getAsFloat(); // may add to config
        float healthLimit = sp.getMaxHealth();
        float absorptionHealth = sp.getAbsorptionAmount();
        float normalHealth = sp.getHealth();

        float healthLeft = normalHealth + absorptionHealth;
        float healthLimitLeft = healthLimit + absorptionHealth;
        float split75Left = healthLimit * 0.75f + absorptionHealth;
        boolean renderSplit75 = healthLeft < split75Left;
        // color
        float r, g, b, a;
        if (normalHealth < healthLimit * 0.25f) { // red
            r = 0.863f; g = 0.34f; b = 0.291f; a = 0.8f; // #dc564a 220,86,74
        } else if (normalHealth < healthLimit * 0.5f) { // yellow
            r = 0.98f; g = 0.895f; b = 0.648f; a = 0.8f; // #f9e4a5 249.228,165
        } else if (normalHealth < healthLimit) { // white
            r = 0.95f; g =0.95f; b = 0.95f; a = 0.8f; // #f2f2f2 242,242,242
        } else { // grey
            r = 0.648f; g = 0.648f; b = 0.648f; a = 0.8f; // #a5a5a5 165.165,165
        }
        for (int raw = 0; healthLeft > 0 || healthLimit - raw*lineLimit >= 0; raw++) { // for health over 20, healthLimit doesn't include absorption health
            int topPosAdjust = raw * (barHeight + 2);
            int curTopPos = topPos - topPosAdjust;

            // Transparent background
            float barLimit = DevUtil.wrap(healthLimitLeft, 0, lineLimit);
            float barPercentage = barLimit / lineLimit;
            ImageUtil.drawShape(leftPos, curTopPos, leftPos+barWidth*barPercentage, curTopPos + barHeight, 0.197f, 0.197f, 0.197f, 0.3f); // #323232 50,50,50
            healthLimitLeft -= barLimit;
            // health
            float health = DevUtil.wrap(healthLeft, 0, lineLimit);
            float percentage = health / lineLimit;
            ImageUtil.drawShape(leftPos, curTopPos, leftPos + barWidth * percentage, curTopPos + barHeight, r, g, b, a);
            healthLeft -= health;
            // 75% health
            if (renderSplit75) {
                if (split75Left > 0 && healthLeft < split75Left) {
                    float split75 = Math.min(split75Left, lineLimit);
                    float splitPercentage = split75 / lineLimit;
                    ImageUtil.drawShape(leftPos + barWidth * percentage, curTopPos, leftPos + barWidth * splitPercentage, curTopPos + barHeight, 0.346f, 0.346f, 0.346f, 0.3f); // #585858 88,88,88
                    split75Left -= split75;
                }
            }
        }
    }
    private static void renderArmorIcons(RenderGameOverlayEvent.Pre e, EntityPlayer player, ScaledResolution res, Minecraft mc, IPlayerData data) {
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int left = width / 2 + 93;
        int top = height - 19;
        int offset = 0;
        float shade = 0.4F;

        for (EntityEquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getItemStackFromSlot(slot);
            if (stack.isEmpty())
                continue;
            if (!(stack.getItem() instanceof BulletproofArmor))
                continue;
            BulletproofArmor armor = (BulletproofArmor) stack.getItem();
            BulletproofArmor.DamageArea protectionArea = slot == EntityEquipmentSlot.HEAD ? BulletproofArmor.DamageArea.HEAD : BulletproofArmor.DamageArea.OTHER;
            float durability = 1.0F - (stack.getItemDamage() / (float) stack.getMaxDamage());
            ResourceLocation image = armor.getArmorIcon(protectionArea, durability);
            float shadeDiff = 1.0F - shade;
            float white = shade + (1.0F - ((durability - 0.5F) / 0.5F)) * shadeDiff;
            float red = durability < 0.5F ? 1.0F : white;
            float green = durability < 0.5F ? durability / 0.5F : white;
            float blue = durability < 0.5F ? durability / 0.5F : white;
            renderIconWithBackground(mc, image, left + offset, top, 16, 16, red, green, blue, 1.0F);
            offset += 17;
        }

        ItemStack backpackStack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.BACKPACK);
        if (!backpackStack.isEmpty() && backpackStack.getItem() instanceof Backpack) {
            Backpack backpack = (Backpack) backpackStack.getItem();
            renderIconWithBackground(mc, backpack.getHotbarIconPath(), left + offset, top, 16, 16, shade, shade, shade, 1.0F);
            offset += 17;
        }

        ItemStack nightvisionStack = data.getSpecialItemFromSlot(SpecialEquipmentSlot.NIGHT_VISION);
        if (!nightvisionStack.isEmpty() && nightvisionStack.getItem() instanceof NightVisionGoggles) {
            NightVisionGoggles goggles = (NightVisionGoggles) nightvisionStack.getItem();
            boolean activeNightvision = data.isNightVisionActive();
            ResourceLocation iconPath = goggles.getHotbarIconPath(activeNightvision);
            float overlayRed = activeNightvision ? 0.0F : shade;
            float overlayGreen = activeNightvision ? 1.0F : shade;
            float overlayBlue = activeNightvision ? 0.0F : shade;
            renderIconWithBackground(mc, iconPath, left + offset, top, 16, 16, overlayRed, overlayGreen, overlayBlue, 1.0F);
        }
    }

    private static void renderIconWithBackground(Minecraft minecraft, ResourceLocation texture, int left, int top, int width, int height, float r, float g, float b, float a) {
        ImageUtil.drawShape(left, top, left + width, top + height, 0.0F, 0.0F, 0.0F, 0.6902F);
        ImageUtil.drawTintedImage(minecraft, texture, left, top, width, height, r, g, b, a);
    }

    private static void renderVehicleOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Post e) {
        if (e.getType() == ElementType.TEXT && player.getRidingEntity() instanceof EntityVehicle) {
            EntityVehicle car = (EntityVehicle) player.getRidingEntity();
            double speed = car.getSpeed() * 20;
            mc.fontRenderer.drawStringWithShadow("Speed: " + (int) (speed * 3.6) + "km/h", 15, res.getScaledHeight() - 60, 16777215);
        } else if (e.getType() == ElementType.ALL && player.getRidingEntity() instanceof EntityVehicle) {
            EntityVehicle car = (EntityVehicle) player.getRidingEntity();
            float barWidth = 120;
            float fuelPercentage = car.fuel / 100.0f;
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, fuelPercentage * barWidth, 5, 0.0, 0.25, 1.0, 0.375, false);
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, barWidth, 5, 0.0, 0.375, 1.0, 0.5, true);
            ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 50, barWidth, 5, 0.0, 0.125, 1.0, 0.25, false);
            float healthPercentage = car.health / car.getVehicleConfiguration().maxHealth.getAsFloat();
            // color
            float r, g, b, a;
            if (healthPercentage < car.getDamageLevel2()) { // red
                r = 0.863f; g = 0.34f; b = 0.291f; a = 0.8f; // #dc564a 220,86,74
            } else if (healthPercentage < car.getDamageLevel1()) { // yellow
                r = 0.98f; g = 0.895f; b = 0.648f; a = 0.8f; // #f9e4a5 249.228,165
            } else if (healthPercentage < 1.0f) { // white
                r = 0.95f; g =0.95f; b = 0.95f; a = 0.8f; // #f2f2f2 242,242,242
            } else { // grey
                r = 0.648f; g = 0.648f; b = 0.648f; a = 0.8f; // #a5a5a5 165.165,165
            }
            ImageUtil.drawShape(15, res.getScaledHeight() - 50, 15 + healthPercentage * barWidth, res.getScaledHeight() - 45, r, g, b, a);
        }
    }

    private static void drawItemUseOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Pre e, ItemStack stack) {
        int useDuration = stack.getMaxItemUseDuration();
        int useTime = Math.max(player.getItemInUseCount(), 0);
        float progress = 1.0F - (useTime / (float) useDuration);
        FontRenderer font = mc.fontRenderer;
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        String useTimeText = CONSUMABLE_USE_FORMAT.format(useTime / 20.0F);
        int textWidth = font.getStringWidth(useTimeText);
        float left = (width - textWidth) / 2.0F;
        float top = (height - font.FONT_HEIGHT) / 2.0F + 15.0F;
        int margin = 2;
        float leftPos = left - margin;
        float rightPos = left + textWidth + margin;
        float topPos = top - margin;
        float bottomPos = top + font.FONT_HEIGHT + margin + 1;
        ImageUtil.drawShape(leftPos, topPos, rightPos, bottomPos, 0xFF << 24);
        ImageUtil.drawShape(leftPos, bottomPos - 1, rightPos, bottomPos, 0x66FFFFFF);
        float xDiff = rightPos - leftPos;
        ImageUtil.drawShape(leftPos, bottomPos - 1, leftPos + xDiff * progress, bottomPos, 0xFFFFFFFF);
        font.drawStringWithShadow(useTimeText, left, top + 0.5F, 0xFFFFFF);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static boolean isEquipAnimationDone(Minecraft mc) {
        ItemRenderer renderer = mc.getItemRenderer();
        return renderer.equippedProgressMainHand == 1.0F;
    }

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        CONSUMABLE_USE_FORMAT.setDecimalFormatSymbols(symbols);
    }
}
