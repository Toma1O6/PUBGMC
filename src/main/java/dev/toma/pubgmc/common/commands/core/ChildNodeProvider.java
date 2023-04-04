package dev.toma.pubgmc.common.commands.core;

import java.util.Collection;

public interface ChildNodeProvider {

    CommandNode getChildNode(String value);

    Collection<CommandNode> listNodes();
}
