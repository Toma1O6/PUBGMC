package dev.toma.pubgmc.asm;

import dev.toma.pubgmc.Pubgmc;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class WorldProviderClassTransformer {

    private static final ASMMethodTarget METHOD_TARGET = new ASMMethodTarget("getRandomizedSpawnPoint", "getRandomizedSpawnPoint", "()Lnet/minecraft/util/math/BlockPos;");
    private static final ASMTarget WORLD_FIELD = new ASMTarget("field_76579_a", "world");

    static byte[] transform(byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        transform(node);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    private static void transform(ClassNode node) {
        MethodNode methodNode = ASMHelper.findMethod(METHOD_TARGET, node, Pubgmc.isDevEnvironment);
        if (methodNode == null)
            return;
        InsnList list = methodNode.instructions;
        int index = ASMHelper.findInstruction(list, (insn, i) -> insn.getOpcode() == Opcodes.ARETURN, 0, Pubgmc.isDevEnvironment);
        if (index == -1)
            return;
        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new FieldInsnNode(
                Opcodes.GETFIELD,
                "net/minecraft/world/WorldProvider",
                WORLD_FIELD.getValue(PMCClassTransformer.isObfuscated),
                "Lnet/minecraft/world/World;"
        ));
        insnList.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooks",
                "adjustSpawnPosition",
                "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/World;)Lnet/minecraft/util/math/BlockPos;",
                false
        ));
        list.insertBefore(list.get(index), insnList);
    }
}
