package dev.toma.pubgmc.init;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.client.event.RegisterGameRendererEvent;
import dev.toma.pubgmc.api.client.event.RegisterMapPointRendererEvent;
import dev.toma.pubgmc.client.games.BattleRoyaleGameRenderer;
import dev.toma.pubgmc.client.games.DominationGameRenderer;
import dev.toma.pubgmc.client.games.FFAGameRenderer;
import dev.toma.pubgmc.client.models.BakedModelGun;
import dev.toma.pubgmc.client.renderer.poi.CaptureZoneRenderer;
import dev.toma.pubgmc.client.renderer.poi.PointOfInterestRenderer;
import dev.toma.pubgmc.client.renderer.poi.SubMapPointRenderer;
import dev.toma.pubgmc.client.renderer.poi.TeamSpawnerRenderer;
import dev.toma.pubgmc.common.games.GameTypes;
import dev.toma.pubgmc.common.games.map.GameMapPoints;
import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientRegistry {

    @SubscribeEvent
    public static void bakeModels(ModelBakeEvent e) {
        BakedModelGun instance = new BakedModelGun();
        ForgeRegistries.ITEMS.getValuesCollection().stream()
                .filter(it -> it instanceof GunBase)
                .forEach(it -> {
                    ModelResourceLocation mrl = new ModelResourceLocation(it.getRegistryName(), "inventory");
                    e.getModelRegistry().putObject(mrl, instance);
                });
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

    @SubscribeEvent
    public static void registerGameRenderers(RegisterGameRendererEvent event) {
        event.registerRenderer(GameTypes.BATTLE_ROYALE, new BattleRoyaleGameRenderer());
        event.registerRenderer(GameTypes.FFA, new FFAGameRenderer());
        event.registerRenderer(GameTypes.DOMINATION, new DominationGameRenderer());
    }

    @SubscribeEvent
    public static void registerPoiRenderers(RegisterMapPointRendererEvent event) {
        event.registerRenderer(GameMapPoints.POINT_OF_INTEREST, new PointOfInterestRenderer());
        event.registerRenderer(GameMapPoints.CAPTURE_ZONE, new CaptureZoneRenderer());
        event.registerRenderer(GameMapPoints.TEAM_SPAWNER, new TeamSpawnerRenderer());
        event.registerRenderer(GameMapPoints.PARTIAL_PLAY_AREA, new SubMapPointRenderer());
    }

    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    private static void registerModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
