package dev.toma.pubgmc.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public final class SlotClassTransformer {

    private static final ASMMethodTarget M_IS_ITEM_VALID = new ASMMethodTarget("func_75214_a", "isItemValid", "(Lnet/minecraft/item/ItemStack;)Z");
    private static final ASMMethodTarget M_GET_SLOT_TEXTURE = new ASMMethodTarget("func_178171_c", "getSlotTexture", "()Ljava/lang/String;");

    static byte[] transform(byte[] bytes) {
        PMCClassTransformer.log.debug("Transforming net.minecraft.inventory.Slot class");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        transform_isItemValidMethod(node);
        transform_getSlotTextureMethod(node);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    private static void transform_isItemValidMethod(ClassNode node) {
        MethodNode method = ASMHelper.findMethod(M_IS_ITEM_VALID, node, false);
        if (method == null) {
            return;
        }
        InsnList insnList = method.instructions;
        int iconstInsnIndex = ASMHelper.findInstruction(insnList, (insn, i) -> insn.getOpcode() == Opcodes.ICONST_1, 0, false);
        if (iconstInsnIndex < 0) {
            PMCClassTransformer.log.error("Patch of method " + M_IS_ITEM_VALID + " failed");
            return;
        }
        AbstractInsnNode iconstInsn = insnList.get(iconstInsnIndex);
        PMCClassTransformer.log.debug("Injecting into " + M_IS_ITEM_VALID + " method");
        InsnList injection = new InsnList();
        injection.add(new VarInsnNode(Opcodes.ALOAD, 0));
        injection.add(new VarInsnNode(Opcodes.ALOAD, 1));
        injection.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooks",
                "isItemValid",
                "(Lnet/minecraft/inventory/Slot;Lnet/minecraft/item/ItemStack;)Z",
                false
        ));
        insnList.insert(iconstInsn, injection);
        insnList.remove(iconstInsn);
    }

    private static void transform_getSlotTextureMethod(ClassNode node) {
        MethodNode method = ASMHelper.findMethod(M_GET_SLOT_TEXTURE, node, false);
        if (method == null) {
            return;
        }
        InsnList insnList = method.instructions;
        int returnInsnIndex = ASMHelper.findInstruction(insnList, (insn, i) -> insn.getOpcode() == Opcodes.ARETURN, 0, false);
        if (returnInsnIndex < 0) {
            return;
        }
        PMCClassTransformer.log.debug("Injecting into " + M_GET_SLOT_TEXTURE + " method");
        InsnList injection = new InsnList();
        injection.add(new VarInsnNode(Opcodes.ALOAD, 0));
        injection.add(new MethodInsnNode(
                Opcodes.INVOKESTATIC,
                "dev/toma/pubgmc/asm/ASMHooks",
                "getSlotTexture",
                "(Ljava/lang/String;Lnet/minecraft/inventory/Slot;)Ljava/lang/String;",
                false
        ));
        insnList.insertBefore(insnList.get(returnInsnIndex), injection);
    }
}
