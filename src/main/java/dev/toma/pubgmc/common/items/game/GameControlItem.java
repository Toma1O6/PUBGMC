package dev.toma.pubgmc.common.items.game;

import dev.toma.pubgmc.api.games.Game;
import dev.toma.pubgmc.api.games.GameObjectiveBased;
import dev.toma.pubgmc.api.objectives.types.GameArea;
import dev.toma.pubgmc.common.capability.IGameData;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketOpenObjectiveGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public final class GameControlItem extends PMCItem {

    private final RClickAction action;

    public GameControlItem(String name, RClickAction action) {
        super(name);
        this.action = action;
        this.addDescription("Used for game objectives", "Right click on ground in order to use", "Crouch for secondary function");
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        this.fixNBT(stack);
        this.action.click(worldIn, pos, stack, player, player.isSneaking() ? ClickContex.SNEAK : ClickContex.NORMAL);
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    private void fixNBT(ItemStack stack) {
        if(!stack.hasTagCompound()) {
            NBTTagCompound nbt = new NBTTagCompound();
            GameArea.AreaType areaType = GameArea.Types.BOMBSITE;
            nbt.setString("areaID", "pubgmc:bombsite");
            stack.setTagCompound(nbt);
        } else if(!stack.getTagCompound().hasKey("areaID")) {
            stack.getTagCompound().setString("areaID", "pubgmc:bombsite");
        }
    }

    public interface RClickAction {
        void click(World world, @Nullable BlockPos pos, ItemStack stack, EntityPlayer player, ClickContex ctx);
    }

    public enum ClickContex {
        NORMAL, SNEAK;

        public boolean isNormal() {
            return this == NORMAL;
        }
    }

    public static final class Actions {
        public static final RClickAction DEBUG = ((world, pos, stack, player, ctx) -> {
            if(!ctx.isNormal() || !world.isRemote) {
                return;
            }
            Game.isDebugMode = !Game.isDebugMode;
            player.sendStatusMessage(new TextComponentString("Debug mode: " + (Game.isDebugMode ? "ON" : "OFF")), true);
        });
        public static final RClickAction OBJECTIVE_ADD = ((world, pos, stack, player, ctx) -> {
            if(!world.isRemote) {
                if(ctx.isNormal()) {
                    Game g = getGame(world);
                    if(g instanceof GameObjectiveBased) {
                        GameObjectiveBased game = (GameObjectiveBased) g;
                        GameArea area = new GameArea(GameArea.Types.TYPE_MAP.get(new ResourceLocation(stack.getTagCompound().getString("areaID"))), pos, 5);
                        if(stack.hasDisplayName()) {
                            area.setName(stack.getDisplayName());
                        }
                        game.createObjective(world, pos, area);
                        player.sendStatusMessage(new TextComponentString("Added new area!"), true);
                    }
                } else {
                    PacketHandler.sendToClient(new PacketOpenObjectiveGui(stack), (EntityPlayerMP) player);
                }
            }
        });
        public static final RClickAction OBJECTIVE_REMOVE = ((world, pos, stack, player, ctx) -> {
            if(ctx.isNormal() && !world.isRemote) {
                Game game = getGame(world);
                if(game instanceof GameObjectiveBased) {
                    GameObjectiveBased g = (GameObjectiveBased) game;
                    boolean found = g.getObjectives().containsKey(pos);
                    if(!found) {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Nothing found here!"), true);
                        return;
                    }
                    g.getObjectives().remove(pos);
                    player.sendStatusMessage(new TextComponentString("Area has been removed!"), true);
                }
            }
        });
        public static final RClickAction OBJECTIVE_CHANGE_SIZE = ((world, pos, stack, player, ctx) -> {
            if(!world.isRemote) {
                int i = ctx.isNormal() ? 1 : -1;
                Game game = getGame(world);
                if(game instanceof GameObjectiveBased) {
                    GameObjectiveBased gameObjectiveBased = (GameObjectiveBased) game;
                    GameArea area = gameObjectiveBased.getObjectives().get(pos);
                    if(area == null) return;
                    area.updateSize(area.getRadius() + i);
                    player.sendStatusMessage(new TextComponentString("Area size updated to " + area.getRadius()), true);
                }
            }
        });

        private static Game getGame(World world) {
            return world.getCapability(IGameData.GameDataProvider.GAMEDATA, null).getCurrentGame();
        }
    }
}
