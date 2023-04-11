package dev.toma.pubgmc.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class EntityRendererClassTransformer {

    private static final ASMMethodTarget M_UPDATE_LIGHTMAP = new ASMMethodTarget("func_78472_g", "updateLightmap", "(F)V");
    private static final ASMMethodTarget M_UPDATE_LIGHTMAP_TEXTURE = new ASMMethodTarget("func_110564_a", "updateDynamicTexture", "()V");
    private static final ASMTarget F_LIGHTMAP_COLORS = new ASMTarget("field_78504_Q", "lightmapColors");

    static byte[] transform(byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        transform_updateLightmapMethod(node);
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    /*
    ALOAD 0
    GETFIELD net/minecraft/client/renderer/EntityRenderer.lightmapTexture : Lnet/minecraft/client/renderer/texture/DynamicTexture;
    INVOKEVIRTUAL net/minecraft/client/renderer/texture/DynamicTexture.updateDynamicTexture ()V
     */
    private static void transform_updateLightmapMethod(ClassNode node) {
        MethodNode methodNode = ASMHelper.findMethod(M_UPDATE_LIGHTMAP, node, false);
        if (methodNode == null) {
            return;
        }
        InsnList insnList = methodNode.instructions;
        int updateLightmapTextureIndex = ASMHelper.findInstruction(insnList, EntityRendererClassTransformer::isUpdateLightmapTextureMethodInsn, 0, true);
        int insertTarget = updateLightmapTextureIndex - 2;
        InsnList injection = new InsnList();
        injection.add(new VarInsnNode(Opcodes.ALOAD, 0));
        injection.add(new FieldInsnNode(
                Opcodes.GETFIELD,
                "net/minecraft/client/renderer/EntityRenderer",
                F_LIGHTMAP_COLORS.getValue(PMCClassTransformer.isObfuscated),
                "[I"
        ));
        injection.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooksClient",
                "updateLightmap",
                "([I)V",
                false
        ));
        insnList.insertBefore(insnList.get(insertTarget), injection);
    }

    private static boolean isUpdateLightmapTextureMethodInsn(AbstractInsnNode node, int i) {
        if (node.getOpcode() != Opcodes.INVOKEVIRTUAL) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
        return methodInsnNode.name.equals(M_UPDATE_LIGHTMAP_TEXTURE.getValue(PMCClassTransformer.isObfuscated))
                && methodInsnNode.desc.equals(M_UPDATE_LIGHTMAP_TEXTURE.getDescriptor());
    }
}
