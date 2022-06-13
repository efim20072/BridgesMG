package me.elijahproductions.bridgesmg.infrastructure.minigame.repository;

import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.GameDao;
import me.elijahproductions.bridgesmg.common.entity.Corners;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.CornersDatabase;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.database.GameConfigDatabase;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameConfig;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GamePortals;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameWaitingRooms;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameConfigRepository {
    private final GameDao gameDao;

    public GameConfigRepository(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public List<GameConfig> getGameConfigs() {
        List<GameConfigDatabase> configDatabases = gameDao.loadAllConfigs();
        List<GameConfig> configs = new ArrayList<>();
        for (GameConfigDatabase config : configDatabases)
            configs.add(
                    GameConfig.Builder.create()
                            .setId(config.getId())
                            .setName(config.getName())
                            .setGameWorldName(config.getGameWorldName())
                            .setTeamSize(config.getTeamSize())
                            .setGamePortals(new GamePortals(generateCornersGroup(config.getId())))
                            .setWaitingRooms(generateGameWaitingRooms(config.getId()))
                            .build()
            );
        return configs;
    }

    private List<Corners> generateCornersGroup(int configId) {
        List<CornersDatabase> cornersDatabase = gameDao.loadCornersGroupById(configId);
        List<Corners> cornersList = new ArrayList<>();
        cornersDatabase.forEach(value -> cornersList.add(generateCorners(value)));
        return cornersList;
    }

    private Corners generateCorners(CornersDatabase cornersDatabase) {

        Location first = gameDao.loadLocationById(cornersDatabase.getFirstLocId());
        Location second = gameDao.loadLocationById(cornersDatabase.getSecondLocId());
        TeamType teamType = TeamType.valueOf(cornersDatabase.getTeamType());
        System.out.println(teamType + " : " + first + " : " + second);
        return new Corners(
                teamType,
                first,
                second
        );
    }

    private GameWaitingRooms generateGameWaitingRooms(int configId) {
        List<WaitingRoom> waitingRoomList = new ArrayList<>();

        gameDao.loadWaitingRoomsByConfigId(configId).forEach(value ->
                waitingRoomList.add(
                        new WaitingRoom(
                                TeamType.valueOf(value.getType()),
                                gameDao.loadLocationById(value.getLocationId())
                        )
                )
        );
        return new GameWaitingRooms(waitingRoomList);
    }
}
