package de.aurorus.betterjump;

import de.aurorus.betterjump.listener.EntityDamageListener;
import de.aurorus.betterjump.listener.PlayerChangeWorldListener;
import de.aurorus.betterjump.listener.PlayerJumpListener;
import de.aurorus.betterjump.listener.PlayerToggleFlightListener;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import de.aurorus.betterjump.util.ParticleManager;
import de.aurorus.betterjump.util.WorldWhitelistManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BetterJump extends JavaPlugin {

    private static BetterJump instance;

    private ConfigManager configManager;
    private CooldownManager cooldownManager;
    private WorldWhitelistManager worldWhitelistManager;
    private ParticleManager particleManager;

    public List<Player> preventFallDamage = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager();
        configManager.createConfig();

        cooldownManager = new CooldownManager();

        worldWhitelistManager = new WorldWhitelistManager();

        particleManager = new ParticleManager();

        registerEvents();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJumpListener(), this);
        pluginManager.registerEvents(new PlayerToggleFlightListener(), this);
        pluginManager.registerEvents(new PlayerChangeWorldListener(), this);
        pluginManager.registerEvents(new EntityDamageListener(), this);
    }


    public static BetterJump getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public WorldWhitelistManager getWorldWhitelistManager() {
        return worldWhitelistManager;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }
}
