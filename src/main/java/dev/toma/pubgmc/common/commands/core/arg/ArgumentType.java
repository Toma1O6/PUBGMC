package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;

import java.util.Collections;
import java.util.List;

public class ArgumentType<T> {

    private final ArgumentParser<T> parser;

    public ArgumentType(ArgumentParser<T> parser) {
        this.parser = parser;
    }

    public ArgumentParser<T> getParser() {
        return parser;
    }

    public List<String> suggest(SuggestionProvider.Context context) {
        return Collections.emptyList();
    }

    public int getArgumentCount() {
        return 1;
    }
}
