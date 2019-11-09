package com.toma.pubgmc.core;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

public class PMCDummyModContainer extends DummyModContainer {

    public static Logger log = LogManager.getLogger("PUBGMC Core");

    public PMCDummyModContainer() {
        super(new ModMetadata());
        ModMetadata metadata = this.getMetadata();
        metadata.modId = "pubgmccore";
        metadata.name = "PUBGMC Core mod";
        metadata.version = "1.0";
        metadata.authorList = Collections.singletonList("Toma");
        metadata.description = "Core mod for PUBGMC mod. Used to patch net.minecraft.client.model.ModelPlayer class. Inserts event call to the ModelPlayer#setRotationAngles. You may have issues with mods which modify player model";
    }
}
