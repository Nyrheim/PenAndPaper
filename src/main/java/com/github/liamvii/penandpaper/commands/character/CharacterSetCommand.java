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

    public CharacterSetCommand(Pen plugin) {
        this.plugin = plugin;
        this.characterSetAgeCommand = new CharacterSetAgeCommand(plugin);
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
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " set [age]");
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " set [age]");
        }
        return true;
    }
}
