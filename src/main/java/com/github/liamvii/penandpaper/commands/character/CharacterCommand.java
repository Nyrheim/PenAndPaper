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

import static org.bukkit.ChatColor.*;

public final class CharacterCommand implements CommandExecutor {

    private final Pen plugin;
    private final CharacterStatsCommand characterStatsCommand;
    private final CharacterSetCommand characterSetCommand;
    private final CharacterAddCommand characterAddCommand;
    private final CharacterDeleteCommand characterDeleteCommand;

    public CharacterCommand(Pen plugin) {
        this.plugin = plugin;
        this.characterStatsCommand = new CharacterStatsCommand(plugin);
        this.characterSetCommand = new CharacterSetCommand(plugin);
        this.characterAddCommand = new CharacterAddCommand(plugin);
        this.characterDeleteCommand = new CharacterDeleteCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "stats":
                    return characterStatsCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "set":
                    return characterSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "add":
                    return characterAddCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "delete":
                    return characterDeleteCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    target = plugin.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(RED + "There is no player online by that name.");
                        return true;
                    }
                    break;
            }
        }
        if (target == null) {
            sender.sendMessage(RED + "You must specify a player when running this command from console.");
            return true;
        }
        PlayerId playerId = new PlayerId(target);
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId activeCharacterId = activeCharacterTable.get(playerId);
        if (activeCharacterId == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        PlayerCharacter character = characterTable.get(activeCharacterId);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        sender.sendMessage(GOLD + character.getName());
        sender.sendMessage(AQUA + "Age: " + WHITE + (character.getAge() < 0 ? "Empty" : character.getAge()));
        sender.sendMessage(AQUA + "Height: " + WHITE + (character.getHeight().isEmpty() ? "Empty" : character.getHeight()));
        sender.sendMessage(AQUA + "Weight: " + WHITE + (character.getWeight().isEmpty() ? "Empty" : character.getWeight()));
        sender.sendMessage(AQUA + "Appearance: " + WHITE + (character.getAppearance().isEmpty() ? "Empty" : character.getAppearance()));
        sender.sendMessage(AQUA + "Presence: " + WHITE + (character.getPresence().isEmpty() ? "Empty" : character.getPresence()));
        sender.sendMessage(AQUA + "Player: " + WHITE + target.getName());
        return true;
    }
}
