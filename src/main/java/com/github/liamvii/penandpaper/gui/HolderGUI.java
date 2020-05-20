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

public class HolderGUI implements InventoryHolder {

    private final Inventory inv;
    private Pen main;

    public HolderGUI() {
        inv = Bukkit.createInventory(this, 27, "Souls");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems(Player player) {
        CharacterHolder holder = Pen.getHolder(player);
        if (holder.getChar(1) == -1) {
            inv.setItem(0, createGuiItem(Material.WRITABLE_BOOK, "Create", "§6Create a new Soul."));
            inv.setItem(9, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
            inv.setItem(18, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
        }
        else {
            PlayerCharacter character = Pen.getCharacter(holder.getChar(1));
            Job job = Pen.getJob(character.getJobID(1));
            String firstName = character.getFirstName();
            String familyName = character.getFamilyName();
            String jobName = job.getJobName();
            String jobLevel = Integer.toString(job.getJobLevel());
            String raceName = character.getRaceName();
            inv.setItem(0, createGuiItem(Material.PLAYER_HEAD, firstName + " " + familyName, "§6" + raceName,"§6" + jobName + " " + jobLevel));
            inv.setItem(9, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
            inv.setItem(18, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
        }
  //      if (player.hasPermission("penandpaper.2char")) {
            if (holder.getChar(2) == -1) {
                inv.setItem(1, createGuiItem(Material.WRITABLE_BOOK, "Create", "§6Create a new Soul."));
                inv.setItem(10, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
                inv.setItem(19, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
            }
            else {
                int charID = holder.getChar(2);
                PlayerCharacter character = Pen.getCharacter(holder.getChar(2));
                Job job = Pen.getJob(character.getJobID(2));
                String firstName = character.getFirstName();
                String familyName = character.getFamilyName();
                String jobName = job.getJobName();
                String jobLevel = Integer.toString(job.getJobLevel());
                inv.setItem(1, createGuiItem(Material.PLAYER_HEAD, firstName + " " + familyName, "§6" + jobName + " " + jobLevel));
                inv.setItem(10, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
                inv.setItem(19, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
            }
  //      }
    //    if (player.hasPermission("penandpaper.3char")) {
            if (holder.getChar(2) == -1) {
                inv.setItem(2, createGuiItem(Material.WRITABLE_BOOK, "Create", "§6Create a new Soul."));
                inv.setItem(11, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
                inv.setItem(20, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
            }
            else {
                int charID = holder.getChar(3);
                String firstName = Pen.getCharacter(holder.getChar(3)).getFirstName();
                String familyName = Pen.getCharacter(holder.getChar(3)).getFamilyName();
                inv.setItem(2, createGuiItem(Material.PLAYER_HEAD, firstName + " " + familyName, "Get Character Details"));
                inv.setItem(11, createGuiItem(Material.CAMPFIRE, "Delete", "§fPermanently deletes this Soul."));
                inv.setItem(20, createGuiItem(Material.CHEST, "Store", "§fStores this Soul if you have an open storage slot."));
            }
//        }


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

