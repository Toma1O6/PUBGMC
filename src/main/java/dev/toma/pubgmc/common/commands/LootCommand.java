package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.common.blocks.BlockLootSpawner;
import dev.toma.pubgmc.common.capability.IWorldData;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.BlockPosArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.common.tileentity.TileEntityLootGenerator;
import dev.toma.pubgmc.network.PacketHandler;
import dev.toma.pubgmc.network.client.PacketDisplayLootSetupGui;
import dev.toma.pubgmc.util.TileEntityUtil;
import dev.toma.pubgmc.util.game.loot.ILootSpawner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LootCommand extends AbstractCommand {

    private static final String ARG_POS = "pos";
    private static final String ARG_RANGE = "range";

    private static final ITextComponent KEY_NO_LOOT_SPAWNERS = new TextComponentTranslation("commands.pubgmc.loot.no_loot_spawner");
    private static final ITextComponent[] HELP_COMPONENTS = {
        new TextComponentTranslation("commands.pubgmc.loot.help.header"),
        new TextComponentTranslation("commands.pubgmc.loot.help.generate"),
        new TextComponentTranslation("commands.pubgmc.loot.help.clear"),
        new TextComponentTranslation("commands.pubgmc.loot.help.show"),
        new TextComponentTranslation("commands.pubgmc.loot.help.reset"),
        new TextComponentTranslation("commands.pubgmc.loot.help.setup"),
        new TextComponentTranslation("commands.pubgmc.loot.help.destroy"),
        new TextComponentTranslation("commands.pubgmc.loot.help.count"),
    };

    private static final CommandTree COMMAND = CommandTree.Builder.command("loot")
            .usage("/loot [generate|clear|show|info|reset|setup|destroy|count|help]")
            .permissionLevel(2)
            .defaultExecutorPropagationStrategy(DefaultExecutorPropagationStrategy.LAST_NODE)
            .executes(LootCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("generate")
                            .executes(LootCommand::generateLoot)
                            .node(
                                    CommandNodeProvider.argument(ARG_POS, BlockPosArgument.blockpos())
                                            .node(
                                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("clear")
                            .executes(LootCommand::clearLoot)
                            .node(
                                    CommandNodeProvider.argument(ARG_POS, BlockPosArgument.blockpos())
                                            .node(
                                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("show")
                            .executes(LootCommand::showLootGenerators)
            )
            .node(
                    CommandNodeProvider.literal("reset")
                            .executes(LootCommand::resetLootConfiguration)
            )
            .node(
                    CommandNodeProvider.literal("setup")
                            .executes(LootCommand::setupLootConfiguration)
            )
            .node(
                    CommandNodeProvider.literal("destroy")
                            .executes(LootCommand::destroyLootGenerators)
                            .node(
                                    CommandNodeProvider.argument(ARG_POS, BlockPosArgument.blockpos())
                                            .node(
                                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("count")
                            .executes(LootCommand::countLootGenerators)
                            .node(
                                    CommandNodeProvider.argument(ARG_POS, BlockPosArgument.blockpos())
                                            .node(
                                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("help")
                            .executes(LootCommand::showHelp)
            )
            .build();

    public LootCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("You must specify more arguments!");
    }

    private static void generateLoot(CommandContext context) throws CommandException {
        String currentGameId = getGameData(context).getGameID();
        int affectedLootSpawnerCount = forEachLootGeneratorInRange(context, (tileEntity, lootSpawner) -> {
            lootSpawner.setGameHash(currentGameId);
            lootSpawner.onLoaded();
            TileEntityUtil.syncToClient(tileEntity);
        }, (tile, spawner) -> spawner.generateLootOnCommand());
        ITextComponent feedback = affectedLootSpawnerCount > 0
                ? new TextComponentTranslation("commands.pubgmc.loot.generated", affectedLootSpawnerCount)
                : KEY_NO_LOOT_SPAWNERS;
        context.getSender().sendMessage(feedback);
    }

    private static void clearLoot(CommandContext context) throws CommandException {
        int affectedLootSpawnerCount = forEachLootGeneratorInRange(context, (tileEntity, lootSpawner) -> {
            TileEntityLootGenerator lootGenerator = (TileEntityLootGenerator) tileEntity;
            lootGenerator.clear();
            TileEntityUtil.syncToClient(lootGenerator);
        }, (tile, spawner) -> tile instanceof TileEntityLootGenerator);
        ITextComponent feedback = affectedLootSpawnerCount > 0
                ? new TextComponentTranslation("commands.pubgmc.loot.cleared", affectedLootSpawnerCount)
                : KEY_NO_LOOT_SPAWNERS;
        context.getSender().sendMessage(feedback);
    }

    private static void showLootGenerators(CommandContext context) throws CommandException {
        World world = context.getSender().getEntityWorld();
        forEachLootGeneratorInRange(context, (tileEntity, lootSpawner) -> {
            TileEntityLootGenerator lootGenerator = (TileEntityLootGenerator) tileEntity;
            IBlockState state = world.getBlockState(tileEntity.getPos());
            int tier = state.getValue(BlockLootSpawner.LOOT);
            ItemStack fillStack = getDisplayItemByTier(tier);
            for (int i = 0; i < lootGenerator.getSizeInventory(); i++) {
                lootGenerator.setInventorySlotContents(i, fillStack.copy());
            }
            TileEntityUtil.syncToClient(lootGenerator);
        }, (tile, spawner) -> tile instanceof TileEntityLootGenerator);
    }

    private static void resetLootConfiguration(CommandContext context) throws CommandException {
        IWorldData worldData = getWorldData(context);
        worldData.toggleAirdropWeapons(false);
        worldData.toggleAmmoLoot(true);
        worldData.toggleRandomAmmoCount(false);
        worldData.resetWeaponLootGeneration();
        worldData.setLootChanceMultiplier(1.0D);
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.loot.configuration_reset"));
    }

    private static void setupLootConfiguration(CommandContext context) throws CommandException {
        IWorldData worldData = getWorldData(context);
        if (context.getSender() instanceof EntityPlayerMP) {
            PacketHandler.sendToClient(new PacketDisplayLootSetupGui(worldData.serializeNBT()), (EntityPlayerMP) context.getSender());
        }
    }

    private static void destroyLootGenerators(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        int affectedCount = forEachLootGeneratorInRange(context, (tileEntity, lootSpawner) -> {
            world.destroyBlock(tileEntity.getPos(), false);
        }, (tile, spawner) -> spawner.generateLootOnCommand());
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.loot.destroyed", affectedCount));
    }

    private static void countLootGenerators(CommandContext context) throws CommandException {
        int count = getAllLoadedLootSpawnersByCriteria(context, (tile, spawner) -> spawner.generateLootOnCommand()).size();
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.loot.found", count));
    }

    private static void showHelp(CommandContext context) {
        ICommandSender sender = context.getSender();
        for (ITextComponent component : HELP_COMPONENTS) {
            sender.sendMessage(component);
        }
    }

    private static int forEachLootGeneratorInRange(CommandContext context, BiConsumer<TileEntity, ILootSpawner> consumer, BiPredicate<TileEntity, ILootSpawner> customFilter) throws CommandException {
        List<TileEntity> list = getAllLoadedLootSpawnersByCriteria(context, customFilter);
        list.forEach(te -> consumer.accept(te, (ILootSpawner) te));
        return list.size();
    }

    private static List<TileEntity> getAllLoadedLootSpawnersByCriteria(CommandContext context, BiPredicate<TileEntity, ILootSpawner> customFilter) throws CommandException {
        World world = context.getSender().getEntityWorld();
        BlockPos pos = context.<BlockPos>getArgument(ARG_POS)
                .orElse(null);
        Integer range = null;
        if (pos != null) {
            range = context.getArgumentMandatory(ARG_RANGE);
        }
        int rangeSq = range == null ? 0 : range * range;
        List<TileEntity> tileEntities = world.loadedTileEntityList;
        Predicate<TileEntity> typeFilter = te -> te instanceof ILootSpawner;
        Predicate<TileEntity> rangeFilter = te -> pos == null || pos.distanceSq(te.getPos()) <= rangeSq;
        Predicate<TileEntity> filter = te -> typeFilter.test(te) && rangeFilter.test(te) && customFilter.test(te, (ILootSpawner) te);
        return tileEntities.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    private static ItemStack getDisplayItemByTier(int tier) {
        switch (tier) {
            case 2:
                return new ItemStack(Items.DYE, 1, 10);
            case 1:
                return new ItemStack(Items.DYE, 1, 11);
            case 0:
            default:
                return new ItemStack(Items.DYE, 1, 1);
        }
    }
}
