package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.CommandContext;
import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorArgument extends ArgumentType<Integer> {

    private static final Pattern REGEX_RGB = Pattern.compile("(#|0x|0X|0)?[0-9a-fA-F]{1,6}");
    private static final Pattern REGEX_ARGB = Pattern.compile("(#|0x|0X|0)?[0-9a-fA-F]{1,8}");
    private static final List<String> PREFIX_LIST = Arrays.asList("#", "0x", "0X", "0");
    private final boolean argb;

    private ColorArgument(boolean argb) {
        super(ColorArgument::parse);
        this.argb = argb;
    }

    public static ColorArgument rgb() {
        return new ColorArgument(false);
    }

    public static ColorArgument argb() {
        return new ColorArgument(true);
    }

    public static int getColor(String key, CommandContext context) throws CommandException {
        return context.getArgumentMandatory(key);
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return PREFIX_LIST;
    }

    private static int parse(ArgumentContext<Integer> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        String input = reader.read();
        ColorArgument argument = context.castArgument();
        Pattern pattern = argument.argb ? REGEX_ARGB : REGEX_RGB;
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new SyntaxErrorException("Invalid color format! Must follow " + pattern + " pattern");
        }
        String colorInput = matcher.group();
        try {
            long value = Long.decode(colorInput);
            return (int) value;
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("Invalid format: " + e.getMessage());
        }
    }
}
