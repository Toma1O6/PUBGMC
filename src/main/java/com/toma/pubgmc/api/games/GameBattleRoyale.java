package com.toma.pubgmc.api.games;

import com.toma.pubgmc.Pubgmc;
import com.toma.pubgmc.api.Game;
import com.toma.pubgmc.api.GameUtils;
import com.toma.pubgmc.common.capability.IGameData;
import com.toma.pubgmc.common.network.PacketHandler;
import com.toma.pubgmc.common.network.server.PacketChooseLocation;
import com.toma.pubgmc.util.PUBGMCUtil;
import com.toma.pubgmc.util.game.ZoneSettings;
import com.toma.pubgmc.util.math.ZonePos;
import com.toma.pubgmc.world.BlueZone;
import com.toma.pubgmc.world.MapLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameBattleRoyale extends Game {

    private int zoneTimer;
    private List<BlockPos> scheduledAirdrops = new ArrayList<>();

    public GameBattleRoyale(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public BlueZone initializeZone(World world) {
        ZoneSettings settings = ZoneSettings.Builder.create().damage(0.1f).speed(0.25f).build();
        return new BlueZone(this.getGameData(world), settings);
    }

    @Override
    public void onGameStart(World world) {
        if(world.isRemote) return;
        GameUtils.createAndFillPlanes(world);
        IGameData gameData = this.getGameData(world);
        zoneTimer = 0;
        scheduledAirdrops.clear();
        joinedPlayers.forEach(p -> {
            for(MapLocation location : gameData.getSpawnLocations()) {
                TextComponentString msg = new TextComponentString("- " + location.name());
                msg.setStyle(msg.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "") {
                    @Override
                    public Action getAction() {
                        PacketHandler.sendToServer(new PacketChooseLocation(location.pos(), p.getRidingEntity().getEntityId()));
                        for(int i = 0; i < 50; i++) {
                            p.sendMessage(new TextComponentString(""));
                        }
                        p.sendMessage(new TextComponentString(TextFormatting.GREEN + "Successfully selected drop location, you will be dropped automatically"));
                        return super.getAction();
                    }
                }).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(location.pos().toString()))));
                p.sendMessage(msg);
            }
        });
    }

    @Override
    public void populatePlayerList(World world) {
        joinedPlayers.addAll(world.playerEntities);
    }

    @Override
    public void onGameTick(World world) {
        if(!zone.isShrinking()) {
            zoneTimer++;
            if(zone.currentStage == 0) {
                if(zoneTimer >= 200) {
                    zone.notifyFirstZoneCreation(world);
                    this.scheduleAirdrop(world);
                    zoneTimer = 0;
                }
            }
            else if(zoneTimer >= 200) {
                zone.shrink();
                if(zone.currentStage < 5) {
                    this.scheduleAirdrop(world);
                }
                zoneTimer = 0;
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
        int time = zone.currentStage == 0 ? 200 : 200;
        int timeleft = time/20 - zoneTimer/20;
        mc.fontRenderer.drawStringWithShadow("Airdrop in " + timeleft + "s", 100, 10, 0xFFFFFF);
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
        ZonePos min = zone.nextBounds.min();
        ZonePos max = zone.nextBounds.max();
        // to allow multiple airdrops
        boolean hasDroppedOnce = false;
        while(world.rand.nextFloat() <= 0.01F || !hasDroppedOnce) {
            int x = Pubgmc.rng().nextInt(Math.abs((int)max.distanceX(min)));
            int z = Pubgmc.rng().nextInt(Math.abs((int)max.distanceZ(min)));
            int y = world.getTopSolidOrLiquidBlock(new BlockPos(min.x + x, 250, min.z + z)).getY();
            y += 100;
            if(y > 255) y = 255;
            BlockPos airdrop = new BlockPos(min.x + x, y, min.z + z);
            scheduledAirdrops.add(airdrop);
            this.notifyAllPlayers(TextFormatting.BOLD + "Airdrop is appearing at [ " + airdrop.getX() + " ; " + airdrop.getZ() + " ]! Hurry up!");
            hasDroppedOnce = true;
        }
    }

    private void tickScheduledDrops(World world) {
        Iterator<BlockPos> it = this.scheduledAirdrops.iterator();
        while (it.hasNext()) {
            BlockPos pos = it.next();
            if(world.isBlockLoaded(pos)) {
                PUBGMCUtil.spawnAirdrop(world, pos, false);
                it.remove();
            }
        }
    }
}
