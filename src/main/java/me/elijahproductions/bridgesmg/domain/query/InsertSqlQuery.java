package me.elijahproductions.bridgesmg.domain.query;

import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

import java.sql.ResultSet;

public interface InsertSqlQuery {
    long execute(UnitDBWork dbWork);
}
