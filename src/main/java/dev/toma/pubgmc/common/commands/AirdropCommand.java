package dev.toma.pubgmc.common.commands;

import dev.toma.pubgmc.common.commands.core.AbstractCommand;
import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.CommandNodeProvider;
import dev.toma.pubgmc.common.commands.core.CommandTree;
import dev.toma.pubgmc.common.commands.core.arg.BlockPosArgument;
import dev.toma.pubgmc.common.commands.core.arg.BooleanArgument;
import dev.toma.pubgmc.common.commands.core.arg.IntArgument;
import dev.toma.pubgmc.common.commands.core.arg.StringArgument;
import dev.toma.pubgmc.util.PUBGMCUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AirdropCommand extends AbstractCommand {

    private static final String ARG_FLARE = "flare";
    private static final String ARG_COUNT = "count";
    private static final String ARG_SPREAD = "spread";
    private static final String ARG_PLAYER = "player";
    private static final String ARG_POS = "pos";

    private static final CommandTree COMMAND_TREE = CommandTree.Builder.command("airdrop")
            .usage("/airdrop [flare<true,false>] [<count>|player|position] [<spread>|<playerName>|pos<x,y,z>]")
            .permissionLevel(2)
            .executes(AirdropCommand::spawnAirdrop)
            .node(
                    CommandNodeProvider.arg(ARG_FLARE, BooleanArgument.booleanArgument())
                            .executes(AirdropCommand::spawnAirdrop)
                            .node(
                                    CommandNodeProvider.arg(ARG_COUNT, IntArgument.range(1, 16))
                                            .executes(AirdropCommand::spawnAirdrop)
                                            .node(
                                                    CommandNodeProvider.arg(ARG_SPREAD, IntArgument.range(1, 96))
                                                            .executes(AirdropCommand::spawnAirdrop)
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("player")
                                            .executes(AirdropCommand::spawnAtPlayer)
                                            .node(
                                                    CommandNodeProvider.arg(ARG_PLAYER, StringArgument.stringArgument())
                                                            .executes(AirdropCommand::spawnAtPlayer)
                                                            .suggests(AbstractCommand::suggestPlayer)
                                            )
                            )
                            .node(
                                    CommandNodeProvider.literal("position")
                                            .executes(AirdropCommand::spawnAtPosition)
                                            .node(
                                                    CommandNodeProvider.arg(ARG_POS, BlockPosArgument.blockpos())
                                                            .executes(AirdropCommand::spawnAtPosition)
                                            )
                            )
            )
            .build();

    public AirdropCommand() {
        super(COMMAND_TREE);
    }

    private static void spawnAirdrop(CommandContext context) {
        ICommandSender sender = context.getSender();
        World world = sender.getEntityWorld();
        int count = getCount(context);
        Random random = world.rand;
        List<EntityPlayerMP> playerList = context.getServer().getPlayerList().getPlayers();
        int spread = getSpread(context);
        for (int i = 0; i < count; i++) {
            EntityPlayerMP player = PUBGMCUtil.randomListElement(playerList, random);
            int x = random.nextInt(spread) - random.nextInt(spread);
            int z = random.nextInt(spread) - random.nextInt(spread);
            int y = world.getHeight((int) player.posX + x, (int) player.posZ + z);
            BlockPos pos = new BlockPos(player.posX + x, y + 50, player.posZ + z);
            generateAirdropAt(world, pos, context);
        }
    }

    private static void spawnAtPlayer(CommandContext context) throws CommandException {
        Optional<String> playerNameArg = context.getArgument(ARG_PLAYER);
        BlockPos pos = context.getSender().getPosition();
        if (playerNameArg.isPresent()) {
            EntityPlayerMP player = getPlayerByName(context, playerNameArg.get());
            pos = player.getPosition();
        }
        generateAirdropAt(context.getSender().getEntityWorld(), new BlockPos(pos.getX(), pos.getY() + 50, pos.getZ()), context);
    }

    private static void spawnAtPosition(CommandContext context) {
        BlockPos pos = context.<BlockPos>getArgument(ARG_POS)
                .orElseGet(() -> context.getSender().getPosition());
        generateAirdropAt(context.getSender().getEntityWorld(), pos, context);
    }

    private static void generateAirdropAt(World world, BlockPos pos, CommandContext context) {
        PUBGMCUtil.spawnAirdrop(world, pos, isFlareAirdrop(context));
    }

    private static int getCount(CommandContext context) {
        return context.<Integer>getArgument(ARG_COUNT)
                .orElse(1);
    }

    private static int getSpread(CommandContext context) {
        return context.<Integer>getArgument(ARG_SPREAD)
                .orElse(1);
    }

    private static boolean isFlareAirdrop(CommandContext context) {
        return context.<Boolean>getArgument(ARG_FLARE)
                .orElse(false);
    }
}
