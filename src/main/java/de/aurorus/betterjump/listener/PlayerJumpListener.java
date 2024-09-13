package de.aurorus.betterjump.listener;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.aurorus.betterjump.BetterJump;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJumpListener implements Listener {

    private ConfigManager configManager = BetterJump.getInstance().getConfigManager();
    private CooldownManager cooldownManager = BetterJump.getInstance().getCooldownManager();

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();

        if (configManager.getConfig().getBoolean("settings.enableDoubleJump")) {
            if (BetterJump.getInstance().getWorldWhitelistManager().isWorldWhitelisted(player) && cooldownManager.canDoubleJump(player.getUniqueId())) {
                player.setAllowFlight(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setAllowFlight(false);
                    }
                }.runTaskLater(BetterJump.getInstance(), 20);
            }

            if (!BetterJump.getInstance().getWorldWhitelistManager().isWorldWhitelisted(player)) return;

            if (configManager.getConfig().getBoolean("settings.enableJumpBoost") && player.hasPermission(configManager.getConfig().getString("permissions.useJumpBoost"))) {
                if (cooldownManager.canUseJumpBoost(player.getUniqueId())) {

                    player.setVelocity(player.getLocation().getDirection().setY(configManager.getConfig().getDouble("settings.jumpBoost.velocity")));
                    BetterJump.getInstance().preventFallDamage.add(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if(BetterJump.getInstance().preventFallDamage.contains(player)) BetterJump.getInstance().preventFallDamage.remove(player);
                        }
                    }.runTaskLater(BetterJump.getInstance(), 60);
                }

                    if (configManager.getConfig().getBoolean("settings.jumpBoost.sound.enableSound"))
                        player.playSound(player.getLocation(), Sound.valueOf(configManager.getConfig().getString("settings.jumpBoost.sound.soundOnJump")), 1.0f, 1.0f);

                    if (configManager.getConfig().getBoolean("settings.jumpBoost.particle.enableParticles"))
                        BetterJump.getInstance().getParticleManager().spawnJumpParticles(player, "jumpBoost");

                    int cooldown = configManager.getConfig().getInt("settings.jumpBoost.cooldown");
                    if (cooldown == 0) return;
                    if (!player.hasPermission(configManager.getConfig().getString("permissions.bypassCooldown")))
                        cooldownManager.setJumpBoostCooldown(player.getUniqueId());

                }
                }
            }

        }



