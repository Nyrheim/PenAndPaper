package com.github.liamvii.penandpaper.commands.stat;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class AbilityCommand implements CommandExecutor {

    private final AbilitySetCommand abilitySetCommand;

    public AbilityCommand(Pen plugin) {
        this.abilitySetCommand = new AbilitySetCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "set":
                    return abilitySetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " [set]");
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " [set]");
        }
        return true;
    }
}
