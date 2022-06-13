package me.elijahproductions.bridgesmg.infrastructure.minigame.utility;

import lombok.experimental.UtilityClass;
import me.elijahproductions.bridgesmg.common.utils.entity.Pair;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PortalUtil {

    public static List<Location> blocksFromTwoPoints(Pair<Location, Location> corners) {
        return blocksFromTwoPoints(corners.first, corners.second);
    }

    public static boolean locationInsideLocations(List<Location> locations, Location location) {
        for (Location loc : locations) {
            if (compareLocationByBlockCoordinates(location, loc))
                return true;
        }
        return false;
    }

    public static boolean compareLocationByBlockCoordinates(Location first, Location second) {
        return Math.round(first.getX()) == Math.round(second.getX()) &&
                //     Math.round(first.getY()) == Math.round(second.getY()) &&
                Math.round(first.getZ()) == Math.round(second.getZ());
    }

    public static List<Location> blocksFromTwoPoints(Location from, Location to) {
        List<Location> locations = new ArrayList<>();

        int topBlockX = Math.max(from.getBlockX(), to.getBlockX());
        int bottomBlockX = Math.min(from.getBlockX(), to.getBlockX());

        int topBlockY = Math.max(from.getBlockY(), to.getBlockY());
        int bottomBlockY = Math.min(from.getBlockY(), to.getBlockY());

        int topBlockZ = Math.max(from.getBlockZ(), to.getBlockZ());
        int bottomBlockZ = Math.min(from.getBlockZ(), to.getBlockZ());

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    locations.add(from.getWorld().getBlockAt(x, y, z).getLocation());
                }
            }
        }
        return locations;
    }
}
