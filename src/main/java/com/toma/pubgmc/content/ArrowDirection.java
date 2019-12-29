package com.toma.pubgmc.content;

import net.minecraft.client.renderer.BufferBuilder;

public enum ArrowDirection {

    UP((xStart, yStart, xEnd, yEnd, builder) -> {
        float diff = xEnd - xStart;
        builder.pos(xStart, yEnd, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xStart + diff/2, yStart, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xEnd, yEnd, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    }),
    DOWN((xStart, yStart, xEnd, yEnd, builder) -> {
        float diff = xEnd - xStart;
        builder.pos(xStart, yStart, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xStart + diff/2, yEnd, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xEnd, yStart, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    }),
    RIGHT((xStart, yStart, xEnd, yEnd, builder) -> {
        float diff = yEnd - yStart;
        builder.pos(xStart, yStart, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xEnd, yStart + diff/2, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xStart, yEnd, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();

    }),
    LEFT((xStart, yStart, xEnd, yEnd, builder) -> {
        float diff = yEnd - yStart;
        builder.pos(xEnd, yStart, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xStart, yStart + diff/2, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        builder.pos(xEnd, yEnd, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
    });

    private ArrowButtonConsumer consumer;

    ArrowDirection(ArrowButtonConsumer consumer) {
        this.consumer = consumer;
    }

    public ArrowButtonConsumer getConsumer() {
        return consumer;
    }

    public interface ArrowButtonConsumer {

        void apply(float xStart, float yStart, float xEnd, float yEnd, BufferBuilder builder);
    }
}
