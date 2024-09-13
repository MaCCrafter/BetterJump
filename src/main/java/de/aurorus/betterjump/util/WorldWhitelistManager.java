package de.aurorus.betterjump.util;

import de.aurorus.betterjump.BetterJump;
import org.bukkit.entity.Player;

import java.util.List;

public class WorldWhitelistManager {

    public boolean isWorldWhitelisted(Player player) {
        List<String> worldWhitelist = BetterJump.getInstance().getConfig().getStringList("settings.worlds");

        if (worldWhitelist.isEmpty()) return true;

        if (!worldWhitelist.contains(player.getLocation().getWorld().getName())) {
            return false;
        }
        return true;
    }

}
