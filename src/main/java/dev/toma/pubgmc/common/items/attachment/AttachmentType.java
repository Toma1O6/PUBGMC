package dev.toma.pubgmc.common.items.attachment;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public final class AttachmentType<I extends ItemAttachment> {

    public static AttachmentType<?>[] allTypes = new AttachmentType[0];

    public static final AttachmentType<ItemMuzzle> MUZZLE = new AttachmentType<>("Muzzle", 26, 45);
    public static final AttachmentType<ItemGrip> GRIP = new AttachmentType<>("Grip", 44, 88);
    public static final AttachmentType<ItemMagazine> MAGAZINE = new AttachmentType<>("Magazine", 80, 88);
    public static final AttachmentType<ItemStock> STOCK = new AttachmentType<>("Stock", 134, 45);
    public static final AttachmentType<ItemScope> SCOPE = new AttachmentType<>("Scope", 80, 15);

    private final int index;
    private final String name;
    private final String slotTexture;
    private final int x;
    private final int y;
    private final String localizationKey;

    public AttachmentType(String name, int slotX, int slotY) {
        this(name, name.toLowerCase(Locale.ROOT), slotX, slotY);
    }

    public AttachmentType(String name, String slotTexture, int slotX, int slotY) {
        this.name = name;
        this.localizationKey = "gun.attachment.type." + name.toLowerCase(Locale.ROOT);
        this.slotTexture = "pubgmc:textures/items/" + slotTexture + ".png";
        this.x = slotX;
        this.y = slotY;
        this.index = allTypes.length;
        addToArray(this);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public String getTranslatedName() {
        return I18n.format(this.localizationKey);
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

    static void addToArray(AttachmentType<?> type) {
        AttachmentType<?>[] array = new AttachmentType[allTypes.length + 1];
        System.arraycopy(allTypes, 0, array, 0, allTypes.length);
        array[array.length - 1] = type;
        allTypes = array;
    }
}
