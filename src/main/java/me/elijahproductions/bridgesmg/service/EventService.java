package me.elijahproductions.bridgesmg.service;

import me.elijahproductions.bridgesmg.eventlistener.BlockBreakListener;
import me.elijahproductions.bridgesmg.eventlistener.DeathListener;
import me.elijahproductions.bridgesmg.eventlistener.FallDamageListener;
import me.elijahproductions.bridgesmg.eventlistener.LobbyExitEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class EventService {
    private JavaPlugin plugin;
    private PluginManager manager;

    public EventService(JavaPlugin plugin) {
        this.plugin = plugin;
        this.manager = plugin.getServer().getPluginManager();
    }

    public void registerEvents() {
        Listener[] listeners = {
                new DeathListener(),
                new LobbyExitEvent(),
                new BlockBreakListener(),
                new FallDamageListener()
        };

        Arrays.stream(listeners).forEach(this::registerEvent);
    }

    private void registerEvent(Listener listener) {
        manager.registerEvents(listener, plugin);
    }
}
