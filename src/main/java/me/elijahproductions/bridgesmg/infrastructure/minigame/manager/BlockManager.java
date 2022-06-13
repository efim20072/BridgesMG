package me.elijahproductions.bridgesmg.infrastructure.minigame.manager;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {
    private final Set<Material> allowedToBreak = new HashSet<>();

    public BlockManager() {
        allowedToBreak.add(Material.RED_TERRACOTTA);
        allowedToBreak.add(Material.BLUE_TERRACOTTA);
    }

    public boolean canBreak(Block block){return allowedToBreak.contains(block.getType());}
}
