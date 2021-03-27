package dev.toma.pubgmc.client.models.renderer;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;

public class ExtendedTexturedQuad extends TexturedQuad {

    public ExtendedTexturedQuad(PositionTextureVertex[] vertices) {
        super(vertices);
        vertices[0] = vertices[0].setTexturePosition(1.0F, 0.0F);
        vertices[1] = vertices[1].setTexturePosition(0.0F, 0.0F);
        vertices[2] = vertices[2].setTexturePosition(0.0F, 1.0F);
        vertices[3] = vertices[3].setTexturePosition(1.0F, 1.0F);
    }

    @Override
    public void draw(BufferBuilder renderer, float scale) {
        Vec3d vec3d = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[0].vector3D);
        Vec3d vec3d1 = this.vertexPositions[1].vector3D.subtractReverse(this.vertexPositions[2].vector3D);
        Vec3d vec3d2 = vec3d1.crossProduct(vec3d).normalize();
        float f = (float) vec3d2.x;
        float f1 = (float) vec3d2.y;
        float f2 = (float) vec3d2.z;

        renderer.begin(7, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);
        for (int i = 0; i < 4; ++i) {
            PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
            renderer
                    .pos(positiontexturevertex.vector3D.x * (double) scale, positiontexturevertex.vector3D.y * (double) scale, positiontexturevertex.vector3D.z * (double) scale)
                    .tex(positiontexturevertex.texturePositionX, positiontexturevertex.texturePositionY)
                    .normal(f, f1, f2)
                    .endVertex();
        }
        Tessellator.getInstance().draw();
    }
}
