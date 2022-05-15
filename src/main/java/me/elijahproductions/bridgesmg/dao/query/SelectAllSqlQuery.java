package me.elijahproductions.bridgesmg.dao.query;

import me.elijahproductions.bridgesmg.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;

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
