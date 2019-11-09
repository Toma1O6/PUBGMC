package com.toma.pubgmc.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions("com.toma.pubgmc.core")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1001)
public class PMCPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {"com.toma.pubgmc.core.PMCClassTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return PMCDummyModContainer.class.getName();
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }
}
