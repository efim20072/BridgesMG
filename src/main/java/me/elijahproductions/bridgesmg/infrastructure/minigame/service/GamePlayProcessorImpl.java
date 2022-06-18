package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import me.elijahproductions.bridgesmg.common.listener.BaseObserver;
import me.elijahproductions.bridgesmg.common.listener.EventObservable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamePlayProcessorImpl implements GamePlayProcessor {

    public interface JoinObserver extends BaseObserver {
        void onJump(Player player);
    }

    public static final EventObservable<JoinObserver> observable = new EventObservable<>();

    @Override
    public void join(CommandSender sender, String[] args) {
        observable.listeners.forEach(event -> event.onJump((Player) sender));
    }
}
