package com.toma.pubgmc.api.objectives.types;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GameArea {

    private BlockPos center;
    private int radius;
    private AxisAlignedBB box;
    private String name;

    public GameArea(final BlockPos center, final int radius) {
        this.center = center;
        this.radius = radius;
        this.box = this.getBox();
    }

    public static NBTTagCompound createNBT(GameArea area) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("radius", area.radius);
        nbt.setTag("center", NBTUtil.createPosTag(area.center));
        return nbt;
    }

    public static GameArea getFromNBT(NBTTagCompound nbt) {
        BlockPos pos = NBTUtil.getPosFromTag(nbt);
        int radius = nbt.getInteger("radius");
        return new GameArea(pos, radius);
    }

    @SideOnly(Side.CLIENT)
    public void renderGameArea() {

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
}
