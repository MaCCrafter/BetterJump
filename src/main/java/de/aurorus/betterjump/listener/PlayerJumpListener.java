package de.aurorus.betterjump.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.aurorus.betterjump.BetterJump;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJumpListener implements Listener {

    private ConfigManager configManager = BetterJump.getInstance().getConfigManager();
    private CooldownManager cooldownManager = BetterJump.getInstance().getCooldownManager();

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();

        if(!configManager.getConfig().getList("settings.worlds").isEmpty() || !configManager.getConfig().getList("settings.worlds").contains(player.getWorld().getName()))
            return;

        if(configManager.getConfig().getBoolean("settings.enableJumpBoost") && player.hasPermission(configManager.getConfig().getString("permissions.useJumpBoost"))) {
            if(cooldownManager.canUseJumpBoost(player.getUniqueId())) {

                player.setVelocity(player.getLocation().getDirection().setY(configManager.getConfig().getDouble("settings.jumpBoost.velocity")));

                if(configManager.getConfig().getBoolean("settings.jumpBoost.sound.enableSound"))
                    player.playSound(player.getLocation(), Sound.valueOf(configManager.getConfig().getString("settings.jumpBoost.sound.soundOnJump")), 1.0f, 1.0f);

                if(configManager.getConfig().getBoolean("settings.jumpBoost.particle.enableParticles"))
                    player.getWorld().spawnParticle(Particle.valueOf(configManager.getConfig().getString("settings.jumpBoost.particle.particleType")),
                            player.getLocation(),
                            configManager.getConfig().getInt("settings.jumpBoost.particle.particleCount"));

                int cooldown = configManager.getConfig().getInt("settings.jumpBoost.cooldown");
                if(cooldown == 0) return;
                if(!player.hasPermission(configManager.getConfig().getString("permissions.bypassCooldown")))
                    cooldownManager.setJumpBoostCooldown(player.getUniqueId());

            } else {
                if(configManager.getConfig().getBoolean("settings.jumpBoost.sendCooldownMessage")) {
                    String duration = String.valueOf(cooldownManager.getJumpBoostRemainingTime(player.getUniqueId()));
                    player.sendMessage(configManager.getConfig().getString("settings.messages.cooldownMessage").replace("%duration%", duration).replace("&", "§"));
                }
            }
        }

    }

}
