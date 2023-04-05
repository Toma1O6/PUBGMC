package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;

import java.util.Arrays;
import java.util.List;

public class BooleanArgument extends ArgumentType<Boolean> {

    private static final BooleanArgument INSTANCE = new BooleanArgument();

    private BooleanArgument() {
        super(BooleanArgument::parse);
    }

    public static BooleanArgument booleanArgument() {
        return INSTANCE;
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return Arrays.asList("true", "false");
    }

    private static boolean parse(ArgumentContext<Boolean> context) {
        String value = context.getReader().read();
        return Boolean.parseBoolean(value);
    }
}
