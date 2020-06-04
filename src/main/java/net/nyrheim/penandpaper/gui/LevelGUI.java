package net.nyrheim.penandpaper.gui;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.clazz.CharacterClass;
import net.nyrheim.penandpaper.clazz.MulticlassingRequirement;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.bukkit.ChatColor.*;

public final class LevelGUI extends GUI {

    private final PenAndPaper plugin;

    public LevelGUI(PenAndPaper plugin) {
        super("Level up");
        this.plugin = plugin;
    }

    private final Map<Integer, PenClass> slotClasses = new HashMap<>();

    @Override
    public void initializeItems(Player player) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) return;
        int i = 0;
        for (PenClass clazz : PenClass.values()) {
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) return;
        if (character.classes().stream()
                .map(CharacterClass::getLevel)
                .reduce(0, Integer::sum) >= character.getLevel()) {
            player.closeInventory();
            player.sendMessage(RED + "You do not have enough levels to level up in a class at this time.");
            return;
        }
        if (slotClasses.containsKey(slot)) {
            PenClass clazz = slotClasses.get(slot);
            CharacterClass characterClass = character.clazz(clazz);
            if (characterClass == null) {
                if (character.classes().size() < PenCharacter.MAX_CLASSES) {
                    if (character.classes().isEmpty()) {
                        character.addClass(clazz);
                        characterClass = character.clazz(clazz);
                    } else {
                        if (!character.classes().stream().allMatch(requirementsClass ->
                                requirementsClass.getClazz().getMulticlassingRequirement().meets(character))
                                    || !clazz.getMulticlassingRequirement().meets(character)) {
                            player.closeInventory();
                            player.sendMessage(RED + "You do not meet the following requirements to multiclass: ");
                            character.classes().stream()
                                    .filter(requirementsClass ->
                                            !requirementsClass.getClazz().getMulticlassingRequirement().meets(character))
                                    .forEach(requirementsClass -> {
                                        MulticlassingRequirement requirement = requirementsClass.getClazz().getMulticlassingRequirement();
                                        player.sendMessage(RED + "== " + requirementsClass.getClazz().getName() + " ==");
                                        player.sendMessage(Arrays.stream(requirement.printRequirements())
                                                .map(line -> RED + line).toArray(String[]::new));
                                    });
                            if (!clazz.getMulticlassingRequirement().meets(character)) {
                                MulticlassingRequirement requirement = clazz.getMulticlassingRequirement();
                                player.sendMessage(RED + "== " + clazz.getName() + " ==");
                                player.sendMessage(Arrays.stream(requirement.printRequirements())
                                        .map(line -> RED + line).toArray(String[]::new));
                            }
                            return;
                        }

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
            characterService.updateClasses(character);
            player.closeInventory();
            if (characterClass != null) {
                player.sendMessage(GREEN + "Levelled up " + clazz.getName() + " to lv" + characterClass.getLevel());
            }
        }
    }
}
