package me.elijahproductions.bridgesmg.service;

import me.elijahproductions.bridgesmg.command.CommandsListener;
import me.elijahproductions.bridgesmg.command.JumpLobbyCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class  CommandService {

    private final JavaPlugin plugin;

    public CommandService(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void registerCommands(){
        CommandsListener[] commandExecutors = {
                new JumpLobbyCommandExecutor()
        };
        Arrays.stream(commandExecutors).forEach(this::registerCommand);
    }

    private void registerCommand(CommandsListener executor){
        plugin.getCommand(executor.getLabel()).setExecutor(executor);

    }
}
