package dev.toma.pubgmc.common.items.guns;

public interface WeaponStats {

    float getDamage();

    float getVelocity();

    float getGravityModifier();

    int getGravityEffectStart();

    float getVerticalRecoil();

    float getHorizontalRecoil();

    default boolean shouldDisplayStatistics() {
        return true;
    }

    static WeaponStats simple(float damage, float velocity, float gravityModifier, int effectStart, float verticalRecoil, float horizontalRecoil, boolean allowDisplay) {
        return new WeaponStats() {
            @Override
            public float getDamage() {
                return damage;
            }

            @Override
            public float getVelocity() {
                return velocity;
            }

            @Override
            public float getGravityModifier() {
                return gravityModifier;
            }

            @Override
            public int getGravityEffectStart() {
                return effectStart;
            }

            @Override
            public float getVerticalRecoil() {
                return verticalRecoil;
            }

            @Override
            public float getHorizontalRecoil() {
                return horizontalRecoil;
            }

            @Override
            public boolean shouldDisplayStatistics() {
                return allowDisplay;
            }
        };
    }

    static WeaponStats empty() {
        return simple(0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0F, false);
    }
}
