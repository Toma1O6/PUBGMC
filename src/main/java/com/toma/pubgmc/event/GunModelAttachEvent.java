package com.toma.pubgmc.event;

import com.toma.pubgmc.client.models.ModelGun;
import com.toma.pubgmc.client.renderer.WeaponTEISR;
import com.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GunModelAttachEvent extends Event {
    private final GunBase gun;
    private final ResourceLocation name;

    public GunModelAttachEvent(GunBase gunToRegister, ResourceLocation resourceLocation) {
        this.gun = gunToRegister;
        this.name = resourceLocation;
    }

    public WeaponTEISR getTEISR() {
        return (WeaponTEISR) gun.getTileEntityItemStackRenderer();
    }

    public void attachModel(ModelGun model) {
        this.gun.setGunModel(model);
    }

    public GunBase getGun() {
        return gun;
    }

    public String getName() {
        return name.getResourcePath();
    }
}
