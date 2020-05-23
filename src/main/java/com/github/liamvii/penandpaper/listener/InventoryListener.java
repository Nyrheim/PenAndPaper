package com.github.liamvii.penandpaper.listener;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.gui.JobGUI;
import com.github.liamvii.penandpaper.gui.RaceGUI;
import com.github.liamvii.penandpaper.gui.SoulGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public final class InventoryListener implements Listener {

    private final Pen plugin;

    public InventoryListener(Pen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(
                event.getInventory().getHolder() instanceof JobGUI
                    || event.getInventory().getHolder() instanceof SoulGUI
                    || event.getInventory().getHolder() instanceof RaceGUI
        )) {
            return;
        }
        if (event.getClick().equals(ClickType.NUMBER_KEY)) {
            event.setCancelled(true);
        }
        event.setCancelled(true);

        if (event.getInventory().getHolder() instanceof SoulGUI) {
            SoulGUI gui = (SoulGUI) event.getInventory().getHolder();
            gui.onClick(player, event.getSlot());
        }

        if (event.getInventory().getHolder() instanceof JobGUI) {
            
        }
        if (event.getInventory().getHolder() instanceof RaceGUI) {

        }
    }


}

