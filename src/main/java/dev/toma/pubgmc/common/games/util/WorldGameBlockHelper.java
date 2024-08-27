package dev.toma.pubgmc.common.games.util;

import dev.toma.pubgmc.api.capability.ChunkGameBlockDataProvider;
import dev.toma.pubgmc.util.helper.GameHelper;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public final class WorldGameBlockHelper {

    private static final List<BlockMatcher> MATCHERS = new ArrayList<>();

    private WorldGameBlockHelper() {
    }

    public static void init() {
        register(state -> state.getBlock() instanceof BlockDoor && state.getValue(BlockDoor.HALF) == BlockDoor.EnumDoorHalf.LOWER, (world, pos, state) -> ((BlockDoor) state.getBlock()).toggleDoor(world, pos, false));
        register(state -> state.getBlock() instanceof BlockFenceGate, (world, pos, state) -> toggleCloseable(world, pos, state, BlockFenceGate.OPEN));
        register(state -> state.getBlock() instanceof BlockTrapDoor, (world, pos, state) -> toggleCloseable(world, pos, state, BlockTrapDoor.OPEN));
    }

    public static void processChunk(Chunk chunk) {
        World world = chunk.getWorld();
        UUID currentGameId = GameHelper.getGameUUID(world);
        ChunkGameBlockDataProvider.getChunkData(chunk).ifPresent(data -> {
            if (data.isSameGameId(currentGameId))
                return;
            Collection<Short> blocks = data.getBlockData();
            for (short positionData : blocks) {
                int x = positionData & 0xF;
                int z = positionData >> 4 & 0xF;
                int y = positionData >> 8 & 0xFF;
                BlockPos pos = new BlockPos(x, y, z);
                IBlockState state = chunk.getBlockState(pos);
                WorldGameBlockHelper.OnBlockLoaded listener = WorldGameBlockHelper.getEvent(state);
                if (listener != null) {
                    ChunkPos chunkPos = chunk.getPos();
                    BlockPos worldPosition = chunkPos.getBlock(x, y, z);
                    listener.onLoaded(world, worldPosition, state);
                }
            }
            data.updateGameId(currentGameId);
        });
    }

    public static boolean isGameBlock(IBlockState state) {
        for (BlockMatcher matcher : MATCHERS) {
            if (matcher.test(state))
                return true;
        }
        return false;
    }

    public static OnBlockLoaded getEvent(IBlockState state) {
        for (BlockMatcher matcher : MATCHERS) {
            if (matcher.test(state)) {
                return matcher.onBlockLoaded;
            }
        }
        return null;
    }

    private static void register(Predicate<IBlockState> predicate, OnBlockLoaded onBlockLoaded) {
        MATCHERS.add(new BlockMatcher(predicate, onBlockLoaded));
    }

    private static void toggleCloseable(World world, BlockPos pos, IBlockState state, PropertyBool bool) {
        if (state.getValue(bool)) {
            state = state.withProperty(bool, false);
            world.setBlockState(pos, state, 10);
        }
    }

    private static final class BlockMatcher implements Predicate<IBlockState> {

        private final Predicate<IBlockState> delegate;
        private final OnBlockLoaded onBlockLoaded;

        public BlockMatcher(Predicate<IBlockState> delegate, OnBlockLoaded onBlockLoaded) {
            this.delegate = delegate;
            this.onBlockLoaded = onBlockLoaded;
        }

        @Override
        public boolean test(IBlockState state) {
            return this.delegate.test(state);
        }

        public void onLoaded(World world, BlockPos pos, IBlockState state) {
            this.onBlockLoaded.onLoaded(world, pos, state);
        }
    }

    public interface OnBlockLoaded {
        void onLoaded(World world, BlockPos pos, IBlockState state);
    }
}
