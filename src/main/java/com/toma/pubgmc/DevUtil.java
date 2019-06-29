package com.toma.pubgmc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;

public class DevUtil {
	
	private static final ModelCreator CREATOR = new ModelCreator();
	
	public static ModelCreator creator() {
		return CREATOR;
	}
	
	public static boolean isDev() {
		return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
	
	public static final class ModelCreator {
		
		public void createAllFiles() {
			// does nothing. 
		}
		
		public void createBlockstateFile(Block block) {
			String name = block.getRegistryName().getResourcePath();
			File file = new File("D:/mcmods/1.12.2/PUBGMC/src/main/resources/assets/pubgmc/blockstates/" + name + ".json");
			if(!file.exists()) {
				try {
					file.createNewFile();
					FileWriter writer = new FileWriter(file);
					String json = 
					"{\n"+
					"\t\"variants\": {\n"+
					"\t\t\"normal\": {\n"+
					"\t\t\t\"model\": \""+block.getRegistryName().toString()+"\"\n"+
					"\t\t}\n"+
					"\t}\n"+
					"}";
					writer.write(json);
					writer.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void createItemBlockFile(Block block) {
			String name = block.getRegistryName().getResourcePath();
			File file = new File("D:/mcmods/1.12.2/PUBGMC/src/main/resources/assets/pubgmc/models/item/" + name + ".json");
			if(!file.exists()) {
				try {
					file.createNewFile();
					FileWriter writer = new FileWriter(file);
					String json =
					"{\n"+
					"\t\"parent\": \""+Pubgmc.MOD_ID+":block/"+name+"\"\n"+
					"}";
					writer.write(json);
					writer.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
