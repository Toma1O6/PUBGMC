package dev.toma.pubgmc.common.game.argument;

public final class SharedArguments {
    public static final ArgumentKey<Boolean> ALLOW_AI = new ArgumentKey.Builder<Boolean>().key("ai").fallback(AbstractArgument.bool(true)).build();
    public static final ArgumentKey<Boolean> ALLOW_AIRDROPS = new ArgumentKey.Builder<Boolean>().key("airdrops").fallback(AbstractArgument.bool(true)).build();
}
