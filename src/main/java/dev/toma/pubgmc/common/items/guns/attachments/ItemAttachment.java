package dev.toma.pubgmc.common.items.guns.attachments;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.client.util.KeyBinds;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.init.PMCItems;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

// TODO rework completely
@SuppressWarnings("ConditionCoveredByFurtherCondition")
public class ItemAttachment extends PMCItem implements IAttachment {
    private final Type type;

    public ItemAttachment(String name, Type attachment) {
        super(name);
        setMaxStackSize(1);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
        LootManager.register(LootType.ATTACHMENT, new LootManager.LootEntry(this, 1, name.equalsIgnoreCase("scope15x")));
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

        if (item == PMCItems.SILENCER_PISTOL || item == PMCItems.SILENCER_SMG || item == PMCItems.SILENCER_AR || item == PMCItems.SILENCER_SNIPER)
            id = 1;
        if (item == PMCItems.COMPENSATOR_SMG || item == PMCItems.COMPENSATOR_AR || item == PMCItems.COMPENSATOR_SNIPER)
            id = 2;
        if (item == PMCItems.RED_DOT) id = 1;
        if (item == PMCItems.HOLOGRAPHIC) id = 2;
        if (item == PMCItems.SCOPE2X) id = 3;
        if (item == PMCItems.SCOPE4X) id = 4;
        if (item == PMCItems.SCOPE8X) id = 5;
        if (item == PMCItems.SCOPE15X) id = 6;
        if (item == PMCItems.GRIP_VERTICAL) id = 1;
        if (item == PMCItems.GRIP_ANGLED) id = 2;
        if (item == PMCItems.QUICKDRAW_MAG_PISTOL || item == PMCItems.QUICKDRAW_MAG_SMG || item == PMCItems.QUICKDRAW_MAG_AR || item == PMCItems.QUICKDRAW_MAG_SNIPER)
            id = 1;
        if (item == PMCItems.EXTENDED_MAG_PISTOL || item == PMCItems.EXTENDED_MAG_SMG || item == PMCItems.EXTENDED_MAG_AR || item == PMCItems.EXTENDED_MAG_SNIPER)
            id = 2;
        if (item == PMCItems.EXTENDED_QUICKDRAW_MAG_PISTOL || item == PMCItems.EXTENDED_QUICKDRAW_MAG_SMG || item == PMCItems.EXTENDED_QUICKDRAW_MAG_AR || item == PMCItems.EXTENDED_QUICKDRAW_MAG_SNIPER)
            id = 3;
        if (item == PMCItems.BULLET_LOOPS_SHOTGUN || item == PMCItems.BULLET_LOOPS_SNIPER)
            id = 1;
        if (item == PMCItems.CHEEKPAD) id = 2;

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
