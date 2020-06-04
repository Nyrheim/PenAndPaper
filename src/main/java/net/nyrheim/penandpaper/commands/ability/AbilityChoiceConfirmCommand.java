package net.nyrheim.penandpaper.commands.ability;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.ability.AbilityScoreCostLookupTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class AbilityChoiceConfirmCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public AbilityChoiceConfirmCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        if (Arrays.stream(Ability.values()).anyMatch(ability -> character.getAbilityScore(ability) > 0)) {
            sender.sendMessage(RED + "You have already chosen this character's stats. Please contact a member of staff for any modifications.");
            return true;
        }
        if (!Arrays.stream(Ability.values())
                .allMatch(ability -> character.getAbilityScoreChoice(ability) >= 8
                        && character.getAbilityScoreChoice(ability) <= 15)) {
            sender.sendMessage(RED + "Some of your ability scores are not within bounds. Please fix them and try again.");
            return true;
        }
        int scoreCost = Arrays.stream(Ability.values())
                .map(ability -> AbilityScoreCostLookupTable.getAbilityScoreCost(character.getAbilityScoreChoice(ability)))
                .reduce(0, Integer::sum);
        if (scoreCost > AbilityScoreCostLookupTable.MAX_ABILITY_COST) {
            sender.sendMessage(RED + "Your overall score cost is too high. Please reduce some of your scores and try again.");
            return true;
        }
        Arrays.stream(Ability.values()).forEach(ability ->
                character.setAbilityScore(ability, character.getAbilityScoreChoice(ability)));
        characterService.updateAbilityScores(character);
        sender.sendMessage(GREEN + "Ability scores locked in.");
        return true;
    }
}
