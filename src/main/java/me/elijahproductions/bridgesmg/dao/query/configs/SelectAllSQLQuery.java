package me.elijahproductions.bridgesmg.dao.query.configs;

import me.elijahproductions.bridgesmg.dao.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectAllSQLQuery implements SelectSQLQuery {
    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = "SELECT * FROM configs";
        return dbWork.selectQuery(query);
    }
}
