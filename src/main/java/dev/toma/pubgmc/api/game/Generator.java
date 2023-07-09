package dev.toma.pubgmc.api.game;

public interface Generator extends GameObject {

    void generate(GenerationType.Context context);
}
