package me.elijahproductions.bridgesmg.entity.game;

import lombok.Data;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class GameMatch {

    UUID uuid;
    String name;
    int teamSize;

    World gameWorld;
    World lobbyWorld;

    GamePortals gamePortals;

    GameWaitingRooms waitingRooms;

    GameScore gameScore;

    GameMatchState state = GameMatchState.OFFLINE;

    private List<Player> players;
    private GameMatchManager manager;

    private GameMatch(
            UUID uuid,
            String name,
            int teamSize,
            GamePortals gamePortals,
            GameWaitingRooms waitingRooms
    ) {
        this.uuid = uuid;
        this.name = name;
        this.teamSize = teamSize;
        this.gamePortals = gamePortals;
        this.waitingRooms = waitingRooms;
        this.gameScore = new GameScore();
        this.manager = new GameMatchManager(this);
    }

    public static GameMatch of(GameConfig config) {
        return new GameMatch(
                UUID.randomUUID(),
                config.getName(),
                config.getTeamSize(),
                config.getGamePortals(),
                config.getWaitingRooms()
        );
    }

    public void setGameWorld(World gameWorld) {
        this.gameWorld = gameWorld;
    }

    List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setLobbyWorld(World lobbyWorld) {
        this.lobbyWorld = lobbyWorld;
    }

    public GameMatchManager getManager() {
        return manager;
    }
}
