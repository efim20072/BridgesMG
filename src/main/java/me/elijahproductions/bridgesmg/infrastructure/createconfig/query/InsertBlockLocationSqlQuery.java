package me.elijahproductions.bridgesmg.infrastructure.createconfig.query;

import lombok.val;
import me.elijahproductions.bridgesmg.domain.query.InsertSqlQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import org.bukkit.Location;

public class InsertBlockLocationSqlQuery implements InsertSqlQuery {

    private final Location location;

    public InsertBlockLocationSqlQuery(Location location) {
        this.location = location;
    }

    @Override
    public long execute(UnitDBWork dbWork) {
        val query = String.format(
                "INSERT INTO %s VALUES (NULL, %d, %d, %d)",
                DatabaseStructure.Location.TITLE,
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        );

        System.out.println(query);
        return dbWork.insertQuery(query);
    }
}
