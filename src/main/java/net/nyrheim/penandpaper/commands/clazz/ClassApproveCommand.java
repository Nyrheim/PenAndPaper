package net.nyrheim.penandpaper.commands.clazz;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.clazz.CharacterClass;
import net.nyrheim.penandpaper.clazz.MulticlassingRequirement;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static net.nyrheim.penandpaper.character.PenCharacter.MAX_CLASSES;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ClassApproveCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public ClassApproveCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("penandpaper.command.class.approve")) {
            sender.sendMessage(RED + "You do not have permission to approve classes.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "You must specify a player and a class to approve.");
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(RED + "There is no player by that name online.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + target.getName() + " does not have an active character.");
            return true;
        }
        PenClass clazz = PenClass.getByName(args[1]);
        if (clazz == null) {
            sender.sendMessage(RED + "There is no class by that name. (Please be aware this command is case sensitive!)");
            return true;
        }
        if (character.clazz(clazz) != null) {
            sender.sendMessage(RED + "That player already has that class. Maybe it was already approved by someone else?");
            return true;
        }
        if (character.classes().size() >= MAX_CLASSES) {
            sender.sendMessage(RED + "That player has the maximum amount of classes.");
            return true;
        }
        if (character.classes().stream()
                .map(CharacterClass::getLevel)
                .reduce(0, Integer::sum) >= character.getLevel()) {
            sender.sendMessage(RED + "That player does not have enough levels to level up another class.");
            return true;
        }
        if (!character.classes().stream().allMatch(requirementsClass ->
                requirementsClass.getClazz().getMulticlassingRequirement().meets(character))
                || !clazz.getMulticlassingRequirement().meets(character)) {
            sender.sendMessage(RED + character.getName() + " does not meet the following requirements to multiclass: ");
            character.classes().stream()
                    .filter(requirementsClass ->
                            !requirementsClass.getClazz().getMulticlassingRequirement().meets(character))
                    .forEach(requirementsClass -> {
                        MulticlassingRequirement requirement = requirementsClass.getClazz().getMulticlassingRequirement();
                        sender.sendMessage(RED + "== " + requirementsClass.getClazz().getName() + " ==");
                        sender.sendMessage(Arrays.stream(requirement.printRequirements())
                                .map(line -> RED + line).toArray(String[]::new));
                    });
            if (!clazz.getMulticlassingRequirement().meets(character)) {
                MulticlassingRequirement requirement = clazz.getMulticlassingRequirement();
                sender.sendMessage(RED + "== " + clazz.getName() + " ==");
                sender.sendMessage(Arrays.stream(requirement.printRequirements())
                        .map(line -> RED + line).toArray(String[]::new));
            }
            return true;
        }
        character.addClass(clazz);
        characterService.updateClasses(character);
        sender.sendMessage(GREEN + "Class approved.");
        target.sendMessage(GREEN + "Your request to multiclass into " + clazz.getName() + " has been approved.");
        return true;
    }

}
