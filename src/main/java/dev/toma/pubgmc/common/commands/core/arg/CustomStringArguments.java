package dev.toma.pubgmc.common.commands.core.arg;

import java.util.ArrayList;
import java.util.List;

public class CustomStringArguments extends ArgumentType<String[]> {

    private static final CustomStringArguments INSTANCE = new CustomStringArguments();

    private CustomStringArguments() {
        super(CustomStringArguments::parse);
    }

    public static CustomStringArguments arguments() {
        return INSTANCE;
    }

    private static String[] parse(ArgumentContext<String[]> context) {
        ArgumentReader reader = context.getReader();
        List<String> strings = new ArrayList<>();
        while (reader.peek() != null) {
            strings.add(reader.read());
        }
        return strings.toArray(new String[0]);
    }
}
