package dev.toma.pubgmc.client.models.atachments;

import dev.toma.pubgmc.common.items.attachment.ItemAttachment;
import net.minecraft.client.model.ModelBase;

public abstract class ModelAttachment<I extends ItemAttachment> extends ModelBase {

    public abstract void render(float aimPct);
}
