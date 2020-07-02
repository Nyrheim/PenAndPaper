package net.nyrheim.penandpaper.commands.race;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class RaceCommand implements CommandExecutor {

    private final RaceTraitsCommand raceTraitsCommand;

    public RaceCommand(PenAndPaper plugin) {
        raceTraitsCommand = new RaceTraitsCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(RED + "Usage: /" + label + " [traits]");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "traits":
                return raceTraitsCommand.onCommand(
                        sender,
                        command,
                        label,
                        Arrays.stream(args).skip(1).toArray(String[]::new)
                );
            default:
                sender.sendMessage(RED + "Usage: /" + label + " [traits]");
                return true;
        }
    }
}
