package me.elijahproductions.bridgesmg.infrastructure.minigame.dao.mappers;

import lombok.Value;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.DatabaseStructure.GameConfig;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.GameConfigDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

@Value
public class GameConfigMapper {

    private static GameConfigMapper instance;
    GameConfigMapper.DataBase dataBaseMapper;

    private GameConfigMapper(GameConfigMapper.DataBase dataBaseMapper) {
        this.dataBaseMapper = dataBaseMapper;
    }

    public static GameConfigMapper get() {
        if (instance == null) instance = new GameConfigMapper(new GameConfigMapper.DataBase());
        return instance;
    }

    public static class DataBase extends Mapper<GameConfigDatabase> {

        @Override
        public GameConfigDatabase parseSingle(ResultSet set) {
            try {
                int id = set.getInt(set.findColumn(GameConfig.ID));
                String name = set.getString(set.findColumn(GameConfig.NAME));
                int winScore = set.getInt(set.findColumn(GameConfig.WIN_SCORE));
                int teamSize = set.getInt(set.findColumn(GameConfig.TEAM_SIZE));
                String worldName = set.getString(set.findColumn(GameConfig.WORLD_NAME));
                return new GameConfigDatabase(id, teamSize, name, winScore, worldName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }


    /*public static List<GameConfig> parseList(ResultSet set) {
        List<GameConfig> configs = new ArrayList<>();
        try {
            while (set.next()) {
                configs.add(parseSingle(set));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return configs;
    }

    public static GameConfig parseSingle(ResultSet set) {
        try {
            return new GameConfig.Builder()
                    .setName(set.getString(set.findColumn(DatabaseStructure.GameConfig.NAME)))
                    .setTeamSize(set.getInt(set.findColumn(DatabaseStructure.GameConfig.TEAM_SIZE)))
                    .build();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }*/
}
