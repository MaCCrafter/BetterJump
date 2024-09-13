package de.aurorus.betterjump.util;

import de.aurorus.betterjump.BetterJump;
import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    private final ConfigManager configManager = BetterJump.getInstance().getConfigManager();
    private final HashMap<UUID, Long> doubleJumpCooldowns = new HashMap<>();
    private final HashMap<UUID, Long> jumpBoostCooldowns = new HashMap<>();

    public boolean canDoubleJump(UUID player) {
        return !doubleJumpCooldowns.containsKey(player) || System.currentTimeMillis() >= doubleJumpCooldowns.get(player);
    }

    public boolean canUseJumpBoost(UUID player) {
        return !jumpBoostCooldowns.containsKey(player) || System.currentTimeMillis() >= jumpBoostCooldowns.get(player);
    }

    public void setDoubleJumpCooldown(UUID player) {
        long doubleJumpCooldownTime = configManager.getConfig().getLong("settings.doubleJump.cooldown");
        doubleJumpCooldowns.put(player, System.currentTimeMillis() + doubleJumpCooldownTime * 1000);
    }

    public void setJumpBoostCooldown(UUID player) {
        long jumpBoostCooldownTime = configManager.getConfig().getLong("settings.jumpBoost.cooldown");
        jumpBoostCooldowns.put(player, System.currentTimeMillis() + jumpBoostCooldownTime * 1000);
    }

    public long getDoubleJumpRemainingTime(UUID player) {
        return doubleJumpCooldowns.containsKey(player)
                ? Math.max(0, (doubleJumpCooldowns.get(player) - System.currentTimeMillis()) / 1000)
                : 0;
    }

    public long getJumpBoostRemainingTime(UUID player) {
        return jumpBoostCooldowns.containsKey(player)
                ? Math.max(0, (jumpBoostCooldowns.get(player) - System.currentTimeMillis()) / 1000)
                : 0;
    }
}

