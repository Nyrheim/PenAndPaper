package net.nyrheim.penandpaper.gui;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.md_5.bungee.api.ChatColor.DARK_AQUA;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.*;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.Material.*;

public final class SoulGUI extends GUI {

    private final PenAndPaper plugin;

    public SoulGUI(PenAndPaper plugin) {
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
                        character.getName() + "(" + character.getId().getValue() + ")",
                        GOLD + (character.getRace() != null ? character.getRace().getName() : "Race not set"),
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
            if (slot == 1 && !player.hasPermission("penandpaper.2slot")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to create a character in Slot 2.");
                return;
            }
            if (slot == 2 && !player.hasPermission("penandpaper.3slot")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to create a character in Slot 3.");
                return;
            }
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
            List<String> purse = new ArrayList<>();
            purse.add("A purse of foreign currency, brought with you from a foreign shore.");
            purse.add("There's just about enough money in it to rent a house,");
            purse.add("or you could go to the bank and exchange it.");
            purse.add("[Contact a GM to interact.]");
            purse.add(DARK_AQUA + "[Soulbound to " + penPlayer.getPlayer().getName() + " with ID: " + character.getId().toString() + "]");
            player.getInventory().addItem(new PenItemStack(COMMON_CLOTHES, 1).toItemStack());
            player.getInventory().addItem(new PenItemStack(RATIONS, 20).toItemStack());
            player.getInventory().addItem(new PenItemStack(PURSE_OF_FOREIGN_CURRENCY, 1).toLoredItemStack(character, purse));
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
                            new ComponentBuilder("Click here to delete " + character.getName()).create()
                    ));
                    BaseComponent[] built = new ComponentBuilder("Click here to confirm: ")
                            .color(ChatColor.WHITE)
                            .append(deleteButton)
                            .color(ChatColor.RED)
                            .create();
                    player.spigot().sendMessage(built);
                }
            }
        }
    }

}

