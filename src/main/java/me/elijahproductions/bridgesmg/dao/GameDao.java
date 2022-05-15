package me.elijahproductions.bridgesmg.dao;

import me.elijahproductions.bridgesmg.dao.mappers.CornersMapper;
import me.elijahproductions.bridgesmg.dao.mappers.GameConfigMapper;
import me.elijahproductions.bridgesmg.dao.mappers.LocationMapper;
import me.elijahproductions.bridgesmg.dao.mappers.WaitingRoomMapper;
import me.elijahproductions.bridgesmg.dao.query.configs.SelectAllSQLQuery;
import me.elijahproductions.bridgesmg.dao.query.configs.SelectLocationByIdQuery;
import me.elijahproductions.bridgesmg.dao.query.configs.SelectTeamCornersByIdSQLQuery;
import me.elijahproductions.bridgesmg.dao.query.configs.SelectWaitingRoomByConfigId;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;
import me.elijahproductions.bridgesmg.entity.database.CornersDatabase;
import me.elijahproductions.bridgesmg.entity.database.GameConfigDatabase;
import me.elijahproductions.bridgesmg.entity.database.WaitingRoomDatabase;
import org.bukkit.Location;

import java.util.List;

//DAO - data aссess object
public class GameDao {

    private final UnitDBWork dbWork;

    public GameDao(UnitDBWork dbWork) {
        this.dbWork = dbWork;
    }

    public List<GameConfigDatabase> loadAllConfigs() {
        return GameConfigMapper.get().getDataBaseMapper().parseList(
                new SelectAllSQLQuery()
                        .execute(dbWork)
        );
    }

    public List<CornersDatabase> loadCornersGroupById(int id) {
        return CornersMapper.get().getDataBaseMapper().parseList(
                new SelectTeamCornersByIdSQLQuery(id)
                        .execute(dbWork)
        );
    }

    public List<WaitingRoomDatabase> loadWaitingRoomsByConfigId(int id) {
        return WaitingRoomMapper.get().getDataBaseMapper().parseList(
                new SelectWaitingRoomByConfigId(id)
                        .execute(dbWork)
        );
    }

    public Location loadLocationById(int id) {
        return LocationMapper.get().getDataBaseMapper().parseSingle(
                new SelectLocationByIdQuery(id)
                        .execute(dbWork)
        );
    }
}
