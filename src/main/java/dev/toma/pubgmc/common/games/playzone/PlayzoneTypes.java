package dev.toma.pubgmc.common.games.playzone;

import dev.toma.pubgmc.Pubgmc;
import dev.toma.pubgmc.api.game.playzone.Playzone;
import dev.toma.pubgmc.api.game.playzone.PlayzoneSerializer;
import dev.toma.pubgmc.api.game.playzone.PlayzoneType;

public final class PlayzoneTypes {

    public static final PlayzoneType<StaticPlayzone> STATIC_PLAYZONE = create("static", new StaticPlayzone.Serializer());
    public static final PlayzoneType<DynamicPlayzone> DYNAMIC_PLAYZONE = create("dynamic", new DynamicPlayzone.Serializer());

    private static <A extends Playzone> PlayzoneType<A> create(String path, PlayzoneSerializer<A> serializer) {
        return PlayzoneType.create(Pubgmc.getResource(path), serializer);
    }
}
