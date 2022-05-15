package me.elijahproductions.bridgesmg.dao.mappers;

import lombok.Value;
import me.elijahproductions.bridgesmg.entity.DatabaseStructure;
import me.elijahproductions.bridgesmg.entity.database.CornersDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Value
public class CornersMapper {

    private static CornersMapper instance;
    CornersMapper.DataBase dataBaseMapper;

    private CornersMapper(CornersMapper.DataBase dataBaseMapper) {
        this.dataBaseMapper = dataBaseMapper;
    }

    public static CornersMapper get() {
        if (instance == null) instance = new CornersMapper(new CornersMapper.DataBase());
        return instance;
    }

    public static class DataBase extends Mapper<CornersDatabase> {

        @Override
        public CornersDatabase parseSingle(ResultSet set) {
            try {
                String type = set.getString(set.findColumn(DatabaseStructure.Corners.TYPE)).toUpperCase(Locale.ROOT);
                int firstId = set.getInt(set.findColumn(DatabaseStructure.Corners.FIRST_LOCATION_ID));
                int secondId = set.getInt(set.findColumn(DatabaseStructure.Corners.SECOND_LOCATION_ID));

                return new CornersDatabase(type, firstId, secondId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

        @Override
        public List<CornersDatabase> parseList(ResultSet set) {
            List<CornersDatabase> list = new ArrayList<>();
            try {
                while (set.next()) list.add(parseSingle(set));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return list;
        }
    }
}
