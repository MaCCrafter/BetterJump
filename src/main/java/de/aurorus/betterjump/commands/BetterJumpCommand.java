package de.aurorus.betterjump.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BetterJumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player) {
            player.sendMessage("&8[&6&lBetterJump&8] §6BetterJump §7- made by Aurorus \n &8[&6&lBetterJump&8] §7- Support: https://aurocode.com/dc");
        } else {
            commandSender.sendMessage("BetterJump is loaded and enabled!");
        }
        return false;
    }
}
