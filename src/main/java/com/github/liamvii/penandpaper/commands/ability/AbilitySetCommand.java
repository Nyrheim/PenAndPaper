package com.github.liamvii.penandpaper.commands.ability;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterAbilityScoreTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.GREEN;

public final class AbilitySetCommand implements CommandExecutor {

    private final Pen plugin;

    public AbilitySetCommand(Pen plugin) {
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
        PlayerId playerId = new PlayerId(target);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId activeCharacterId = activeCharacterTable.get(playerId);
        if (activeCharacterId == null) {
            sender.sendMessage(RED + target.getName() + " does not have an active character.");
            return true;
        }
        PlayerCharacter character = characterTable.get(activeCharacterId);
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
        CharacterAbilityScoreTable characterAbilityScoreTable = plugin.getDatabase().getTable(CharacterAbilityScoreTable.class);
        characterAbilityScoreTable.insertOrUpdateAbilityScores(character);
        sender.sendMessage(GREEN + (sender == target ? "Your " : character.getName() + "'s ") + ability.getName() + " score was set to " + score + ".");
        if (sender != target) {
            target.sendMessage(GREEN + "Your " + ability.getName() + " score was set to " + score + ".");
        }
        return true;
    }
}
