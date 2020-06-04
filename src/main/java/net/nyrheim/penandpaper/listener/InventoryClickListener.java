package net.nyrheim.penandpaper.listener;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public final class InventoryClickListener implements Listener {

    private final PenAndPaper plugin;

    public InventoryClickListener(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(event.getInventory().getHolder() instanceof GUI)) {
            return;
        }
        event.setCancelled(true);
        GUI gui = (GUI) event.getInventory().getHolder();
        gui.onClick(player, event.getSlot());
    }


}

