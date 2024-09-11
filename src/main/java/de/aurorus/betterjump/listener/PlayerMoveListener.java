package de.aurorus.betterjump.listener;

import de.aurorus.betterjump.BetterJump;
import de.aurorus.betterjump.util.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private ConfigManager configManager = BetterJump.getInstance().getConfigManager();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(configManager.getConfig().getBoolean("settings.enableDoubleJump") && player.isOnGround()) {
            if (configManager.getConfig().getList("settings.worlds").isEmpty() || configManager.getConfig().getList("settings.worlds").contains(player.getWorld().getName()))
                player.setAllowFlight(true);
        }
    }

}
