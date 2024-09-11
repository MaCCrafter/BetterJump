package de.aurorus.betterjump;

import de.aurorus.betterjump.commands.BetterJumpCommand;
import de.aurorus.betterjump.listener.PlayerJumpListener;
import de.aurorus.betterjump.listener.PlayerMoveListener;
import de.aurorus.betterjump.listener.PlayerToggleFlightListener;
import de.aurorus.betterjump.util.ConfigManager;
import de.aurorus.betterjump.util.CooldownManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterJump extends JavaPlugin {

    private static BetterJump instance;

    private ConfigManager configManager;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager();
        configManager.createConfig();

        cooldownManager = new CooldownManager();

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
        PluginCommand command = getCommand("betterjump");

        if (command != null) {
            command.setExecutor(new BetterJumpCommand());
        }
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
