package me.elijahproductions.bridgesmg.dao.query.configs;

import me.elijahproductions.bridgesmg.dao.query.SelectSQLQuery;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure.WaitingRoom;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;

import java.sql.ResultSet;

public class AddExemple implements SelectSQLQuery {

    private final int x;
    private final int z;

    public AddExemple(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public ResultSet execute(UnitDBWork dbWork) {
        String query = String.format(
                "Insert *** * (%s, %s)",
                x,
                z
        );
        return dbWork.selectQuery(query);
    }
}
