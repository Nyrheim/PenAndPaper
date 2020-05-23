package com.github.liamvii.penandpaper.commands.character;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class CharacterAddCommand implements CommandExecutor {

    private final CharacterAddAppearanceCommand characterAddAppearanceCommand;

    public CharacterAddCommand(Pen plugin) {
        this.characterAddAppearanceCommand = new CharacterAddAppearanceCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "appearance":
                    return characterAddAppearanceCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " add [appearance]");
                    break;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " add [appearance]");
        }
        return true;
    }
}
