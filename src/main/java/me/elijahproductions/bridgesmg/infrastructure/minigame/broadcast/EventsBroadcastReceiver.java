package me.elijahproductions.bridgesmg.infrastructure.minigame.broadcast;

import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatch;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatchEvents;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatchManager;
import me.elijahproductions.bridgesmg.infrastructure.minigame.entity.game.GameMatchState;
import me.elijahproductions.bridgesmg.infrastructure.minigame.repository.GameMatchesRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/*
 event ->
 EventsBroadcastReceiver (берет из репозитория активных игр матчи и смотрит какому матчу принадлежит игрок из события) ->
 когда игрок есть в матче берем этот матч и вызываем у него метод события
 */
public class EventsBroadcastReceiver implements GameMatchEvents {

    private GameMatchesRepository repository;

    public EventsBroadcastReceiver(GameMatchesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onDie(Player player) {
        for (GameMatch match: repository.getMatches()) {
            GameMatchManager manager = match.getManager();
            if (manager.contains(player)) {
                manager.onDie(player);
                return;
            }
        } }

    @Override
    public void onGoal(Player player) {
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        for (GameMatch match : repository.getMatches()) {
            GameMatchManager manager = match.getManager();
            if (manager.contains(player)) {
                if (match.getState() == GameMatchState.ACTIVE) {
                    manager.onBlockBreak(event);
                    return;
                }}
        }}

    @Override
    public void onDeathByVoid(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        for (GameMatch match : repository.getMatches()) {
            GameMatchManager manager = match.getManager();
            if (manager.contains(player)) {
                if (match.getState() == GameMatchState.ACTIVE) {
                    EntityDamageEvent.DamageCause cause = event.getCause();
                    if (cause.equals(EntityDamageEvent.DamageCause.VOID)){
                    manager.onDeathByVoid(event);
                    return;
                }}}
        }}

    @Override
    public void onDamageByFall(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        for (GameMatch match : repository.getMatches()) {
            GameMatchManager manager = match.getManager();
            if (manager.contains(player)) {
                if (match.getState() == GameMatchState.ACTIVE) {
                    EntityDamageEvent.DamageCause cause = event.getCause();
                    if (cause.equals(EntityDamageEvent.DamageCause.FALL)){
                    manager.onDamageByFall(event);
                    return;
                }}}
      }}

}

