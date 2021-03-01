package dev.toma.pubgmc.init;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.models.BakedModelGun;
import dev.toma.pubgmc.client.renderer.WeaponTEISR;
import dev.toma.pubgmc.common.items.guns.GunBase;
import dev.toma.pubgmc.event.GunModelAttachEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientRegistry {

    @SubscribeEvent
    public static void bakeModels(ModelBakeEvent e) {
        ModelResourceLocation location;
        for (int i = 0; i < GunBase.GUNS.size(); i++) {
            GunBase gun = GunBase.GUNS.get(i);
            location = new ModelResourceLocation(gun.getRegistryName(), "inventory");
            e.getModelRegistry().putObject(location, new BakedModelGun());
        }
    }

    @SubscribeEvent
    public static void onModelAttach(GunModelAttachEvent e) {
        GunBase g = e.getGun();

        switch (e.getName()) {
            case "flare_gun":
                e.attachModel(e.getTEISR().flareGun);
                break;
            case "p92":
                e.attachModel(e.getTEISR().p92);
                break;
            case "p1911":
                e.attachModel(e.getTEISR().p1911);
                break;
            case "p18c":
                e.attachModel(e.getTEISR().p18c);
                break;
            case "r1895":
                e.attachModel(e.getTEISR().r1895);
                break;
            case "r45":
                e.attachModel(e.getTEISR().r45);
                break;
            case "scorpion":
                e.attachModel(e.getTEISR().scorpion);
                break;
            case "deagle":
                e.attachModel(e.getTEISR().deagle);
                break;
            case "win94":
                e.attachModel(e.getTEISR().win94);
                break;
            case "sawed_off":
                e.attachModel(e.getTEISR().sawedOff);
                break;
            case "s1897":
                e.attachModel(e.getTEISR().s1897);
                break;
            case "s686":
                e.attachModel(e.getTEISR().s686);
                break;
            case "s12k":
                e.attachModel(e.getTEISR().s12k);
                break;
            case "microuzi":
                e.attachModel(e.getTEISR().microuzi);
                break;
            case "vector":
                e.attachModel(e.getTEISR().vector);
                break;
            case "bizon":
                e.attachModel(e.getTEISR().bizon);
                break;
            case "mp5k":
                e.attachModel(e.getTEISR().mp5k);
                break;
            case "tommy_gun":
                e.attachModel(e.getTEISR().tommygun);
                break;
            case "ump45":
                e.attachModel(e.getTEISR().ump);
                break;
            case "m16a4":
                e.attachModel(e.getTEISR().m16a4);
                break;
            case "m416":
                e.attachModel(e.getTEISR().m416);
                break;
            case "scar_l":
                e.attachModel(e.getTEISR().scar);
                break;
            case "qbz":
                e.attachModel(e.getTEISR().qbz);
                break;
            case "g36c":
                e.attachModel(e.getTEISR().g36c);
                break;
            case "aug":
                e.attachModel(e.getTEISR().aug);
                break;
            case "akm":
                e.attachModel(e.getTEISR().akm);
                break;
            case "beryl_m762":
                e.attachModel(e.getTEISR().m762);
                break;
            case "mk47_mutant":
                e.attachModel(e.getTEISR().mk47);
                break;
            case "groza":
                e.attachModel(e.getTEISR().groza);
                break;
            case "dp28":
                e.attachModel(e.getTEISR().dp28);
                break;
            case "m249":
                e.attachModel(e.getTEISR().m249);
                break;
            case "vss":
                e.attachModel(e.getTEISR().vss);
                break;
            case "mini14":
                e.attachModel(e.getTEISR().mini14);
                break;
            case "qbu":
                e.attachModel(e.getTEISR().qbu);
                break;
            case "sks":
                e.attachModel(e.getTEISR().sks);
                break;
            case "slr":
                e.attachModel(e.getTEISR().slr);
                break;
            case "mk14":
                e.attachModel(e.getTEISR().mk14);
                break;
            case "kar98k":
                e.attachModel(e.getTEISR().kar98k);
                break;
            case "m24":
                e.attachModel(e.getTEISR().m24);
                break;
            case "awm":
                e.attachModel(e.getTEISR().awm);
                break;
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        for (ResourceLocation rl : ForgeRegistries.ITEMS.getKeys()) {
            if (rl.getResourceDomain().equals(Pubgmc.MOD_ID))
                registerModel(ForgeRegistries.ITEMS.getValue(rl));
        }

        for (ResourceLocation rl : ForgeRegistries.BLOCKS.getKeys()) {
            if (rl.getResourceDomain().equals(Pubgmc.MOD_ID))
                registerModel(ForgeRegistries.BLOCKS.getValue(rl));
        }
    }

    static final WeaponTEISR TEISR = new WeaponTEISR();

    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

        if (item instanceof GunBase) {
            item.setTileEntityItemStackRenderer(TEISR);
            MinecraftForge.EVENT_BUS.post(new GunModelAttachEvent((GunBase) item, item.getRegistryName()));
        }
    }

    private static void registerModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
