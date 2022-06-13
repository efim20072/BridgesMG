package me.elijahproductions.bridgesmg.infrastructure.minigame.service;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameConfig;

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
