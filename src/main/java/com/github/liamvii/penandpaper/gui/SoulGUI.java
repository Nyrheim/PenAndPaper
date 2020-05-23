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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.Material.*;

public final class SoulGUI extends GUI {

    private final Pen plugin;

    public SoulGUI(Pen plugin) {
        super("Souls");
        this.plugin = plugin;
    }

    private final Map<Integer, CharacterId> slotCharacters = new HashMap<>();

    @Override
    public void initializeItems(Player player) {
        PlayerId playerId = new PlayerId(player);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        List<PlayerCharacter> characters = characterTable.get(playerId);
        for (int i = 0; i < 3; i++) {
            if (i < characters.size()) {
                PlayerCharacter character = characters.get(i);
                getInventory().setItem(i, createGuiItem(
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
                getInventory().setItem(i + 9, createGuiItem(CAMPFIRE, "Delete", WHITE + "Permanently deletes this Soul."));
            } else {
                getInventory().setItem(i, createGuiItem(WRITABLE_BOOK, "Create", GOLD + "Create a new Soul."));
            }
        }
    }

    @Override
    public void onClick(Player player, int slot) {
        ItemStack clickedItem = getInventory().getItem(slot);
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

}

