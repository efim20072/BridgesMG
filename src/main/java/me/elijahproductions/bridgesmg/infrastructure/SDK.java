package me.elijahproductions.bridgesmg.infrastructure;

import me.elijahproductions.bridgesmg.BridgesMG;
import me.elijahproductions.bridgesmg.domain.createconfig.service.SaveConfigService;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.service.GameCreateProcessorImpl;
import me.elijahproductions.bridgesmg.infrastructure.createconfig.service.SaveConfigServiceImpl;
import me.elijahproductions.bridgesmg.infrastructure.minigame.broadcast.EventsBroadcastReceiver;
import me.elijahproductions.bridgesmg.infrastructure.minigame.controller.MatchesController;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.GameDao;
import me.elijahproductions.bridgesmg.infrastructure.minigame.dao.LocationDao;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.UnitDBWork;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatchManager;
import me.elijahproductions.bridgesmg.infrastructure.minigame.helper.FileDatabaseHelper;
import me.elijahproductions.bridgesmg.infrastructure.minigame.manager.BlockManager;
import me.elijahproductions.bridgesmg.infrastructure.minigame.manager.PortalManager;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameConfigRepository;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameMatchesRepository;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameWorldRepository;
import me.elijahproductions.bridgesmg.infrastructure.minigame.service.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;

public class SDK {

    private final Plugin plugin = JavaPlugin.getPlugin(BridgesMG.class);
    public final String ROOT_PATH = plugin.getDataFolder().getAbsolutePath();
    private final String DATABASE_PATH = ROOT_PATH + "\\database\\";

    //minigame
    private GameWorldRepository gameWorldRepository;
    private GameConfigRepository gameConfigRepository;
    private GameMatchesRepository gameMatchesRepository;

    private LobbyService lobbyService;
    private GameConfigService gameConfigService;
    private WorldGenerateService worldGenerateService;
    private GameMatchCreateHelper gameMatchCreateHelper;

    private GameCreateProcessorImpl gameCreateProcessor;
    private GamePlayProcessorImpl gamePlayProcessor;

    private EventsBroadcastReceiver eventsBroadcastReceiver;

    private GameMatchManager gameMatchManager;
    private PortalManager portalManager;

    private BlockManager blockManager;

    private MatchesController matchesController;

    private Connection databaseConnection;

    //Create
    private SaveConfigService saveConfigService;

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

    public GameCreateProcessorImpl getGameCreateProcessor() {
        if (gameCreateProcessor != null) return gameCreateProcessor;
        gameCreateProcessor = new GameCreateProcessorImpl();
        return gameCreateProcessor;
    }

    public GamePlayProcessorImpl getGamePlayProcessor() {
        if (gamePlayProcessor != null) return gamePlayProcessor;
        gamePlayProcessor = new GamePlayProcessorImpl();
        return gamePlayProcessor;
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

    public SaveConfigService getSaveConfigService() {
        if (saveConfigService == null)
            saveConfigService = new SaveConfigServiceImpl(new UnitDBWork(databaseConnection));
        return saveConfigService;
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

    public PortalManager getPortalManager() {
        if (portalManager != null) return portalManager;
        portalManager = new PortalManager();
        return portalManager;
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
