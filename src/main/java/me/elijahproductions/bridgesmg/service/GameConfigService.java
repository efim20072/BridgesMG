package me.elijahproductions.bridgesmg.service;

import me.elijahproductions.bridgesmg.SDK;
import me.elijahproductions.bridgesmg.entity.game.GameConfig;
import me.elijahproductions.bridgesmg.repository.GameConfigRepository;

import java.util.List;
import java.util.Random;

public class GameConfigService {

    private final Random random = new Random();

    public GameConfig getRandom() {
        List<GameConfig> configs = SDK.get()
                .getGameConfigRepository()
                .getGameConfigs();

        int randomIndex = configs.size();
        return configs.get(random.nextInt(randomIndex));
    }
}
