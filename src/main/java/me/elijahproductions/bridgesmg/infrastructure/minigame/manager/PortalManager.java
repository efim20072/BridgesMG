package me.elijahproductions.bridgesmg.infrastructure.minigame.manager;

import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GamePortals;
import me.elijahproductions.bridgesmg.infrastructure.minigame.utility.PortalUtil;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;

public class PortalManager {

    public void createPortals(GameMatch match) {
        GamePortals portals = match.getGamePortals();
        for (var entry : portals.getPortals().entrySet()) {
            List<Location> locations = PortalUtil.blocksFromTwoPoints(
                    entry.getValue().getFirst(),
                    entry.getValue().getSecond()
            );

            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).getBlock().setType(Material.END_PORTAL);
            }
        }
    }
}