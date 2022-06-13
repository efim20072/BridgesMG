package me.elijahproductions.bridgesmg;

import lombok.SneakyThrows;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.CommandService;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.EventService;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.LobbyService;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public final class BridgesMG extends JavaPlugin {

    private static BridgesMG instance;

    public static BridgesMG get() {
        if (instance == null) throw new NullPointerException("Plugin is not stated");
        return instance;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
       /* GameConfig config = new GameConfigRepository(SDK.get().getGameConfigDao())
                .getGameConfigs().stream().findFirst().get();

        new LobbyService();

        GameMatchService gameMatchService = new GameMatchService(SDK.get().getWorldGenerateService());

        GameMatch math = gameMatchService.createMatch(config);
        new Task(math).runTaskTimer(this, 0, 20);*/
        LobbyService lobbyService = SDK.get().getLobbyService();
        EventService eventService = new EventService(this);
        CommandService commandService = new CommandService(this);

        eventService.registerEvents();
        commandService.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    class Task extends BukkitRunnable {

        GameMatch match;
        int count = 20;

        public Task(GameMatch match) {
            this.match = match;
        }

        @Override
        public void run() {
            if (--count > 0) return;

            System.out.println("*************************************");
            AtomicInteger count = new AtomicInteger();
            getServer().getOnlinePlayers().forEach(player -> {
                if (count.incrementAndGet() % 2 == 0) {
                    player.teleport(match.getGamePortals().getPortals().get(TeamType.BLUE).getFirst());
                } else {
                    player.teleport(match.getGamePortals().getPortals().get(TeamType.BLUE).getSecond());
                }
            });
            cancel();
        }
    }
}
