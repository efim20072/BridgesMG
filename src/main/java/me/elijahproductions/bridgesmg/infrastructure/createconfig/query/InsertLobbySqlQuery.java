package me.elijahproductions.bridgesmg.infrastructure.createconfig.query;

import lombok.val;
import me.elijahproductions.bridgesmg.domain.query.InsertSqlQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

public class InsertLobbySqlQuery implements InsertSqlQuery {

    private final long configId;
    private final long locationId;

    public InsertLobbySqlQuery(long configId, long locationId) {
        this.configId = configId;
        this.locationId = locationId;
    }

    @Override
    public long execute(UnitDBWork dbWork) {
        val query = String.format(
                "INSERT INTO %s (%s, %s) VALUES (%d, %d)",
                DatabaseStructure.Lobby.TITLE,
                DatabaseStructure.Lobby.CONFIG_ID,
                DatabaseStructure.Lobby.LOCATION_ID,
                configId,
                locationId
        );
        return dbWork.insertQuery(query);
    }
}
