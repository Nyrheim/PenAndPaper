package net.nyrheim.penandpaper.commands.exhaustion;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class ExhaustionCommand implements CommandExecutor {

    private final ExhaustionAddCommand exhaustionAddCommand;
    private final ExhaustionRemoveCommand exhaustionRemoveCommand;
    private final ExhaustionSetCommand exhaustionSetCommand;
    private final ExhaustionPlayerCheckCommand exhaustionPlayerCheckCommand;

    public ExhaustionCommand(PenAndPaper plugin) {
        exhaustionAddCommand = new ExhaustionAddCommand(plugin);
        exhaustionRemoveCommand = new ExhaustionRemoveCommand(plugin);
        exhaustionSetCommand = new ExhaustionSetCommand(plugin);
        exhaustionPlayerCheckCommand = new ExhaustionPlayerCheckCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length == 0) {
            return exhaustionPlayerCheckCommand.onCommand(
                    sender,
                    command,
                    label,
                    Arrays.stream(args).skip(1).toArray(String[]::new));
        }
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "add": case "a":
                    return exhaustionAddCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "remove": case "r":
                    return exhaustionRemoveCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "set": case "s":
                    return exhaustionSetCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " [add|remove|set]");
                    return true;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " [add|remove|set]");
        }
        return true;
    }
}
