package dev.toma.pubgmc.common.commands.core;

public enum DefaultExecutorPropagationStrategy {

    ROOT,
    LAST_NODE;

    public boolean shouldReadNodeExecutors() {
        return this == LAST_NODE;
    }
}
