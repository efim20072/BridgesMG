package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers;

import lombok.Value;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure.WaitingRoom;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.WaitingRoomDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@Value
public class WaitingRoomMapper {

    private static WaitingRoomMapper instance;
    WaitingRoomMapper.DataBase dataBaseMapper;

    private WaitingRoomMapper(WaitingRoomMapper.DataBase dataBaseMapper) {
        this.dataBaseMapper = dataBaseMapper;
    }

    public static WaitingRoomMapper get() {
        if (instance == null) instance = new WaitingRoomMapper(new WaitingRoomMapper.DataBase());
        return instance;
    }

    public static class DataBase extends Mapper<WaitingRoomDatabase> {

        @Override
        public WaitingRoomDatabase parseSingle(ResultSet set) {
            try {
                String type = set.getString(set.findColumn(WaitingRoom.TYPE)).toUpperCase(Locale.ROOT);
                int locationId = set.getInt(set.findColumn(WaitingRoom.LOCATION_ID));
                return new WaitingRoomDatabase(type, locationId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }
}
