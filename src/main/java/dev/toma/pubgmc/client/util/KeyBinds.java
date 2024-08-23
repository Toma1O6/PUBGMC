package dev.toma.pubgmc.client.util;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBinds {
    static String category = "key.category." + Pubgmc.MOD_ID;

    public static final KeyBinding RELOAD = new KeyBinding(Pubgmc.MOD_ID + ".key.reload", Keyboard.KEY_R, category);
    public static final KeyBinding FIREMODE = new KeyBinding(Pubgmc.MOD_ID + ".key.firemode", Keyboard.KEY_B, category);
    public static final KeyBinding NV = new KeyBinding(Pubgmc.MOD_ID + ".key.nv", Keyboard.KEY_N, category);
    public static final KeyBinding ATTACHMENT = new KeyBinding(Pubgmc.MOD_ID + ".key.attachment", Keyboard.KEY_P, category);
    public static final KeyBinding PRONE = new KeyBinding(Pubgmc.MOD_ID + ".key.prone", Keyboard.KEY_V, category);
    public static final KeyBinding GAME_MENU = new KeyBinding(Pubgmc.MOD_ID + ".key.game_menu", Keyboard.KEY_L, category);
    public static final KeyBinding EQUIPMENT_INVENTORY = new KeyBinding(Pubgmc.MOD_ID + ".key.equipment_inventory", Keyboard.KEY_I, category);

    public static void registerKeybinding() {
        ClientRegistry.registerKeyBinding(RELOAD);
        ClientRegistry.registerKeyBinding(FIREMODE);
        ClientRegistry.registerKeyBinding(NV);
        ClientRegistry.registerKeyBinding(ATTACHMENT);
        ClientRegistry.registerKeyBinding(PRONE);
        ClientRegistry.registerKeyBinding(GAME_MENU);
        ClientRegistry.registerKeyBinding(EQUIPMENT_INVENTORY);
    }
}
