package me.elijahproductions.bridgesmg;

import me.elijahproductions.bridgesmg.broadcast.EventsBroadcastReceiver;
import me.elijahproductions.bridgesmg.controller.MatchesController;
import me.elijahproductions.bridgesmg.dao.GameDao;
import me.elijahproductions.bridgesmg.dao.LocationDao;
import me.elijahproductions.bridgesmg.entity.UnitDBWork;
import me.elijahproductions.bridgesmg.entity.game.GameMatchManager;
import me.elijahproductions.bridgesmg.helper.FileDatabaseHelper;
import me.elijahproductions.bridgesmg.manager.BlockManager;
import me.elijahproductions.bridgesmg.repository.GameConfigRepository;
import me.elijahproductions.bridgesmg.repository.GameMatchesRepository;
import me.elijahproductions.bridgesmg.repository.GameWorldRepository;
import me.elijahproductions.bridgesmg.service.GameConfigService;
import me.elijahproductions.bridgesmg.service.GameMatchCreateHelper;
import me.elijahproductions.bridgesmg.service.LobbyService;
import me.elijahproductions.bridgesmg.service.WorldGenerateService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public class SDK {

    private final Plugin plugin = JavaPlugin.getPlugin(BridgesMG.class);
    public final String ROOT_PATH = plugin.getDataFolder().getAbsolutePath();
    private final String DATABASE_PATH = ROOT_PATH + "\\database\\";

    private GameWorldRepository gameWorldRepository;
    private GameConfigRepository gameConfigRepository;
    private GameMatchesRepository gameMatchesRepository;

    private LobbyService lobbyService;
    private GameConfigService gameConfigService;
    private WorldGenerateService worldGenerateService;
    private GameMatchCreateHelper gameMatchCreateHelper;

    private EventsBroadcastReceiver eventsBroadcastReceiver;

    private GameMatchManager gameMatchManager;
    private BlockManager blockManager;

    private MatchesController matchesController;

    private Connection databaseConnection;

    private File dataFolder;
    private File gameMapsFolder;

    private static SDK instance;

    private SDK() {
        databaseConnection = FileDatabaseHelper.getConnection(DATABASE_PATH);
    }

    public static SDK get() {
        if (instance == null) instance = new SDK();
        return instance;
    }

    public File getRootPath() {
        return Bukkit.getWorldContainer().getParentFile();
    }

    public File getDataFolder() {
        if (dataFolder != null) return dataFolder;
        dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();
        return dataFolder;
    }

    public LobbyService getLobbyService() {
        if (lobbyService != null) return lobbyService;
        lobbyService = new LobbyService(plugin);
        return lobbyService;
    }

    public MatchesController getMatchesController() {
        if (matchesController != null) return matchesController;
        matchesController = new MatchesController();
        return matchesController;
    }

    public GameConfigService getGameConfigService() {
        if (gameConfigService != null) return gameConfigService;
        gameConfigService = new GameConfigService();
        return gameConfigService;
    }

    public GameConfigRepository getGameConfigRepository() {
        if (gameConfigRepository != null) return gameConfigRepository;
        gameConfigRepository = new GameConfigRepository(getGameConfigDao());
        return gameConfigRepository;
    }

    public GameMatchesRepository getGameMatchesRepository() {
        if (gameMatchesRepository != null) return gameMatchesRepository;
        gameMatchesRepository = new GameMatchesRepository();
        return gameMatchesRepository;
    }

    public GameMatchCreateHelper getGameMatchCreateHelper() {
        if (gameMatchCreateHelper != null) return gameMatchCreateHelper;
        gameMatchCreateHelper = new GameMatchCreateHelper(getWorldGenerateService());
        return gameMatchCreateHelper;
    }

    public WorldGenerateService getWorldGenerateService() {
        if (worldGenerateService == null)
            worldGenerateService = new WorldGenerateService(getGameWorldRepository());
        return worldGenerateService;
    }

   /* public GameMatchManager getGameMatchManager(){
        if (gameMatchManager != null) return gameMatchManager;
        gameMatchManager = new GameMatchManager()
    }*/

    public BlockManager getBlockManager(){
        if (blockManager != null) return blockManager;
        blockManager = new BlockManager();
        return blockManager;
    }

    public File getGameMapsFolder() {
        if (gameMapsFolder != null) return gameMapsFolder;
        gameMapsFolder = new File(getDataFolder(), "gameMaps");
        System.out.println(gameMapsFolder);
        if (!gameMapsFolder.exists()) {
            gameMapsFolder.mkdirs();
        }
        return gameMapsFolder;
    }

    public GameDao getGameConfigDao() {
        return new GameDao(new UnitDBWork(databaseConnection));
    }

    public LocationDao getLocationDao() {
        return new LocationDao(new UnitDBWork(databaseConnection));
    }

    public GameWorldRepository getGameWorldRepository() {
        if (gameWorldRepository == null)
            gameWorldRepository = new GameWorldRepository();
        return gameWorldRepository;
    }

    public EventsBroadcastReceiver getEventsBroadcastReceiver() {
        if (eventsBroadcastReceiver != null) return eventsBroadcastReceiver;
        eventsBroadcastReceiver = new EventsBroadcastReceiver(gameMatchesRepository);
        return eventsBroadcastReceiver;
    }
}
