package dev.toma.pubgmc.common.commands.core.arg;

import dev.toma.pubgmc.common.commands.core.SuggestionProvider;
import net.minecraft.command.CommandException;
import net.minecraft.command.SyntaxErrorException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapLookupArgument<K, V> extends ArgumentType<V> {

    private final Map<K, V> map;
    private final KeyMapper<K> mapper;
    private final List<String> suggestions;

    private MapLookupArgument(Map<K, V> map, KeyMapper<K> mapper) {
        super(MapLookupArgument::parse);
        this.map = map;
        this.mapper = mapper;
        this.suggestions = map.keySet().stream()
                .map(mapper::mapFromKey)
                .collect(Collectors.toList());
    }

    public static <K, V> MapLookupArgument<K, V> map(Map<K, V> map, KeyMapper<K> mapper) {
        return new MapLookupArgument<>(map, mapper);
    }

    @Override
    public List<String> suggest(SuggestionProvider.Context context) {
        return suggestions;
    }

    private static <K, V> V parse(ArgumentContext<V> context) throws CommandException {
        ArgumentReader reader = context.getReader();
        MapLookupArgument<K, V> argument = context.castArgument();
        String rawKey = reader.read();
        K key = argument.mapper.remapKey(rawKey);
        if (!argument.map.containsKey(key)) {
            throw new SyntaxErrorException("No such element found: " + rawKey);
        }
        return argument.map.get(key);
    }

    @FunctionalInterface
    public interface KeyMapper<K> {

        K remapKey(String value) throws CommandException;

        default String mapFromKey(K key) {
            return key.toString();
        }
    }
}
