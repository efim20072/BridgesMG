package me.elijahproductions.bridgesmg.infrastructure.minigame.eventlistener;

import me.elijahproductions.bridgesmg.infrastructure.SDK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        SDK.get().getEventsBroadcastReceiver().onBlockBreak(event);
    }
}
