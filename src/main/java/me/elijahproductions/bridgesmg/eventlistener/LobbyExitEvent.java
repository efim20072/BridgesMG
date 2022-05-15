package me.elijahproductions.bridgesmg.eventlistener;

import me.elijahproductions.bridgesmg.broadcast.EventsBroadcastReceiver;
import common.listener.BaseObserver;
import common.listener.EventObservable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyExitEvent implements Listener {

    public static final EventObservable<Observer> observable = new EventObservable<>();

    EventsBroadcastReceiver broadcastReceiver;

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
