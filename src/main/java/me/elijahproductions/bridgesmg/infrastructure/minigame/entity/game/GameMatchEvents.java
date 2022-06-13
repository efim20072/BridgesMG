package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public interface GameMatchEvents {
    void onDie(Player player);
    void onGoal(Player player);
    void onBlockBreak(BlockBreakEvent event);
    void onDeathByVoid(EntityDamageEvent event);
    void onDamageByFall(EntityDamageEvent event);
}
