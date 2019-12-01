package com.toma.pubgmc;

import net.minecraft.block.Block;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.io.FileWriter;

public class DevUtil {

    private static final ModelCreator CREATOR = new ModelCreator();

    public static ModelCreator creator() {
        return CREATOR;
    }

    public static boolean isDev() {
        return Pubgmc.isDevEnvironment;
    }

    /**
     * Compares elements in group directly using the ==
     */
    public static <E> boolean containsD(E[] group, E element) {
        for(E e : group) {
            if(element == e) {
                return true;
            }
        }
        return false;
    }

    public static final class ModelCreator {

        public void createAllFiles() {
            // does nothing.
        }

        public void createBlockstateFile(Block block) {
            String name = block.getRegistryName().getResourcePath();
            File file = new File("D:/mcmods/1.12.2/PUBGMC/src/main/resources/assets/pubgmc/blockstates/" + name + ".json");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    String json =
                            "{\n" +
                                    "\t\"variants\": {\n" +
                                    "\t\t\"normal\": {\n" +
                                    "\t\t\t\"model\": \"" + block.getRegistryName().toString() + "\"\n" +
                                    "\t\t}\n" +
                                    "\t}\n" +
                                    "}";
                    writer.write(json);
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void createItemBlockFile(Block block) {
            String name = block.getRegistryName().getResourcePath();
            File file = new File("D:/mcmods/1.12.2/PUBGMC/src/main/resources/assets/pubgmc/models/item/" + name + ".json");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    String json =
                            "{\n" +
                                    "\t\"parent\": \"" + Pubgmc.MOD_ID + ":block/" + name + "\"\n" +
                                    "}";
                    writer.write(json);
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
