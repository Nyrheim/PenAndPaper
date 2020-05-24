package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PlayerId;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class CharacterDeleteCommand implements CommandExecutor {

    private final Pen plugin;

    public CharacterDeleteCommand(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify a character ID.");
            return true;
        }
        int characterIdInt;
        try {
            characterIdInt = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Character ID must be an integer.");
            return true;
        }
        CharacterId characterId = new CharacterId(characterIdInt);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        PlayerCharacter character = characterTable.get(characterId);
        if (character == null) {
            sender.sendMessage(RED + "There is no character by that ID.");
            return true;
        }
        if (!character.getPlayerId().getValue().equals(player.getUniqueId())) {
            sender.sendMessage(RED + "You do not own that character.");
            return true;
        }
        PlayerId playerId = new PlayerId(player);
        if (characterId.equals(activeCharacterTable.get(playerId))) {
            PenPlayer penPlayer = new PenPlayer(plugin, playerId);
            penPlayer.switchCharacter(null);
        }
        characterTable.delete(character);
        sender.sendMessage(GREEN + character.getName() + " successfully deleted.");
        return true;
    }
}
