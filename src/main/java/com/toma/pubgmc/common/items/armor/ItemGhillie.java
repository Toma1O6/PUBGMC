package com.toma.pubgmc.common.items.armor;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.init.PMCRegistry;
import com.toma.pubgmc.init.PMCRegistry.PMCItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemGhillie extends ItemArmor {
    public ItemGhillie(String name) {
        super(PMCRegistry.ToolMaterials.GHILLIE_SUIT, 1, EntityEquipmentSlot.LEGS);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(Pubgmc.pmcitemstab);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GREEN + "Right click to get whole ghillie armor set!");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEHELMET));
            playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEBODY));
            playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIELEGS));
            playerIn.addItemStackToInventory(new ItemStack(PMCItems.GHILLIEBOOTS));

            if (!playerIn.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return null;
        //return Pubgmc.MOD_ID+":textures/models/armor/ghillie_suit_layer_2.png";
    }

    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
    	/*if(!itemStack.isEmpty()) {
    		if(itemStack.getItem() == PMCItems.GHILLIE_SUIT) {
    			boolean flag = armorSlot == EntityEquipmentSlot.CHEST;
    			ModelGhillie ghillie = new ModelGhillie();
    			ghillie.bipedHead.showModel = flag;
    			ghillie.bipedBody.showModel = flag;
    			ghillie.bipedLeftArm.showModel = flag;
    			ghillie.bipedRightArm.showModel = flag;
    			ghillie.bipedLeftLeg.showModel = flag;
    			ghillie.bipedRightLeg.showModel = flag;
    			ghillie.isChild = _default.isChild;
    			ghillie.isSneak = _default.isSneak;
    			ghillie.isRiding = _default.isRiding;
    			ghillie.leftArmPose = _default.leftArmPose;
    			ghillie.rightArmPose = _default.rightArmPose;
    			return ghillie;
    		}
    	}*/
        return null;
    }
}
