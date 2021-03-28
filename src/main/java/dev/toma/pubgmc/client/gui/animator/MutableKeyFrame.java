package dev.toma.pubgmc.client.gui.animator;

import dev.toma.pubgmc.client.animation.interfaces.KeyFrame;
import net.minecraft.util.math.Vec3d;

public class MutableKeyFrame implements KeyFrame {

    float endpoint;
    Vec3d move = Vec3d.ZERO;
    Vec3d rotate = Vec3d.ZERO;
    Vec3d positionStart = Vec3d.ZERO;
    Vec3d rotationStart = Vec3d.ZERO;

    public static MutableKeyFrame fromImmutable(KeyFrame frame) {
        MutableKeyFrame keyFrame = new MutableKeyFrame();
        keyFrame.setEndpoint(frame.endPoint());
        keyFrame.setMove(frame.moveTarget());
        keyFrame.setRotate(frame.rotateTarget());
        return keyFrame;
    }

    @Override
    public float endPoint() {
        return endpoint;
    }

    public void setEndpoint(float endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Vec3d moveTarget() {
        return move;
    }

    public void setMove(Vec3d move) {
        this.move = move;
    }

    @Override
    public Vec3d rotateTarget() {
        return rotate;
    }

    public void setRotate(Vec3d rotate) {
        this.rotate = rotate;
    }

    @Override
    public Vec3d getPositionStart() {
        return positionStart;
    }

    @Override
    public void setPositionStart(Vec3d positionStart) {
        this.positionStart = positionStart;
    }

    @Override
    public Vec3d getRotationStart() {
        return rotationStart;
    }

    @Override
    public void setRotationStart(Vec3d rotationStart) {
        this.rotationStart = rotationStart;
    }
}
