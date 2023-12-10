package dev.toma.pubgmc.asm;

public class ASMTarget {

    private final String obfuscated, development;

    public ASMTarget(String obfuscated, String development) {
        this.obfuscated = obfuscated;
        this.development = development;
    }

    public String getValue(boolean obfuscatedEnvironment) {
        return obfuscatedEnvironment ? obfuscated : development;
    }

    public String getValue() {
        return getValue(PMCClassTransformer.isObfuscated);
    }

    @Override
    public String toString() {
        return getValue(PMCClassTransformer.isObfuscated);
    }
}
