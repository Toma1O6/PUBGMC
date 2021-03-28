package dev.toma.pubgmc.client.animation.interfaces;

import net.minecraft.util.math.Vec3d;

public interface KeyFrame {

    KeyFrame EMPTY_FRAME = () -> 0.0F;

    float endPoint();

    default Vec3d moveTarget() {
        return Vec3d.ZERO;
    }

    default Vec3d rotateTarget() {
        return Vec3d.ZERO;
    }

    default Vec3d getPositionStart() {
        return Vec3d.ZERO;
    }

    default Vec3d getRotationStart() {
        return Vec3d.ZERO;
    }

    default void setPositionStart(Vec3d positionStart) {
    }

    default void setRotationStart(Vec3d rotationStart) {
    }

    static KeyFrame move(float endpoint, Vec3d move) {
        return new Basic(endpoint, move);
    }

    static KeyFrame rotate(float endpoint, Vec3d move, Vec3d rotate) {
        return new Rotate(endpoint, move, rotate);
    }

    class Basic implements KeyFrame {

        final float endPoint;
        final Vec3d move;
        private Vec3d positionStart = Vec3d.ZERO;
        private Vec3d rotationStart = Vec3d.ZERO;

        Basic(float end, Vec3d move) {
            this.endPoint = end;
            this.move = move;
        }

        @Override
        public float endPoint() {
            return endPoint;
        }

        @Override
        public Vec3d moveTarget() {
            return move;
        }

        @Override
        public Vec3d getRotationStart() {
            return rotationStart;
        }

        @Override
        public Vec3d getPositionStart() {
            return positionStart;
        }

        @Override
        public void setRotationStart(Vec3d rotationStart) {
            this.rotationStart = rotationStart;
        }

        @Override
        public void setPositionStart(Vec3d positionStart) {
            this.positionStart = positionStart;
        }
    }

    class Rotate extends Basic {

        final Vec3d rotate;

        Rotate(float end, Vec3d move, Vec3d rotate) {
            super(end, move);
            this.rotate = rotate;
        }

        @Override
        public Vec3d rotateTarget() {
            return rotate;
        }
    }
}
