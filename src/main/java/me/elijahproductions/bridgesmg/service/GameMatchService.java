package me.elijahproductions.bridgesmg.service;

import me.elijahproductions.bridgesmg.SDK;
import me.elijahproductions.bridgesmg.entity.TeamType;
import me.elijahproductions.bridgesmg.entity.game.GameConfig;
import me.elijahproductions.bridgesmg.entity.game.GameMatch;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class GameMatchService {

    private final GameMatchCreateHelper gameMatchCreateHelper;
    private final GameConfigService gameConfigService;

    public GameMatchService() {
        this.gameMatchCreateHelper = SDK.get().getGameMatchCreateHelper();
        this.gameConfigService = SDK.get().getGameConfigService();
    }

    public GameMatch createMatch(List<Player> players) {
        GameConfig config = gameConfigService.getRandom();
        System.out.println("CFG" + config);
        GameMatch match = gameMatchCreateHelper.createMatch(config);
        match.setPlayers(players);
        //TODO некрасиво
        System.out.println("PLAYERS - " + players.toString());
        System.out.println("WaitingRooms - " + match.getWaitingRooms().getRooms().get(TeamType.BLUE));
        players.forEach(player -> player.teleport(match.getWaitingRooms().getRooms().get(TeamType.BLUE)));
        return match;
    }
}
