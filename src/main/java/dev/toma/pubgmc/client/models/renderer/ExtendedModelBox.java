package dev.toma.pubgmc.client.models.renderer;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ExtendedModelBox extends ModelBox {

    public ExtendedModelBox(ModelRenderer renderer, float x, float y, float z, int dx, int dy, int dz, float delta) {
        super(renderer, 0, 0, x, y, z, dx, dy, dz, delta, false);
        this.replaceQuads();
    }

    public void replaceQuads() {
        quadList[0] = new ExtendedTexturedQuad(quadList[0].vertexPositions);
        quadList[1] = new ExtendedTexturedQuad(quadList[1].vertexPositions);
        quadList[2] = new ExtendedTexturedQuad(quadList[2].vertexPositions);
        quadList[3] = new ExtendedTexturedQuad(quadList[3].vertexPositions);
        quadList[4] = new ExtendedTexturedQuad(quadList[4].vertexPositions);
        quadList[5] = new ExtendedTexturedQuad(quadList[5].vertexPositions);
    }
}
