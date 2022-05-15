package me.elijahproductions.bridgesmg.dao;

import me.elijahproductions.bridgesmg.dao.mappers.GameConfigMapper;
import me.elijahproductions.bridgesmg.dao.mappers.LocationMapper;
import me.elijahproductions.bridgesmg.dao.query.configs.SelectAllSQLQuery;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;
import me.elijahproductions.bridgesmg.entity.database.GameConfigDatabase;
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
