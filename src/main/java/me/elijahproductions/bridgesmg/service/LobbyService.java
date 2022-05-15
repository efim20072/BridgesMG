package me.elijahproductions.bridgesmg.service;

import lombok.val;
import me.elijahproductions.bridgesmg.SDK;
import me.elijahproductions.bridgesmg.command.JumpLobbyCommandExecutor;
import me.elijahproductions.bridgesmg.controller.MatchesController;
import me.elijahproductions.bridgesmg.eventlistener.LobbyExitEvent;
import me.elijahproductions.bridgesmg.task.StartMatchCountdownTask;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LobbyService {

    private static final int PLAYERS_GROUP_SIZE = 1;

    private final List<Player> players = new ArrayList<>();
    private MatchesController matchesController;
    private final Plugin plugin;
    private Location spawn;
    private StartMatchCountdownTask task;

    public LobbyService(Plugin plugin) {
        this.matchesController = SDK.get().getMatchesController();
        this.plugin = plugin;
        init();
    }

    private void init() {
        JumpLobbyCommandExecutor.observable.subscribe(
                player -> {
                    //TODO проверка что чел уже зашел
                    if (players.contains(player)) return;
                    players.add(player);
                    if (enough()) {
                        val players = getPlayersGroup();
                        removePlayersFromCommonGroup(players);
                        startTask(players);
                    }
                }
        );


        LobbyExitEvent.observable.subscribe(
                p -> {
                    //TODO хранить разные таски, првоерячть из какой грппы ушел человек и останавливать ту таску а не все
                    players.remove(p);
                    if (!enough()) task.cancel();
                }
        );
    }

    private void startTask(List<Player> players) {
        task = new StartMatchCountdownTask(
                players,
                5,
                () -> {
                    System.out.println("Старт игры");
                    matchesController.createMatch(players);
                }
        );
        task.setOnCancelListener(() -> players.addAll(task.getPlayers()));
        task.runTaskTimer(plugin, 0, 20);
    }

    private List<Player> getPlayersGroup() {
        List<Player> group = new ArrayList<>();
        for (int i = 0; i < PLAYERS_GROUP_SIZE; i++) {
            group.add(players.get(i));
        }
        return group;
    }

    private void removePlayersFromCommonGroup(List<Player> group) {
        group.forEach(players::remove);
    }

    public boolean enough() {
        return players.size() == PLAYERS_GROUP_SIZE;
    }

}
