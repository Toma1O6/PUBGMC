package dev.toma.pubgmc.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PMCClassTransformer implements IClassTransformer {

    static final Logger log = LogManager.getLogger("PUBGMC-CORE");

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        boolean isObfEnv = !name.equals(transformedName);
        switch (transformedName) {
            case "net.minecraft.client.model.ModelPlayer":
                return this.injectPlayerSetupAngles(basicClass, isObfEnv);
            case "net.minecraft.client.renderer.entity.RenderPlayer":
                return this.injectPlayerPreRenderCallback(basicClass, isObfEnv);
        }
        return basicClass;
    }

    /*
    LOCALVARIABLE this Lnet/minecraft/client/model/ModelPlayer; L0 L10 0
    LOCALVARIABLE limbSwing F L0 L10 1
    LOCALVARIABLE limbSwingAmount F L0 L10 2
    LOCALVARIABLE ageInTicks F L0 L10 3
    LOCALVARIABLE netHeadYaw F L0 L10 4
    LOCALVARIABLE headPitch F L0 L10 5
    LOCALVARIABLE scaleFactor F L0 L10 6
    LOCALVARIABLE entityIn Lnet/minecraft/entity/Entity; L0 L10 7
    MAXSTACK = 8
    MAXLOCALS = 8
     */
    byte[] injectPlayerSetupAngles(byte[] bytes, boolean isObf) {
        log.info("Preparing injection into 'net.minecraft.client.model.ModelPlayer'");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        Name m_setRotationAngles = new Name("setRotationAngles", "func_78087_a");
        String d_setRotationAngles = "(FFFFFFLnet/minecraft/entity/Entity;)V";
        for (MethodNode methodNode : node.methods) {
            if(methodNode.name.equals(m_setRotationAngles.getName(isObf)) && methodNode.desc.equals(d_setRotationAngles)) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = insnList.size() - 1; i >= 0; i--) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if(insnNode.getOpcode() == Opcodes.RETURN) {
                        log.info("Injecting hook into {} method", m_setRotationAngles.getName(isObf));
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 1));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 2));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 3));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 4));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 5));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 6));
                        list.add(new VarInsnNode(Opcodes.ALOAD, 7));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "dev/toma/pubgmc/ClientHooks",
                                "player_setupModelAngles",
                                "(Lnet/minecraft/client/model/ModelPlayer;FFFFFFLnet/minecraft/entity/Entity;)V",
                                false
                        ));
                        insnList.insertBefore(insnNode, list);
                        injected = true;
                        log.info("Injection successful");
                        break;
                    }
                }
                if(!injected) {
                    log.fatal("Injection failed, this is very wrong");
                }
                break;
            }
        }
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    /*
    LOCALVARIABLE this Lnet/minecraft/client/renderer/entity/RenderPlayer; L0 L3 0
    LOCALVARIABLE entitylivingbaseIn Lnet/minecraft/client/entity/AbstractClientPlayer; L0 L3 1
    LOCALVARIABLE partialTickTime F L0 L3 2
    LOCALVARIABLE f F L1 L3 3
    MAXSTACK = 3
    MAXLOCALS = 4
     */
    byte[] injectPlayerPreRenderCallback(byte[] bytes, boolean isObf) {
        log.info("Preparing injection into 'net.minecraft.client.renderer.entity.RenderPlayer'");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        Name m_preRenderCallback = new Name("preRenderCallback", "func_77041_b");
        String d_preRenderCallback = "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V";
        for (MethodNode methodNode : node.methods) {
            if(methodNode.name.equals(m_preRenderCallback.getName(isObf)) && methodNode.desc.equals(d_preRenderCallback)) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = insnList.size() - 1; i >= 0; i--) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if(insnNode.getOpcode() == Opcodes.RETURN) {
                        log.info("Injecting hook into {} method", m_preRenderCallback.getName(isObf));
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 2));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "dev/toma/pubgmc/ClientHooks",
                                "player_preRenderCallback",
                                "(Lnet/minecraft/client/renderer/entity/RenderPlayer;Lnet/minecraft/client/entity/AbstractClientPlayer;F)V",
                                false
                        ));
                        insnList.insertBefore(insnNode, list);
                        injected = true;
                        log.info("Injection successful");
                        break;
                    }
                }
                if(!injected) {
                    log.fatal("Injection failed, this is very wrong");
                }
                break;
            }
        }
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    private static class Name {

        private final String obf, deobf;

        public Name(final String mapped, final String mcp) {
            this.obf = mcp;
            this.deobf = mapped;
        }

        public String getName(boolean isObfEnv) {
            return isObfEnv ? this.obf : this.deobf;
        }
    }
}
