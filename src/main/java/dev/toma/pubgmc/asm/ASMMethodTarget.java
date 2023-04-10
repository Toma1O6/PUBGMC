package dev.toma.pubgmc.asm;

public final class ASMMethodTarget extends ASMTarget {

    private final String descriptor;

    public ASMMethodTarget(String obfuscated, String development, String descriptor) {
        super(obfuscated, development);
        this.descriptor = descriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
