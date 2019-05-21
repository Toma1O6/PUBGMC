package com.toma.pubgmc.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.toma.pubgmc.ConfigPMC;
import com.toma.pubgmc.ConfigPMC.ActionType;
import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelGhillie;
import com.toma.pubgmc.client.util.KeyBinds;
import com.toma.pubgmc.client.util.ModelDebugger;
import com.toma.pubgmc.common.capability.IPlayerData;
import com.toma.pubgmc.common.capability.IPlayerData.PlayerDataProvider;
import com.toma.pubgmc.common.entity.EntityParachute;
import com.toma.pubgmc.common.entity.EntityVehicle;
import com.toma.pubgmc.common.items.ItemAmmo;
import com.toma.pubgmc.common.items.ItemFuelCan;
import com.toma.pubgmc.common.items.armor.ArmorBase;
import com.toma.pubgmc.common.items.guns.GunBase;
import com.toma.pubgmc.common.items.guns.GunBase.Firemode;
import com.toma.pubgmc.common.items.guns.GunBase.GunType;
import com.toma.pubgmc.common.items.guns.GunBase.ReloadType;
import com.toma.pubgmc.common.items.heal.ItemHealing;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketAim;
import com.toma.pubgmc.common.network.server.PacketHandleParachuteInputs;
import com.toma.pubgmc.common.network.server.PacketHandleVehicleInput;
import com.toma.pubgmc.common.network.server.PacketNightVision;
import com.toma.pubgmc.common.network.server.PacketOpenGui;
import com.toma.pubgmc.common.network.server.PacketReload;
import com.toma.pubgmc.common.network.server.PacketReloading;
import com.toma.pubgmc.common.network.server.PacketSetScopeVariants;
import com.toma.pubgmc.common.network.server.PacketShoot;
import com.toma.pubgmc.common.network.server.PacketUpdateBoostValue;
import com.toma.pubgmc.event.SoundTickEvent;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCSounds;
import com.toma.pubgmc.util.ImageUtil;
import com.toma.pubgmc.util.handlers.GuiHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ClientEvents
{   
	//Scope overlays
	private static final ResourceLocation ScopeVSS = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scopevss.png");
    private static final ResourceLocation Scope2X = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope2x.png");
    private static final ResourceLocation[] Scope4X = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope4x_arrow.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope4x_cross.png")};
    private static final ResourceLocation Scope8X = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope8x.png");
    private static final ResourceLocation Scope15X = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope15x.png");
    private static final ResourceLocation NV = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nv.png");
    private static final ResourceLocation BOOST = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_empty.png");
    private static final ResourceLocation BOOST_FULL = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_full.png");
    private static final ResourceLocation BOOST_OVERLAY = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/boost_overlay.png");
    private static final ResourceLocation[] BACKPACK_OVERLAY = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack1.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack2.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/backpack3.png")};
    private static final ResourceLocation[] NIGHT_VISION_OVERLAY = {new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nightvision_off.png"), new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/nightvision_on.png")};
    private static final ResourceLocation VEHICLE = new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/vehicle.png");
	private static final List<ResourceLocation> SCOPES = new ArrayList<ResourceLocation>();
	private static final List<ResourceLocation> HOLOS = new ArrayList<ResourceLocation>();
	private static final DecimalFormat DECIMAL = new DecimalFormat("###");
	
	/** Time it takes to render red dot / holographic overlay after aiming **/
	private static final int AIM_TIME = 10;
	
	/** Time since started aiming **/
	private int aimingTicks;
    
    /** Random Number Generator **/
    private final Random rand = new Random();
    
    /** Slot to determine which gun is being reloaded **/
    private int reloadingSlot;
    
    /** Fov the player has before he started aiming **/
    private float prevFov;
    
    /** The brightness the player had when joins world **/
    private float prevBrightness;
    
    /** Timer for boost decrease **/
	private int timer;
	
	/** Number of shots from one burst **/
	private int shotsFired;
	
	/** Burst firemode; so my clientick event knows it's supposed to shoot **/
	private boolean shooting;
	
	/** Timer for burst fire **/
	private int shootingTimer;
	
	/** Mouse sensitivity player had before he started aiming **/
	private float mouseSens;
	
	/** Used for reloading to tell the game to play the sound or not. Simple workaround to prevent multiple reload sound playing **/
	private boolean hasAmmo;
	
	/** Used to stop aiming when player switches slot **/
	private int aimSlot;
	
	/** Used to render ghillie suit on player **/
	private final ModelGhillie ghillie = new ModelGhillie();
	
	/** IDs for different red dot sight styles and colors **/
	private int currentColor = 0;
	private int currentType = 0;
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Post e)
	{
		//e.getRenderer().addLayer(new LayerGhillie(e.getRenderer()));
		/*EntityPlayer player = e.getEntityPlayer();
		if(player != null)
		{
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
			
			if(stack.getItem() == PMCItems.GHILLIE_SUIT)
			{
				GlStateManager.pushMatrix();
				{
					GlStateManager.scale(0.07, 0.07, 0.07);
					GlStateManager.rotate(180, 1, 0, 0);
					GlStateManager.translate(0.0, -20.5, 0.0);
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Pubgmc.MOD_ID + ":textures/models/armor/ghillie_suit_layer_2.png"));
					ghillie.render(player);
				}
				GlStateManager.popMatrix();
			}
		}*/
	}
	
	@SubscribeEvent
	public void drawNameTags(RenderLivingEvent.Specials.Pre e)
	{
		if(e.getEntity() instanceof EntityPlayer && e.isCancelable())
		{
			if(!ConfigPMC.common.playerSettings.renderPlayerNameTags)
			{
				e.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void renderOverlayPost(RenderGameOverlayEvent.Post e)
	{
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayerSP sp = mc.player;
    	ScaledResolution res = new ScaledResolution(mc);
    	
		//Get the player capability to use the stored data
		IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		
    	//e.getType() == ElementType.TEXT - this is very important otherwise it will mess all fonts used in mc
    	if(!e.isCancelable() && e.getType() == ElementType.TEXT && !sp.capabilities.isCreativeMode && !sp.isSpectator() && !ConfigPMC.client.overlays.imageBoostOverlay && data.getBoost() > 0)
    	{
    		mc.entityRenderer.setupOverlayRendering();
    		int width = res.getScaledWidth();
    		int height = res.getScaledHeight();
    		int left = width / 2 + 45;
    		int top = height - 49;
        	
        	int color = 0;
        	
        	if(data.getBoost() >= 50)
        	{
        		color = 14651904;
        	}
        	
        	if(data.getBoost() < 50)
        	{
        		color = 14664960;
        	}
        	
        	if(data.getBoost() > 9)
        	{
        		left -= 5;
        	}
        	
        	//There is the rendering
        	//Users can edit the position using their configs
        	//Position by default: Above hunger bar
        	mc.fontRenderer.drawStringWithShadow(data.getBoost() + " / 100", left + ConfigPMC.client.overlays.overlayX, top + ConfigPMC.client.overlays.overlayY, color);
    	}
    	
    	//Ammo and Firemode info rendering
    	if(!e.isCancelable() && e.getType() == ElementType.TEXT && sp.getHeldItemMainhand().getItem() instanceof GunBase)
    	{
    		ItemStack weaponStack = sp.getHeldItemMainhand();
    		GunBase gun = (GunBase)weaponStack.getItem();
    		mc.entityRenderer.setupOverlayRendering();
    		int x = 15;
    		int y = e.getResolution().getScaledHeight() - 15;
    		int totalCount = 0;
    		for(int i = 0; i < sp.inventory.getSizeInventory(); i++)
    		{
    			ItemStack stack = sp.inventory.getStackInSlot(i);
    			if(stack.getItem() instanceof ItemAmmo)
    			{
    				ItemAmmo ammo = (ItemAmmo)stack.getItem();
    				if(ammo.type == gun.getAmmoType())
    				{
    					totalCount += stack.getCount();
    				}
    			}
    		}
    		if(weaponStack.hasTagCompound())
    		{
    			int ammo = weaponStack.getTagCompound().getInteger("ammo");
        		mc.fontRenderer.drawStringWithShadow(gun.getItemStackDisplayName(new ItemStack(gun)) + ": " + gun.getFiremode(), x, y - 9, 16777215);
    			mc.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + ammo + TextFormatting.RESET + " | " + totalCount, x, y, 16777215);
    		}
    	}
    	
    	renderVehicleOverlay(sp, mc, res, e);
	}
	
    //All pre overlay rendering
    @SubscribeEvent
    public void renderOverlayPre(RenderGameOverlayEvent.Pre e)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayerSP sp = mc.player;
    	ScaledResolution res = new ScaledResolution(mc);
    	ItemStack stack = sp.getHeldItemMainhand();
    	
		//Get the player capability to use the stored data
		IPlayerData data = sp.getCapability(PlayerDataProvider.PLAYER_DATA, null);
		
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		
    	//We don't want to render the crosshair in scopes
    	if(e.getType() == ElementType.CROSSHAIRS)
    	{
    		if(stack.getItem() instanceof GunBase)
    		{
    			//e.setCanceled(true);
    		}
    	}
    	
		if(ConfigPMC.client.overlays.imageBoostOverlay)
		{
			//We cancel the xp bar, but just only if the boost bar position is different than by default
	    	if(e.getType() == ElementType.EXPERIENCE)
	    	{
	    		if(ConfigPMC.client.overlays.imgOverlayX == 0 && ConfigPMC.client.overlays.imgOverlayY == 0 && data.getBoost() > 0)
	    		{
		    		e.setCanceled(true);
	    		}
	    	}
		}
    	
    	if(e.getType() == ElementType.ALL)
    	{	
    		//Render NIGHT VISION overlay
    		if(data.isUsingNV())
    		{
    			ImageUtil.drawFullScreenImage(mc, res, NV, true);
    		}
    		
    		//BOOST
    		if(!sp.capabilities.isCreativeMode && !sp.isSpectator() && data.getBoost() > 0)
    		{
        		renderBoost(data);
    		}
    		
    		//Scopes
    		if(stack.getItem() instanceof GunBase)
    		{
        		GunBase gun = (GunBase) stack.getItem();
        		if(data.isAiming() && mc.gameSettings.thirdPersonView == 0)
        		{
        			if(stack.getItem() == PMCRegistry.PMCItems.VSS)
        			{
        				ImageUtil.drawFullScreenImage(mc, res, ScopeVSS, true);
        			}
        			
        			if(stack.hasTagCompound())
        			{
        				int halfX = res.getScaledWidth() / 2;
        				int halfY = res.getScaledHeight() / 2;
        				int left = halfX - 8;
        				int top = halfY - 8;
        				
        				if(stack.getTagCompound().getInteger("scope") == 1 && aimingTicks >= AIM_TIME)
        				{
            				ImageUtil.drawCustomSizedImage(mc, SCOPES.get(getScopeTypeID(data.getScopeType(), data.getScopeColor())), left, top, 17, 17, true);
        					
        				}
        				
        				else if(stack.getTagCompound().getInteger("scope") == 2 && aimingTicks >= AIM_TIME)
        				{
        					ImageUtil.drawCustomSizedImage(mc, HOLOS.get(getHoloID(data.getScopeColor())), left, top, 17, 17, true);
        				}
        				
        				else if(stack.getTagCompound().getInteger("scope") == 3)
            			{
            				ImageUtil.drawFullScreenImage(mc, res, Scope2X, true);
            			}
            			
            			else if(stack.getTagCompound().getInteger("scope") == 4)
            			{
            				ImageUtil.drawFullScreenImage(mc, res, Scope4X[get4xIDFromGun(gun)], true);
            			}
            			
            			else if(stack.getTagCompound().getInteger("scope") == 5)
            			{
            				ImageUtil.drawFullScreenImage(mc, res, Scope8X, true);
            			}
            			
            			else if(stack.getTagCompound().getInteger("scope") == 6)
            			{
            				ImageUtil.drawFullScreenImage(mc, res, Scope15X, true);
            			}
        			}
        		}
    		}
    		
    		if(ConfigPMC.client.overlays.armorOverlayIcons && !sp.isSpectator())
    			renderArmorIcons(e, sp, res, mc, data);
    		
    		if(stack.getItem() instanceof ItemHealing || stack.getItem() instanceof ItemFuelCan)
    		{
    			if(sp.isHandActive())
    			{
    				drawItemUseOverlay(sp, mc, res, e, stack);
    			}
    		}
    	}
    }
    
    //Keybinding stuff
    //Everything has to be synced with server!
    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event)
    {
    	EntityPlayerSP sp = Minecraft.getMinecraft().player;
    	/** DEBUGGER **/
    	if(KeyBinds.TRANSLATEXPLUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.X += ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.X += ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.TRANSLATEXMINUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.X -= ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.X -= ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.TRANSLATEYPLUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.Y += ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.Y += ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.TRANSLATEYMINUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.Y -= ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.Y -= ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.TRANSLATEZPLUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.Z += ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.Z += ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.TRANSLATEZMINUS.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.Z -= ModelDebugger.SMALL_MODIFIER;
    		}
    		else
    		{
    			ModelDebugger.Z -= ModelDebugger.MODIFIER;
    		}
    	}
    	
    	if(KeyBinds.PRINT.isPressed())
    	{
    		ModelDebugger.printCode();
    	}
    	
    	if(KeyBinds.RESET.isPressed())
    	{
    		ModelDebugger.resetAllValues();
    	}
    	
    	if(KeyBinds.SCALE_UP.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.scale += 0.01f;
    		}
    		
    		else ModelDebugger.scale += 0.1f;
    	}
    	
    	if(KeyBinds.SCALE_DOWN.isPressed())
    	{
    		if(sp.isSneaking())
    		{
    			ModelDebugger.scale -= 0.01f;
    		}
    		
    		else ModelDebugger.scale -= 0.1f;
    	}
    	
    	/** NORMAL MODE ========================================================================== **/
    	
    	/** Scope Variants */
    	if(KeyBinds.CHANGE_SCOPETYPE.isPressed() && (canSwitchType(sp.getHeldItemMainhand()) && sp.hasCapability(PlayerDataProvider.PLAYER_DATA, null)))
    	{
    		switchScopeType(sp.getCapability(PlayerDataProvider.PLAYER_DATA, null));
    	}
    	if(KeyBinds.CHANGE_SCOPECOLOR.isPressed() && sp.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
    	{
    		switchScopeColor(sp.getCapability(PlayerDataProvider.PLAYER_DATA, null));
    	}
    	
    	if(ConfigPMC.common.worldSettings.enableGuns)
    	{
        	if(sp instanceof EntityPlayer)
        	{
        		EntityPlayer player = (EntityPlayer)sp;
        		IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
        		
        		if(KeyBinds.ATTACHMENT.isPressed())
        		{
        			PacketHandler.INSTANCE.sendToServer(new PacketOpenGui(GuiHandler.GUI_ATTACHMENTS));
        		}
        		
                if(KeyBinds.RELOAD.isPressed())
                {	
                	if(data.isReloading())
                	{
                		data.setReloading(false);
                		data.setReloadingTime(0);
                		PacketHandler.INSTANCE.sendToServer(new PacketReloading(false));
                	}
                	//Check if the player is able to reload
                	//Ammo checking is being handled in the onReload method from GunBase.class
                	if(player.getHeldItemMainhand().getItem() instanceof GunBase)
                	{
                		GunBase gun = (GunBase)player.getHeldItemMainhand().getItem();
                		
                		if(player.getHeldItemMainhand().hasTagCompound())
                		{
                			int ammo = player.getHeldItemMainhand().getTagCompound().getInteger("ammo");

                            if(!data.isReloading() && ammo < gun.getWeaponAmmoLimit(player.getHeldItemMainhand()))
                            {
                                data.setReloading(true);
                                data.setReloadingTime(0);
                                
                                //Sync with server
                                PacketHandler.INSTANCE.sendToServer(new PacketReloading(true));
                                
                                //Get the slot with gun which is being reloaded
                                reloadingSlot = player.inventory.currentItem;
                                
                                //You can't aim while you're reloading
                                if(data.isAiming())
                                {
                                	data.setAiming(false);
                                	PacketHandler.INSTANCE.sendToServer(new PacketAim(false));
                					Minecraft.getMinecraft().gameSettings.mouseSensitivity = this.mouseSens;
                                }
                            }
                		}
                	}
                }
                
                //Same as above, we just send packet to server and everything else will be done in the rendering method above
                if(KeyBinds.NV.isPressed())
                {
                	if(data.getEquippedNV())
                	{
                		if(!data.isUsingNV())
                		{
                			data.setNV(true);
                			PacketHandler.INSTANCE.sendToServer(new PacketNightVision(true));
                		}
                		
                		else
                		{
                			data.setNV(false);
                			PacketHandler.INSTANCE.sendToServer(new PacketNightVision(false));
                		}
                	}
                }
                
                //Switch firemode
                //Syncing happens from the GunBase.class
                if(KeyBinds.FIREMODE.isPressed())
                {
                	if(player.getHeldItemMainhand().getItem() instanceof GunBase)
                	{
                    	GunBase item = (GunBase)player.getHeldItemMainhand().getItem();
                    	
                    	if(item.getCanSwitchFiremode())
                    	{
                    		item.getNextFiremode(player);
                    	}
                	}
                }
        	}
    	}
    }
    
    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent e)
    {
    	GameSettings gs = Minecraft.getMinecraft().gameSettings;
    	EntityPlayerSP player = Minecraft.getMinecraft().player;
    	CooldownTracker tracker = player.getCooldownTracker();
    	//To prevent crash on startup
    	if(player != null)
    	{
    		ItemStack stack = player.getHeldItemMainhand();
    		if(stack.getItem() instanceof GunBase)
    		{
    			GunBase gun = (GunBase)stack.getItem();
				IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);

    			//Check if the LMB has been pressed
    			if(gs.keyBindAttack.isPressed())
    			{
    				if(ConfigPMC.common.worldSettings.enableGuns)
    				{
            			//Shoot only once if the firemode is single
        				if(gun.getFiremode() == Firemode.SINGLE)
        				{
        					//If the gun has no cooldown
            				if(!tracker.hasCooldown(gun) && !data.isReloading())
            				{
            					if(gun.hasAmmo(stack))
            					{
                					//We send packet to server telling it to spawn new entity
                    				PacketHandler.INSTANCE.sendToServer(new PacketShoot());
                    				//Do the recoil
                    				applyRecoil(player, stack);
            					}
            					else
            					{
            						player.playSound(PMCSounds.gun_noammo, 4f, 1f);
            					}
            				}
        				}
    				}
    				else
    				{
    					sendWarningToPlayer(player, "Guns are disabled!");
    				}
    				//This is being handled in ClientTickEvent so we just prepare some stuff here
    				if(gun.getFiremode() == Firemode.BURST)
    				{
    					if(ConfigPMC.common.worldSettings.enableGuns)
    					{
        					if(!tracker.hasCooldown(gun) && !shooting && !data.isReloading())
        					{
        						if(gun.hasAmmo(stack))
        						{
            						shooting = true;
            						shotsFired = 0;
        						}
        						else
        						{
        							player.playSound(PMCSounds.gun_noammo, 4f, 1f);
        						}
        					}
    					}
    					else
    					{
    						sendWarningToPlayer(player, "Guns are disabled!");
    					}
    				}
    			}

    			//Aiming on RMB press
    			if(gs.keyBindUseItem.isPressed())
    			{
    				if(!data.isAiming())
    				{
    					aimingTicks = 0;
    					aimSlot = player.inventory.currentItem;
    					data.setAiming(true);
    					//We have to tell the server the player is aiming to make it work on servers
    					PacketHandler.INSTANCE.sendToServer(new PacketAim(true));
    					int scopeID = stack.getTagCompound().getInteger("scope");

    					//sensitivity modifier
    					switch(scopeID)
    					{
    						case 1: gs.mouseSensitivity *= 0.95f; break;
    						case 2: gs.mouseSensitivity *= 0.95f; break;
    						case 3: gs.mouseSensitivity *= 0.85f; break;
    						case 4: gs.mouseSensitivity *= 0.65f; break;
    						case 5: gs.mouseSensitivity *= 0.4f; break;
    						case 6: gs.mouseSensitivity *= 0.01f; break;
    						default: break;
    					}
    				}
    				else
    				{
    					data.setAiming(false);
    					PacketHandler.INSTANCE.sendToServer(new PacketAim(false));
    					gs.mouseSensitivity = this.mouseSens;
    				}
    			}
    		}
    	}
    }
	
	//We get the player fov setting which will be used later with scopes; This is for returning the fov to the base value
	@SubscribeEvent
	public void onInitialize(EntityEvent.EntityConstructing e)
	{
		if(e.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)e.getEntity();
			if(player.world.isRemote)
			{
				initScopes();
				
				//Your fov cannot be under the "Normal" setting since it would be overpowered in some cases
				//Purely balance stuff
				float prevfov = Minecraft.getMinecraft().gameSettings.fovSetting;
				
				if(prevfov < 70)
				{
					prevfov = 70;
					Minecraft.getMinecraft().gameSettings.fovSetting = 70f;
				}
				
				//We set the base value to this if the previous value didn't meet our requirements
				this.prevFov = prevfov;
			}
		}
	}
	
	//Client tick event
	//All tick related stuff from client will be handled here
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onClientTick(TickEvent.ClientTickEvent ev)
	{
        EntityPlayer player = Minecraft.getMinecraft().player;
        GameSettings gs = Minecraft.getMinecraft().gameSettings;
        
        //We have to check this otherwise it would crash in the menu since this event is running as soon as your minecraft client is started
        if(player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
        {
        	//Get the player capability
            IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
            
        	//This takes care of vehicle controls
        	if(ev.phase == Phase.END)
        	{
                handleVehicleControls(gs.keyBindForward.isKeyDown(), gs.keyBindBack.isKeyDown(), gs.keyBindRight.isKeyDown(), gs.keyBindLeft.isKeyDown(), player);
                handleParachuteControls(gs.keyBindForward.isKeyDown(), gs.keyBindBack.isKeyDown(), gs.keyBindRight.isKeyDown(), gs.keyBindLeft.isKeyDown(), player);
        	}
        	
        	//Automatic fire is handled here because Mouse input event is acting weirdly
        	if(gs.keyBindAttack.isKeyDown() && ev.phase == Phase.END)
        	{	
        		if(player.getHeldItemMainhand().getItem() instanceof GunBase)
        		{
        			if(ConfigPMC.common.worldSettings.enableGuns)
        			{
            			GunBase gun = (GunBase)player.getHeldItemMainhand().getItem();
            			CooldownTracker tracker = player.getCooldownTracker();
            			
            			if(gun.getFiremode() == Firemode.AUTO && !tracker.hasCooldown(gun) && !data.isReloading())
            			{
            				if(gun.hasAmmo(player.getHeldItemMainhand()))
            				{
                  				PacketHandler.INSTANCE.sendToServer(new PacketShoot());
                				this.applyRecoil(player, player.getHeldItemMainhand());
            				}
            				
            				else
            				{
            					player.playSound(PMCSounds.gun_noammo, 4f, 1f);
            				}
            			}
        			}
        			
            		else
            		{
            			sendWarningToPlayer(player, "Guns are disabled!");
            		}
        		}
        	}
        	
        	//Burst fire is handled here
        	if(player.getHeldItemMainhand().getItem() instanceof GunBase)
        	{
        		ItemStack stack = player.getHeldItemMainhand();
        		GunBase gun = (GunBase)player.getHeldItemMainhand().getItem();
        		CooldownTracker tracker = player.getCooldownTracker();
        		int maxRounds = 0;
        		
        		if(gun.isHasTwoRoundBurst())
        		{
        			maxRounds = 3;
        		}
        		else
        		{
        			//Workaround for 3 round burst since we are now having small delay on bullets because shooting 
        			//code is being executed from minecraft thread
        			maxRounds = 6;
        		}
        		
        		if(stack.hasTagCompound() && gun.hasAmmo(stack))
        		{
        			if(shooting && gun.getFiremode() == Firemode.BURST && ConfigPMC.common.worldSettings.enableGuns)
        			{
        				shootingTimer++;
        				
        				//Set it to 5 for 3 round burst
        				if(shootingTimer >= gun.getFireRate() && shotsFired < maxRounds)
        				{
        					PacketHandler.INSTANCE.sendToServer(new PacketShoot());
        					applyRecoil(player, stack);
        					shotsFired++;
        					shootingTimer = 0;
        				}
        				
        				if(shotsFired >= maxRounds || !gun.hasAmmo(stack))
        				{
        					shooting = false;
        					shootingTimer = 0;
        					shotsFired = 0;
        				}
        			}
        			
        			else
        			{
        				shooting = false;
        				shootingTimer = 0;
        				shotsFired = 0;
        			}
        		}
        		
        		else
        		{
        			if(shooting)
        			{
        				shooting = false;
        				shootingTimer = 0;
        				shotsFired = 0;
        			}
        		}
        	}
        	
            if(data.isAiming())
            {
            	if(aimingTicks < AIM_TIME) aimingTicks++;
            	
            	if(aimSlot != player.inventory.currentItem)
            	{
            		setAiming(data, false);
            		gs.mouseSensitivity = this.mouseSens;
            	}
            	
            	if(ConfigPMC.client.keys.aimStyle == ActionType.HOLD) {
            		if(!gs.keyBindUseItem.isKeyDown()) {
            			setAiming(data, false);
            		}
            	}
            }
            
    		if(player.isSprinting() && data.isAiming())
    		{
    			setAiming(data, false);
				gs.mouseSensitivity = this.mouseSens;
    		}
        	
    		if(!(player.getHeldItemMainhand().getItem() instanceof GunBase) && data.isAiming())
    		{
    			setAiming(data, false);
				gs.mouseSensitivity = this.mouseSens;
    		}
    		
    		//If the boost is above the max value reset it and sync with server
    		if(data.getBoost() > 100f)
    		{
    			data.setBoost(100f);
    			PacketHandler.INSTANCE.sendToServer(new PacketUpdateBoostValue(100f));
    		}
    		
    		//Reset the mouse sensitivity after aiming
    		if(!data.isAiming())
    		{
    			this.mouseSens = gs.mouseSensitivity;
    			
    			if(gs.mouseSensitivity != this.mouseSens)
    			{
    				gs.mouseSensitivity = this.mouseSens;
    			}
    		}
			
    		//Increase the boost timer
			timer++;
			
			//After 4 seconds decrease the boost by 1 and send the new boost value to server
			if(timer >= 80 && data.getBoost() > 0)
			{
				timer = 0;
				data.removeBoost(1);
				PacketHandler.INSTANCE.sendToServer(new PacketUpdateBoostValue(data.getBoost()));
			}
			
			//Check if the player is having selected the slot where he started reloading
	        if(ConfigPMC.common.worldSettings.enableGuns)
	        {
	            if(data.isReloading())
	            {
	                if(reloadingSlot != player.inventory.currentItem)
	                {
	                    setReloading(data, true);
	                }
	            }
	        }
        }
        
        //Disabling the third person view to make true first person experience
		if(ev.phase == Phase.END && player != null && player.hasCapability(PlayerDataProvider.PLAYER_DATA, null))
		{
			ItemStack itemstack = player.getHeldItemMainhand();
			Item item = itemstack.getItem();
	    	//Get the player capability
	        IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA, null);
			
			if(player.getRidingEntity() instanceof EntityParachute && !player.capabilities.isCreativeMode)
			{
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView != 1)
				{
					Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
				}
			}
			
			if(ConfigPMC.common.playerSettings.enableTP)
			{
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView != 0 && !player.capabilities.isCreativeMode && !(player.getRidingEntity() instanceof EntityParachute))
				{
					Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
				}
			}
			
        	//Zoom level based on weapon type; in future this will be based on item NBT - The scope you have attached
	        if(data.isAiming())
	        {	
	        	if(item instanceof GunBase)
	        	{
	        		GunBase gun = (GunBase)item;
	        		
	        		if(itemstack.hasTagCompound())
	        		{
		        		if(itemstack.getTagCompound().getInteger("scope") == 1 || itemstack.getTagCompound().getInteger("scope") == 2)
		        		{
			        		if(Minecraft.getMinecraft().gameSettings.fovSetting != 45)
			        		{
					        	Minecraft.getMinecraft().gameSettings.fovSetting = 45;
			        		}
		        		}
		        		
		        		if(itemstack.getTagCompound().getInteger("scope") == 3)
		        		{
			        		if(Minecraft.getMinecraft().gameSettings.fovSetting != 38)
			        		{
					        	Minecraft.getMinecraft().gameSettings.fovSetting = 38;
			        		}
		        		}
		        		
		        		if(itemstack.getTagCompound().getInteger("scope") == 4 || itemstack.getItem() == PMCRegistry.PMCItems.VSS)
		        		{
			        		if(Minecraft.getMinecraft().gameSettings.fovSetting != 25)
			        		{
					        	Minecraft.getMinecraft().gameSettings.fovSetting = 25;
			        		}
		        		}
		        		
		        		if(itemstack.getTagCompound().getInteger("scope") == 5)
		        		{
			        		if(Minecraft.getMinecraft().gameSettings.fovSetting != 10)
			        		{
					        	Minecraft.getMinecraft().gameSettings.fovSetting = 10;
			        		}
		        		}
		        		
		        		if(itemstack.getTagCompound().getInteger("scope") == 6)
		        		{
			        		if(Minecraft.getMinecraft().gameSettings.fovSetting != 3)
			        		{
					        	Minecraft.getMinecraft().gameSettings.fovSetting = 3;
			        		}
		        		}
	        		}
	       		}
			}
	        
        	else
        	{
        		if(Minecraft.getMinecraft().gameSettings.fovSetting < prevFov)
        		{
	        		Minecraft.getMinecraft().gameSettings.fovSetting += 5;
        		}
        		
        		if(Minecraft.getMinecraft().gameSettings.fovSetting > prevFov)
        		{
	        		Minecraft.getMinecraft().gameSettings.fovSetting = prevFov;
        		}
        		
        		Minecraft.getMinecraft().gameSettings.mouseSensitivity = this.mouseSens;
        	}
	        
	        //For better experience with NV googles
	        if(ConfigPMC.common.playerSettings.forceBrightness)
	        {
		        float bright = Minecraft.getMinecraft().gameSettings.gammaSetting;
		        
		        if(bright != ConfigPMC.common.playerSettings.brightness)
		        {
		        	Minecraft.getMinecraft().gameSettings.gammaSetting = ConfigPMC.common.playerSettings.brightness;
		        }
	        }
	        
	        /** =========================================[RELOADING]=========================================== **/
	        
	        if(data.isReloading())
	        {
	        	if(player.getHeldItemMainhand().getItem() instanceof GunBase && player.getHeldItemMainhand().getTagCompound().getBoolean("isValidWeapon"))
	        	{
		        	GunBase gun = (GunBase)player.getHeldItemMainhand().getItem();
		        	data.setReloadingTime(data.getReloadingTime() + 1);
		        	
		        	for(int i = 0; i < player.inventory.getSizeInventory(); i++)
		        	{
		        		ItemStack stack = player.inventory.getStackInSlot(i);
		        		if((stack.getItem() instanceof ItemAmmo) && player.world.isRemote)
		        		{
		        			ItemAmmo ammo = (ItemAmmo)stack.getItem();
		        			if(ammo.type == gun.getAmmoType())
		        			{
		        				hasAmmo = true;
		        			}
		        			
		    	        	if(gun.getReloadType() == ReloadType.MAGAZINE)
		    	        	{
		    	        		if(data.getReloadingTime() >= gun.getReloadTime(itemstack))
		    	        		{
				    	        	PacketHandler.INSTANCE.sendToServer(new PacketReload());
				    	        	data.setReloadingTime(0);
				    	        	hasAmmo = false;
				    	        	setReloading(data, false);
		    	        		}
		    	        	}
		    	        	
		    	        	else if(gun.getReloadType() == ReloadType.SINGLE)
		    	        	{
		    	        		if(data.getReloadingTime() >= gun.getReloadTime(itemstack))
		    	        		{
				    	        	PacketHandler.INSTANCE.sendToServer(new PacketReload());
				    	        	data.setReloadingTime(0);
				    	        	hasAmmo = false;
		    	        		}
		    	        	}
		    	        	
		    	        	else if(gun.getReloadType() == ReloadType.KAR98K)
		    	        	{
		    	        		if(itemstack.hasTagCompound())
		    	        		{
		    	        			if(itemstack.getTagCompound().getInteger("ammo") == 0)
		    	        			{
		    	        				if(data.getReloadingTime() >= gun.getReloadTime(itemstack))
		    	        				{
			    	        				PacketHandler.INSTANCE.sendToServer(new PacketReload());
			    	        				data.setReloadingTime(0);
			    	        				hasAmmo = false;
		    	        				}
		    	        			}
		    	        			
		    	        			else
		    	        			{
		    	        				if(data.getReloadingTime() >= 18)
		    	        				{
		    	        					PacketHandler.INSTANCE.sendToServer(new PacketReload());
		    	        					data.setReloadingTime(0);
		    	        					hasAmmo = false;
		    	        				}
		    	        			}
		    	        		}
		    	        	}
		        		}
		        	}
		        	
		        	if(data.getReloadingTime() == 1 && data.isReloading())
		        	{
		        		if(gun.getReloadType() == ReloadType.MAGAZINE && hasAmmo)
		        		{
		        			player.playSound(gun.getWeaponReloadSound(), 1f, 1f);
		        			hasAmmo = false;
		        		}
		        		
		        		else if(gun.getReloadType() == ReloadType.SINGLE)
		        		{
		        			if(itemstack.hasTagCompound())
		        			{
		        				if(itemstack.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(itemstack) && hasAmmo)
		        				{
		        					player.playSound(gun.getWeaponReloadSound(), 1f, 1f);
		        					hasAmmo = false;
		        				}
		        			}
		        		}
		        		
		        		else if(gun.getReloadType() == ReloadType.KAR98K)
		        		{
		        			if(itemstack.hasTagCompound())
		        			{
		        				if(itemstack.getTagCompound().getInteger("ammo") == 0 && hasAmmo)
		        				{
		        					player.playSound(PMCSounds.reload_kar98k, 1f, 1f);
		        					hasAmmo = false;
		        				}
		        				
		        				else if(itemstack.getTagCompound().getInteger("ammo") < gun.getWeaponAmmoLimit(itemstack) && hasAmmo)
		        				{
		        					player.playSound(PMCSounds.reload_kar98k_single, 1f, 1f);
		        					hasAmmo = false;
		        				}
		        			}
		        		}
		        	}
	        	}
	        	
	        	//cancel reloading 
	        	else
	        	{
	        		data.setReloadingTime(0);
	        		setReloading(data, false);
	        		hasAmmo = false;
	        	}
	        	
	        	// You cannot sprint while reloading
	        	if(player.isSprinting())
	        	{
	        		player.setSprinting(false);
	        	}
	        	
	        	// You cannot aim while reloading 
	        	if(data.isAiming())
	        	{
	        		setAiming(data, false);
	        	}
	        	
	        	// to prevent reloading happening when player doesn't have ammo for the weapon
	        	if(player.getHeldItemMainhand().getItem() instanceof GunBase && !player.inventory.hasItemStack(((GunBase)player.getHeldItemMainhand().getItem()).getAmmoType().ammoStack()))
	        	{
	        		setReloading(data, false);
	        	}
	        }
	        
	        else
	        {
	        	if(hasAmmo)
	        	{
	        		hasAmmo = false;
	        	}
	        }
	        if(ConfigPMC.client.other.betterSound)
	        	MinecraftForge.EVENT_BUS.post(new SoundTickEvent());
		}
	}
	
// ========================================================================================================
// -----------------------------------[ HELPER FUNCTIONS ]-------------------------------------------------
// ========================================================================================================
	
    //Do the recoil for the player
    private void applyRecoil(EntityPlayer player, ItemStack gun)
    {
    	float gripModifier = 1f;
    	float angledGrip = 1f;
    	float stockMod = 1f;
    	
    	if(gun.getItem() instanceof GunBase)
    	{
    		if(gun.hasTagCompound())
    		{
    			if(gun.getTagCompound().getInteger("stock") == 2)
    			{
    				stockMod = 0.9f;
    			}
    			
    			if(gun.getTagCompound().getInteger("grip") == 1)
    			{
    				gripModifier = 0.8f;
    			}
    			
    			else if(gun.getTagCompound().getInteger("grip") == 2)
    			{
    				angledGrip = 0.8f;
    			}
    			
    			else
    			{
    				gripModifier = 1f;
    				angledGrip = 1f;
    			}
    		}
    		else
    		{
    			throw new IllegalArgumentException("No valid NBT found. Report this to PUBGMC author!");
    		}
    		
    		GunBase wep = (GunBase)gun.getItem();
    		
        	//10% vertical recoil reduction while you're sneaking
    		if(player.isSneaking())
    		{
    			if(gun.hasTagCompound())
    			{
    				if(gun.getTagCompound().getInteger("barrel") == 2)
    				{
    	    			player.rotationPitch = player.rotationPitch - (((wep.getVerticalRecoil(gun) * 0.8f * gripModifier * stockMod) * (float)rand.nextDouble() * 1.5f) * 0.9f);
    				}
    				
    				else player.rotationPitch = player.rotationPitch - (((wep.getVerticalRecoil(gun) * gripModifier * stockMod) * (float)rand.nextDouble() * 1.5f) * 0.9f);
    			}
    		}
    		else
    		{
    			if(gun.hasTagCompound())
    			{
    				if(gun.getTagCompound().getInteger("barrel") == 2)
    				{
    					player.rotationPitch = player.rotationPitch - ((wep.getVerticalRecoil(gun) * 0.8f * gripModifier * stockMod) * (float)rand.nextDouble() * 1.5f);
    				}
    				
    				else player.rotationPitch = player.rotationPitch - ((wep.getVerticalRecoil(gun) * gripModifier * stockMod) * (float)rand.nextDouble() * 1.5f);
    			}
    		}
    		
    		//set horizontal recoil (50% to go right and 50% to go left)
    		if(Math.random() * 100 <= 50)
    		{
    			if(gun.hasTagCompound())
    			{
    				if(gun.getTagCompound().getInteger("barrel") == 2)
    				{
    					player.rotationYaw = player.rotationYaw - ((wep.getHorizontalRecoil(gun) * 0.8f * angledGrip * stockMod) * (float)rand.nextDouble() * 1.5f);
    				}
    				
    				else player.rotationYaw = player.rotationYaw - ((wep.getHorizontalRecoil(gun) * angledGrip * stockMod) * (float)rand.nextDouble() * 1.5f);
    			}
    		}
    		
    		else
    		{
    			if(gun.hasTagCompound())
    			{
    				if(gun.getTagCompound().getInteger("barrel") == 2)
    				{
    					player.rotationYaw = player.rotationYaw + ((wep.getHorizontalRecoil(gun) * 0.8f * angledGrip * stockMod) * (float)rand.nextDouble() * 1.5f);
    				}
    				
    				else player.rotationYaw = player.rotationYaw + ((wep.getHorizontalRecoil(gun) * angledGrip * stockMod) * (float)rand.nextDouble() * 1.5f);
    			}
    		}
    	}
    }
    
    /** Function for rendering the textured boost overlays
     *  Used to make rendering actual rendering code shorter and easier to read
     */
    private static void renderBoost(IPlayerData data)
    {
    	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		
		if(ConfigPMC.client.overlays.imageBoostOverlay)
		{	
    		int left = width / 2 - 91;
    		int top = height - 32 + 3;
    		short barWidth = 182;
    		
    		//Actual drawing code
    		ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST, left + ConfigPMC.client.overlays.imgOverlayX, top + ConfigPMC.client.overlays.imgOverlayY, barWidth, 5, false);

    		if(data.getBoost() > 0)
    		{
    			int boost = (int)data.getBoost();
    			double sizeX = ((182D / 100D) * boost);
    			ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST_FULL, left + ConfigPMC.client.overlays.imgOverlayX, top + ConfigPMC.client.overlays.imgOverlayY, sizeX, 5, false);
    		}
    		
    		//This will render after these 2 above to make sure this will always be on the top
    		ImageUtil.drawCustomSizedImage(Minecraft.getMinecraft(), BOOST_OVERLAY, left + ConfigPMC.client.overlays.imgOverlayX, top + ConfigPMC.client.overlays.imgOverlayY, barWidth, 5, true);
		}
    }
    
    private static void sendWarningToPlayer(EntityPlayer player, String textToSend)
    {
    	player.sendMessage(new TextComponentString(TextFormatting.RED + textToSend));
    }
    
    private void setReloading(IPlayerData data, boolean reload)
    {
    	data.setReloading(reload);
    	PacketHandler.INSTANCE.sendToServer(new PacketReloading(reload));
    }
    
    private void setAiming(IPlayerData data, boolean aim)
    {
    	data.setAiming(aim);
    	PacketHandler.INSTANCE.sendToServer(new PacketAim(aim));
    }
    
    private static void initScopes()
    {
    	SCOPES.clear();
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_dot_red.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_dot_green.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_dot_yellow.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_dot_blue.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_arrow_red.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_arrow_green.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_arrow_yellow.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_arrow_blue.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_crosshair_red.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_crosshair_green.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_crosshair_yellow.png"));
    	SCOPES.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/scope_crosshair_blue.png"));
    	
    	HOLOS.clear();
    	HOLOS.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/holo_red.png"));
    	HOLOS.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/holo_green.png"));
    	HOLOS.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/holo_yellow.png"));
    	HOLOS.add(new ResourceLocation(Pubgmc.MOD_ID + ":textures/overlay/holo_blue.png"));
    }
    
    private static int getScopeTypeID(int scope, int color)
    {
    	int id = 0;
    	switch(scope)
    	{
    		case 0: id += 0; break;
    		case 1: id += 4; break;
    		case 2: id += 8; break;
    	}
    	
    	switch(color)
    	{
    		case 0: id += 0; break;
    		case 1: id += 1; break;
    		case 2: id += 2; break;
    		case 3: id += 3; break;
    	}
    	
    	return id;
    }
    
    private static int getHoloID(int color)
    {
    	int id = 0;
    	switch(color)
    	{
    		case 0: id += 0; break;
    		case 1: id += 1; break;
    		case 2: id += 2; break;
    		case 3: id += 3; break;
    	}
    	
    	return id;
    }
    
    private void switchScopeType(IPlayerData data)
    {
    	if(currentType < 2) {
    		currentType ++;
    	}
    	else currentType = 0;
    	data.setScopeType(currentType);
    	PacketHandler.INSTANCE.sendToServer(new PacketSetScopeVariants(currentType, currentColor));
    }
    
    private void switchScopeColor(IPlayerData data)
    {
    	if(currentColor < 3)
    	{
    		currentColor ++;
    	}
    	else currentColor = 0;
    	data.setScopeColor(currentColor);
    	PacketHandler.INSTANCE.sendToServer(new PacketSetScopeVariants(currentType, currentColor));
    }
    
    private boolean canSwitchType(ItemStack heldStack)
    {
    	return heldStack.hasTagCompound() && heldStack.getTagCompound().getInteger("scope") == 1;
    }
    
    private static int get4xIDFromGun(GunBase gun)
    {
    	return gun.getGunType() == GunType.DMR || gun.getGunType() == GunType.SR ? 1 : 0;
    }
    
    private void handleVehicleControls(boolean forward, boolean back, boolean right, boolean left, EntityPlayer player)
    {
    	if(player.getRidingEntity() instanceof EntityVehicle)
    	{
    		EntityVehicle vehicle = (EntityVehicle)player.getRidingEntity();
    		
    		//vehicle.handleInputs(forward, back, right, left, player);
    		PacketHandler.sendToServer(new PacketHandleVehicleInput(forward, back, right, left, vehicle.getEntityId()));
    	}
    }
    
    private void handleParachuteControls(boolean down, boolean up, boolean right, boolean left, EntityPlayer player)
    {
    	if(player.getRidingEntity() instanceof EntityParachute)
    	{
    		EntityParachute chute = (EntityParachute)player.getRidingEntity();
    		
    		chute.handleInputs(down, up, right, left);
    		PacketHandler.INSTANCE.sendToServer(new PacketHandleParachuteInputs(up, down, right, left));
    	}
    }
    
    private static void renderArmorIcons(RenderGameOverlayEvent.Pre e, EntityPlayer player, ScaledResolution res, Minecraft mc, IPlayerData data)
    {
    	ArmorBase head = player.inventory.getStackInSlot(39).getItem() instanceof ArmorBase ? (ArmorBase)player.inventory.getStackInSlot(39).getItem() : null;
    	ArmorBase body = player.inventory.getStackInSlot(38).getItem() instanceof ArmorBase ? (ArmorBase)player.inventory.getStackInSlot(38).getItem() : null;
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		int left = width / 2 + 93;
		int top = height - 19;
		int offset = 0;
		
    	if(head != null)
    	{
    		int level = head.armorLevel().getArmorLevel();
    		ItemStack headS = player.inventory.getStackInSlot(39);
    		ResourceLocation img = head.armorLevel().getIcon(true, level, getDamageLevel(headS));
    		
    		ImageUtil.drawCustomSizedImage(mc, img, left + offset, top, 16, 16, true);
    		offset += 17;
    	}
    	
    	if(body != null)
    	{
    		int level = body.armorLevel().getArmorLevel();
    		ItemStack bodyS = player.inventory.getStackInSlot(38);
    		ResourceLocation img = body.armorLevel().getIcon(false, level, getDamageLevel(bodyS));
    		
    		ImageUtil.drawCustomSizedImage(mc, img, left + offset, top, 16, 16, true);
    		offset += 17;
    	}
    	
    	if(data.getBackpackLevel() > 0)
    	{
    		int level = data.getBackpackLevel() - 1;
    		
    		ImageUtil.drawCustomSizedImage(mc, BACKPACK_OVERLAY[level], left + offset, top, 16, 16, true);
    		offset += 17;
    	}
    	
    	if(data.getEquippedNV())
    	{
    		int i = data.isUsingNV() ? 1 : 0;
    		
    		ImageUtil.drawCustomSizedImage(mc, NIGHT_VISION_OVERLAY[i], left + offset, top, 16, 16, true);
    	}
    }
    
    private static int getDamageLevel(ItemStack stack)
    {
    	final double val = (double)stack.getItemDamage() / (double)stack.getMaxDamage();
    	return val < 0.2d ? 0 : val > 0.7d ? 2 : 1;
    }
    
    private static void renderVehicleOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Post e)
    {
    	if(e.getType() == ElementType.TEXT && player.getRidingEntity() instanceof EntityVehicle)
    	{
    		EntityVehicle car = (EntityVehicle)player.getRidingEntity();
    		
    		mc.fontRenderer.drawStringWithShadow("Speed: " + DECIMAL.format(Math.abs(car.currentSpeed) * 48.5) + "km/h", 15, res.getScaledHeight() - 60, 16777215);
    	}
    	
    	else if(e.getType() == ElementType.ALL && player.getRidingEntity() instanceof EntityVehicle)
    	{
    		EntityVehicle car = (EntityVehicle)player.getRidingEntity();
    		double health = car.health / car.maxHealth * 100;
    		ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, car.fuel * 1.2, 5, 0.0, 0.25, 1.0, 0.375, false);
    		ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 40, 120, 5, 0.0, 0.375, 1.0, 0.5, true);
    		ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 50, 120, 5, 0.0, 0.125, 1.0, 0.25, false);
    		ImageUtil.drawImageWithUV(mc, VEHICLE, 15, res.getScaledHeight() - 50, health * 1.2, 5, 0.0, 0.0, 1.0, 0.125, false);
    	}
    }
    
    private static void drawItemUseOverlay(EntityPlayer player, Minecraft mc, ScaledResolution res, RenderGameOverlayEvent.Pre e, ItemStack stack)
    {
    	final DecimalFormat f = new DecimalFormat("#,#0.0");
    	final float useTime = (float)player.getItemInUseCount();
    	FontRenderer font = mc.fontRenderer;
    	int width = res.getScaledWidth();
    	int height = res.getScaledHeight();
    	int left = width/2;
    	int top = height/2;
    	font.drawStringWithShadow(f.format(useTime/20), left - 6, top + 3, 0xFFFFFF);
    }
}
