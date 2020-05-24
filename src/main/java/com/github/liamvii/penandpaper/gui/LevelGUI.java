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
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.liamvii.penandpaper.character.PlayerCharacter.MAX_CLASSES;
import static org.bukkit.ChatColor.*;

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
                    if (character.classes().isEmpty()) {
                        character.addClass(clazz);
                        characterClass = character.clazz(clazz);
                    } else {
                        TextComponent approveButton = new TextComponent("Approve");
                        approveButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/class approve " + player.getName() + " " + clazz.getName()));
                        approveButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new ComponentBuilder().append("Click here to approve multiclassing combination").create()));
                        BaseComponent[] approvalMessage = new ComponentBuilder()
                                .append(approveButton)
                                .color(net.md_5.bungee.api.ChatColor.GREEN)
                                .create();
                        List<Player> approvers = plugin.getServer().getOnlinePlayers()
                                .stream()
                                .filter(staff -> staff.hasPermission("penandpaper.multiclassapproval"))
                                .collect(Collectors.toList());
                        if (approvers.isEmpty()) {
                            player.sendMessage(RED + "Your multiclassing combination requires staff approval, but no staff members with the requisite permissions are online right now.");
                            player.sendMessage(RED + "Please try again later when there are staff members online.");
                        } else {
                            approvers
                                    .forEach(staff -> {
                                        staff.sendMessage(GOLD + player.getName() + " wishes to adopt the new class " + clazz.getName());
                                        staff.sendMessage(GOLD + "Their current classes are: "
                                                + character.classes().stream()
                                                .map(characterClassListItem ->
                                                        characterClassListItem.getClazz().getName()
                                                                + " " + characterClassListItem.getLevel())
                                                .reduce((a, b) -> a + "/" + b)
                                                .orElse("None")
                                        );
                                        staff.spigot().sendMessage(approvalMessage);
                                    });
                            player.sendMessage(GREEN + "Your multiclassing combination has been sent to online staff for approval.");
                        }
                    }
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
