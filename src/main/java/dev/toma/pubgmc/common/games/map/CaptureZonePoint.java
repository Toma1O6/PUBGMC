package dev.toma.pubgmc.common.games.map;

import dev.toma.pubgmc.api.game.map.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CaptureZonePoint extends GameMapPoint implements Bounds3 {

    private Vec3d nSize, pSize;
    @Nullable
    private String label;

    public CaptureZonePoint(BlockPos pointPosition, Vec3d nSize, Vec3d pSize, @Nullable String label) {
        super(pointPosition);
        this.nSize = nSize;
        this.pSize = pSize;
        this.label = label;
    }

    public Vec3d getLeftScale() {
        return nSize;
    }

    public Vec3d getRightScale() {
        return pSize;
    }

    public void setLabel(@Nullable String label) {
        this.label = label;
    }

    public void setDimensions(Vec3d left, Vec3d right) {
        this.nSize = left;
        this.pSize = right;
    }

    @Nullable
    public String getLabel() {
        return label;
    }

    @Override
    public boolean isWithin(double x, double y, double z) {
        BlockPos pos = getPointPosition();
        return  x >= pos.getX() + 0.5 + nSize.x && x <= pos.getX() + 0.5 + pSize.x &&
                y >= pos.getY() + 0.5 + nSize.y && y <= pos.getY() + 0.5 + pSize.y &&
                z >= pos.getZ() + 0.5 + nSize.z && z <= pos.getZ() + 0.5 + pSize.z;
    }

    @Override
    public GameMapPointType<?> getType() {
        return GameMapPoints.CAPTURE_ZONE;
    }

    public enum CaptureStatus {

        CAPTURED,
        CAPTURING,
        BLOCKED,
        BLOCKING,
        LOSING,
        STALEMATE;

        private final ITextComponent title;

        CaptureStatus() {
            this.title = new TextComponentTranslation("label.pubgmc.capture_status." + name().toLowerCase());
        }

        public ITextComponent getTitle() {
            return title;
        }
    }

    public static final class Serializer implements GameMapPointSerializer<CaptureZonePoint> {

        @Override
        public NBTTagCompound serializePointData(CaptureZonePoint point) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setDouble("nx", point.nSize.x);
            nbt.setDouble("ny", point.nSize.y);
            nbt.setDouble("nz", point.nSize.z);
            nbt.setDouble("px", point.pSize.x);
            nbt.setDouble("py", point.pSize.y);
            nbt.setDouble("pz", point.pSize.z);
            if (point.label != null && !point.label.isEmpty()) {
                nbt.setString("label", point.label);
            }
            return nbt;
        }

        @Override
        public CaptureZonePoint deserializePointData(BlockPos pointPosition, NBTTagCompound nbt) {
            Vec3d nSize = new Vec3d(nbt.getDouble("nx"), nbt.getDouble("ny"), nbt.getDouble("nz"));
            Vec3d pSize = new Vec3d(nbt.getDouble("px"), nbt.getDouble("py"), nbt.getDouble("pz"));
            String label = nbt.hasKey("label") ? nbt.getString("label") : null;
            return new CaptureZonePoint(pointPosition, nSize, pSize, label);
        }

        @Override
        public CaptureZonePoint createDefaultInstance(BlockPos pos, World world, GameMap map) {
            return new CaptureZonePoint(pos, new Vec3d(-5, -5, -5), new Vec3d(5, 5, 5), null);
        }
    }
}
