package net.nyrheim.penandpaper.commands.ability;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class AbilitySetCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public AbilitySetCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("penandpaper.command.ability.set")) {
            sender.sendMessage(RED + "You do not have permission to set abilities.");
            return true;
        }
        if (args.length < 3) {
            sender.sendMessage(RED + "You must specify a player, stat and value.");
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
        Ability ability;
        try {
            ability = Ability.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException exception) {
            ability = Ability.getByAbbreviation(args[1]);
            if (ability == null) {
                sender.sendMessage(RED + "There is no ability by that name.");
                return true;
            }
        }
        int score;
        try {
            score = Integer.parseInt(args[2]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Ability score must be an integer.");
            return true;
        }
        character.setAbilityScore(ability, score);
        characterService.updateAbilityScores(character);
        sender.sendMessage(GREEN + (sender == target ? "Your " : character.getName() + "'s ") + ability.getName() + " score was set to " + score + ".");
        if (sender != target) {
            target.sendMessage(GREEN + "Your " + ability.getName() + " score was set to " + score + ".");
        }
        return true;
    }
}
