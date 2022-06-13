package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game;

import lombok.Data;
import me.elijahproductions.bridgesmg.common.entity.Corners;
import me.elijahproductions.bridgesmg.common.entity.TeamType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GamePortals {

    private Map<TeamType, Corners> portals;

    public GamePortals(List<Corners> list){
        portals = new HashMap<>();
        list.forEach(corners -> {
            TeamType type = corners.getTeamType();
            portals.put(type, corners);
        });
        System.out.println("GamePortals List " + list);
        System.out.println("GamePortals portals " + portals);
    }

    @Override
    public String toString() {
        return portals.toString();
    }
}
