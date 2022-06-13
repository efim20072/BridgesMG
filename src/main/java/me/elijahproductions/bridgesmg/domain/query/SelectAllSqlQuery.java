package me.elijahproductions.bridgesmg.domain.query;

import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectAllSqlQuery implements SelectSQLQuery {

    private final String table;

    public SelectAllSqlQuery(String table) {
        this.table = table;
    }

    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = "SELECT * FROM " + table;
        return dbWork.selectQuery(query);
    }
}
