package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterClassTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static com.github.liamvii.penandpaper.character.PlayerCharacter.MAX_CLASSES;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class LevelGUI extends GUI {

    private final Pen plugin;

    public LevelGUI(Pen plugin) {
        super("Level up");
        this.plugin = plugin;
    }

    private final Map<Integer, DnDClass> slotClasses = new HashMap<>();

    @Override
    public void initializeItems(Player player) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        PlayerId playerId = new PlayerId(player);
        CharacterId characterId = activeCharacterTable.get(playerId);
        if (characterId == null) return;
        PlayerCharacter character = characterTable.get(characterId);
        if (character == null) return;
        int i = 0;
        for (DnDClass clazz : DnDClass.values()) {
            CharacterClass characterClass = character.clazz(clazz);
            if (characterClass != null) {
                getInventory().setItem(
                        i,
                        createGuiItem(
                                getMaterial(clazz),
                                clazz.getName(),
                                characterClass.getLevel() > 0 ? "Lv" + characterClass.getLevel() : "No levels"
                        )
                );
            } else {
                getInventory().setItem(
                        i,
                        createGuiItem(
                                getMaterial(clazz),
                                clazz.getName(),
                                "No levels"
                        )
                );
            }
            slotClasses.put(i, clazz);
            i++;
        }
    }

    @Override
    public void onClick(Player player, int slot) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterClassTable characterClassTable = plugin.getDatabase().getTable(CharacterClassTable.class);
        PlayerId playerId = new PlayerId(player);
        CharacterId characterId = activeCharacterTable.get(playerId);
        if (characterId == null) return;
        PlayerCharacter character = characterTable.get(characterId);
        if (character == null) return;
        if (character.classes().stream()
                .map(CharacterClass::getLevel)
                .reduce(0, Integer::sum) >= character.getLevel()) {
            player.closeInventory();
            player.sendMessage(RED + "You do not have enough levels to level up in a class at this time.");
            return;
        }
        if (slotClasses.containsKey(slot)) {
            DnDClass clazz = slotClasses.get(slot);
            CharacterClass characterClass = character.clazz(clazz);
            if (characterClass == null) {
                if (character.classes().size() < MAX_CLASSES) {
                    character.addClass(clazz);
                    characterClass = character.clazz(clazz);
                } else {
                    player.sendMessage(RED + "You have the maximum amount of classes.");
                }
            } else {
                characterClass.setLevel(characterClass.getLevel() + 1);
            }
            characterClassTable.insertOrUpdateClasses(character);
            player.closeInventory();
            if (characterClass != null) {
                player.sendMessage(GREEN + "Levelled up " + clazz.getName() + " to lv" + characterClass.getLevel());
            }
        }
    }
}
