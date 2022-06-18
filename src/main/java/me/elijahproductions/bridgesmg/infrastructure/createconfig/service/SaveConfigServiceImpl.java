package me.elijahproductions.bridgesmg.infrastructure.createconfig.service;

import lombok.val;
import me.elijahproductions.bridgesmg.common.entity.Corners;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.common.entity.WaitingRoom;
import me.elijahproductions.bridgesmg.domain.createconfig.entity.CreateState;
import me.elijahproductions.bridgesmg.domain.createconfig.service.SaveConfigService;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.query.*;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import org.bukkit.Location;

public class SaveConfigServiceImpl implements SaveConfigService {

    private final UnitDBWork unitDBWork;

    private long configId = -1;

    public SaveConfigServiceImpl(UnitDBWork unitDBWork) {
        this.unitDBWork = unitDBWork;
    }

    @Override
    public void save(CreateState state) {
        configId = insertConfig(state);
        insertLobby(state.getLobby());
        state.getPortals().forEach(this::insertCorners);
        state.getTeamSpawns().forEach(this::insertSpawn);
        state.getWaitingRooms().values().forEach(this::insertWaitingRoom);
    }

    private long insertConfig(CreateState state) {
        return new InsertConfigSqlQuery(
                state.getName(),
                state.getWorldName(),
                state.getTeamSize(),
                state.getMaxScore()
        ).execute(unitDBWork);
    }

    private void insertLobby(Location location) {
        val locationId = insertLocation(location);
        new InsertLobbySqlQuery(
                configId,
                locationId
        ).execute(unitDBWork);
    }

    private void insertCorners(TeamType teamType, Corners corners) {
        val firstLocationId = insertLocation(corners.getFirst());
        val secondLocationId = insertLocation(corners.getSecond());

        new InsertPortalSqlQuery(
                configId,
                firstLocationId,
                secondLocationId,
                teamType.getTitle()
        ).execute(unitDBWork);
    }

    private void insertSpawn(TeamType teamType, Location location) {
        val locationId = insertLocation(location);

        new InsertSpawnSqlQuery(
                configId,
                locationId,
                teamType.getTitle()
        ).execute(unitDBWork);
    }

    private void insertWaitingRoom(WaitingRoom waitingRoom) {
        val locationId = insertLocation(waitingRoom.getLocation());

        new InsertWaitingRoomSqlQuery(
                configId,
                locationId,
                waitingRoom.getTeamType().getTitle()
        ).execute(unitDBWork);
    }

    private long insertLocation(Location location) {
        return new InsertBlockLocationSqlQuery(location).execute(unitDBWork);
    }
}
