package me.elijahproductions.bridgesmg.infrastructure.minigame.task;

import org.bukkit.entity.Player;

import java.util.List;

public class StartMatchCountdownTask extends CountdownTask{

    private List<Player> players;

    public StartMatchCountdownTask(List<Player> players, OnEndListener listener) {
        super(listener);
        this.players = players;
    }

    public StartMatchCountdownTask(List<Player> players, int timeLeft, OnEndListener listener) {
        super(timeLeft, listener);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
