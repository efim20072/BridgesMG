package me.elijahproductions.bridgesmg.dao.query.configs;

import me.elijahproductions.bridgesmg.dao.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure.Corners;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectTeamCornersByIdSQLQuery implements SelectSQLQuery {

    private final int id;

    public SelectTeamCornersByIdSQLQuery(int id) {
        this.id = id;
    }

    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = "SELECT * FROM " + Corners.TITLE + " WHERE " + Corners.CONFIG_ID + " = " + id;
        return dbWork.selectQuery(query);
    }
}
