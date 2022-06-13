package me.elijahproductions.bridgesmg.infrastructure.createconfig.query;

import lombok.val;
import me.elijahproductions.bridgesmg.domain.query.InsertSqlQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import org.bukkit.Location;

public class InsertPortalSqlQuery implements InsertSqlQuery {

    private final long configId;
    private final long firstLocationId;
    private final long secondLocationId;
    private final String teamType;

    public InsertPortalSqlQuery(long configId, long firstLocationId, long secondLocationId, String teamType) {
        this.configId = configId;
        this.firstLocationId = firstLocationId;
        this.secondLocationId = secondLocationId;
        this.teamType = teamType;
    }

    @Override
    public long execute(UnitDBWork dbWork) {
        val query = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES (%d, %d, %d, '%s')",
                DatabaseStructure.Corners.TITLE,
                DatabaseStructure.Corners.CONFIG_ID,
                DatabaseStructure.Corners.FIRST_LOCATION_ID,
                DatabaseStructure.Corners.SECOND_LOCATION_ID,
                DatabaseStructure.Corners.TYPE,
                configId,
                firstLocationId,
                secondLocationId,
                teamType
        );
        return dbWork.insertQuery(query);
    }
}
