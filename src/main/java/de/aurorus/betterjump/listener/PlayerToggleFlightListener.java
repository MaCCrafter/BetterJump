package de.aurorus.betterjump.listener;

import de.aurorus.betterjump.BetterJump;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlightListener implements Listener {

    private ConfigManager configManager = BetterJump.getInstance().getConfigManager();
    private CooldownManager cooldownManager = BetterJump.getInstance().getCooldownManager();

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if(!player.getGameMode().equals(GameMode.ADVENTURE) || !player.getGameMode().equals(GameMode.SURVIVAL))
            return;

        if(!configManager.getConfig().getList("settings.worlds").isEmpty() || !configManager.getConfig().getList("settings.worlds").contains(player.getWorld().getName()))
            return;

        if(configManager.getConfig().getBoolean("settings.enableDoubleJump") && player.hasPermission(configManager.getConfig().getString("permissions.useDoubleJump"))) {

            if (cooldownManager.canDoubleJump(player.getUniqueId())) {

                event.setCancelled(true);
                player.setAllowFlight(false);
                player.setVelocity(player.getLocation().getDirection().setY(configManager.getConfig().getDouble("settings.doubleJump.velocity")));

                if(configManager.getConfig().getBoolean("settings.doubleJump.sound.enableSound"))
                    player.playSound(player.getLocation(), Sound.valueOf(configManager.getConfig().getString("settings.doubleJump.sound.soundOnJump")), 1.0f, 1.0f);

                if(configManager.getConfig().getBoolean("settings.doubleJump.particle.enableParticles"))
                    player.getWorld().spawnParticle(Particle.valueOf(configManager.getConfig().getString("settings.doubleJump.particle.particleType")),
                            player.getLocation(),
                            configManager.getConfig().getInt("settings.doubleJump.particle.particleCount"));

                int cooldown = configManager.getConfig().getInt("settings.doubleJump.cooldown");
                if(cooldown == 0) return;
                if(!player.hasPermission(configManager.getConfig().getString("permissions.bypassCooldown")))
                    cooldownManager.setDoubleJumpCooldown(player.getUniqueId());

            } else {
                if (configManager.getConfig().getBoolean("settings.jumpBoost.sendCooldownMessage")) {
                    String duration = String.valueOf(cooldownManager.getDoubleJumpRemainingTime(player.getUniqueId()));
                    player.sendMessage(configManager.getConfig().getString("settings.messages.cooldownMessage").replace("%duration%", duration).replace("&", "§"));

                }
            }
        }
    }


}
