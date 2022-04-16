package me.marenji.transmutables;

import me.marenji.util.ConfigHelper;
import org.bukkit.Material;

import java.util.HashMap;

public final class TransmutableManager {

    private static TransmutableManager single_instance = null;

    private HashMap<Material, HeartTransmutable> heartTransmutables;
    private HashMap<Material, TreasureTransmutable> treasureTransmutables;

    private TransmutableManager()
    {
        var heartTransmutables = ConfigHelper.getHeartTransmutables();
        var treasureTransmutables = ConfigHelper.getTreasureTransmutables();
        for (var heartTransmutable: heartTransmutables) {
            this.heartTransmutables.put(heartTransmutable.getMaterial(), heartTransmutable);
        }
        for (var treasureTransmutable: treasureTransmutables) {
            this.treasureTransmutables.put(treasureTransmutable.getMaterial(), treasureTransmutable);
        }
    }

    public static TransmutableManager getInstance()
    {
        if (single_instance == null) {
            single_instance = new TransmutableManager();
        }
        return single_instance;
    }



    public Transmutable getTransmutable(Material material) {
        if (isHeartTransmutable(material)) {
            return getHeartTransmutable(material);
        }

        if (isTreasureTransmutable(material)) {
            return getTreasureTransmutable(material);
        }

        return null;
    }

    private boolean isHeartTransmutable(Material material) {
        return heartTransmutables.containsKey(material);
    }

    private boolean isTreasureTransmutable(Material material) {
        return treasureTransmutables.containsKey(material);
    }

    private HeartTransmutable getHeartTransmutable(Material material) {
        return heartTransmutables.get(material);
    }

    private TreasureTransmutable getTreasureTransmutable(Material material) {
        return treasureTransmutables.get(material);
    }

}