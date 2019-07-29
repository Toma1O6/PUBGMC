package com.toma.pubgmc;

import com.toma.pubgmc.util.VehicleConfiguration;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Pubgmc.MOD_ID, name = Pubgmc.NAME + " Config")
public class ConfigPMC {
    @Name("Client-side only options")
    public static ClientConfig client = new ClientConfig();

    @Name("Common options")
    public static CommonConfig common = new CommonConfig();

    public enum ActionType {
        TOGGLE,
        HOLD;
    }

    public static class CommonConfig {
        @Name("World Settings")
        @Comment("All world related fields are here")
        public WorldSettings worldSettings = new WorldSettings();

        @Name("Player Settings")
        @Comment("All player related fields are here")
        public PlayerSettings playerSettings = new PlayerSettings();

        @Name("Weapon Settings")
        @Comment("All weapon related fiels are here")
        public WeaponSettings weaponSettings = new WeaponSettings();

        @Name("Vehicle Settings")
        @Comment("All vehicle related fiels are here")
        public VehicleSettings vehicleSettings = new VehicleSettings();
    }

    /**
     * Fields which won't be synced from server -> client on player join
     */
    public static class ClientConfig {
        @Name("Overlays")
        @Comment("Overlay rendering options")
        public OverlaySettings overlays = new OverlaySettings();

        @Name("Other")
        public OtherSettings other = new OtherSettings();

        @Name("Keybind settings")
        public KeySettings keys = new KeySettings();
    }

    public static class OtherSettings {
        @Config.Name("Messages on world join")
        @Config.Comment("You will receive message when you join world")
        public boolean enableMessagesSentOnJoin = true;

        @Config.Name("Loot render quality")
        @Config.Comment({"0 - OFF", "1 - Fast", "2 - Fancy"})
        @Config.RangeInt(min = 0, max = 2)
        @Config.RequiresMcRestart
        public int lootRenderType = 2;

        @Name("Enhanced sound")
        @Comment({"Enable improved sound physics for gun shots", "Currently does nothing!"})
        @RequiresMcRestart
        public boolean betterSound = true;
    }

    public static class KeySettings {
        @Name("Aiming mode")
        public ActionType aimStyle = ActionType.TOGGLE;
    }

    public static class WorldSettings {
        @Name("Airdrop loot generation type")
        @Comment({"Loot is being generated when airdrop is created", "0 - No loot", "1 - Only armor and healing items", "2 - Weapons"})
        @RangeInt(min = 0, max = 2)
        @RequiresWorldRestart
        public int airdropLootGen = 2;

        @Name("Gun loot generator")
        @Comment({"Enable gun loot generation.", "If false only healing items and grenades will be spawned"})
        @RequiresWorldRestart
        public boolean enableGunLoot = true;

        @Name("Airdrop dissapear range")
        @Comment("Define minimum player range from airdrop for making it despawn")
        @RangeInt(min = 10, max = 100)
        @RequiresWorldRestart
        public int aidropRange = 40;

        @Name("Enable Guns")
        @Comment("Use this to enable/disable weapons")
        @RequiresWorldRestart
        public boolean enableGuns = true;

        @Name("Plane fly height")
        @Comment("Set default height where planes will spawn")
        @RangeInt(min = 25, max = 256)
        @RequiresWorldRestart
        public int planeHeight = 150;

        @Name("Plane wait time")
        @Comment("Set time [in seconds] how long will plane wait after spawning. This is for selecting drop locations")
        @RangeInt(min = 0, max = 30)
        @RequiresWorldRestart
        public int planeWaitTime = 5;

        @Name("Title zone notification")
        @Comment("If true you will receive zone shrink notification through title instead of chat")
        public boolean zoneShrinkNotification = true;
    }

    public static class PlayerSettings {
        @Config.Name("Disable third person perspective")
        @Config.Comment("Enable/disable third person perspective")
        @RequiresWorldRestart
        public boolean enableTP = true;

        @Config.Name("Inventory limit")
        @Config.Comment("Your inventory will be limited based on your backpack level")
        @RequiresWorldRestart
        public boolean enableInventoryLimit = true;

        @Config.Name("Force brightness level")
        @Config.Comment("Using this you can force players to have defined level of brightness")
        @RequiresWorldRestart
        public boolean forceBrightness = false;

        @Config.Name("Brightness level")
        @Config.Comment({"Level of brightness all players will have", "You need to enable the Force brightness field!"})
        @Config.RangeInt(min = 0, max = 100)
        @RequiresWorldRestart
        public int brightness = 25;

        @Config.Name("Render player nametags")
        @Config.Comment("Use this to disable/enable nametag visibility")
        @Config.RequiresMcRestart
        public boolean renderPlayerNameTags = true;
    }

    public static class VehicleSettings {
        @Name("UAZ")
        public VehicleConfiguration uaz = new VehicleConfiguration(250F, 1.6F, 3.0F, 0.015F, 0.3F);

        @Name("Dacia")
        public VehicleConfiguration dacia = new VehicleConfiguration(200F, 2.35F, 3.3F, 0.01F, 0.3f);
    }

    public static class OverlaySettings {
        @Name("Use image overlay for boost rendering")
        @Comment("Your boost overlay will be rendered instead of the XP bar. If this is disabled, you'll be able to see numbers above hunger bar which will indicate your boost value")
        public boolean imageBoostOverlay = true;

        @Name("Boost overlay x-position offset")
        @Comment("Use this to adjust your boost overlay position if you have problems with it - this is horizontal movement")
        public int imgOverlayX = 0;

        @Name("Boost overlay y-position offset")
        @Comment("Use this to adjust your boost overlay position if you have problems with it - this is vertical movement")
        public int imgOverlayY = 0;

        @Name("Textured boost bar position x")
        @Comment("Use this to adjust position of the overlay")
        public int overlayX = 0;

        @Name("Textured boost bar position y")
        @Comment("Use this to adjust position of the overlay")
        public int overlayY = 0;

        @Name("Armor icons in HUD")
        @Comment("Icons indicating your state of gear will be rendered next to your hotbar")
        public boolean armorOverlayIcons = true;
    }

    public static class WeaponSettings {
        @Name("P92")
        @RequiresMcRestart
        public WeaponCFG p92 = new WeaponCFG(4f, 7, 0.015f, 4);

        @Name("P1911")
        @RequiresMcRestart
        public WeaponCFG p1911 = new WeaponCFG(5f, 7.25f, 0.01f, 5);

        @Name("P18C")
        @RequiresMcRestart
        public WeaponCFG p18c = new WeaponCFG(4f, 7, 0.015f, 4);

        @Name("R1895")
        @RequiresMcRestart
        public WeaponCFG r1895 = new WeaponCFG(8f, 7.5f, 0.01f, 5);

        @Name("R45")
        @RequiresMcRestart
        public WeaponCFG r45 = new WeaponCFG(6f, 7.25f, 0.01f, 5);

        @Name("Scorpion")
        @RequiresMcRestart
        public WeaponCFG scorpion = new WeaponCFG(4f, 7f, 0.015f, 4);

        @Name("Deagle")
        @RequiresMcRestart
        public WeaponCFG deagle = new WeaponCFG(12.5f, 9f, 0.015f, 4);

        @Name("Winchester-94")
        @RequiresMcRestart
        public WeaponCFG win94 = new WeaponCFG(10f, 12f, 0.008f, 7);

        @Name("Sawed-off")
        @RequiresMcRestart
        public WeaponCFG sawedoff = new WeaponCFG(3f, 5f, 0.175f, 0);

        @Name("S1897")
        @RequiresMcRestart
        public WeaponCFG s1897 = new WeaponCFG(4f, 5.5f, 0.175f, 0);

        @Name("S686")
        @RequiresMcRestart
        public WeaponCFG s686 = new WeaponCFG(4f, 5.5f, 0.175f, 0);

        @Name("S12K")
        @RequiresMcRestart
        public WeaponCFG s12k = new WeaponCFG(3.5f, 5.5f, 0.175f, 0);

        @Name("Micro uzi")
        @RequiresMcRestart
        public WeaponCFG microuzi = new WeaponCFG(4f, 8f, 0.02f, 4);

        @Name("UMP-45")
        @RequiresMcRestart
        public WeaponCFG ump45 = new WeaponCFG(5f, 8.5f, 0.02f, 5);

        @Name("PP-19 Bizon")
        @RequiresMcRestart
        public WeaponCFG bizon = new WeaponCFG(4f, 8f, 0.035f, 4);

        @Name("MP5K")
        @RequiresMcRestart
        public WeaponCFG mp5k = new WeaponCFG(4.0f, 8f, 0.035f, 4);

        @Name("Vector")
        @RequiresMcRestart
        public WeaponCFG vector = new WeaponCFG(4f, 8f, 0.035f, 4);

        @Name("Tommy-gun")
        @RequiresMcRestart
        public WeaponCFG tommygun = new WeaponCFG(5f, 8.5f, 0.02f, 5);

        @Name("M16A4")
        @RequiresMcRestart
        public WeaponCFG m16a4 = new WeaponCFG(8f, 12f, 0.005f, 8);

        @Name("M416")
        @RequiresMcRestart
        public WeaponCFG m416 = new WeaponCFG(8f, 12f, 0.0065f, 7);

        @Name("SCAR-L")
        @RequiresMcRestart
        public WeaponCFG scarl = new WeaponCFG(8f, 11f, 0.007f, 7);

        @Name("QBZ-95")
        @RequiresMcRestart
        public WeaponCFG qbz = new WeaponCFG(8f, 11f, 0.007f, 7);

        @Name("G36C")
        @RequiresMcRestart
        public WeaponCFG g36c = new WeaponCFG(8f, 11f, 0.0065f, 7);

        @Name("AUG")
        @RequiresMcRestart
        public WeaponCFG aug = new WeaponCFG(8f, 12f, 0.0065f, 7);

        @Name("AKM")
        @RequiresMcRestart
        public WeaponCFG akm = new WeaponCFG(9.5f, 9f, 0.025f, 7);

        @Name("Beryl M-762")
        @RequiresMcRestart
        public WeaponCFG m762 = new WeaponCFG(9f, 9.5f, 0.025f, 7);

        @Name("MK-47 Mutant")
        @RequiresMcRestart
        public WeaponCFG mk47 = new WeaponCFG(9.5f, 9f, 0.025f, 7);

        @Name("Groza")
        @RequiresMcRestart
        public WeaponCFG groza = new WeaponCFG(9.5f, 9f, 0.025f, 7);

        @Name("M249")
        @RequiresMcRestart
        public WeaponCFG m249 = new WeaponCFG(8f, 11f, 0.0065f, 6);

        @Name("DP-28")
        @RequiresMcRestart
        public WeaponCFG dp28 = new WeaponCFG(9.5f, 9f, 0.03f, 6);

        @Name("VSS")
        @RequiresMcRestart
        public WeaponCFG vss = new WeaponCFG(6f, 7f, 0.035f, 2);

        @Name("Mini-14")
        @RequiresMcRestart
        public WeaponCFG mini14 = new WeaponCFG(9f, 14f, 0.015f, 8);

        @Name("QBU")
        @RequiresMcRestart
        public WeaponCFG qbu = new WeaponCFG(9f, 14f, 0.015f, 8);

        @Name("SKS")
        @RequiresMcRestart
        public WeaponCFG sks = new WeaponCFG(10f, 10f, 0.035f, 7);

        @Name("SLR")
        @RequiresMcRestart
        public WeaponCFG slr = new WeaponCFG(11f, 10f, 0.035f, 7);

        @Name("MK-14 EBR")
        @RequiresMcRestart
        public WeaponCFG mk14 = new WeaponCFG(12.5f, 11f, 0.025f, 7);

        @Name("Kar98k")
        @RequiresMcRestart
        public WeaponCFG kar98k = new WeaponCFG(18f, 11f, 0.04f, 8);

        @Name("M24")
        @RequiresMcRestart
        public WeaponCFG m24 = new WeaponCFG(19f, 11.5f, 0.03f, 7);

        @Name("AWM")
        @RequiresMcRestart
        public WeaponCFG awm = new WeaponCFG(24f, 17f, 0.005f, 10);
    }

    @Mod.EventBusSubscriber(modid = Pubgmc.MOD_ID)
    public static class Synchronization {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent e) {
            if (e.getModID().equals(Pubgmc.MOD_ID)) {
                ConfigManager.sync(Pubgmc.MOD_ID, Type.INSTANCE);
            }
        }
    }

    public static class WeaponCFG {

        @Name("Damage")
        @Comment("Weapon damage")
        @RangeDouble(min = 1, max = 100)
        public float damage;

        @Name("Bullet velocity")
        @Comment("Bullet movement per tick")
        @RangeDouble(min = 0.1, max = 50)
        public float velocity;

        @Name("Gravity modifier")
        @Comment("Amount of -Y movement per tick")
        public float gravityModifier;

        @Name("Gravity apply time")
        @Comment("Amount of ticks to start applying gravity effect")
        public int gravityEffectStart;

        @Name("Horizontal recoil scale")
        @Comment("Multiplier which is applied to current weapon's recoil")
        public float recoilHorizontalMultiplier;

        @Name("Vertical recoil scale")
        @Comment("Multiplier which is applied to current weapon's recoil")
        public float recoilVerticalMultiplier;

        public WeaponCFG(float damage, float velocity, float gravity, int time) {
            this.damage = damage;
            this.velocity = velocity;
            this.gravityModifier = gravity;
            this.gravityEffectStart = time;
            this.recoilHorizontalMultiplier = 1f;
            this.recoilVerticalMultiplier = 1f;
        }

        public static void writeToBuf(ByteBuf toBuf, WeaponCFG config) {
            toBuf.writeFloat(config.damage);
            toBuf.writeFloat(config.velocity);
            toBuf.writeFloat(config.gravityModifier);
            toBuf.writeInt(config.gravityEffectStart);
            toBuf.writeFloat(config.recoilHorizontalMultiplier);
            toBuf.writeFloat(config.recoilVerticalMultiplier);
        }

        public static WeaponCFG readFromBuf(ByteBuf fromBuf) {
            WeaponCFG cfg = new WeaponCFG(0, 0, 0, 0);
            cfg.damage = fromBuf.readFloat();
            cfg.velocity = fromBuf.readFloat();
            cfg.gravityModifier = fromBuf.readFloat();
            cfg.gravityEffectStart = fromBuf.readInt();
            cfg.recoilHorizontalMultiplier = fromBuf.readFloat();
            cfg.recoilVerticalMultiplier = fromBuf.readFloat();
            return cfg;
        }

        @Override
        public String toString() {
            return "[Damage:" + damage + ", Velocity:" + velocity + ", Gravity Modifier:" + gravityModifier + ", Gravity Effect Time:" + gravityEffectStart + ", Recoil(V;H): [" + recoilVerticalMultiplier + ";" + recoilHorizontalMultiplier + "]]";
        }
    }
}
