package de.aurorus.betterjump;

import de.aurorus.betterjump.commands.BetterJumpCommand;
import de.aurorus.betterjump.listener.PlayerJumpListener;
import de.aurorus.betterjump.listener.PlayerMoveListener;
import de.aurorus.betterjump.listener.PlayerToggleFlightListener;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterJump extends JavaPlugin {

    private static BetterJump instance;

    private final ConfigManager configManager = new ConfigManager();
    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    public void onEnable() {
        instance = this;

        configManager.createConfig();

        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJumpListener(), this);
        pluginManager.registerEvents(new PlayerToggleFlightListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
    }

    private void registerCommands() {
        getCommand("betterjump").setExecutor(new BetterJumpCommand());
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
}
