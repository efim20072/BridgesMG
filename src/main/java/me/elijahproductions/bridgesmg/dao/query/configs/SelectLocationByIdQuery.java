package me.elijahproductions.bridgesmg.dao.query.configs;

import me.elijahproductions.bridgesmg.dao.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;

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
