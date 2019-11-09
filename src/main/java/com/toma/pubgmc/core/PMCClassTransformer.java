package com.toma.pubgmc.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class PMCClassTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(basicClass == null) return null;
        boolean isObfEnv = !name.equals(transformedName);
        if(transformedName.equals("net.minecraft.client.model.ModelPlayer")) {
            return this.patchModelPlayer(basicClass, isObfEnv);
        }
        return basicClass;
    }

    public byte[] patchModelPlayer(byte[] bytes, boolean isObf) {
        long l = System.currentTimeMillis();
        PMCDummyModContainer.log.info("Patching net.minecraft.client.model.ModelPlayer");
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        Name setupAngles = new Name("setRotationAngles", "func_78087_a");
        String methodParams = "(FFFFFFLnet/minecraft/entity/Entity;)V";
        for(MethodNode node : classNode.methods) {
            if(node.name.equals(setupAngles.getName(isObf)) && node.desc.equals(methodParams)) {
                AbstractInsnNode targetNode = null;
                for(AbstractInsnNode abstractInsnNode : node.instructions.toArray()) {
                    if(abstractInsnNode.getOpcode() == Opcodes.INVOKESPECIAL) {
                        targetNode = abstractInsnNode;
                        break;
                    }

                }
                if(targetNode != null) {
                    PMCDummyModContainer.log.info("Found the required method");
                    InsnList event = new InsnList();
                    event.add(new LabelNode());
                    event.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;"));
                    event.add(new TypeInsnNode(Opcodes.NEW, "com/toma/pubgmc/event/client/SetupAnglesEvent"));
                    event.add(new InsnNode(Opcodes.DUP));
                    event.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    event.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "com/toma/pubgmc/event/client/SetupAnglesEvent", "<init>", "(Lnet/minecraft/client/model/ModelPlayer;)V", false));
                    event.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z"));
                    event.add(new InsnNode(Opcodes.POP));
                    node.instructions.insert(targetNode, event);
                    PMCDummyModContainer.log.info("Successfully patched net.minecraft.client.model.ModelPlayer class. Took {} ms", System.currentTimeMillis() - l);
                } else PMCDummyModContainer.log.fatal("Patching failed, things are not going to work!");
            }
        }
        ClassWriter classWriter = new ClassWriter(0);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }

    private class Name {

        private final String obf, deobf;

        public Name(final String deobf, final String obf) {
            this.obf = obf;
            this.deobf = deobf;
        }

        public String getName(boolean isObfEnv) {
            return isObfEnv ? this.obf : this.deobf;
        }
    }
}
