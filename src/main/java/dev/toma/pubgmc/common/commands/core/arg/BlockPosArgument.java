package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

public class BlockPosArgument extends ArgumentType<BlockPos> {

    public static final char RELATIVE_POSITION_CHAR = '~';
    private static final BlockPosArgument INSTANCE = new BlockPosArgument();

    private BlockPosArgument() {
        super(BlockPosArgument::parse);
    }

    public static BlockPosArgument blockpos() {
        return INSTANCE;
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        ICommandSender sender = context.getSender();
        BlockPos pos = context.getTargettedPosition() != null ? context.getTargettedPosition() : sender.getPosition();
        List<String> values = new ArrayList<>();
        switch (context.getCursor()) {
            case 0:
                values.add(String.valueOf(pos.getX()));
                break;
            case 1:
                values.add(String.valueOf(pos.getY()));
                break;
            case 2:
                values.add(String.valueOf(pos.getZ()));
                break;
        }
        values.add("~");
        return values;
    }

    private static BlockPos parse(ArgumentContext<BlockPos> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        BlockPos senderPosition = context.getSender().getPosition();
        int x = readPosition(reader, senderPosition::getX);
        int y = readPosition(reader, senderPosition::getY);
        int z = readPosition(reader, senderPosition::getZ);
        return new BlockPos(x, y, z);
    }

    private static int readPosition(ArgumentReader reader, IntSupplier positionProvider) throws CommandException {
        String position = ensureHasNext(reader);
        if (position.startsWith(String.valueOf(RELATIVE_POSITION_CHAR))) {
            int relativeOffset = getIntSimple(position.substring(1), 0);
            return positionProvider.getAsInt() + relativeOffset;
        }
        return getIntSimple(position);
    }

    private static String ensureHasNext(ArgumentReader reader) throws CommandException {
        if (reader.peek() == null) {
            throw new SyntaxErrorException("Invalid position coordinate format, must be made of 3 integers!");
        }
        return reader.read();
    }

    private static int getIntSimple(String value) throws CommandException {
        return getIntSimple(value, null);
    }

    private static int getIntSimple(String value, @Nullable Integer defaultValue) throws CommandException {
        try {
            if (value.isEmpty() && defaultValue != null) {
                return defaultValue;
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new SyntaxErrorException("Not a valid number: " + nfe.getMessage());
        }
    }
}
