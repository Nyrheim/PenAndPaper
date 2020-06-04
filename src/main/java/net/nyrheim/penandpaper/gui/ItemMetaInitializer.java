package net.nyrheim.penandpaper.gui;

import org.bukkit.inventory.meta.ItemMeta;

public interface ItemMetaInitializer {

    ItemMeta invoke(ItemMeta meta);

}
