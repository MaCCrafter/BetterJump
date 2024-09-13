package de.aurorus.betterjump.listener;

import de.aurorus.betterjump.BetterJump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {

            if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && BetterJump.getInstance().preventFallDamage.contains(player)) {
                BetterJump.getInstance().preventFallDamage.remove(player);
                event.setCancelled(true);
            }

        }
    }
}
