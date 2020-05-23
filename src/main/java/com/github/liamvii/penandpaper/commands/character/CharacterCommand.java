package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public final class CharacterCommand implements CommandExecutor {

    private final Database database;

    public CharacterCommand(Database database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        PlayerId playerId = new PlayerId(player);
        ActiveCharacterTable activeCharacterTable = database.getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = database.getTable(CharacterTable.class);
        CharacterId activeCharacterId = activeCharacterTable.get(playerId);
        if (activeCharacterId == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        PlayerCharacter character = characterTable.get(activeCharacterId);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        sender.sendMessage(GOLD + character.getFirstName() + character.getFamilyName());
        sender.sendMessage(AQUA + "Age: " + WHITE + (character.getAge() == -1 ? "Empty" : character.getAge()));
        sender.sendMessage(AQUA + "Height: " + WHITE + (character.getHeight().isEmpty() ? "Empty" : character.getHeight()));
        sender.sendMessage(AQUA + "Weight: " + WHITE + (character.getWeight().isEmpty() ? "Empty" : character.getWeight()));
        sender.sendMessage(AQUA + "Appearance: " + WHITE + (character.getAppearance().isEmpty() ? "Empty" : character.getAppearance()));
        sender.sendMessage(AQUA + "Presence: " + WHITE + (character.getPresence().isEmpty() ? "Empty" : character.getPresence()));
        return true;
    }
}
