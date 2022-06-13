package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs;

import me.elijahproductions.bridgesmg.domain.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectLocationByIdQuery implements SelectSQLQuery {

    private final int id;

    public SelectLocationByIdQuery(int id){
        this.id = id;
    }

    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = %d",
                DatabaseStructure.Location.TITLE,
                DatabaseStructure.Location.ID,
                id
        );
        return dbWork.selectQuery(query);
    }
}
