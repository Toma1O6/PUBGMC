package dev.toma.pubgmc.common.commands.core.arg;

public final class ArgumentReader {

    private final String[] arguments;
    private int index;

    public ArgumentReader(String[] arguments) {
        this.arguments = arguments;
    }

    public String peek() {
        if (index >= arguments.length) {
            return null;
        }
        return arguments[index];
    }

    public String read() {
        return arguments[index++];
    }
}
