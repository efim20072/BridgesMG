package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import lombok.val;
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
        int counter = 0;
        for (Player player : players) {
            val teamType = ++counter % 2 == 0 ? TeamType.BLUE : TeamType.RED;
            player.teleport(
                    match.getWaitingRooms()
                            .getRooms()
                            .get(teamType)
            );
        }

        portalManager.createPortals(match);

        return match;
    }
}
