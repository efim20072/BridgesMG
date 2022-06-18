package me.elijahproductions.bridgesmg.infrastructure.common.service;

import lombok.val;
import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameWorldRepository;
import me.elijahproductions.bridgesmg.infrastructure.minigame.utility.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

public class WorldGenerateService {

    private final File rootFolder;
    private final File gameMapsFolder;
    private final GameWorldRepository gameWorldRepository;

    public WorldGenerateService(GameWorldRepository gameWorldRepository) {
        this.rootFolder = SDK.get().getRootPath();
        this.gameMapsFolder = SDK.get().getGameMapsFolder();
        this.gameWorldRepository = gameWorldRepository;
    }

    public World generateWorld(String worldName) {
        File folder = createFolderForNewWorld(worldName);
        if (folder == null) return null;
        World world = createWorld(folder);
        gameWorldRepository.addWorld(world, folder);
        return world;
    }

    private File createFolderForNewWorld(String worldName) {
        try {
            val generatedName = worldName + "_active_" + System.currentTimeMillis();
            File pathFromCopy = new File(gameMapsFolder, worldName);
            File pathToCopy = new File(rootFolder, generatedName);
            FileUtil.copy(pathFromCopy, pathToCopy);
            //TODO цикл в одельныфй метод
            for(File file : pathToCopy.listFiles()) {
                System.out.println("File - " + file.getName());
                //TODO Магическая переменная с именем файла
                if (file.getName().equals("uid.dat")){
                    file.delete();
                    //throw new NullPointerException("**************************************");
                    break;
                }
            }
            return pathToCopy;
        } catch (IOException e) {
            Bukkit.getLogger().severe("Не удалось загрузить карту из исходной папки " + rootFolder.getName());
            e.printStackTrace();
            return null;
        }
    }
    private World createWorld(File worldFolder) {
        World bukkitWorld = Bukkit.createWorld(
                new WorldCreator(worldFolder.getName())
        );
        System.out.println("bukkitWorld - " + worldFolder.getName() + " -- " + (bukkitWorld == null));
        if (bukkitWorld != null) bukkitWorld.setAutoSave(false);
        return bukkitWorld;
    }

    public void deleteWorld(World world) {
        if (world == null) return;
        Bukkit.unloadWorld(world, false);
        FileUtil.delete(gameWorldRepository.getWorldPath(world));
    }
}
