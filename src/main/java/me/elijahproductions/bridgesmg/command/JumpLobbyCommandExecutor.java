package me.elijahproductions.bridgesmg.command;


import common.listener.EventObservable;
import common.listener.BaseObserver;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JumpLobbyCommandExecutor implements CommandsListener {

    public static final EventObservable<Observer> observable = new EventObservable<>();

    @Override
    public String getLabel() {
        return "jumplobby";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args
    ) {
        Player player = (Player) sender;
        observable.listeners.forEach(event -> event.onJump(player));
        // accept player в relay

        return false;
    }

    public interface Observer extends BaseObserver {
        void onJump(Player player);
    }
}
