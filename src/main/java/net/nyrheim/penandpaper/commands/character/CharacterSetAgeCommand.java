package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.Race;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class CharacterSetAgeCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public CharacterSetAgeCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        int argOffset = 0;
        if (args.length > 1) {
            if (sender.hasPermission("penandpaper.command.character.set.age.other")) {
                target = plugin.getServer().getPlayer(args[0]);
                argOffset = 1;
                if (target == null) {
                    sender.sendMessage(RED + "There is no player online by that name.");
                    return true;
                }
            } else {
                sender.sendMessage(RED + "You do not have permission to set the age of other players' characters.");
            }
        }
        if (target == null) {
            sender.sendMessage(RED + "You must specify a player when running this command from console.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : target.getName() + " does") + " not currently have an active character.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify how old you wish to become.");
            return true;
        }
        int age;
        try {
            age = Integer.parseInt(args[argOffset]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Age must be an integer.");
            return true;
        }
        if (age < 0) {
            character.setAge(-1);
            sender.sendMessage(RED + (sender == target ? "Your" : (character.getName() + "'s")) + " age is now unknown.");
            return true;
        }
        if (age < 16) {
            sender.sendMessage(RED + (sender == target ? "You" : character.getName()) + " may not be a child.");
            return true;
        }
        if (age > 2000) {
            sender.sendMessage(RED + (sender == target ? "You" : character.getName()) + " may not be over 2000 years old.");
            return true;
        }

        Race race = character.getRace();

        if (race == null){
            sender.sendMessage(RED + "You must set your race first before deciding your age.");
            return true;
        }

        if (age < race.getMinimumAge()) {
            sender.sendMessage(RED + (sender == target ? "You've" : character.getName() + " has") + " not reached adulthood yet. "
                    + race.getName() + "'s reach adulthood at " + race.getMinimumAge() + " years old.");
            return true;
        }

        if (age > race.getMaximumAge()){
            sender.sendMessage(RED + (sender == target ? "You're" : character.getName() + " is") +
                    " too old, " + race.getName() + "'s can live to " + race.getMaximumAge() + " years young.");
            return true;
        }

        character.setAge(age);
        characterService.updateCharacter(character);
        sender.sendMessage(GREEN + (sender == target ? "Your" : (character.getName() + "'s")) + " age is now " + age + ".");
        if (sender != target) {
            target.sendMessage(GREEN + "Your age is now " + age + ".");
        }
        return true;
    }
}
