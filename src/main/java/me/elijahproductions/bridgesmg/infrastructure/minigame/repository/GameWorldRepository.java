package me.elijahproductions.bridgesmg.infrastructure.minigame.repository;

import org.bukkit.World;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameWorldRepository {

    private final Map<World, File> map;

    public GameWorldRepository() {
        this.map = new HashMap<>();
    }

    public void addWorld(World world, File path) {
        map.put(world, path);
    }

    public File getWorldPath(World world) {
        return map.get(world);
    }

}
