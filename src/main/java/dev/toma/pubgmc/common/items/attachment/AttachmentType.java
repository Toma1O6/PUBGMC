package dev.toma.pubgmc.common.items.attachment;

public final class AttachmentType<I extends ItemAttachment> {

    public static final AttachmentType<ItemMuzzle> MUZZLE = new AttachmentType<>("Muzzle", 26, 45);
    public static final AttachmentType<ItemGrip> GRIP = new AttachmentType<>("Grip", 44, 88);
    public static final AttachmentType<ItemMagazine> MAGAZINE = new AttachmentType<>("Magazine", 80, 88);
    public static final AttachmentType<ItemStock> STOCK = new AttachmentType<>("Stock", 134, 45);
    public static final AttachmentType<ItemScope> SCOPE = new AttachmentType<>("Scope", 80, 15);
    public static AttachmentType<?>[] allTypes = new AttachmentType[0];
    final int index;
    final String name;
    final String slotTexture;
    final int x;
    final int y;

    public AttachmentType(String name, int slotX, int slotY) {
        this(name, name.toLowerCase(), slotX, slotY);
    }

    public AttachmentType(String name, String slotTexture, int slotX, int slotY) {
        this.name = name;
        this.slotTexture = "pubgmc:textures/items/" + slotTexture + ".png";
        this.x = slotX;
        this.y = slotY;
        this.index = allTypes.length;
        addToArray(this);
    }

    static void addToArray(AttachmentType<?> type) {
        AttachmentType<?>[] array = new AttachmentType[allTypes.length + 1];
        System.arraycopy(allTypes, 0, array, 0, allTypes.length);
        array[array.length - 1] = type;
        allTypes = array;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getSlotTexture() {
        return slotTexture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
