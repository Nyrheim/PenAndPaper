package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.character.jobs.Job;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LevelGUI extends GUI implements InventoryHolder  {

    private final Inventory inv;
    private Pen main;

    public LevelGUI() {
        inv = Bukkit.createInventory(this, 36, "Race Selection");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        PlayerCharacter character = Pen.getCharacter(holder.getActive());
        Job job = Pen.getJob(character.getJobID(1));
        inv.setItem(3, createGuiItem(GUI.getMaterial(job.getJobName()), GUI.getJobItemName(job.getJobName()), "§6"+ job.getJobName() + " " + job.getJobLevel() + " -> " + (job.getJobLevel() + 1)));
        inv.setItem(5, createGuiItem(Material.ENDER_CHEST, "Multiclass", "§6Automatically contacts staff for multiclassing."));
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
