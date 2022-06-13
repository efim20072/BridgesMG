package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database;

import lombok.Value;

@Value
public class WaitingRoomDatabase {
    String type;
    int locationId;

    public WaitingRoomDatabase(String type, int locationId) {
        this.type = type;
        this.locationId = locationId;
    }
}
