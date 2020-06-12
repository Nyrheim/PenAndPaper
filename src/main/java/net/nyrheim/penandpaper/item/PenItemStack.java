package net.nyrheim.penandpaper.item;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.DARK_AQUA;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.inventory.ItemFlag.*;

public final class PenItemStack {

    private final ItemType type;
    private final int amount;
    private final List<String> lore;

    public PenItemStack(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
        this.lore = null;
    }

    public ItemType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack toItemStack() {
        ItemStack stack = getType().createItemStack(getAmount());
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(WHITE + getType().getName());
            meta.setLore(getType().createLore());
            meta.setUnbreakable(true);
            meta.addItemFlags(HIDE_ATTRIBUTES);
            meta.addItemFlags(HIDE_UNBREAKABLE);
            meta.addItemFlags(HIDE_PLACED_ON);
            stack.setItemMeta(meta);
        }
        return stack;
    }

    public ItemStack toLoredItemStack(PenCharacter character, List<String> lore) {
        ItemStack stack = getType().createItemStack(getAmount());
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(WHITE + getType().getName());
            meta.setLore(getType().createLore());
            meta.setUnbreakable(true);
            List<String> itemLore = meta.getLore();
            if (itemLore != null) {
                itemLore.addAll(lore);
                meta.setLore(lore);
            }
            meta.addItemFlags(HIDE_ATTRIBUTES);
            meta.addItemFlags(HIDE_UNBREAKABLE);
            meta.addItemFlags(HIDE_PLACED_ON);
            stack.setItemMeta(meta);
        }
        return stack;
    }

    public static PenItemStack fromItemStack(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            if (!meta.hasDisplayName()) return null;
            String displayName = meta.getDisplayName();
            if (displayName.startsWith(WHITE.toString())) displayName = displayName.replaceFirst(WHITE.toString(), "");
            ItemType itemType = ItemType.getByName(displayName);
            if (itemType == null) return null;
            return new PenItemStack(itemType, itemStack.getAmount());
        }
        return null;
    }

}
