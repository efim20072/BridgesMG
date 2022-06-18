package me.elijahproductions.bridgesmg.domain.createconfig.entity;

import lombok.Data;
import lombok.Getter;
import me.elijahproductions.bridgesmg.common.entity.Corners;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

@Data
public class CreateState {
    String name = null;
    String worldName = null;
    int teamSize = 2;
    int maxScore = 5;
    Location lobby = null;

    @Getter
    Map<TeamType, Corners> portals = new HashMap<>();

    @Getter
    Map<TeamType, Location> teamSpawns = new HashMap<>();

    @Getter
    Map<TeamType, WaitingRoom> waitingRooms = new HashMap<>();
}
