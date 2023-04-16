package dev.toma.pubgmc.common.commands.core;

public enum NodePropagationStrategy {

    ROOT,
    LAST_NODE;

    public boolean shouldReadNodeExecutors() {
        return this == LAST_NODE;
    }
}
