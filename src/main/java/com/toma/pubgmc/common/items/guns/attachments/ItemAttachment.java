package com.toma.pubgmc.common.items.guns.attachments;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.client.util.KeyBinds;
import com.toma.pubgmc.common.items.PMCItem;
import com.toma.pubgmc.init.PMCRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemAttachment extends PMCItem implements IAttachment {
    private final Type type;

    public ItemAttachment(String name, Type attachment) {
        super(name);
        setMaxStackSize(1);
        setCreativeTab(Pubgmc.TAB_ACCESSORIES);

        this.type = attachment;
    }

    @Override
    public Type getType() {
        return type;
    }

    /**
     * Get the ID of an attachment for NBT
     *
     * @param item - the item which is being checked
     * @return ID of an attachment
     */
    public int getID(Item item) {
        int id = 0;

        if (item == PMCRegistry.PMCItems.SILENCER_PISTOL || item == PMCRegistry.PMCItems.SILENCER_SMG || item == PMCRegistry.PMCItems.SILENCER_AR || item == PMCRegistry.PMCItems.SILENCER_SNIPER)
            id = 1;
        if (item == PMCRegistry.PMCItems.COMPENSATOR_SMG || item == PMCRegistry.PMCItems.COMPENSATOR_AR || item == PMCRegistry.PMCItems.COMPENSATOR_SNIPER)
            id = 2;
        if (item == PMCRegistry.PMCItems.RED_DOT) id = 1;
        if (item == PMCRegistry.PMCItems.HOLOGRAPHIC) id = 2;
        if (item == PMCRegistry.PMCItems.SCOPE2X) id = 3;
        if (item == PMCRegistry.PMCItems.SCOPE4X) id = 4;
        if (item == PMCRegistry.PMCItems.SCOPE8X) id = 5;
        if (item == PMCRegistry.PMCItems.SCOPE15X) id = 6;
        if (item == PMCRegistry.PMCItems.GRIP_VERTICAL) id = 1;
        if (item == PMCRegistry.PMCItems.GRIP_ANGLED) id = 2;
        if (item == PMCRegistry.PMCItems.QUICKDRAW_MAG_PISTOL || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SMG || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_AR || item == PMCRegistry.PMCItems.QUICKDRAW_MAG_SNIPER)
            id = 1;
        if (item == PMCRegistry.PMCItems.EXTENDED_MAG_PISTOL || item == PMCRegistry.PMCItems.EXTENDED_MAG_SMG || item == PMCRegistry.PMCItems.EXTENDED_MAG_AR || item == PMCRegistry.PMCItems.EXTENDED_MAG_SNIPER)
            id = 2;
        if (item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SMG || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_AR || item == PMCRegistry.PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)
            id = 3;
        if (item == PMCRegistry.PMCItems.BULLET_LOOPS_SHOTGUN || item == PMCRegistry.PMCItems.BULLET_LOOPS_SNIPER)
            id = 1;
        if (item == PMCRegistry.PMCItems.CHEEKPAD) id = 2;

        return id;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.BOLD + "Add attachment to weapon throught attachment GUI - press " + Keyboard.getKeyName(KeyBinds.ATTACHMENT.getKeyCode()));
        switch (type) {
            case SCOPE: {

                tooltip.add("Scopes are used for better accuracy on longer ranges.");
                break;
            }

            case MAGAZINE: {

                tooltip.add("Quickdraw magazine: Reduces reloading time by 30%");
                tooltip.add("Extended magazine: Increases bullet capacity for the weapon");
                tooltip.add("Extended quickdraw magazine: Reduces reloading time by 30% and increases bullet capacity.");
                break;
            }

            case GRIP: {

                tooltip.add("Vertical grip: Reduces AttachmentGripVertical recoil");
                tooltip.add("Angled grip: Reduces horizontal recoil");
                break;
            }

            case BARREL: {

                tooltip.add("Compensators greatly reduce both AttachmentGripVertical and horizontal recoil");
                break;
            }

            case STOCK: {

                tooltip.add("Bullet loops: Reduces reloading time by 30%");
                tooltip.add("Cheekpad: Slightly reduces both AttachmentGripVertical and horizontal recoil");
                break;
            }
        }
    }
}
