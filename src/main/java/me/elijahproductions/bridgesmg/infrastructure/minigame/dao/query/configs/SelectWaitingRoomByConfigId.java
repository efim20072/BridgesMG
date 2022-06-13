package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs;

import me.elijahproductions.bridgesmg.domain.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure.WaitingRoom;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;

import java.sql.ResultSet;

public class SelectWaitingRoomByConfigId implements SelectSQLQuery {

    private final int id;

    public SelectWaitingRoomByConfigId(int id){
        this.id = id;
    }

    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = %s",
                WaitingRoom.TITLE,
                WaitingRoom.CONFIG_ID,
                id
        );
        return dbWork.selectQuery(query);
    }
}
