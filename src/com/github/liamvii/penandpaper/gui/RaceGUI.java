package com.github.liamvii.penandpaper.gui;

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

public class RaceGUI implements InventoryHolder {

    private final Inventory inv;
    private Pen main;

    public RaceGUI() {
        inv = Bukkit.createInventory(this, 36, "Race Selection");
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems() {
        inv.setItem(0, (createGuiItem(Material.PLAYER_HEAD, "Human", "§6The most widespread of all races, humans and human civilization reaches to nearly every corner of the world. Though quite unremarkable physically, with a shorter lifespan than many races, humans show a tenacity and drive that many races lack, compelled to spread the race as far as possible and to even the most inhospitable lands. Humans are mostly liked by other races, and in general don’t have a hatred toward any specific race, though they can be racist towards many of the more monstrous looking humanoids.")));
        inv.setItem(9, (createGuiItem(Material.PLAYER_HEAD, "Variant Human", "§6The most widespread of all races, humans and human civilization reaches to nearly every corner of the world. Though quite unremarkable physically, with a shorter lifespan than many races, humans show a tenacity and drive that many races lack, compelled to spread the race as far as possible and to even the most inhospitable lands. Humans are mostly liked by other races, and in general don’t have a hatred toward any specific race, though they can be racist towards many of the more monstrous looking humanoids.", "Ability Score Increase: Two different ability scores of your choice increase by 1", "Skill Versatility: You gain proficiency in any combination of three skills, tools, or languages of your choice.")));
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
