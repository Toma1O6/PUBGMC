package com.toma.pubgmc.client.util;

import org.lwjgl.input.Keyboard;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.models.ModelGun;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ModelDebugger
{
	public static final double MODIFIER = 1f;
	public static final double SMALL_MODIFIER = 0.05f;
	
	public static ModelDebugger instance;
	public static float x = 0;
	public static float y = 0;
	public static float z = 0;
	public static float scale = 1f;
	private static boolean initialized = false;
	
	public static KeyBinding TRANSLATEYPLUS;
	public static KeyBinding TRANSLATEYMINUS;
	public static KeyBinding TRANSLATEXPLUS;
	public static KeyBinding TRANSLATEXMINUS;
	public static KeyBinding TRANSLATEZPLUS;
	public static KeyBinding TRANSLATEZMINUS;
	public static KeyBinding SCALE_UP;
	public static KeyBinding SCALE_DOWN;
	public static KeyBinding PRINT;
	public static KeyBinding RESET;
	
	public static void init() {
		if(initialized) {
			throw new IllegalStateException("Debugger is already initialized");
		}
		
		TRANSLATEYPLUS = init("debug.translateY+", Keyboard.KEY_UP);
		TRANSLATEYMINUS = init("debug.translateY-", Keyboard.KEY_DOWN);
		TRANSLATEXPLUS = init("debug.translateX+", Keyboard.KEY_LEFT);
		TRANSLATEXMINUS = init("debug.translateX-", Keyboard.KEY_RIGHT);
		TRANSLATEZPLUS = init("debug.translateZ+", Keyboard.KEY_DIVIDE);
		TRANSLATEZMINUS = init("debug.translateZ-", Keyboard.KEY_MULTIPLY);
		SCALE_UP = init("debug.scaleUp", Keyboard.KEY_ADD);
		SCALE_DOWN = init("debug.scaleDown", Keyboard.KEY_MINUS);
		PRINT = init("debug.printCode", Keyboard.KEY_END);
		RESET = init("debug.reset", Keyboard.KEY_BACK);
		MinecraftForge.EVENT_BUS.register(new Handler());
		initialized = true;
	}
	
	private static KeyBinding init(String name, int key) {
		KeyBinding bind = new KeyBinding(name, key, Pubgmc.MOD_ID);
		ClientRegistry.registerKeyBinding(bind);
		return bind;
	}
	
	public static void translateModel()
	{
		GlStateManager.translate(x, y, z);
	}
	
	public static void scaleModel()
	{
		GlStateManager.scale(scale, scale, scale);
	}
	
	public static void printCode()
	{
		System.out.println("========[ START ]========");
		if(scale != 1f)
		{
			System.out.println("GlStateManager.scale("+scale+", "+scale+", "+scale+");");
		}
		
		if(x != 0 || y != 0 || z != 0)
		{
			System.out.println("GlStateManager.translate("+x+", "+y+", "+z+");");
		}
		System.out.println("========[ END ]========");
	}
	
	public static void transformAllModifications()
	{
		scaleModel();
		translateModel();
	}
	
	public static void resetAllValues()
	{
		x = 0;
		y = 0;
		z = 0;
		scale = 1f;
	}
	
	public static void debugRedDot(ModelGun gun, ItemStack stack) {
		gun.renderRedDot(x, y, z, scale, stack);
	}
	
	public static void debugHolo(ModelGun gun, ItemStack stack) {
		gun.renderHolo(x, y, z, scale, stack);
	}
	
	public static void debug2x(ModelGun gun, ItemStack stack) {
		gun.renderScope2X(x, y, z, scale, stack);
	}
	
	public static void debug4x(ModelGun gun, ItemStack stack) {
		gun.renderScope4X(x, y, z, scale, stack);
	}
	
	public static void debug8x(ModelGun gun, ItemStack stack) {
		gun.renderScope8X(x, y, z, scale, stack);
	}
	
	public static void debug15x(ModelGun gun, ItemStack stack) {
		gun.renderScope15X(x, y, z, scale, stack);
	}
	
	public static void debugPistolSilencer(ModelGun gun, ItemStack stack) {
		gun.renderPistolSilencer(x, y, z, scale, stack);
	}
	
	public static void debugSMGSilencer(ModelGun gun, ItemStack stack) {
		gun.renderSMGSilencer(x, y, z, scale, stack);
	}
	
	public static void debugARSilencer(ModelGun gun, ItemStack stack) {
		gun.renderARSilencer(x, y, z, scale, stack);
	}
	
	public static void debugSRSilencer(ModelGun gun, ItemStack stack) {
		gun.renderSniperSilencer(x, y, z, scale, stack);
	}
	
	public static class Handler {
		
		@SubscribeEvent
		public void handleInput(InputEvent.KeyInputEvent e) {
			if(!ModelDebugger.initialized) {
				return;
			}
			EntityPlayer sp = Minecraft.getMinecraft().player;
			if(ModelDebugger.TRANSLATEXPLUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.x += ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.x += ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.TRANSLATEXMINUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.x -= ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.x -= ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.TRANSLATEYPLUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.y += ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.y += ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.TRANSLATEYMINUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.y -= ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.y -= ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.TRANSLATEZPLUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.z += ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.z += ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.TRANSLATEZMINUS.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.z -= ModelDebugger.SMALL_MODIFIER;
	    		}
	    		else
	    		{
	    			ModelDebugger.z -= ModelDebugger.MODIFIER;
	    		}
	    	}
	    	
	    	if(ModelDebugger.PRINT.isPressed())
	    	{
	    		ModelDebugger.printCode();
	    	}
	    	
	    	if(ModelDebugger.RESET.isPressed())
	    	{
	    		ModelDebugger.resetAllValues();
	    	}
	    	
	    	if(ModelDebugger.SCALE_UP.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.scale += 0.01f;
	    		}
	    		
	    		else ModelDebugger.scale += 0.1f;
	    	}
	    	
	    	if(ModelDebugger.SCALE_DOWN.isPressed())
	    	{
	    		if(sp.isSneaking())
	    		{
	    			ModelDebugger.scale -= 0.01f;
	    		}
	    		
	    		else ModelDebugger.scale -= 0.1f;
	    	}
		}
	}
}
