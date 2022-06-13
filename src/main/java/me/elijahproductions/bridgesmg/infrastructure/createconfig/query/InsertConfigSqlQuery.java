package me.elijahproductions.bridgesmg.infrastructure.createconfig.query;

import lombok.val;
import me.elijahproductions.bridgesmg.domain.query.InsertSqlQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

public class InsertConfigSqlQuery implements InsertSqlQuery {

    private final String name;
    private final String worldName;
    private final long teamSize;
    private final long winScore;

    public InsertConfigSqlQuery(String name, String worldName, long teamSize, long winScore) {
        this.name = name;
        this.worldName = worldName;
        this.teamSize = teamSize;
        this.winScore = winScore;
    }

    @Override
    public long execute(UnitDBWork dbWork) {
        val query = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', %d, %d)",
                DatabaseStructure.GameConfig.TITLE,
                DatabaseStructure.GameConfig.NAME,
                DatabaseStructure.GameConfig.WORLD_NAME,
                DatabaseStructure.GameConfig.TEAM_SIZE,
                DatabaseStructure.GameConfig.WIN_SCORE,
                name,
                worldName,
                teamSize,
                winScore
        );
        return dbWork.insertQuery(query);
    }
}
