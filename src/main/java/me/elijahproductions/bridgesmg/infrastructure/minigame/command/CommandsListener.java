package me.elijahproductions.bridgesmg.infrastructure.minigame.command;

import org.bukkit.command.CommandExecutor;

public interface CommandsListener extends CommandExecutor {
    String getLabel();
}
