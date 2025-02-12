package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class CharacterAddCommand implements CommandExecutor {

    private final CharacterAddAppearanceCommand characterAddAppearanceCommand;
    private final CharacterAddPresenceCommand characterAddPresenceCommand;

    public CharacterAddCommand(PenAndPaper plugin) {
        this.characterAddAppearanceCommand = new CharacterAddAppearanceCommand(plugin);
        this.characterAddPresenceCommand = new CharacterAddPresenceCommand(plugin);
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
                case "presence":
                    return characterAddPresenceCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " add [appearance|presence]");
                    break;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " add [appearance|presence]");
        }
        return true;
    }
}
