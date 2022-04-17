package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        var blockBrokenType = event.getBlock().getType();
        var transmutable = TransmutableManager.getInstance().getTransmutable(blockBrokenType);
        if (transmutable == null) return;
        transmutable.transmute(event);
    }

}
