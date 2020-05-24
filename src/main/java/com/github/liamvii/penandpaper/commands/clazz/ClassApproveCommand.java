package com.github.liamvii.penandpaper.commands.clazz;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.liamvii.penandpaper.character.PlayerCharacter.MAX_CLASSES;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ClassApproveCommand implements CommandExecutor {

    private final Pen plugin;

    public ClassApproveCommand(Pen plugin) {
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
        DnDClass clazz = DnDClass.getByName(args[1]);
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
        character.addClass(clazz);
        sender.sendMessage(GREEN + "Class approved.");
        target.sendMessage(GREEN + "Your request to multiclass into " + clazz.getName() + " has been approved.");
        return true;
    }

}
