package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import lombok.val;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameConfig;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import org.bukkit.World;

import java.util.Arrays;

public class GameMatchCreateHelper {

    private final WorldGenerateService worldGenerateService;

    public GameMatchCreateHelper(WorldGenerateService worldGenerateService) {
        this.worldGenerateService = worldGenerateService;
    }

    public GameMatch createMatch(GameConfig config) {
        World gameWorld = worldGenerateService.generateWorld(config.getGameWorldName());
        GameMatch gameMatch = GameMatch.of(config);
        gameMatch.setGameWorld(gameWorld);
        setWorldToLocations(config, gameWorld);
        return gameMatch;
    }

    private void setWorldToLocations(GameConfig config, World world) {
        Arrays.asList(TeamType.values()).forEach(teamType -> {
            setWorldToPortalLocations(config, world, teamType);
            setWorldToWaitingRoomsLocation(config, world, teamType);
        });
    }

    private void setWorldToPortalLocations(GameConfig config, World world, TeamType type) {

        val a =config.getGamePortals()
                .getPortals();
                //.get(type);
        System.out.println("TYPE********** - " + type);
        System.out.println("Pots********** - " + a);
        /*a
                .getAllLocations()
                .forEach(location -> location.setWorld(world));*/

    }

    private void setWorldToWaitingRoomsLocation(GameConfig config, World world, TeamType type) {
        config.getWaitingRooms()
                .getRooms()
                .get(type)
                .setWorld(world);
    }
}
