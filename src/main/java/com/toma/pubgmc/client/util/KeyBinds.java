package com.toma.pubgmc.client.util;

import org.lwjgl.input.Keyboard;

import com.toma.pubgmc.Pubgmc;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBinds 
{
	static String category = "key.category." + Pubgmc.MOD_ID;
	
	public static final KeyBinding RELOAD = new KeyBinding(Pubgmc.MOD_ID + ".key.reload", Keyboard.KEY_R, category);
	public static final KeyBinding FIREMODE = new KeyBinding(Pubgmc.MOD_ID + ".key.firemode", Keyboard.KEY_B, category);
	public static final KeyBinding NV = new KeyBinding(Pubgmc.MOD_ID + ".key.nv", Keyboard.KEY_N, category);
	public static final KeyBinding ATTACHMENT = new KeyBinding(Pubgmc.MOD_ID + ".key.attachment", Keyboard.KEY_P, category);
	public static final KeyBinding CHANGE_SCOPETYPE = new KeyBinding(Pubgmc.MOD_ID + ".key.scopetype", Keyboard.KEY_PRIOR, category);
	public static final KeyBinding CHANGE_SCOPECOLOR = new KeyBinding(Pubgmc.MOD_ID + ".key.scopecolor", Keyboard.KEY_NEXT, category);
	public static final KeyBinding PRONE = new KeyBinding(Pubgmc.MOD_ID + ".key.prone", Keyboard.KEY_V, category);
	
	//Debug
	//ONLY FOR ADJUSTING THE WEAPONS POSITIONS AND ATTACHMENTS ON THEM
	public static final KeyBinding TRANSLATEYPLUS = new KeyBinding("debug.translateY+", Keyboard.KEY_UP, category);
	public static final KeyBinding TRANSLATEYMINUS = new KeyBinding("debug.translateY-", Keyboard.KEY_DOWN, category);
	public static final KeyBinding TRANSLATEXPLUS = new KeyBinding("debug.translateX+", Keyboard.KEY_LEFT, category);
	public static final KeyBinding TRANSLATEXMINUS = new KeyBinding("debug.translateX-", Keyboard.KEY_RIGHT, category);
	public static final KeyBinding TRANSLATEZPLUS = new KeyBinding("debug.translateZ+", Keyboard.KEY_DIVIDE, category);
	public static final KeyBinding TRANSLATEZMINUS = new KeyBinding("debug.translateZ-", Keyboard.KEY_MULTIPLY, category);
	public static final KeyBinding SCALE_UP = new KeyBinding("debug.scaleUp", Keyboard.KEY_ADD, category);
	public static final KeyBinding SCALE_DOWN = new KeyBinding("debug.scaleDown", Keyboard.KEY_MINUS, category);
	public static final KeyBinding PRINT = new KeyBinding("debug.printCode", Keyboard.KEY_END, category);
	public static final KeyBinding RESET = new KeyBinding("debug.reset", Keyboard.KEY_BACK, category);
	
	public static void registerKeybinding()
	{
		ClientRegistry.registerKeyBinding(RELOAD);
		ClientRegistry.registerKeyBinding(FIREMODE);
		ClientRegistry.registerKeyBinding(NV);
		ClientRegistry.registerKeyBinding(ATTACHMENT);
		ClientRegistry.registerKeyBinding(CHANGE_SCOPETYPE);
		ClientRegistry.registerKeyBinding(CHANGE_SCOPECOLOR);
		//ClientRegistry.registerKeyBinding(PRONE);
	}
}
