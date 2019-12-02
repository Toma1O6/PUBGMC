package com.toma.pubgmc.api.objectives.types;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameArea {

    public final AreaType areaType;
    private BlockPos center;
    private int radius;
    private AxisAlignedBB box;
    private String name;

    public GameArea(final AreaType type, final BlockPos center, final int radius) {
        this.areaType = type;
        this.center = center;
        this.radius = radius;
        this.box = this.getBox();
    }

    public static NBTTagCompound createNBT(GameArea area) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("radius", area.radius);
        nbt.setTag("center", NBTUtil.createPosTag(area.center));
        nbt.setString("type", area.areaType.toString());
        return nbt;
    }

    public static GameArea getFromNBT(NBTTagCompound nbt) {
        BlockPos pos = NBTUtil.getPosFromTag(nbt);
        int radius = nbt.getInteger("radius");
        AreaType areaType = Types.TYPE_MAP.get(new ResourceLocation(nbt.getString("type")));
        return new GameArea(areaType, pos, radius);
    }

    public BlockPos getRandomPos(World world) {
        int attempts = 10;
        int minX = this.center.getX() - radius;
        int minZ = this.center.getZ() - radius;
        int max = radius * 2;
        Random rand = Pubgmc.rng();
        while (attempts > 0) {
            --attempts;
            BlockPos pos = new BlockPos(minX + rand.nextInt(max), this.center.getY(), minZ + rand.nextInt(max));
            if(world.isAirBlock(pos) && world.isAirBlock(pos.up())) {
                return pos;
            }
        }
        return null;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    @SideOnly(Side.CLIENT)
    public void renderGameArea(float partialTicks) {
        this.getAreaType().render(this, partialTicks);
    }

    @SideOnly(Side.CLIENT)
    public void renderAABB(float r, float g, float b, float a, float partialTicks) {
        Entity player = Minecraft.getMinecraft().getRenderViewEntity();
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bb = tessellator.getBuffer();
        bb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bb.setTranslation(-x, -y, -z);
        bb.pos(box.minX, box.maxY - 2.5, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.maxY - 2.5, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.maxY - 2.5, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.maxY - 2.5, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.maxY - 2.5, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.maxY - 2.5, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.maxY - 2.5, box.minZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.maxY - 2.5, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY, box.maxZ).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY, box.minZ).color(r, g, b, a).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableCull();
        bb.setTranslation(0, 0, 0);
    }

    public List<EntityLivingBase> getEntitiesInArea(World world) {
        return world.getEntitiesWithinAABB(EntityLivingBase.class, this.box);
    }

    public boolean isInArea(EntityLivingBase entity) {
        return entity.getCollisionBoundingBox().intersects(this.box);
    }

    public boolean isInArea(BlockPos pos) {
        return pos.getX() >= this.box.minX && pos.getX() <= this.box.maxX && pos.getZ() >= this.box.minZ && pos.getZ() <= this.box.minZ && pos.getY() >= this.box.minY && pos.getY() <= this.box.maxY;
    }

    public boolean isLoaded(World world) {
        return world.isAreaLoaded(this.center, this.radius);
    }

    public void updateCenter(final BlockPos center) {
        this.center = center;
        this.box = this.getBox();
    }

    public void updateSize(final int size) {
        this.radius = size;
        this.box = this.getBox();
    }

    public AxisAlignedBB getBox() {
        return new AxisAlignedBB(this.center.getX() - this.radius, this.center.getY(), this.center.getZ() - this.radius, this.center.getX() + this.radius, this.center.getY() + 4, this.center.getZ() + this.radius);
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockPos getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public String getName() {
        return name;
    }

    public static class AreaType {

        private String name;

        public AreaType(ResourceLocation registryName, String name) {
            this.name = name;
            Types.TYPE_MAP.put(registryName, this);
        }

        @SideOnly(Side.CLIENT)
        public void render(GameArea area, float partialTicks) {

        }

        public String getDisplayName() {
            return name;
        }
    }

    public static final class Types {
        public static final Map<ResourceLocation, AreaType> TYPE_MAP = new HashMap<>();
        public static final AreaType BOMBSITE = new AreaType(new ResourceLocation(Pubgmc.MOD_ID, "bombsite"), "[BD]Bombsite") {
            @SideOnly(Side.CLIENT)
            @Override
            public void render(GameArea area, float partialTicks) {
                if(Game.isDebugMode) {
                    area.renderAABB(1f, 0f, 0f, 0.5F, partialTicks);
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
                    double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
                    double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
                    GlStateManager.pushMatrix();
                    GlStateManager.disableCull();
                    GlStateManager.rotate(180, 1, 0, 0);
                    GlStateManager.translate(-x, -y, -z);
                    GlStateManager.scale(0.25, 0.25, 0.25);
                    BlockPos pos = area.center;
                    GlStateManager.translate(pos.getX() + 0.5, pos.getY() + 3, pos.getZ() + 0.5);
                    Minecraft.getMinecraft().fontRenderer.drawString("Bombsite A", -3, 1, 0xFFFFFF);
                    GlStateManager.enableCull();
                    GlStateManager.popMatrix();
                }
            }
        };
        public static final AreaType BD_SPAWN = new AreaType(new ResourceLocation(Pubgmc.MOD_ID, "bd_spawn"), "[BD]Spawn Point");

    }
}
