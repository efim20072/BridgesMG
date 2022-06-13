package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener.BlockBreakListener;
import me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener.DeathListener;
import me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener.FallDamageListener;
import me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener.LobbyExitEvent;
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
