package dev.toma.pubgmc.init;

import dev.toma.pubgmc.common.items.guns.GunBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.Random;

public class DamageSourceGun extends EntityDamageSourceIndirect {

    private final Random rand = new Random();
    private ItemStack weapon;
    private Entity source;
    private Entity indirect;
    private boolean headshot;

    public DamageSourceGun(String damageType, Entity source, Entity indirect, ItemStack weapon, boolean headshot) {
        super(damageType, source, indirect);
        this.weapon = weapon;
        this.source = source;
        this.indirect = indirect;
        this.headshot = headshot;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        GunBase gun = (GunBase) weapon.getItem();
        if(wasHeadshot()) {
            return new TextComponentTranslation("death.gun.headshot", source.getName(), indirect.getName(), weapon.getDisplayName());
        }
        return new TextComponentTranslation("death.gun", source.getName(), indirect.getName(), weapon.getDisplayName());
    }

    @Nullable
    public ItemStack getGun() {
        return weapon.getItem() instanceof GunBase ? weapon : ItemStack.EMPTY;
    }

    public boolean wasHeadshot() {
        return headshot;
    }

    @Override
    public Entity getTrueSource() {
        return source;
    }
}
