package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import me.elijahproductions.bridgesmg.infrastructure.createconfig.comand.BaseCommandExecutor;
import me.elijahproductions.bridgesmg.infrastructure.minigame.command.CommandsListener;
import me.elijahproductions.bridgesmg.infrastructure.minigame.command.JumpLobbyCommandExecutor;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CommandService {

    private final JavaPlugin plugin;

    public CommandService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        CommandsListener[] commandExecutors = (CommandsListener[]) ArrayUtils
                .addAll(getGameCommands(), getCreateCommands());
        Arrays.stream(commandExecutors).forEach(this::registerCommand);
    }

    private CommandsListener[] getGameCommands() {
        return new CommandsListener[]{
                new JumpLobbyCommandExecutor()
        };
    }

    private CommandsListener[] getCreateCommands() {
        return new CommandsListener[]{
            new BaseCommandExecutor()
        };
    }

    private void registerCommand(CommandsListener executor) {
        PluginCommand command = plugin.getCommand(executor.getLabel());
        if (command != null) {
            System.out.println("**SET - " + executor.getLabel());
            command.setExecutor(executor);
        }
    }
}
