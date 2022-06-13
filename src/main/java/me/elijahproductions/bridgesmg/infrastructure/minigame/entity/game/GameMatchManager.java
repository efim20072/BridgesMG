package me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game;

import me.elijahproductions.bridgesmg.BridgesMG;
import me.elijahproductions.bridgesmg.infrastructure.SDK;
import me.elijahproductions.bridgesmg.common.entity.TeamType;
import me.elijahproductions.bridgesmg.infrastructure.minigame.task.CountdownTask;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class GameMatchManager implements GameMatchEvents {

    private static final int COUNTDOWN = 5;

    private final GameMatch match;

    public GameMatchManager(GameMatch match) {
        this.match = match;
    }

    private void updateState(GameMatchState state) {
        match.state = state;
        System.out.println(state);
        switch (state) {
            case START_COUNTDOWN: {
                startCountdown();
                break;
            }
        }
    }

    public void start() {
        updateState(GameMatchState.START_COUNTDOWN);
    }

    public boolean contains(Player player) {
        for (Player p : match.getPlayers())
            if (p.getUniqueId() == player.getUniqueId())
                return true;
        return false;
    }

    private void startCountdown() {
        new CountdownTask(
                COUNTDOWN,
                () -> updateState(GameMatchState.ACTIVE)



        ).runTaskTimer(BridgesMG.get(), 0, 20);
    }

    @Override
    public void onDie(Player player) {

    }

    @Override
    public void onGoal(Player player) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (!(SDK.get().getBlockManager().canBreak(event.getBlock()))) {
            System.out.println(SDK.get().getBlockManager().canBreak(event.getBlock()));
            event.setCancelled(true);
        }
    }

    @Override
    public void onDeathByVoid(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        event.setCancelled(true);
        die(player);
        player.setHealth(20.0);
    }

    @Override
    public void onDamageByFall(EntityDamageEvent event) {
        event.setCancelled(true);

    }

    private void die(Player player){
        player.teleport(match.getWaitingRooms().getRooms().get(TeamType.BLUE));
    }
}
