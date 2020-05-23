package com.github.liamvii.penandpaper.commands.exp;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class ExperienceCommand implements CommandExecutor {

    private final ExperienceAddCommand experienceAddCommand;
    private final ExperienceSetCommand experienceSetCommand;

    public ExperienceCommand(Pen plugin) {
        this.experienceAddCommand = new ExperienceAddCommand(plugin);
        this.experienceSetCommand = new ExperienceSetCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "add":
                    return experienceAddCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "set":
                    return experienceSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " [add]]");
                    break;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " [add]");
        }
        return true;
    }
}
