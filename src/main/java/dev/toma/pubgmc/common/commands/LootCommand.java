package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.game.LootGenerator;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.EventDispatcher;
import dev.toma.pubgmc.util.helper.GameHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LootCommand extends AbstractCommand {

    private static final String ARG_RANGE = "range";

    private static final CommandTree COMMAND = CommandTree.Builder.command("loot")
            .usage("/loot [generate|clear|delete|info] <range>")
            .permissionLevel(2)
            .defaultExecutorPropagationStrategy(DefaultExecutorPropagationStrategy.LAST_NODE)
            .executes(LootCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("generate")
                            .executes(LootCommand::generateLoot)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.unboundedInt())
                            )
            )
            .node(
                    CommandNodeProvider.literal("clear")
                            .executes(LootCommand::clearLoot)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.unboundedInt())
                            )
            )
            .node(
                    CommandNodeProvider.literal("destroy")
                            .executes(LootCommand::removeLootGenerators)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.unboundedInt())
                            )
            )
            .node(
                    CommandNodeProvider.literal("info")
                            .executes(LootCommand::displayLootGeneratorInformation)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.unboundedInt())
                            )
            )
            .build();

    public LootCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help loot for more details");
    }

    private static void generateLoot(CommandContext context) throws CommandException {
        LootManager manager = LootManager.getInstance();
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        List<LootGenerator> list = getWithinRange(context);
        int itemCount = 0;
        for (LootGenerator generator : list) {
            String configId = generator.getLootConfigurationId();
            BlockPos pos = accept(generator, Entity::getPosition, TileEntity::getPos);
            List<ItemStack> loot = manager.generateFromConfiguration(configId, world, pos);
            generator.fillWithLoot(EventDispatcher.getModifiedLoot(generator, loot));
            itemCount += loot.stream()
                    .mapToInt(ItemStack::getCount)
                    .sum();
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.loot.generate", list.size(), itemCount));
    }

    private static void clearLoot(CommandContext context) {
        List<LootGenerator> list = getWithinRange(context);
        int count = list.size();
        list.forEach(gen -> gen.fillWithLoot(Collections.emptyList()));
        context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.loot.clear", count));
    }

    private static void removeLootGenerators(CommandContext context) throws CommandException {
        List<LootGenerator> list = getWithinRange(context);
        int count = 0;
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        for (LootGenerator generator : list) {
            accept(generator, entity -> {
                entity.setDead();
                return null;
            }, tileEntity -> {
                BlockPos pos = tileEntity.getPos();
                world.destroyBlock(pos, false);
                return null;
            });
            ++count;
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.loot.remove", count));
    }

    private static void displayLootGeneratorInformation(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        List<LootGenerator> list = getWithinRange(context);
        Object2IntMap<String> map = new Object2IntOpenHashMap<>();
        for (LootGenerator generator : list) {
            String name = accept(generator, Entity::getName, tileEntity -> {
                ITextComponent displayName = tileEntity.getDisplayName();
                if (displayName == null) {
                    return tileEntity.getBlockType().getLocalizedName();
                }
                return displayName.getFormattedText();
            });
            int prev = map.getInt(name);
            map.put(name, prev + 1);
        }
        int total = list.size();
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.loot.info", total));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            sender.sendMessage(new TextComponentString("- " + name + ": " + count));
        }
    }

    private static List<LootGenerator> getWithinRange(CommandContext context) {
        int range = context.<Integer>getArgument(ARG_RANGE).orElse(-1);
        ICommandSender sender = context.getSender();
        BlockPos origin = sender.getPosition();
        World world = sender.getEntityWorld();
        return GameHelper.mergeTileEntitiesAndEntitiesByRule(world, t -> t instanceof LootGenerator, t -> (LootGenerator) t)
                .filter(generator -> {
                    try {
                        return isWithinRange(range, origin, generator);
                    } catch (CommandException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    private static boolean isWithinRange(int range, BlockPos origin, LootGenerator generator) throws CommandException {
        if (range < 0) {
            return true;
        }
        return accept(generator, e -> isWithinRange(origin, e.getPosition(), range), t -> isWithinRange(origin, t.getPos(), range));
    }

    private static boolean isWithinRange(BlockPos origin, BlockPos target, int max) {
        int x = origin.getX() - target.getX();
        int y = origin.getY() - target.getY();
        int z = origin.getZ() - target.getZ();
        return (x * x + y * y + z * z) <= (max * max);
    }

    private static <T> T accept(LootGenerator generator, Function<Entity, T> entityAcceptor, Function<TileEntity, T> tileEntityAcceptor) throws CommandException {
        if (generator instanceof TileEntity) {
            return tileEntityAcceptor.apply((TileEntity) generator);
        } else if (generator instanceof Entity) {
            return entityAcceptor.apply((Entity) generator);
        }
        throw new WrongUsageException("Invalid generator type");
    }
}
