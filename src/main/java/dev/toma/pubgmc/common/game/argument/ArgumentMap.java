package dev.toma.pubgmc.common.game.argument;

import dev.toma.pubgmc.DevUtil;
import dev.toma.pubgmc.api.common.game.IArgument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentMap {

    private final Map<ArgumentKey<?>, IArgument<?>> argumentMap;

    public ArgumentMap(Map<ArgumentKey<?>, IArgument<?>> map) {
        argumentMap = map;
    }

    public ArgumentMap(List<ArgumentKey<?>> keys) {
        argumentMap = DevUtil.make(new HashMap<>(), map -> {
            for (ArgumentKey<?> key : keys) {
                map.put(key, key.getDefaultValue().copy());
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <V> V getValue(ArgumentKey<V> key) {
        IArgument<V> arg = (IArgument<V>) argumentMap.get(key);
        if(arg == null || !key.isValid(arg)) {
            return key.getDefaultValue().get();
        }
        return arg.get();
    }
}
