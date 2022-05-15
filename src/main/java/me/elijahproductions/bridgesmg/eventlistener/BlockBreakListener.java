package me.elijahproductions.bridgesmg.eventlistener;

import me.elijahproductions.bridgesmg.SDK;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        SDK.get().getEventsBroadcastReceiver().onBlockBreak(event);
    }
}
