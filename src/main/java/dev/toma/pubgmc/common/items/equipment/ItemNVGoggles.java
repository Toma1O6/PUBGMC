package dev.toma.pubgmc.common.items.equipment;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.common.items.PMCItem;
import dev.toma.pubgmc.util.game.loot.LootManager;
import dev.toma.pubgmc.util.game.loot.LootType;
import net.minecraft.util.ResourceLocation;

public class ItemNVGoggles extends PMCItem implements NightVisionGoggles {

    private static final ResourceLocation TEXTURE = Pubgmc.getResource("textures/overlay/nv.png");
    private static final ResourceLocation ICON_OFF = Pubgmc.getResource("textures/overlay/nightvision_off.png");
    private static final ResourceLocation ICON_ON = Pubgmc.getResource("textures/overlay/nightvision_on.png");

    public ItemNVGoggles(String name) {
        super(name);
        this.setMaxStackSize(1);
        LootManager.register(LootType.ARMOR, new LootManager.LootEntry(this, 15, false));
    }

    @Override
    public ResourceLocation getOverlayTexture() {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getHotbarIconPath(boolean active) {
        return active ? ICON_ON : ICON_OFF;
    }

    @Override
    public float getBrightnessValue() {
        return 1.0F;
    }
}
