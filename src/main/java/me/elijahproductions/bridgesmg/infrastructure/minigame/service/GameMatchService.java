package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameConfig;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import me.elijahproductions.bridgesmg.infrastructure.minigame.manager.PortalManager;
import org.bukkit.entity.Player;

import java.util.List;

public class GameMatchService {

    private final GameMatchCreateHelper gameMatchCreateHelper;
    private final GameConfigService gameConfigService;
    private PortalManager portalManager;

    public GameMatchService() {
        this.gameMatchCreateHelper = SDK.get().getGameMatchCreateHelper();
        this.gameConfigService = SDK.get().getGameConfigService();
        this.portalManager = SDK.get().getPortalManager();
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

        //TODO PortalManager -> create portals
        portalManager.createPortals(match);

        return match;
    }
}
