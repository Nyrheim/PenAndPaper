package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.clazz.PenClass;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

import static com.github.liamvii.penandpaper.clazz.PenClass.*;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.Material.*;
import static org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES;

public abstract class GUI implements InventoryHolder {

    private final Inventory inventory;

    public GUI(String title) {
        this.inventory = Bukkit.createInventory(this, 27, title);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    protected Material getMaterial(PenClass clazz) {
        if (clazz == FIGHTER) return IRON_SWORD;
        if (clazz == RANGER) return BOW;
        if (clazz == ROGUE) return IRON_AXE;
        if (clazz == BARBARIAN) return IRON_AXE;
        if (clazz == MONK) return IRON_SWORD;
        if (clazz == BARD) return MUSIC_DISC_MALL;
        if (clazz == WIZARD) return BOOK;
        if (clazz == SORCERER) return BOOK;
        if (clazz == WARLOCK) return ENDER_EYE;
        if (clazz == DRUID) return SUNFLOWER;
        if (clazz == PALADIN) return CHAINMAIL_CHESTPLATE;
        if (clazz == CLERIC) return IRON_CHESTPLATE;
        return PAPER;
    }

    protected String getItemNameForClass(PenClass clazz) {
        if (clazz == FIGHTER) return "Longsword";
        if (clazz == RANGER) return "Short Bow";
        if (clazz == ROGUE) return "Hunting Knife";
        if (clazz == BARBARIAN) return "Twoheaded Waraxe";
        if (clazz == MONK) return "Handsaw";
        if (clazz == BARD) return "Lute";
        if (clazz == WIZARD) return "Tome of Manaflow";
        if (clazz == SORCERER) return "Tome of Fire";
        if (clazz == WARLOCK) return "Patron's Mark";
        if (clazz == DRUID) return "Nature's Blessing";
        if (clazz == CLERIC) return "Basic Chainmail";
        if (clazz == PALADIN) return "English Knight";
        return null;
    }

    public abstract void initializeItems(Player player);

    public abstract void onClick(Player player, int slot);

    protected ItemStack createGuiItem(Material material, String name, String... lore) {
        return createGuiItem(material, (meta) -> {
            meta.setDisplayName(WHITE + name);
            List<String> metaLore = Arrays.asList(lore);
            meta.setLore(metaLore);
            return meta;
        });
    }

    protected ItemStack stealFaceForGuiItem(OfflinePlayer faceToSteal, String name, String... lore) {
        return createGuiItem(PLAYER_HEAD, (meta) -> {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            if (meta instanceof SkullMeta) {
                SkullMeta skullMeta = (SkullMeta) meta;
                skullMeta.setOwningPlayer(faceToSteal);
            }
            return meta;
        });
    }

    protected ItemStack createGuiItem(Material material, ItemMetaInitializer initializer) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta = initializer.invoke(meta);
            meta.addItemFlags(HIDE_ATTRIBUTES);
        }
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(Player player) {
        player.openInventory(getInventory());
    }
}
