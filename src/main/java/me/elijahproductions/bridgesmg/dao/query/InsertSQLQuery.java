package me.elijahproductions.bridgesmg.dao.query;

import me.elijahproductions.bridgesmg.entity.UnitDBWork;

import java.sql.ResultSet;


public interface InsertSQLQuery {
    ResultSet execute(UnitDBWork dbWork);
}
