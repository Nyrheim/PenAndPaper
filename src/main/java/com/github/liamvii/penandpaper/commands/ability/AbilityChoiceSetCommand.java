package com.github.liamvii.penandpaper.commands.ability;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.github.liamvii.penandpaper.ability.AbilityScoreCostLookupTable.MAX_ABILITY_COST;
import static com.github.liamvii.penandpaper.ability.AbilityScoreCostLookupTable.getAbilityScoreCost;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class AbilityChoiceSetCommand implements CommandExecutor {

    private final Pen plugin;
    private final AbilityChoiceCommand abilityChoiceCommand;

    public AbilityChoiceSetCommand(Pen plugin, AbilityChoiceCommand abilityChoiceCommand) {
        this.plugin = plugin;
        this.abilityChoiceCommand = abilityChoiceCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(RED + "Usage: /" + label + " choice set [ability] [score]");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");;
            return true;
        }
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
        Ability ability;
        try {
            ability = Ability.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            ability = Ability.getByAbbreviation(args[0]);
            if (ability == null) {
                sender.sendMessage(RED + "There is no ability by that name.");
                return true;
            }
        }
        int score;
        try {
            score = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Ability score must be an integer.");
            return true;
        }
        if (score < 8) {
            sender.sendMessage(RED + "Ability score may not be less than 8.");
            return true;
        }
        if (score > 15) {
            sender.sendMessage(RED + "Ability score may not be greater than 15.");
            return true;
        }
        int cost = getAbilityScoreCost(score);
        final Ability finalAbility = ability;
        int otherScoreCost = Arrays.stream(Ability.values())
                .filter(otherAbility -> otherAbility != finalAbility)
                .map(otherAbility -> getAbilityScoreCost(character.getAbilityScoreChoice(otherAbility)))
                .reduce(0, Integer::sum);
        if (otherScoreCost + cost > MAX_ABILITY_COST) {
            sender.sendMessage(RED + "Decrease your other stats if you wish to choose this option.");
            return true;
        }
        character.setAbilityScoreChoice(ability, score);
        characterService.updateAbilityScoreChoices(character);
        sender.sendMessage(GREEN + ability.getName() + " set to " + score + ".");
        abilityChoiceCommand.sendAbilityChoices(sender, character);
        return true;
    }
}
