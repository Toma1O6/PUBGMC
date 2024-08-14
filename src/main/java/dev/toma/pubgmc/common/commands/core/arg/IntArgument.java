package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;

import javax.annotation.Nullable;

public class IntArgument extends ArgumentType<Integer> {

    @Nullable
    private final Integer min, max;

    private IntArgument(@Nullable Integer min, @Nullable Integer max) {
        super(IntArgument::parse);
        this.min = min;
        this.max = max;
    }

    public static IntArgument unboundedInt() {
        return new IntArgument(null, null);
    }

    public static IntArgument min(int minValue) {
        return new IntArgument(minValue, null);
    }

    public static IntArgument max(int maxValue) {
        return new IntArgument(null, maxValue);
    }

    public static IntArgument range(int minValue, int maxValue) {
        return new IntArgument(minValue, maxValue);
    }

    private void performRangeValidation(int value) throws CommandException {
        if (min != null && value < min) {
            throw new SyntaxErrorException(String.format("Invalid number: %s must be bigger than %s!", value, min));
        }
        if (max != null && value > max) {
            throw new SyntaxErrorException(String.format("Invalid number: %s must be smaller than %s!", value, max));
        }
    }

    private static int parse(ArgumentContext<Integer> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        String value = reader.read();
        try {
            int intValue = Integer.parseInt(value);
            IntArgument intArgument = context.castArgument();
            intArgument.performRangeValidation(intValue);
            return intValue;
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException(value + " is not a valid integer!");
        }
    }
}
