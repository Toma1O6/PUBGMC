package dev.toma.pubgmc.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PMCClassTransformer implements IClassTransformer {

    public static boolean isObfuscated = false;
    static final Logger log = LogManager.getLogger("pubgmc-core");

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        isObfuscated = !name.equals(transformedName);
        switch (transformedName) {
            case "net.minecraft.client.model.ModelBiped":
                return this.injectPlayerSetupAngles(basicClass);
            case "net.minecraft.client.renderer.entity.RenderPlayer":
                return this.injectRenderPlayer(basicClass);
            case "net.minecraftforge.client.ForgeHooksClient":
                return this.injectHandleCameraTransforms(basicClass);
            case "net.minecraft.inventory.Slot":
                return SlotClassTransformer.transform(basicClass);
            case "net.minecraft.client.renderer.EntityRenderer":
                return EntityRendererClassTransformer.transform(basicClass);
            case "net.minecraft.client.Minecraft":
                return MinecraftClassTransformer.transform(basicClass);
        }
        return basicClass;
    }

    /*
    Couldn't figure out better injection point. Feel free to suggest anything else
    Why is this needed? Injected method sets last used TransformType which is used
    to determine whether model animation should be applied or not.
    */
    byte[] injectHandleCameraTransforms(byte[] bytes) {
        log.info("Preparing injection into 'net.minecraftforge.client.ForgeHooksClient'");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        for (MethodNode methodNode : node.methods) {
            if (methodNode.name.equals("handleCameraTransforms") && methodNode.desc.equals("(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;Z)Lnet/minecraft/client/renderer/block/model/IBakedModel;")) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = 0; i < insnList.size(); i++) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if (insnNode.getOpcode() == Opcodes.INVOKEINTERFACE && insnNode instanceof MethodInsnNode) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
                        if (methodInsnNode.name.equals("handlePerspective")) {
                            log.info("Injecting hook into {} method", methodInsnNode.name);
                            InsnList list = new InsnList();
                            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                            list.add(new MethodInsnNode(
                                    Opcodes.INVOKESTATIC,
                                    "dev/toma/pubgmc/asm/ASMHooksClient",
                                    "preRenderItem",
                                    "(Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;)V",
                                    false
                            ));
                            insnList.insertBefore(insnList.get(i - 2), list);
                            log.info("Injection successful");
                            injected = true;
                            break;
                        }
                    }
                }
                if (!injected) {
                    log.fatal("Injection failed");
                }
            }
        }
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
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
    byte[] injectPlayerSetupAngles(byte[] bytes) {
        log.info("Preparing injection into 'net.minecraft.client.model.ModelBiped'");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        Name m_setRotationAngles = new Name("setRotationAngles", "func_78087_a");
        String d_setRotationAngles = "(FFFFFFLnet/minecraft/entity/Entity;)V";
        for (MethodNode methodNode : node.methods) {
            if (methodNode.name.equals(m_setRotationAngles.getName(isObfuscated)) && methodNode.desc.equals(d_setRotationAngles)) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = insnList.size() - 1; i >= 0; i--) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if (insnNode.getOpcode() == Opcodes.RETURN) {
                        log.info("Injecting hook into {} method", m_setRotationAngles.getName(isObfuscated));
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new VarInsnNode(Opcodes.ALOAD, 7));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "dev/toma/pubgmc/asm/ASMHooksClient",
                                "model_setupModelAngles",
                                "(Lnet/minecraft/client/model/ModelBiped;Lnet/minecraft/entity/Entity;)V",
                                false
                        ));
                        insnList.insertBefore(insnNode, list);
                        injected = true;
                        log.info("Injection successful");
                        break;
                    }
                }
                if (!injected) {
                    log.fatal("Injection failed, this is very wrong");
                }
                break;
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    /*
    preRenderCallback

    LOCALVARIABLE this Lnet/minecraft/client/renderer/entity/RenderPlayer; L0 L3 0
    LOCALVARIABLE entitylivingbaseIn Lnet/minecraft/client/entity/AbstractClientPlayer; L0 L3 1
    LOCALVARIABLE partialTickTime F L0 L3 2
    LOCALVARIABLE f F L1 L3 3
    MAXSTACK = 3
    MAXLOCALS = 4

    ----------------------------
    <init>

    L10
    LINENUMBER 51 L10
    RETURN
   L11
    LOCALVARIABLE this Lnet/minecraft/client/renderer/entity/RenderPlayer; L0 L11 0
    LOCALVARIABLE renderManager Lnet/minecraft/client/renderer/entity/RenderManager; L0 L11 1
    LOCALVARIABLE useSmallArms Z L0 L11 2
    MAXSTACK = 6
    MAXLOCALS = 3
     */
    byte[] injectRenderPlayer(byte[] bytes) {
        log.info("Preparing injection into 'net.minecraft.client.renderer.entity.RenderPlayer'");
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(bytes);
        reader.accept(node, 0);
        Name m_preRenderCallback = new Name("preRenderCallback", "func_77041_b");
        String d_preRenderCallback = "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V";
        for (MethodNode methodNode : node.methods) {
            if (methodNode.name.equals(m_preRenderCallback.getName(isObfuscated)) && methodNode.desc.equals(d_preRenderCallback)) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = insnList.size() - 1; i >= 0; i--) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if (insnNode.getOpcode() == Opcodes.RETURN) {
                        log.info("Injecting hook into {} method", m_preRenderCallback.getName(isObfuscated));
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        list.add(new VarInsnNode(Opcodes.FLOAD, 2));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "dev/toma/pubgmc/asm/ASMHooksClient",
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
                if (!injected) {
                    log.fatal("Injection failed, this is very wrong");
                }
            } else if (methodNode.name.equals("<init>") && methodNode.desc.equals("(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V")) {
                InsnList insnList = methodNode.instructions;
                boolean injected = false;
                for (int i = insnList.size() - 1; i >= 0; i--) {
                    AbstractInsnNode insnNode = insnList.get(i);
                    if (insnNode.getOpcode() == Opcodes.RETURN) {
                        log.info("Injecting hook into <init> method");
                        InsnList list = new InsnList();
                        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        list.add(new VarInsnNode(Opcodes.ILOAD, 2));
                        list.add(new MethodInsnNode(
                                Opcodes.INVOKESTATIC,
                                "dev/toma/pubgmc/asm/ASMHooksClient",
                                "player_constructRender",
                                "(Lnet/minecraft/client/renderer/entity/RenderPlayer;Lnet/minecraft/client/renderer/entity/RenderManager;Z)V",
                                false
                        ));
                        insnList.insertBefore(insnNode, list);
                        injected = true;
                        log.info("Injection successful");
                        break;
                    }
                }
                if (!injected) {
                    log.fatal("Injection failed, this is very wrong");
                }
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
