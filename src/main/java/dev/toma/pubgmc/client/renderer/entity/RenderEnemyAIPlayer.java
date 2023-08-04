package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerGhillie;
import dev.toma.pubgmc.client.layers.LayerNightVision;
import dev.toma.pubgmc.client.models.ModelAIPlayer;
import dev.toma.pubgmc.common.entity.EntityAIPlayer;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderEnemyAIPlayer extends RenderBiped<EntityAIPlayer> {

    private static final ResourceLocation[] BOT_TEXTURES = {
            new ResourceLocation(Pubgmc.MOD_ID, "textures/entity/bot_0.png"),
            new ResourceLocation(Pubgmc.MOD_ID, "textures/entity/bot_1.png"),
            new ResourceLocation(Pubgmc.MOD_ID, "textures/entity/bot_2.png"),
            new ResourceLocation(Pubgmc.MOD_ID, "textures/entity/bot_3.png")
    };

    public RenderEnemyAIPlayer(RenderManager manager) {
        super(manager, new ModelAIPlayer(), 0.5F);
        this.addLayer(new LayerGhillie<>(this, e -> e));
        this.addLayer(new LayerBackpack<>(this, e -> e));
        this.addLayer(new LayerNightVision<>(this, e -> e, player -> {
            long time = player.world.provider.getWorldTime() % 24000L;
            return time >= 13000L && time <= 23000;
        }));
        this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAIPlayer entity) {
        return BOT_TEXTURES[entity.getVariant()];
    }

    @Override
    protected boolean canRenderName(EntityAIPlayer entity) {
        if (entity.getCurrentGameId().equals(GameHelper.DEFAULT_UUID)) {
            return false;
        }
        EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
        boolean flag = !entity.isInvisibleToPlayer(entityplayersp);
        boolean basePredicate = Minecraft.isGuiEnabled() && entity != renderManager.renderViewEntity && flag && !entity.isBeingRidden();
        return basePredicate && (entity.getAlwaysRenderNameTagForRender() || entity.hasCustomName());
    }
}
