package me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FallDamageListener implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        SDK.get().getEventsBroadcastReceiver().onDamageByFall(event);
    }
}
