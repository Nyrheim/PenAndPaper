package net.nyrheim.penandpaper.item;

import org.bukkit.inventory.ItemStack;

public interface ItemStackInitializer {

    ItemStack invoke(int amount);

}
