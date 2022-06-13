package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import org.bukkit.command.CommandSender;

public interface GamePlayProcessor {
    void join(CommandSender sender, String[] args);
}
