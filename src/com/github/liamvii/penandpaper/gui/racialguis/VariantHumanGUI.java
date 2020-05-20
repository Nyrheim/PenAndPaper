package com.github.liamvii.penandpaper.gui.racialguis;

import com.github.liamvii.penandpaper.Pen;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class VariantHumanGUI implements InventoryHolder {

    private final Inventory inv;
    private Pen main;

    public VariantHumanGUI() {
        inv = Bukkit.createInventory(this, 36, "ASI Selection");
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.PLAYER_HEAD, "Human", "test"));
    }

    // Custom method to insert an item into the gui.
    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1); // Creates the ItemStack.
        ItemMeta meta = item.getItemMeta(); // Retrieves the item's default metadata.
        meta.setDisplayName("§f" + name); // The below methods are designed to update the item's name and lore.
        ArrayList<String> metaLore = new ArrayList<String>();
        for (String loreComments : lore) {
            metaLore.add(loreComments);
        }
        meta.setLore(metaLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }
}
