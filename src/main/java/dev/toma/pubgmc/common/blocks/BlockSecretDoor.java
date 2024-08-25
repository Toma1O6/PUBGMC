package dev.toma.pubgmc.common.blocks;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.items.SecretRoomKey;
import dev.toma.pubgmc.common.tileentity.TileEntitySecretDoor;
import dev.toma.pubgmc.init.CommonRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class BlockSecretDoor extends BlockDoor {

    public BlockSecretDoor(String name, Material materialIn) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setBlockUnbreakable();
        disableStats();
        CommonRegistry.registerItemBlock(this, block -> {
            ItemDoor itemDoor = new ItemDoor(block);
            itemDoor.setUnlocalizedName(name);
            itemDoor.setCreativeTab(PMCTabs.TAB_BLOCKS);
            return itemDoor;
        });
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (worldIn.isRemote || state.getValue(OPEN)) {
            return false;
        }
        if (stack.getItem() instanceof SecretRoomKey) {
            SecretRoomKey key = (SecretRoomKey) stack.getItem();
            UUID uuid = key.getLinkedDoorId(stack);
            TileEntity tileEntity = worldIn.getTileEntity(getTileEntityPos(state, pos));
            if (tileEntity instanceof TileEntitySecretDoor) {
                TileEntitySecretDoor secretDoor = (TileEntitySecretDoor) tileEntity;
                if (playerIn.isCreative()) {
                    // assigning
                    secretDoor.assignDoorKey(uuid);
                    playerIn.sendStatusMessage(new TextComponentTranslation(uuid != null ? "label.pubgmc.secret_door.key_assigned" : "label.pubgmc.secret_door.key_unassigned"), true);
                } else if (secretDoor.test(uuid)) {
                    // unlocking
                    toggleDoor(worldIn, pos, true);
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(HALF) == EnumDoorHalf.LOWER;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return state.getValue(HALF) == EnumDoorHalf.LOWER ? new TileEntitySecretDoor() : null;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
            BlockPos blockpos = pos.down();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            if (iblockstate.getBlock() != this) {
                worldIn.setBlockToAir(pos);
            } else if (blockIn != this) {
                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
            }
        } else {
            boolean flag1 = false;
            BlockPos blockpos1 = pos.up();
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

            if (iblockstate1.getBlock() != this) {
                worldIn.setBlockToAir(pos);
                flag1 = true;
            }

            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
                worldIn.setBlockToAir(pos);
                flag1 = true;

                if (iblockstate1.getBlock() == this) {
                    worldIn.setBlockToAir(blockpos1);
                }
            }

            if (flag1) {
                if (!worldIn.isRemote) {
                    this.dropBlockAsItem(worldIn, pos, state, 0);
                }
            }
        }
    }

    public static BlockPos getTileEntityPos(IBlockState state, BlockPos pos) {
        return state.getValue(HALF) == EnumDoorHalf.UPPER ? pos.down() : pos;
    }
}
