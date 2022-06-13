package me.elijahproductions.bridgesmg.infrastructure.createconfig.comand;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.command.CommandsListener;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.service.GameCreateProcessorImpl;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.GamePlayProcessorImpl;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BaseCommandExecutor implements CommandsListener {

    private final GameCreateProcessorImpl createProcessor = SDK.get().getGameCreateProcessor();
    private final GamePlayProcessorImpl playProcessor = SDK.get().getGamePlayProcessor();

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        Player player = (Player) sender;
        String process = args[0];
        switch (process) {
            case "set" -> createProcessor.set(player, args);
            case "save" -> createProcessor.save(player, args);
            case "join" -> playProcessor.join(player, args);
            case "create" -> createProcessor.create(player, args);
        }
        return false;
    }

    @Override
    public String getLabel() {
        return "bg";
    }
}
