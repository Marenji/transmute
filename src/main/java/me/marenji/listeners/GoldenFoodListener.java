package me.marenji.listeners;

import me.marenji.TransmutePlugin;
import me.marenji.player.PlayerHealthManager;
import me.marenji.player.PlayerMessageManager;
import me.marenji.tribute.DiamondTribute;
import me.marenji.tribute.EmeraldTribute;
import me.marenji.tribute.TributeFactory;
import me.marenji.util.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class GoldenFoodListener implements Listener {

    public GoldenFoodListener() {
        Bukkit.getPluginManager().registerEvents(
            this,
            TransmutePlugin.getInstance()
        );
    }

    @EventHandler
    public void onConsumeGoldenApple(PlayerItemConsumeEvent event) {
        var itemStack = event.getItem();
        var foodType = itemStack.getType();
        var player = event.getPlayer();
        var healthManager = PlayerHealthManager.getInstance();
        var plugin = TransmutePlugin.getInstance();
        var tribute = TributeFactory.getTribute(player, foodType, healthManager);
        if (tribute == null) {
            return;
        }

        var tributeSuccess = tribute.applyTribute();
        if (tributeSuccess) {
            if (tribute instanceof DiamondTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_success_message")));
            } else if (tribute instanceof EmeraldTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("emeraldtribute_success_message")));
            }
        } else {
            if (tribute instanceof DiamondTribute) {
                player.sendMessage(ChatHelper.chat(plugin.getConfig().getString("diamondtribute_fail_message")));
            }
        }
    }

}
