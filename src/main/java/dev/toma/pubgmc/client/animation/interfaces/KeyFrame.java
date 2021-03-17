package dev.toma.pubgmc.client.animation.interfaces;

import net.minecraft.util.math.Vec3d;

public interface KeyFrame {

    KeyFrame EMPTY_FRAME = () -> 0;

    float endPoint();

    default Vec3d moveTarget() {
        return Vec3d.ZERO;
    }

    default Vec3d rotateTarget() {
        return Vec3d.ZERO;
    }

    static KeyFrame move(float endpoint, Vec3d move) {
        return new Basic(endpoint, move);
    }

    class Basic implements KeyFrame {

        final float endPoint;
        final Vec3d move;

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
    }
}
