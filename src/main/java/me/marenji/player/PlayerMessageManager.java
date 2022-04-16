package me.marenji.player;

import me.marenji.TransmutePlugin;
import me.marenji.transmutables.TransmutableManager;
import me.marenji.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class PlayerMessageManager {

    private static PlayerMessageManager single_instance = null;
    private PlayerHealthManager healthManager;
    private TransmutableManager transmutableManager;

    public PlayerMessageManager() {
        this.healthManager = PlayerHealthManager.getInstance();
        this.transmutableManager = TransmutableManager.getInstance();
    }

    public static PlayerMessageManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new PlayerMessageManager();
        }
        return single_instance;
    }

    public void sendNextHeartMessage(Player player) {
        var currentHearts = healthManager.getMaxHearts(player);
        var message = transmutableManager.getNextHeartTransmutableMessage(currentHearts);
        message(player, message);
    }

    private void message(Player player, String text) {
        player.sendMessage(
            ChatColor.translateAlternateColorCodes('&', text)
        );
    }

}