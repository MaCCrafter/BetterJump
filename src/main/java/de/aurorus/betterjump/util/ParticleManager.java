package de.aurorus.betterjump.util;

import de.aurorus.betterjump.BetterJump;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager {

    private final ConfigManager configManager = BetterJump.getInstance().getConfigManager();

    public void spawnJumpParticles(Player player, String jumpType) {
        int particleCount = configManager.getConfig().getInt("settings." + jumpType + ".particle.particleCount");
        Particle particle = Particle.valueOf(configManager.getConfig().getString("settings." + jumpType + ".particle.particleType"));

        double radius = 1.0;
        Location loc = player.getLocation().clone().add(0, 1.5, 0);

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            Location particleLocation = loc.clone().add(xOffset, 0, zOffset);

            if (particle == Particle.REDSTONE) {
                player.getWorld().spawnParticle(particle, particleLocation, 1, new Particle.DustOptions(Color.RED, 1));
            }  else {
                player.getWorld().spawnParticle(particle, particleLocation, 1);
            }
        }
    }
}
