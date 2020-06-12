package net.nyrheim.penandpaper.gui;

import net.nyrheim.penandpaper.clazz.PenClass;
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
        if (clazz == PenClass.FIGHTER) return IRON_SWORD;
        if (clazz == PenClass.RANGER) return BOW;
        if (clazz == PenClass.ROGUE) return IRON_AXE;
        if (clazz == PenClass.BARBARIAN) return IRON_AXE;
        if (clazz == PenClass.MONK) return IRON_SWORD;
        if (clazz == PenClass.BARD) return MUSIC_DISC_MALL;
        if (clazz == PenClass.WIZARD) return BOOK;
        if (clazz == PenClass.SORCERER) return BOOK;
        if (clazz == PenClass.WARLOCK) return ENDER_EYE;
        if (clazz == PenClass.DRUID) return SUNFLOWER;
        if (clazz == PenClass.PALADIN) return CHAINMAIL_CHESTPLATE;
        if (clazz == PenClass.CLERIC) return IRON_CHESTPLATE;
        return PAPER;
    }

    protected String getItemNameForClass(PenClass clazz) {
        if (clazz == PenClass.FIGHTER) return "Longsword";
        if (clazz == PenClass.RANGER) return "Short Bow";
        if (clazz == PenClass.ROGUE) return "Hunting Knife";
        if (clazz == PenClass.BARBARIAN) return "Twoheaded Waraxe";
        if (clazz == PenClass.MONK) return "Handsaw";
        if (clazz == PenClass.BARD) return "Lute";
        if (clazz == PenClass.WIZARD) return "Tome of Manaflow";
        if (clazz == PenClass.SORCERER) return "Tome of Fire";
        if (clazz == PenClass.WARLOCK) return "Patron's Mark";
        if (clazz == PenClass.DRUID) return "Nature's Blessing";
        if (clazz == PenClass.CLERIC) return "Basic Chainmail";
        if (clazz == PenClass.PALADIN) return "English Knight";
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

    protected ItemStack stealFaceForGuiItem(String value, String id, String name, String... lore) {
        ItemStack skull = new ItemStack(PLAYER_HEAD);
        Bukkit.getUnsafe().modifyItemStack(skull,
                "{SkullOwner:{Id:\"" + id + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
        ItemMeta skullMeta = skull.getItemMeta();
        skullMeta.setDisplayName(name);
        skullMeta.setLore(Arrays.asList(lore));
        return createGuiItem(skull, (meta) -> {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
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

    protected ItemStack createGuiItem(ItemStack item, ItemMetaInitializer initializer) {
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
