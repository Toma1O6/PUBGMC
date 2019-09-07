package com.toma.pubgmc.api.games;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.common.entity.EntityPlane;
import com.toma.pubgmc.config.ConfigPMC;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.world.BlueZone;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class GameBattleRoyale extends Game {

    private int zoneTimer;
    private List<BlockPos> scheduledAirdrops = new ArrayList<>();

    public GameBattleRoyale(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public BlueZone initializeZone(World world) {
        ZoneSettings settings = ZoneSettings.Builder.create()
                .damage(0.1f)
                .speed(0.1f)
                .build();
        return new BlueZone(settings, this);
    }

    @Override
    public void onGameStart(World world) {
        if(!world.isRemote) {
            EntityPlane plane = new EntityPlane(world, gameData);
            int joined = 0;
            Iterator<EntityPlayer> iterator = joinedPlayers.iterator();
            while(iterator.hasNext()) {
                EntityPlayer player = iterator.next();
                boolean flag = iterator.hasNext();
                ++joined;
                plane.pendingPlayers.add(iterator.next());
                if(joined >= 31) {
                    BlockPos start = plane.getStartingPosition();
                    plane.pendingPlayers.forEach(p -> p.attemptTeleport(start.getX(), ConfigPMC.common.world.planeHeight, start.getZ()));
                    world.spawnEntity(plane);
                    for(EntityPlayer p : plane.pendingPlayers) {
                        if(p != null) {
                            p.startRiding(plane);
                        }
                    }
                    plane.pendingPlayers = null;
                    if(flag) {
                        plane = new EntityPlane(world, gameData);
                        joined = 0;
                    }
                }
            }
        }
        zoneTimer = 0;
        scheduledAirdrops.clear();
    }

    @Override
    public void populatePlayerList(World world) {
        world.playerEntities.stream()
                .filter(player -> !player.isCreative() && !player.isSpectator())
                .forEach(joinedPlayers::add);
    }

    @Override
    public void onGameTick(World world) {
        if(!zone.isShrinking()) {
            zoneTimer++;
            if(zone.currentStage == 0) {
                if(zoneTimer >= 2400) {
                    zone.notifyFirstZoneCreation();
                    this.scheduleAirdrop(world);
                }
            }
            else if(zoneTimer >= 2000) {
                zone.shrink();
                this.scheduleAirdrop(world);
            }
        }
        if(gameTimer % 20 == 0) {
            this.tickScheduledDrops(world);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderGameInfo(ScaledResolution res) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow("Players left: " + joinedPlayers.size(), 10, 10, 0xFFFFFF);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("zoneTimer", zoneTimer);
        NBTTagList scheduledDrops = new NBTTagList();
        scheduledAirdrops.forEach(p -> scheduledDrops.appendTag(NBTUtil.createPosTag(p)));
        nbt.setTag("scheduledAirdrops", scheduledDrops);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        scheduledAirdrops.clear();
        zoneTimer = nbt.getInteger("zoneTimer");
        NBTTagList list = nbt.getTagList("scheduledAirdrops", Constants.NBT.TAG_COMPOUND);
        list.forEach(tag -> scheduledAirdrops.add(NBTUtil.getPosFromTag((NBTTagCompound) tag)));
    }

    private void scheduleAirdrop(World world) {
        BlockPos min = zone.nextCorners.getLeft();
        BlockPos max = zone.nextCorners.getRight();
        int x = Pubgmc.rng().nextInt(Math.abs(max.getX() - min.getX()));
        int z = Pubgmc.rng().nextInt(Math.abs(max.getZ() - min.getZ()));
        BlockPos airdrop = new BlockPos(min.getX() + x, 250, min.getZ() + z);
        scheduledAirdrops.add(airdrop);
        this.notifyAllPlayers(TextFormatting.BOLD + "Airdrop is appearing at [ " + airdrop.getX() + " ; " + airdrop.getZ() + " ]! Hurry up!");
    }

    private void tickScheduledDrops(World world) {
        for(BlockPos pos : scheduledAirdrops) {
            if(world.isBlockLoaded(pos)) {
                PUBGMCUtil.spawnAirdrop(world, pos, false);
                scheduledAirdrops.remove(pos);
            }
        }
    }
}
