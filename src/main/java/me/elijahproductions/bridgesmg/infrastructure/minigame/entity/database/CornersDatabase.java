package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database;

import lombok.Value;

@Value
public class CornersDatabase {
    String teamType;
    int firstLocId;
    int secondLocId;

    public CornersDatabase(String teamType, int firstLocId, int secondLocId) {
        this.teamType = teamType;
        this.firstLocId = firstLocId;
        this.secondLocId = secondLocId;
    }
}
