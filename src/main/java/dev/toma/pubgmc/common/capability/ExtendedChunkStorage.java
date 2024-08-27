package dev.toma.pubgmc.common.capability;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.capability.ChunkGameBlockData;
import dev.toma.pubgmc.api.capability.ChunkGameBlockDataProvider;
import dev.toma.pubgmc.common.games.util.WorldGameBlockHelper;
import dev.toma.pubgmc.config.ConfigPMC;
import dev.toma.pubgmc.util.helper.GameHelper;
import dev.toma.pubgmc.util.helper.SerializationHelper;
import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.message.FormattedMessage;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class ExtendedChunkStorage implements ChunkGameBlockData {

    private static final byte DATA_VERSION = 1;
    private static final Marker MARKER = MarkerManager.getMarker("ChunkScanner");

    private final ShortSet blockData;
    private byte version;
    private UUID gameId = GameHelper.DEFAULT_UUID;

    public ExtendedChunkStorage() {
        this.blockData = new ShortOpenHashSet();
    }

    @Override
    public boolean isSameGameId(UUID gameId) {
        return this.gameId.equals(gameId);
    }

    @Override
    public void updateGameId(UUID gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean isOutdated() {
        return this.version != DATA_VERSION;
    }

    @Override
    public void markUpToDate() {
        this.version = DATA_VERSION;
    }

    @Override
    public Collection<Short> getBlockData() {
        return this.blockData;
    }

    @Override
    public void set(Collection<Short> data) {
        this.blockData.clear();
        if (data != null)
            this.blockData.addAll(data);
    }

    @Override
    public void setBlockData(short index, boolean exists) {
        if (!exists) {
            this.blockData.remove(index);
        } else {
            this.blockData.add(index);
        }
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("version", this.version);
        nbt.setUniqueId("gameId", this.gameId);
        nbt.setTag("data", SerializationHelper.collectionToNbt(this.blockData, NBTTagShort::new));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.version = nbt.getByte("version");
        this.gameId = nbt.getUniqueId("gameId");
        SerializationHelper.collectionFromNbt(this.blockData, nbt.getTagList("data", Constants.NBT.TAG_SHORT), base -> ((NBTTagShort) base).getShort());
    }

    public static short encodeChunkPosition(BlockPos pos) {
        return encodeWorldPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public static short encodeWorldPosition(int x, int y, int z) {
        int rx = x & 0xF;
        int ry = y & 0xFF;
        int rz = z & 0xF;
        return (short) (rx | rz << 4 | ry << 8);
    }

    public static final class EventHandler {

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public void onChunkLoad(ChunkEvent.Load event) {
            Chunk chunk = event.getChunk();
            World world = chunk.getWorld();
            if (world.isRemote || !ConfigPMC.common.gameConfig.allowFullChunkScans.get())
                return;
            MinecraftServer server = world.getMinecraftServer();
            ChunkGameBlockDataProvider.getChunkData(chunk).ifPresent(data -> {
                if (data.isOutdated()) {
                    ListenableFuture<ShortSet> processedData = Pubgmc.runBackgroundTask(() -> {
                        ShortSet set = new ShortOpenHashSet();
                        for (int y = 0; y < 256; y++) {
                            for (int x = 0; x < 16; x++) {
                                for (int z = 0; z < 16; z++) {
                                    IBlockState state = chunk.getBlockState(x, y, z);
                                    Block block = state.getBlock();
                                    if (block == Blocks.AIR)
                                        continue;
                                    if (WorldGameBlockHelper.isGameBlock(state)) {
                                        set.add((short) (x | z << 4 | y << 8));
                                    }
                                }
                            }
                        }
                        return set;
                    });
                    Futures.addCallback(processedData, new ChunkDataResolver(server, chunk.getWorld(), chunk.getPos()));
                }
            });
            WorldGameBlockHelper.processChunk(chunk);
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void onBlockDestroyed(BlockEvent.BreakEvent event) {
            this.processBlockEvent(event, false);
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
            this.processBlockEvent(event, true);
        }

        private void processBlockEvent(BlockEvent event, boolean blockExists) {
            if (event.isCanceled())
                return;
            World world = event.getWorld();
            if (!world.isRemote)
                return;
            BlockPos pos = event.getPos();
            Chunk chunk = world.getChunkFromBlockCoords(pos);
            if (!WorldGameBlockHelper.isGameBlock(event.getState()))
                return;
            ChunkGameBlockDataProvider.getChunkData(chunk).ifPresent(data -> {
                short index = encodeChunkPosition(pos);
                data.setBlockData(index, blockExists);
            });
        }
    }

    private static final class ChunkDataResolver implements FutureCallback<ShortSet> {

        private final MinecraftServer server;
        private final World world;
        private final ChunkPos chunkPos;

        public ChunkDataResolver(MinecraftServer server, World world, ChunkPos chunkPos) {
            this.server = server;
            this.world = world;
            this.chunkPos = chunkPos;
        }

        @Override
        public void onSuccess(@Nullable ShortSet result) {
            server.addScheduledTask(() -> {
                ChunkProviderServer chunkProvider = (ChunkProviderServer) world.getChunkProvider();
                if (!chunkProvider.chunkExists(chunkPos.x, chunkPos.z)) {
                    return;
                }
                Chunk chunk = world.getChunkFromChunkCoords(chunkPos.x, chunkPos.z);
                Optional<ChunkGameBlockData> optional = ChunkGameBlockDataProvider.getChunkData(chunk);
                if (!optional.isPresent()) {
                    Pubgmc.logger.warn(MARKER, "Failed to load chunk data for chunk {}", chunk.getPos());
                    return;
                }
                ChunkGameBlockData data = optional.get();
                data.set(result);
                if (!result.isEmpty()) {
                    Pubgmc.logger.debug(MARKER, "Saved {} entries for chunk {}", result.size(), chunk.getPos());
                }
                data.markUpToDate();
                WorldGameBlockHelper.processChunk(chunk);
            });
        }

        @Override
        public void onFailure(Throwable t) {
            Pubgmc.logger.error(MARKER, new FormattedMessage("Processing of chunk data {} has failed. No data were saved and chunk will be processed again", chunkPos), t);
        }
    }
}
