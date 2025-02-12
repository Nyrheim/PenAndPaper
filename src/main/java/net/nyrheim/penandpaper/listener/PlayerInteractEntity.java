package net.nyrheim.penandpaper.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import static org.bukkit.inventory.EquipmentSlot.HAND;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        if (event.getHand() != HAND) return;
        Player player = event.getPlayer();
        if (player.isSneaking() && event.getRightClicked() instanceof Player) {
            Player target = (Player) event.getRightClicked();
            player.performCommand("char " + target.getName());
        }
    }
    
}
