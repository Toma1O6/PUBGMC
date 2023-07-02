package dev.toma.pubgmc.client.renderer.entity;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.client.layers.LayerBackpack;
import dev.toma.pubgmc.client.layers.LayerGhillie;
import dev.toma.pubgmc.client.layers.LayerNightVision;
import dev.toma.pubgmc.client.models.ModelAIPlayer;
import dev.toma.pubgmc.common.entity.bot.EntityAIPlayer;
import dev.toma.pubgmc.common.items.equipment.ItemGhillie;
import dev.toma.pubgmc.init.PMCItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

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
        this.addLayer(new LayerNightVision<>(this, e -> e, player -> (player.world.provider.getWorldTime() % 24000L) >= 13000L));
        this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAIPlayer entity) {
        return BOT_TEXTURES[entity.getVariant()];
    }
}
