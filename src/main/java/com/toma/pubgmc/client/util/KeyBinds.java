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
