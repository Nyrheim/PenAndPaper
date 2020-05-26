package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        List<PenCharacter> characters = characterService.getCharacters(penPlayer);
        for (int i = 0; i < 3; i++) {
            if (i < characters.size()) {
                PenCharacter character = characters.get(i);
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
            PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
            PenPlayer penPlayer = playerService.getPlayer(player);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            PenCharacter character = new PenCharacter(
                    plugin,
                    penPlayer.getPlayerId()
            );
            characterService.addCharacter(character);
            characterService.setActiveCharacter(penPlayer, character);
        } else if (clickedItem.getType() == PLAYER_HEAD) {
            player.closeInventory();
            if (slotCharacters.containsKey(slot)) {
                PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
                PenCharacter character = characterService.getCharacter(slotCharacters.get(slot));
                PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
                PenPlayer penPlayer = playerService.getPlayer(player);
                characterService.setActiveCharacter(penPlayer, character);
            }
        } else if (clickedItem.getType() == CAMPFIRE) {
            player.closeInventory();
            if (slotCharacters.containsKey(slot)) {
                PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
                PenCharacter character = characterService.getCharacter(slotCharacters.get(slot));
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

