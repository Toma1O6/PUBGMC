package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.util.math.BlockPos;

public class BlockPosArgument extends ArgumentType<BlockPos> {

    private static final BlockPosArgument INSTANCE = new BlockPosArgument();

    private BlockPosArgument() {
        super(BlockPosArgument::parse);
    }

    public static BlockPosArgument blockpos() {
        return INSTANCE;
    }

    private static BlockPos parse(ArgumentReader reader, ArgumentType<BlockPos> type) throws CommandException {
        // TODO ability to read relative position
        int x = getIntSimple(ensureHasNext(reader));
        int y = getIntSimple(ensureHasNext(reader));
        int z = getIntSimple(ensureHasNext(reader));
        return new BlockPos(x, y, z);
    }

    private static String ensureHasNext(ArgumentReader reader) throws CommandException {
        if (reader.peek() == null) {
            throw new SyntaxErrorException("Invalid position coordinate format, must be made of 3 integers!");
        }
        return reader.read();
    }

    private static int getIntSimple(String value) throws CommandException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new SyntaxErrorException(value + " is not a valid integer!");
        }
    }
}
