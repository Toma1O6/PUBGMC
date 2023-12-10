package dev.toma.pubgmc.asm;

import org.objectweb.asm.tree.MethodInsnNode;

public final class ASMMethodTarget extends ASMTarget {

    private final String descriptor;

    public ASMMethodTarget(String obfuscated, String development, String descriptor) {
        super(obfuscated, development);
        this.descriptor = descriptor;
    }

    public boolean matches(MethodInsnNode node, boolean obf) {
        return node.name.equals(getValue(obf)) && node.desc.equals(descriptor);
    }

    public String getDescriptor() {
        return descriptor;
    }
}
