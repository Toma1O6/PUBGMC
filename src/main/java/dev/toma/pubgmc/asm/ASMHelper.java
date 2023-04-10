package dev.toma.pubgmc.asm;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.function.BiPredicate;

public final class ASMHelper {

    public static MethodNode findMethod(ASMMethodTarget target, ClassNode node, boolean failOnMiss) {
        String name = target.getValue(PMCClassTransformer.isObfuscated);
        for (MethodNode methodNode : node.methods) {
            if (methodNode.name.equals(name) && methodNode.desc.equals(target.getDescriptor())) {
                return methodNode;
            }
        }
        if (failOnMiss) {
            throw new IllegalStateException("Method with name '" + name + "' have not been found");
        }
        return null;
    }

    public static int findInstruction(InsnList list, BiPredicate<AbstractInsnNode, Integer> filter, int startIndex, boolean failOnMiss) {
        for (int i = startIndex; i < list.size(); i++) {
            AbstractInsnNode insnNode = list.get(i);
            if (filter.test(insnNode, i)) {
                return i;
            }
        }
        if (failOnMiss) {
            throw new IllegalStateException("Unable to find specified instruction");
        }
        return -1;
    }
}
