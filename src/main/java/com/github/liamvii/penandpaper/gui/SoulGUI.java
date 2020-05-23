package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PlayerId;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.*;

public final class SoulGUI implements InventoryHolder {

    private final Inventory inv;
    private final Pen plugin;

    public SoulGUI(Pen plugin) {
        this.plugin = plugin;
        inv = Bukkit.createInventory(this, 27, "Souls");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    private final Map<Integer, CharacterId> slotCharacters = new HashMap<>();

    // Put the generated ItemStacks into the inventory.
    public void initializeItems(Player player) {
        PlayerId playerId = new PlayerId(player);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        List<PlayerCharacter> characters = characterTable.get(playerId);
        for (int i = 0; i < 3; i++) {
            if (i < characters.size()) {
                PlayerCharacter character = characters.get(i);
                inv.setItem(i, createGuiItem(
                        PLAYER_HEAD,
                        character.getName(),
                        GOLD + character.getRace(),
                        GOLD + character.classes().stream()
                                .map(characterClass -> characterClass.getClazz().getName() + " "
                                        + characterClass.getLevel())
                                .reduce((a, b) -> a + "/" + b)
                                .orElse("No classes set.")
                ));
                slotCharacters.put(i, character.getId());
                slotCharacters.put(i + 9, character.getId());
                inv.setItem(i + 9, createGuiItem(CAMPFIRE, "Delete", WHITE + "Permanently deletes this Soul."));
            } else {
                inv.setItem(i, createGuiItem(WRITABLE_BOOK, "Create", GOLD + "Create a new Soul."));
            }
        }
    }

    public void onClick(Player player, int slot) {
        ItemStack clickedItem = inv.getItem(slot);
        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.PAPER) {
            return;
        }

        if (clickedItem.getType() == WRITABLE_BOOK) {
            player.closeInventory();
            PlayerCharacter character = new PlayerCharacter(
                    plugin,
                    player
            );
            plugin.getDatabase().getTable(CharacterTable.class).insert(character);
            PlayerId playerId = new PlayerId(player);
            PenPlayer penPlayer = new PenPlayer(plugin, playerId);
            penPlayer.switchCharacter(character);
        } else if (clickedItem.getType() == PLAYER_HEAD) {
            player.closeInventory();
            if (slotCharacters.containsKey(slot)) {
                CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
                PlayerCharacter character = characterTable.get(slotCharacters.get(slot));
                PenPlayer penPlayer = new PenPlayer(plugin, player);
                penPlayer.switchCharacter(character);
            }
        } else if (clickedItem.getType() == CAMPFIRE) {
            player.closeInventory();
            if (slotCharacters.containsKey(slot)) {
                CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
                PlayerCharacter character = characterTable.get(slotCharacters.get(slot));
                if (character != null) {
                    player.sendMessage("You are about to permanently delete " + character.getName() + ".");
                    TextComponent deleteButton = new TextComponent("Delete " + character.getName());
                    deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/char delete " + character.getId().getValue()));
                    deleteButton.setHoverEvent(new HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder()
                                    .append(new TextComponent("Click here to delete " + character.getName()))
                                    .create()
                    ));
                    player.spigot().sendMessage(
                            new ComponentBuilder()
                                .append("Click here to confirm: ")
                                .color(net.md_5.bungee.api.ChatColor.WHITE)
                                .append(deleteButton)
                                .color(net.md_5.bungee.api.ChatColor.RED)
                                .create()
                    );
                }
            }
        }
    }

    // Custom method to insert an item into the gui.
    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1); // Creates the ItemStack.
        ItemMeta meta = item.getItemMeta(); // Retrieves the item's default metadata.
        if (meta != null) {
            meta.setDisplayName(WHITE + name); // The below methods are designed to update the item's name and lore.
            List<String> metaLore = Arrays.asList(lore);
            meta.setLore(metaLore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        item.setItemMeta(meta);
        return item;
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }
}

