package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.Pen;
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

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class CharacterSetFirstNameCommand implements CommandExecutor {

    private final Pen plugin;

    public CharacterSetFirstNameCommand(Pen plugin) {
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
            if (sender.hasPermission("penandpaper.command.character.set.firstname.other")) {
                target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    argOffset = 1;
                } else {
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
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify your first name.");
            return true;
        }
        String firstName = Arrays.stream(args).skip(argOffset).reduce((a, b) -> a + " " + b).orElse("");
        if (firstName.length() > 64) {
            sender.sendMessage(RED + (sender == target ? "Your" : (character.getName() + "'s")) + " first name may be at most 64 characters long.");
            return true;
        }
        character.setFirstName(firstName);
        characterTable.update(character);
        sender.sendMessage(GREEN + (sender == target ? "Your" : (character.getName() + "'s")) + " first name is now " + firstName + ".");
        if (sender != target) {
            target.sendMessage(GREEN + "Your first name is now " + firstName + ".");
        }
        return true;
    }
}