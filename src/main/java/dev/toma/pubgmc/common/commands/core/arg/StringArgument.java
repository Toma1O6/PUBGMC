package dev.toma.pubgmc.common.commands.core.arg;

public class StringArgument extends ArgumentType<String> {

    protected StringArgument() {
        super(StringArgument::parse);
    }

    public static StringArgument stringArgument() {
        return new StringArgument();
    }

    private static String parse(ArgumentContext<String> context) {
        return context.getReader().read();
    }
}
