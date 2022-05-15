package me.elijahproductions.bridgesmg.dao.mappers;

import lombok.Value;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Value
public class LocationMapper {

    private static LocationMapper instance;
    LocationMapper.DataBase dataBaseMapper;

    private LocationMapper(LocationMapper.DataBase dataBaseMapper) {
        this.dataBaseMapper = dataBaseMapper;
    }

    public static LocationMapper get() {
        if (instance == null) instance = new LocationMapper(new LocationMapper.DataBase());
        return instance;
    }

    public static class DataBase extends Mapper<Location> {

        @Override
        @Nullable
        public Location parseSingle(ResultSet set) {
            try {
                int x = set.getInt(set.findColumn(DatabaseStructure.Location.X));
                int y = set.getInt(set.findColumn(DatabaseStructure.Location.Y));
                int z = set.getInt(set.findColumn(DatabaseStructure.Location.Z));
                return new Location(null, x, y, z);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }
}
