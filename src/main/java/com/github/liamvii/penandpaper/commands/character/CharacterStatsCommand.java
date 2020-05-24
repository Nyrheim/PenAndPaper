package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static org.bukkit.ChatColor.*;

public final class CharacterStatsCommand implements CommandExecutor {

    private final Pen plugin;

    public CharacterStatsCommand(Pen plugin) {
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
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        PlayerId playerId = new PlayerId(target);
        CharacterId activeCharacterId = activeCharacterTable.get(playerId);
        if (activeCharacterId == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : target.getName() + " does") + " not currently have an active character.");
            return true;
        }
        PlayerCharacter character = characterTable.get(activeCharacterId);
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
        Arrays.stream(Ability.values()).forEach(ability ->
                sender.sendMessage(GOLD + ability.getAbbreviation() + ": " + WHITE + character.getAbilityScore(ability)
                        + " (" + character.getModifier(ability) + ")"
                        + (character.getTempScore(ability) != 0 ? (" (Temporary: " + character.getTempScore(ability) + ")") : ""))
        );
        return true;
    }

}
