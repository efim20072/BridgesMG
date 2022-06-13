package me.elijahproductions.bridgesmg.common.entity;

import lombok.Value;
import org.bukkit.Location;

@Value
public class WaitingRoom {
    TeamType teamType;
    Location location;

    public WaitingRoom(TeamType teamType, Location location) {
        this.teamType = teamType;
        this.location = location;
    }
}
