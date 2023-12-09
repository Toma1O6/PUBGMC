package dev.toma.pubgmc.common.items;

import dev.toma.pubgmc.PMCTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ItemSecretRoomKey extends PMCItem implements SecretRoomKey {

    public static final String DOOR_ID_NBT_KEY = "pubgmc.doorId";
    public static final String LOCATION_NAME_NBT_KEY = "pubgmc.location";

    public ItemSecretRoomKey(String name) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
    }

    public static void assignDoorData(ItemStack item, UUID doorKey, String locationName) {
        if (item.getTagCompound() == null) {
            item.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound nbt = item.getTagCompound();
        nbt.setString(LOCATION_NAME_NBT_KEY, locationName);
        nbt.setUniqueId(DOOR_ID_NBT_KEY, doorKey);
    }

    @Override
    public UUID getLinkedDoorId(ItemStack stack) {
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey(DOOR_ID_NBT_KEY)) {
            return null;
        }
        return stack.getTagCompound().getUniqueId(DOOR_ID_NBT_KEY);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            if (nbt.hasKey(LOCATION_NAME_NBT_KEY)) {
                tooltip.add(I18n.format("label.pubgmc.item.old_room_secret_key.location", nbt.getString(LOCATION_NAME_NBT_KEY)));
            }
        }
    }
}
