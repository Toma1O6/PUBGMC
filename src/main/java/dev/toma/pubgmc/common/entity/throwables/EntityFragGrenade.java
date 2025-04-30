package dev.toma.pubgmc.common.entity.throwables;

import com.google.common.base.Predicates;
import dev.toma.pubgmc.api.entity.IBombReaction;
import dev.toma.pubgmc.config.ConfigPMC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityFragGrenade extends EntityThrowableExplodeable {
    private static final float explodeInteractRadius = 5F;

    public EntityFragGrenade(World world) {
        super(world);
    }

    public EntityFragGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state) {
        super(world, thrower, state);
    }

    public EntityFragGrenade(World world, EntityLivingBase thrower, EnumEntityThrowState state, int time) {
        super(world, thrower, state, time);
    }

    @Override
    public void onExplode() {
        if (!world.isRemote) {
            boolean canBreakBlocks = ConfigPMC.world().bombs.grenadeGriefing.get();
            this.setPosition(this.posX, this.posY + 1, this.posZ);
            world.createExplosion(getThrower(), this.posX, this.posY, this.posZ, 5.0F, canBreakBlocks);
            handleExplodeInteraction();
        }
        this.setDead();
    }

    public void handleExplodeInteraction() {
        AxisAlignedBB aabb = new AxisAlignedBB(this.posX - explodeInteractRadius, this.posY - explodeInteractRadius, this.posZ - explodeInteractRadius,
                this.posX + explodeInteractRadius, this.posY + explodeInteractRadius, this.posZ + explodeInteractRadius);
        List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, aabb, Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.NOT_SPECTATING));
        for (Entity e : entities) {
            if (e instanceof IBombReaction) {
                IBombReaction reaction = (IBombReaction) e;
                if (reaction.allowBombInteraction(world, null, e)) {
                    double vecX = e.posX - this.posX;
                    double vecY = e.posY - this.posY;
                    double vecZ = e.posZ - this.posZ;
                    double distance = Math.sqrt(vecX*vecX + vecY*vecY + vecZ*vecZ);
                    if (distance < explodeInteractRadius) {
                        // Only provides a slight vertical bounce
                        // Expected: y=0.3
                        double bombStrength = Math.min(1.2 - distance / explodeInteractRadius, 1);
                        vecY = 0.3F * bombStrength;
                        reaction.onBomb(this, new Vec3d(0, vecY, 0), null, e);
                    }
                }
            }
        }
    }
}
