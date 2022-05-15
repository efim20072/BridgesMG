package me.elijahproductions.bridgesmg.entity.game;

import lombok.Data;
import me.elijahproductions.bridgesmg.entity.TeamType;
import me.elijahproductions.bridgesmg.entity.WaitingRoom;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameWaitingRooms {

    private Map<TeamType, Location> rooms;

    public GameWaitingRooms(List<WaitingRoom> list) {
        rooms = new HashMap<>();
        list.forEach(room -> rooms.put(
                room.getTeamType(),
                room.getLocation()
        ));

        System.out.println("GameWaitingRooms List " + list);
        System.out.println("GameWaitingRooms rooms " + rooms);
    }

    public Location getLocationByType(TeamType teamType) {
        return rooms.get(teamType);
    }

    @Override
    public String toString() {
        return rooms.toString();
    }
}
