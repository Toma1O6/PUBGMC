package com.toma.pubgmc.client;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.util.ImageUtil;
import com.toma.pubgmc.util.math.ZonePos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO 1.7.0 Version
@SideOnly(Side.CLIENT)
public class Minimap {

    private static final Minimap INSTANCE = new Minimap();
    private final ResourceLocation MAP_ICONS = null;
    private ResourceLocation resourceLocation;
    private DynamicTexture texture;

    public IGameData mapData;

    public static Minimap getMinimap() {
        return INSTANCE;
    }

    public void createMinimap(IGameData gameData, World world) {
        this.mapData = gameData;
        // TODO make static size
        int size = gameData.getMapSize();
        this.texture = new DynamicTexture(size*2, size*2);
        int[] data = texture.getTextureData();
        BlockPos center = mapData.getMapCenter();
        ZonePos start = new ZonePos(center.getX() - size, center.getZ() - size);
        Pubgmc.logger.info("Generating new minimap");
        long startTime = System.currentTimeMillis();
        for(int x = 0; x < size*2; x++) {
            for(int z = 0; z < size*2; z++) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(start.x + x, 256, start.z + z));
                IBlockState state = world.getBlockState(pos.down());
                int color = state.getBlock().getMapColor(state, world, pos).colorValue;
                texture.getTextureData()[(x*size) + z] = color;
            }
        }
        texture.updateDynamicTexture();
        resourceLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(Pubgmc.MOD_ID + "/map/game_map", this.texture);
        Pubgmc.logger.info("Minimap generated, took {} ms", System.currentTimeMillis() - startTime);
    }

    public void renderMinimap(Minecraft mc, ScaledResolution resolution) {
        if (mapData == null) {
            return;
        }
        ImageUtil.drawFullScreenImage(mc, resolution, resourceLocation, false);
    }

    public void renderIcon(Minecraft mc, BlockPos pos, IconType iconType) {
        ImageUtil.drawImageWithUV(mc, MAP_ICONS, 0, 0, 16, 16, 0, this.getOffset(true, iconType), 1, this.getOffset(false, iconType), true);
    }

    public ResourceLocation getMap() {
        return resourceLocation;
    }

    private double getOffset(boolean start, IconType iconType) {
        return start ? (iconType.ordinal()*16)/(IconType.values().length*16D) : ((iconType.ordinal()+1)*16)/(IconType.values().length*16D);
    }

    private enum IconType {
        PLAYER, DROP, FLARE
    }
}
