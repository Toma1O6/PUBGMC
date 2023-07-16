package dev.toma.pubgmc.asm;

import dev.toma.pubgmc.Pubgmc;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public final class GuiIngameClassTransformer {

    private static final ASMMethodTarget RENDER_PLAYER_LIST = new ASMMethodTarget("renderPlayerList", "renderPlayerList", "(II)V");
    private static final ASMMethodTarget GET_PLAYER_INFO_METHOD = new ASMMethodTarget("func_175106_d", "getPlayerInfoMap", "()Ljava/util/Collection");

    static byte[] transform(byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        transform(node);
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    private static void transform(ClassNode node) {
        MethodNode methodNode = ASMHelper.findMethod(RENDER_PLAYER_LIST, node, Pubgmc.isDevEnvironment);
        if (methodNode == null)
            return;
        InsnList list = methodNode.instructions;
        int callInsn = ASMHelper.findInstruction(list, GuiIngameClassTransformer::isNetworkPlayerInfoCall, 0, Pubgmc.isDevEnvironment);
        if (callInsn >= 0) {
            callInsn += 2;
            AbstractInsnNode insnNode = list.get(callInsn);
            if (insnNode.getOpcode() == Opcodes.ICONST_1) {
                list.set(insnNode, new MethodInsnNode(
                        Opcodes.INVOKESTATIC,
                        "dev/toma/pubgmc/asm/ASMHooksClient",
                        "getPlayerLimitForTabOverlayRender",
                        "()I",
                        false
                ));
            }
        }
    }

    private static boolean isNetworkPlayerInfoCall(AbstractInsnNode insn, int i) {
        return insn.getOpcode() == Opcodes.INVOKEVIRTUAL && ((MethodInsnNode) insn).name.equals(GET_PLAYER_INFO_METHOD.getValue(PMCClassTransformer.isObfuscated));
    }
}
