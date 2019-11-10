package com.toma.pubgmc.event.client;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RenderItemInHandEvent extends Event {

    public final EntityLivingBase entity;
    public final ItemStack stack;
    private final EnumHandSide hand;

    public RenderItemInHandEvent(EntityLivingBase entity, ItemStack stack, EnumHandSide hand) {
        this.entity = entity;
        this.stack = stack;
        this.hand = hand;
    }

    public EnumHandSide getHand() {
        return hand;
    }
}
