package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs;

import me.elijahproductions.bridgesmg.domain.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectAllSQLQuery implements SelectSQLQuery {
    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = "SELECT * FROM configs";
        return dbWork.selectQuery(query);
    }
}
