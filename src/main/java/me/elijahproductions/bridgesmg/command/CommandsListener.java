package me.elijahproductions.bridgesmg.command;

import org.bukkit.command.CommandExecutor;

public interface CommandsListener extends CommandExecutor {
    String getLabel();
}
