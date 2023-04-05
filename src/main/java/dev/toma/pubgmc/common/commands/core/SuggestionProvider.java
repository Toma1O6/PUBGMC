package dev.toma.pubgmc.common.commands.core;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

@FunctionalInterface
public interface SuggestionProvider {

    List<String> suggest(Context context);

    class Context {

        private final MinecraftServer server;
        private final ICommandSender sender;
        private final @Nullable BlockPos lookingAt;
        private final int cursor;

        public Context(MinecraftServer server, ICommandSender sender, @Nullable BlockPos lookingAt, int cursor) {
            this.server = server;
            this.sender = sender;
            this.lookingAt = lookingAt;
            this.cursor = cursor;
        }

        public MinecraftServer getServer() {
            return server;
        }

        public ICommandSender getSender() {
            return sender;
        }

        @Nullable
        public BlockPos getTargettedPosition() {
            return lookingAt;
        }

        public int getCursor() {
            return cursor;
        }
    }
}
