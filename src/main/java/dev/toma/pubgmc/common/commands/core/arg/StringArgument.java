package dev.toma.pubgmc.common.commands.core.arg;

import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;

import javax.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringArgument extends ArgumentType<String> {

    private final @Nullable Pattern pattern;
    private final @Nullable String error;

    private StringArgument(@Nullable Pattern pattern, @Nullable String error) {
        super(StringArgument::parse);
        this.pattern = pattern;
        this.error = pattern != null ? error : null;
    }

    public static StringArgument stringArgument() {
        return stringArgument(null, null);
    }

    public static StringArgument stringArgument(@Nullable Pattern pattern) {
        return stringArgument(pattern, null);
    }

    public static StringArgument stringArgument(@Nullable Pattern pattern, @Nullable String error) {
        return new StringArgument(pattern, error);
    }

    private static String parse(ArgumentContext<String> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        String value = reader.read();
        StringArgument argument = context.castArgument();
        if (argument.pattern != null) {
            Matcher matcher = argument.pattern.matcher(value);
            if (!matcher.matches()) {
                String error = argument.error != null
                        ? argument.error
                        : String.format("%s does not match /%s/ pattern", value, argument.pattern);
                throw new SyntaxErrorException(error);
            }
        }
        return value;
    }
}
