package net.nyrheim.penandpaper.commands.character;

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

import java.util.Arrays;

import static net.nyrheim.penandpaper.ability.ProficiencyBonusLookupTable.proficiencyBonusLookup;
import static org.bukkit.ChatColor.*;

public final class CharacterStatsCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public CharacterStatsCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        if (args.length > 1) {
            if (sender.hasPermission("penandpaper.command.character.stats.other")) {
                target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    if (sender instanceof Player) {
                        target = (Player) sender;
                    }
                }
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
        sender.sendMessage(GOLD + (target == sender ? "Your" : character.getName() + "'s") + " stats");
        sender.sendMessage(GOLD + character.classes().stream()
                .map(characterClass -> characterClass.getClazz().getName() + " " + characterClass.getLevel())
                .reduce((a, b) -> a + "/" + b)
                .orElse("No classes yet")
        );
        sender.sendMessage(AQUA + "Level: " + WHITE + character.getLevel());
        sender.sendMessage(GOLD + "Proficiency Bonus: " + WHITE + "+" + proficiencyBonusLookup(character.getLevel()));
        Arrays.stream(Ability.values()).forEach(ability ->
                sender.sendMessage(GOLD + ability.getAbbreviation() + ": " + WHITE + character.getAbilityScore(ability)
                        + " (" + character.getModifier(ability) + ")"
                        + (character.getTempScore(ability) != 0 ? (" (Temporary: " + character.getTempScore(ability) + ")") : ""))
        );
        return true;
    }

}
