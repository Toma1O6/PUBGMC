package dev.toma.pubgmc.common.game.map;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    private final String mapName;
    private final List<Point> spawnPoints;
    private Area2D area;
    private Area3D lobby;

    public GameMap(String name) {
        this.mapName = name;
        this.spawnPoints = new ArrayList<>();
    }

    public void assignArea(Area2D area) {
        this.area = area;
    }

    public void assignLobby(Area3D lobby) {
        this.lobby = lobby;
    }

    public Area2D getArea() {
        return area;
    }

    public Area3D getLobby() {
        return lobby;
    }

    public void addSpawnPoint(Point point) {
        spawnPoints.add(point);
    }

    public void removeSpawnPoint(Point point) {
        spawnPoints.remove(point);
    }

    public String getMapName() {
        return mapName;
    }
}
