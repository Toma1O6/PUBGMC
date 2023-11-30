package dev.toma.pubgmc.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;

public class ByEntityDamageSource extends EntityDamageSource {

    public ByEntityDamageSource(String damageTypeIn, @Nullable Entity damageSourceEntityIn) {
        super(damageTypeIn, damageSourceEntityIn);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
    {
        String simpleLocalizationKey = "death.attack." + this.damageType;
        String entityLocalizationKey =  simpleLocalizationKey + ".entity";

        return damageSourceEntity != null ?
                new TextComponentTranslation(entityLocalizationKey, entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName()) :
                new TextComponentTranslation(simpleLocalizationKey, entityLivingBaseIn.getDisplayName());
    }
}
