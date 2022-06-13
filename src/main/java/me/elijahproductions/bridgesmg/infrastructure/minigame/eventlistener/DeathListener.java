package me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onFallDeath(EntityDamageEvent event) {
        SDK.get().getEventsBroadcastReceiver().onDeathByVoid(event);


        /*if (gameManager.getGameState() == GameState.ACTIVE) {
            if (event.getEntity() instanceof Player) {
                EntityDamageEvent.DamageCause cause = event.getCause();
                if (cause.equals(EntityDamageEvent.DamageCause.FALL)){
                    if (event.getEntity() == deathPlayer) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }*/
    }
}
