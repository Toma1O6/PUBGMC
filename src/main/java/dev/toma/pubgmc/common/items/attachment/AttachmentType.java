package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.util.ResourceLocation;

public final class AttachmentType<I> {

    public static AttachmentType<?>[] allTypes = new AttachmentType[0];

    public static final AttachmentType<Muzzle> MUZZLE = new AttachmentType<>("muzzle", 0, 0);
    public static final AttachmentType<Grip> GRIP = new AttachmentType<>("grip", 0, 0);
    public static final AttachmentType<Magazine> MAGAZINE = new AttachmentType<>("magazine", 0, 0);
    public static final AttachmentType<Stock> STOCK = new AttachmentType<>("stock", 0, 0);
    public static final AttachmentType<Scope> SCOPE = new AttachmentType<>("scope", 0, 0);

    final ResourceLocation slotTexture;
    final int x;
    final int y;

    public AttachmentType(String texture, int slotX, int slotY) {
        this(Pubgmc.getResource("textures/items/" + texture + ".png"), slotX, slotY);
    }

    public AttachmentType(ResourceLocation slotTexture, int slotX, int slotY) {
        this.slotTexture = slotTexture;
        this.x = slotX;
        this.y = slotY;
        this.addToArray();
    }

    public ResourceLocation getSlotTexture() {
        return slotTexture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void addToArray() {
        AttachmentType<?>[] array = new AttachmentType[allTypes.length + 1];
        System.arraycopy(allTypes, 0, array, 0, allTypes.length);
        array[array.length - 1] = this;
        allTypes = array;
    }
}
