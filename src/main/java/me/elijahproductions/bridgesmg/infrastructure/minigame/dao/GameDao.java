package me.elijahproductions.bridgesmg.infrastructure.minigame.dao;

import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers.CornersMapper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers.GameConfigMapper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers.LocationMapper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers.WaitingRoomMapper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs.SelectAllSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs.SelectLocationByIdQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs.SelectTeamCornersByIdSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs.SelectWaitingRoomByConfigId;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.CornersDatabase;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.GameConfigDatabase;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.WaitingRoomDatabase;
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
