package dev.toma.pubgmc.common.entity.controllable;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityControllable extends Entity implements IControllable {

    protected byte encodedInput;

    public EntityControllable(World world) {
        super(world);
        this.ignoreFrustumCheck = false;
    }

    public double getSpeed() {
        return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
    }

    public void callMovementMethods() {
        if((encodedInput & 0b1) > 0 && (encodedInput & 0b10) == 0) {
            handleForward();
        } else if((encodedInput & 0b1) == 0 && (encodedInput & 0b10) > 0) {
            handleBackward();
        }
        if((encodedInput & 0b100) > 0 && (encodedInput & 0b1000) == 0) {
            handleRight();
        } else if((encodedInput & 0b100) == 0 && (encodedInput & 0b1000) > 0) {
            handleLeft();
        }
    }

    public void updatePre() {

    }

    public void updatePost() {

    }

    @Override
    public final void onUpdate() {
        this.callMovementMethods();
        this.updatePre();
        super.onUpdate();
        this.updatePost();
    }

    public void handleForward() {
    }

    public void handleBackward() {
    }

    public void handleRight() {
    }

    public void handleLeft() {
    }

    public final void reset() {
        this.encodedInput = 0;
    }

    @Override
    public void handle(byte inputs) {
        this.encodedInput = inputs;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int encode(GameSettings settings) {
        int raw = 0;
        raw |= (settings.keyBindForward.isKeyDown() ? 0b0001 : 0);
        raw |= (settings.keyBindBack.isKeyDown() ? 0b0010 : 0);
        raw |= (settings.keyBindRight.isKeyDown() ? 0b0100 : 0);
        raw |= (settings.keyBindLeft.isKeyDown() ? 0b1000 : 0);
        return raw;
    }

    public boolean hasTurnInput() {
        return this.encodedInput >= 0b0100;
    }

    public boolean hasMovementInput() {
        return this.hasInputOn(0b1) || this.hasInputOn(0b10);
    }

    public boolean hasInput() {
        return encodedInput > 0;
    }

    public boolean hasInputOn(int bit) {
        return (encodedInput & bit) > 0;
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    @Override
    public Entity getControllingPassenger() {
        return getPassengers().size() > 0 ? getPassengers().get(0) : null;
    }
}
