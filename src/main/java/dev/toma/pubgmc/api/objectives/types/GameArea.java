package dev.toma.pubgmc.api.objectives.types;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.games.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

    public static final ResourceLocation GRADIENT = new ResourceLocation(Pubgmc.MOD_ID, "textures/world/white_gradient.png");
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
        if(area.hasName()) nbt.setString("name", area.getName());
        return nbt;
    }

    public static GameArea getFromNBT(NBTTagCompound nbt) {
        BlockPos pos = NBTUtil.getPosFromTag(nbt.getCompoundTag("center"));
        int radius = nbt.getInteger("radius");
        AreaType areaType = Types.TYPE_MAP.get(new ResourceLocation(nbt.getString("type")));
        GameArea area = new GameArea(areaType, pos, radius);
        if(nbt.hasKey("name")) area.setName(nbt.getString("name"));
        return area;
    }

    public boolean hasName() {
        return name != null;
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
    public void renderGameArea(double x, double y, double z) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        this.getAreaType().render(x, y, z, this, bufferBuilder);
    }

    @SideOnly(Side.CLIENT)
    public void renderAABB(float r, float g, float b, float a, BufferBuilder bb) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableCull();
        Minecraft.getMinecraft().getTextureManager().bindTexture(GRADIENT);
        bb.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bb.pos(box.minX, box.minY + 0.75, box.minZ).tex(1, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.75, box.minZ).tex(0, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.0, box.minZ).tex(0, 1).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.0, box.minZ).tex(1, 1).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.75, box.maxZ).tex(1, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.75, box.maxZ).tex(0, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.0, box.maxZ).tex(0, 1).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.0, box.maxZ).tex(1, 1).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.75, box.minZ).tex(1, 0).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.75, box.maxZ).tex(0, 0).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.0, box.maxZ).tex(0, 1).color(r, g, b, a).endVertex();
        bb.pos(box.minX, box.minY + 0.0, box.minZ).tex(1, 1).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.75, box.minZ).tex(1, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.75, box.maxZ).tex(0, 0).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.0, box.maxZ).tex(0, 1).color(r, g, b, a).endVertex();
        bb.pos(box.maxX, box.minY + 0.0, box.minZ).tex(1, 1).color(r, g, b, a).endVertex();
        Entity player = Minecraft.getMinecraft().getRenderViewEntity();
        bb.sortVertexData((float)player.posX, (float)player.posY, (float)player.posZ);
        Tessellator.getInstance().draw();
        GlStateManager.disableBlend();
    }

    @SideOnly(Side.CLIENT)
    public void renderAreaName(double x, double y, double z, int height, int aarrggbb) {
        if(!this.hasName()) {
            return;
        }
        Entity player = Minecraft.getMinecraft().getRenderViewEntity();
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate(-x, -y, -z);
        GlStateManager.translate(this.center.getX() + 0.5, this.center.getY() + 2.5, this.center.getZ() + 0.5);
        GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(player.rotationYaw, 0, 1.0F, 0);
        GlStateManager.rotate(-player.rotationPitch, 1, 0, 0);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        fontRenderer.drawString(this.name, -fontRenderer.getStringWidth(this.name) / 2, -height, aarrggbb);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
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
        if(size > 0 && size < 32) {
            this.radius = size;
            this.box = this.getBox();
        }
    }

    public AxisAlignedBB getBox() {
        return new AxisAlignedBB(this.center.getX() + 0.5 - this.radius, this.center.getY() + 1, this.center.getZ() + 0.5 - this.radius, this.center.getX() + 0.5 + this.radius, this.center.getY() + 5, this.center.getZ() + 0.5 + this.radius);
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

        private final ResourceLocation location;
        private String name;

        public AreaType(ResourceLocation registryName, String name) {
            this.location = registryName;
            this.name = name;
            Types.TYPE_MAP.put(registryName, this);
        }

        @SideOnly(Side.CLIENT)
        public void render(double x, double y, double z, GameArea area, BufferBuilder bufferBuilder) {
        }

        public String getDisplayName() {
            return name;
        }

        @Override
        public String toString() {
            return location.toString();
        }
    }

    public static final class Types {
        public static final Map<ResourceLocation, AreaType> TYPE_MAP = new HashMap<>();
        public static final AreaType BOMBSITE = new AreaType(new ResourceLocation(Pubgmc.MOD_ID, "bombsite"), "[BD] Bombsite") {
            @SideOnly(Side.CLIENT)
            @Override
            public void render(double x, double y, double z, GameArea area, BufferBuilder bufferBuilder) {
                area.renderAreaName(x, y, z, 50, 0xFFFFFFFF);
                if(Game.isDebugMode) {
                    bufferBuilder.setTranslation(-x, -y, -z);
                    area.renderAABB(1f, 0f, 0f, 1.0F, bufferBuilder);
                    bufferBuilder.setTranslation(0, 0, 0);

                }
            }
        };
        public static final AreaType CT_SPAWN = new AreaType(new ResourceLocation(Pubgmc.MOD_ID, "ct_spawn"), "[BD] CT spawn");
        public static final AreaType T_SPAWN = new AreaType(new ResourceLocation(Pubgmc.MOD_ID, "t_spawn"), "[BD] T spawn");
    }
}
