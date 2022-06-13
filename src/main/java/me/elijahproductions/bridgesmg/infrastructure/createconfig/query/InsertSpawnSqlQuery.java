package me.elijahproductions.bridgesmg.infrastructure.createconfig.query;

import lombok.val;
import me.elijahproductions.bridgesmg.domain.query.InsertSqlQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

public class InsertSpawnSqlQuery implements InsertSqlQuery {
    private final long configId;
    private final long locationId;
    private final String teamType;

    public InsertSpawnSqlQuery(long configId, long locationId, String teamType) {
        this.configId = configId;
        this.locationId = locationId;
        this.teamType = teamType;
    }

    @Override
    public long execute(UnitDBWork dbWork) {
        val query = String.format(
                "INSERT INTO %s (%s, %s, %s) VALUES (%d, %d, '%s')",
                DatabaseStructure.Spawn.TITLE,
                DatabaseStructure.Spawn.CONFIG_ID,
                DatabaseStructure.Spawn.LOCATION_ID,
                DatabaseStructure.Spawn.TYPE,
                configId,
                locationId,
                teamType
        );
        return dbWork.insertQuery(query);
    }
}
