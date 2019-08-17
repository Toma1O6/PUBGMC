package com.toma.pubgmc.config.common;

import net.minecraftforge.common.config.Config;

public final class CFGItems {

    @Config.Name("Ammo limit")
    @Config.RangeInt(min = 1, max = 64)
    public int ammoLimit = 30;

    @Config.Name("Bandage limit")
    @Config.RangeInt(min = 1, max = 64)
    public int bandageLimit = 5;

    @Config.Name("First aid limit")
    @Config.RangeInt(min = 1, max = 64)
    public int firstAidLimit = 1;

    @Config.Name("Medkit limit")
    @Config.RangeInt(min = 1, max = 64)
    public int medkitLimit = 1;

    @Config.Name("Energy drink limit")
    @Config.RangeInt(min = 1, max = 64)
    public int energyDrinkLimit = 1;

    @Config.Name("Painkiller limit")
    @Config.RangeInt(min = 1, max = 64)
    public int painkillerLimit = 1;

    @Config.Name("Adrenaline syringe limit")
    @Config.RangeInt(min = 1, max = 64)
    public int adrenalineSyringeLimit = 1;
}
