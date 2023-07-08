package dev.toma.pubgmc.api.game;

import java.util.Arrays;
import java.util.EnumSet;

public enum GenerationType {

    NONE,
    ITEMS,
    ENTITIES,
    CUSTOM;

    GenerationType() {
    }

    public static Context empty() {
        return create(NONE);
    }

    public static Context create(GenerationType... modes) {
        return new Context(modes);
    }

    public static final class Context {

        private final EnumSet<GenerationType> set;

        private Context(GenerationType... initialValues) {
            set = EnumSet.noneOf(GenerationType.class);
            if (initialValues.length == 0) {
                set.add(NONE);
            }
            set.addAll(Arrays.asList(initialValues));
        }

        public boolean isEmpty() {
            return has(NONE);
        }

        public boolean has(GenerationType mode) {
            return set.contains(mode);
        }
    }
}
