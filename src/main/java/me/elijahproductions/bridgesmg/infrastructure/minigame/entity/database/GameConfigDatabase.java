package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class GameConfigDatabase {
     int id;
     int teamSize;
     String name;
     int winScore;
     String gameWorldName;
}
