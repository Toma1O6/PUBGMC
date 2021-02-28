package dev.toma.pubgmc.common.items.attachment;

import dev.toma.pubgmc.PMCTabs;
import dev.toma.pubgmc.common.items.PMCItem;

public abstract class ItemAttachment extends PMCItem {

    public ItemAttachment(String name) {
        super(name);
        setCreativeTab(PMCTabs.TAB_ACCESSORIES);
    }
}
