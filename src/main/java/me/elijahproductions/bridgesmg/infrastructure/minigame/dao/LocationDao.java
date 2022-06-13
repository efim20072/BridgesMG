package me.elijahproductions.bridgesmg.infrastructure.minigame.dao;

import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers.LocationMapper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.query.configs.SelectAllSQLQuery;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import org.bukkit.Location;

import java.util.List;

public class LocationDao {

    private final UnitDBWork dbWork;

    public LocationDao(UnitDBWork dbWork) {
        this.dbWork = dbWork;
    }

    public List<Location> loadAll() {
        return LocationMapper.get().getDataBaseMapper().parseList(
                new SelectAllSQLQuery()
                        .execute(dbWork)
        );
    }
}
