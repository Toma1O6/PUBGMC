package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.api.game.GenerationType;
import dev.toma.pubgmc.api.game.Generator;
import dev.toma.pubgmc.common.commands.core.*;
import dev.toma.pubgmc.common.commands.core.arg.EnumArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.data.entity.EntityProviderManager;
import dev.toma.pubgmc.data.loot.LootManager;
import dev.toma.pubgmc.util.helper.GameHelper;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneratorCommand extends AbstractCommand {

    private static final String ARG_MODE = "mode";
    private static final String ARG_RANGE = "range";

    private static final CommandTree COMMAND = CommandTree.Builder.command("generator")
            .usage("/generator [generate|delete|reset|info|resetConfigs]")
            .permissionLevel(2)
            .defaultExecutorPropagationStrategy(NodePropagationStrategy.LAST_NODE)
            .executes(GeneratorCommand::executeDefault)
            .node(
                    CommandNodeProvider.literal("generate")
                            .executes(GeneratorCommand::generateLoot)
                            .node(
                                    CommandNodeProvider.argument(ARG_MODE, EnumArgument.enumeration(GenerationType.class))
                                            .node(
                                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                                            )
                            )
            )
            .node(
                    CommandNodeProvider.literal("destroy")
                            .executes(GeneratorCommand::removeLootGenerators)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                            )
            )
            .node(
                    CommandNodeProvider.literal("resetConfigs")
                            .node(
                                    CommandNodeProvider.literal("loot")
                                            .executes(GeneratorCommand::resetLootConfigs)
                            )
                            .node(
                                    CommandNodeProvider.literal("entity")
                                            .executes(GeneratorCommand::resetEntitySpawnConfigs)
                            )
            )
            .node(
                    CommandNodeProvider.literal("info")
                            .executes(GeneratorCommand::displayLootGeneratorInformation)
                            .node(
                                    CommandNodeProvider.argument(ARG_RANGE, IntArgument.min(1))
                            )
            )
            .build();

    public GeneratorCommand() {
        super(COMMAND);
    }

    private static void executeDefault(CommandContext context) throws CommandException {
        throw new WrongUsageException("Not enough arguments. See /help loot for more details");
    }

    private static void generateLoot(CommandContext context) {
        ICommandSender sender = context.getSender();
        List<Generator> list = getWithinRange(context);
        GenerationType generationType = context.<GenerationType>getArgument(ARG_MODE)
                .orElse(null);
        GenerationType.Context generationContext = generationType == null
                ? GenerationType.create(GenerationType.ITEMS, GenerationType.ENTITIES, GenerationType.CUSTOM)
                : GenerationType.create(generationType);
        for (Generator generator : list) {
            generator.generate(generationContext);
        }
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.generator.generate", list.size()));
    }

    private static void removeLootGenerators(CommandContext context) throws CommandException {
        List<Generator> list = getWithinRange(context);
        int count = 0;
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        for (Generator generator : list) {
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
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.generator.remove", count));
    }

    private static void resetLootConfigs(CommandContext context) throws CommandException {
        LootManager manager = LootManager.getInstance();
        try {
            manager.recreateData();
            context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.generator.reloaded_configs.loot"));
        } catch (Exception e) {
            throw new WrongUsageException("Error occurred: " + e.getMessage(), e);
        }
    }

    private static void resetEntitySpawnConfigs(CommandContext context) throws CommandException {
        EntityProviderManager manager = EntityProviderManager.INSTANCE;
        try {
            manager.recreateData();
            context.getSender().sendMessage(new TextComponentTranslation("commands.pubgmc.generator.reloaded_configs.entity"));
        } catch (Exception e) {
            throw new WrongUsageException("Error occurred: " + e.getMessage(), e);
        }
    }

    private static void displayLootGeneratorInformation(CommandContext context) throws CommandException {
        ICommandSender sender = context.getSender();
        List<Generator> list = getWithinRange(context);
        Object2IntMap<String> map = new Object2IntOpenHashMap<>();
        for (Generator generator : list) {
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
        sender.sendMessage(new TextComponentTranslation("commands.pubgmc.generator.info", total));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            sender.sendMessage(new TextComponentString("- " + name + ": " + count));
        }
    }

    private static List<Generator> getWithinRange(CommandContext context) {
        int range = context.<Integer>getArgument(ARG_RANGE).orElse(-1);
        ICommandSender sender = context.getSender();
        BlockPos origin = sender.getPosition();
        World world = sender.getEntityWorld();
        return GameHelper.mergeTileEntitiesAndEntitiesByRule(world, t -> t instanceof Generator, t -> (Generator) t)
                .filter(generator -> {
                    try {
                        return isWithinRange(range, origin, generator);
                    } catch (CommandException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    private static boolean isWithinRange(int range, BlockPos origin, Generator generator) throws CommandException {
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

    private static <T> T accept(Generator generator, Function<Entity, T> entityAcceptor, Function<TileEntity, T> tileEntityAcceptor) throws CommandException {
        if (generator instanceof TileEntity) {
            return tileEntityAcceptor.apply((TileEntity) generator);
        } else if (generator instanceof Entity) {
            return entityAcceptor.apply((Entity) generator);
        }
        throw new WrongUsageException("Invalid generator type");
    }
}
