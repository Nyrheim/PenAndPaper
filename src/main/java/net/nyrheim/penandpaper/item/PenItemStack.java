package net.nyrheim.penandpaper.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.inventory.ItemFlag.*;

public final class PenItemStack {

    private final ItemType type;
    private final int amount;

    public PenItemStack(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
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
