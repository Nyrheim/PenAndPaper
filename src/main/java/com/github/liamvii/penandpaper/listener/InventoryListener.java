package com.github.liamvii.penandpaper.listener;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.gui.HolderGUI;
import com.github.liamvii.penandpaper.gui.JobGUI;
import com.github.liamvii.penandpaper.gui.RaceGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private Pen plugin;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        if (!(e.getInventory().getHolder() instanceof JobGUI || e.getInventory().getHolder() instanceof HolderGUI || e.getInventory().getHolder() instanceof RaceGUI)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }
        e.setCancelled(true);
        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.PAPER)
            return;

        if (e.getInventory().getHolder() instanceof JobGUI) {

        }
        if (e.getInventory().getHolder() instanceof RaceGUI) {

        }
    }


}

