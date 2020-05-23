package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class CharacterSetCommand implements CommandExecutor {

    private final Pen plugin;

    private final CharacterSetAgeCommand characterSetAgeCommand;
    private final CharacterSetHeightCommand characterSetHeightCommand;
    private final CharacterSetWeightCommand characterSetWeightCommand;
    private final CharacterSetAppearanceCommand characterSetAppearanceCommand;

    public CharacterSetCommand(Pen plugin) {
        this.plugin = plugin;
        this.characterSetAgeCommand = new CharacterSetAgeCommand(plugin);
        this.characterSetHeightCommand = new CharacterSetHeightCommand(plugin);
        this.characterSetWeightCommand = new CharacterSetWeightCommand(plugin);
        this.characterSetAppearanceCommand = new CharacterSetAppearanceCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "age":
                    return characterSetAgeCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "height":
                    return characterSetHeightCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "weight":
                    return characterSetWeightCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "appearance":
                    return characterSetAppearanceCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " set [age|height|weight|appearance]");
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " set [age|height|weight|appearance]");
        }
        return true;
    }
}
