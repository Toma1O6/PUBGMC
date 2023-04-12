package dev.toma.pubgmc.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public final class MinecraftClassTransformer {

    private static final ASMMethodTarget M_RUN_TICK = new ASMMethodTarget("func_71407_l", "runTick", "()V");
    private static final ASMMethodTarget M_WORLD_TICK = new ASMMethodTarget("func_72835_b", "tick", "()V");
    private static final ASMTarget F_WORLD_CLIENT = new ASMTarget("field_71441_e", "world");

    static byte[] transform(byte[] bytes) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        transform(node);
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    /*
    ALOAD 0
    GETFIELD net/minecraft/client/Minecraft.world : Lnet/minecraft/client/multiplayer/WorldClient;
    INVOKEVIRTUAL net/minecraft/client/multiplayer/WorldClient.tick ()V
     */
    private static void transform(ClassNode node) {
        MethodNode methodNode = ASMHelper.findMethod(M_RUN_TICK, node, false);
        if (methodNode == null) {
            return;
        }
        InsnList insnList = methodNode.instructions;
        int worldTickInsnIndex = ASMHelper.findInstruction(insnList, MinecraftClassTransformer::isWorldTickCall, 0, true);
        AbstractInsnNode insnNode = insnList.get(worldTickInsnIndex);
        injectPreTickCall(insnList, worldTickInsnIndex);
        injectPostTickCall(insnList, insnNode);
    }

    private static void injectPreTickCall(InsnList list, int index) {
        AbstractInsnNode selfReferenceNode = list.get(index - 2);
        InsnList injection = new InsnList();
        addWorldClientFieldReference(injection);
        injection.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooksClient",
                "dispatchPreWorldClientTickEvent",
                "(Lnet/minecraft/client/multiplayer/WorldClient;)V",
                false
        ));
        list.insertBefore(selfReferenceNode, injection);
    }

    private static void injectPostTickCall(InsnList list, AbstractInsnNode node) {
        InsnList injection = new InsnList();
        addWorldClientFieldReference(injection);
        injection.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooksClient",
                "dispatchPostWorldClientTickEvent",
                "(Lnet/minecraft/client/multiplayer/WorldClient;)V",
                false
        ));
        list.insert(node, injection);
    }

    private static void addWorldClientFieldReference(InsnList list) {
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new FieldInsnNode(
                Opcodes.GETFIELD,
                "net/minecraft/client/Minecraft",
                F_WORLD_CLIENT.getValue(PMCClassTransformer.isObfuscated),
                "Lnet/minecraft/client/multiplayer/WorldClient;"
        ));
    }

    private static boolean isWorldTickCall(AbstractInsnNode node, int i) {
        if (node.getOpcode() == Opcodes.INVOKEVIRTUAL) {
            MethodInsnNode methodInsnNode = (MethodInsnNode) node;
            return methodInsnNode.name.equals(M_WORLD_TICK.getValue(PMCClassTransformer.isObfuscated))
                    && methodInsnNode.desc.equals(M_WORLD_TICK.getDescriptor())
                    && methodInsnNode.owner.equals("net/minecraft/client/multiplayer/WorldClient");
        }
        return false;
    }
}
