package com.toma.pubgmc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
		
	}
}
