package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs;

import me.elijahproductions.bridgesmg.domain.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure.Corners;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

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
