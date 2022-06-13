package me.elijahproductions.bridgesmg.infrastructure.minigame.controller;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameMatchesRepository;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.GameMatchService;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * !!!!!!!!! EBLAN, все сервисы, репозитори, и тд в SDK !!!!!!!!!!!!!!
 */

/* createMatch ->
1. Выбирает игровую конфигурацию ✔️️
2. Через сервис создает матч ✔️
3. Добавляет в MatchesRepository ✔️
4. ТП игроков в матч ✔️
5. старт матрча (консоль) ✔️

 */
public class MatchesController {

    private final GameMatchService gameMatchService;
    private final GameMatchesRepository gameMatchesRepository;

    public MatchesController() {
        this.gameMatchesRepository = SDK.get().getGameMatchesRepository();
        gameMatchService = new GameMatchService(); //TODO move to SDK
    }

    public void createMatch(List<Player> players) {
        GameMatch match = gameMatchService.createMatch(players);
        gameMatchesRepository.addMatch(match);
        match.getManager().start();
    }


}
