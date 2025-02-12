package net.nyrheim.penandpaper.commands.clazz;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class ClassCommand implements CommandExecutor {

    private final ClassApproveCommand classApproveCommand;

    public ClassCommand(PenAndPaper plugin) {
        this.classApproveCommand = new ClassApproveCommand(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "approve":
                    return classApproveCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " [approve]");
                    break;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " [approve]");
        }
        return true;
    }
}
