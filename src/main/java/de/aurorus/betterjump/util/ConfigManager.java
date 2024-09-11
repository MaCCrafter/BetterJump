package de.aurorus.betterjump.util;

import de.aurorus.betterjump.BetterJump;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private File configFile;
    private FileConfiguration config;

    public void createConfig() {
        configFile = new File(BetterJump.getInstance().getDataFolder(), "custom_config.yml");

        if (!configFile.exists()) {
            BetterJump.getInstance().saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            createConfig();
        }
        return config;
    }

}
