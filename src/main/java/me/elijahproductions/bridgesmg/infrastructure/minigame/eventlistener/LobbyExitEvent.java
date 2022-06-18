package me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.broadcast.EventsBroadcastReceiver;
import me.elijahproductions.bridgesmg.common.listener.BaseObserver;
import me.elijahproductions.bridgesmg.common.listener.EventObservable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyExitEvent implements Listener {

    public static final EventObservable<Observer> observable = new EventObservable<>();

    private final EventsBroadcastReceiver broadcastReceiver = SDK.get().getEventsBroadcastReceiver();

    public interface Observer extends BaseObserver {
        void onExit(Player player);
    }

    @EventHandler
    public void exit(PlayerTeleportEvent event){
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN) return;
        Player player = event.getPlayer();

        broadcastReceiver.onDie(player);

        observable.listeners.forEach(exit -> exit.onExit(player));
    }
}
