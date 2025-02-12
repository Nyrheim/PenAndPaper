package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static org.bukkit.ChatColor.RED;

public final class CharacterSetCommand implements CommandExecutor {

    private final CharacterSetNameCommand characterSetNameCommand;
    private final CharacterSetAgeCommand characterSetAgeCommand;
    private final CharacterSetHeightCommand characterSetHeightCommand;
    private final CharacterSetWeightCommand characterSetWeightCommand;
    private final CharacterSetAppearanceCommand characterSetAppearanceCommand;
    private final CharacterSetPresenceCommand characterSetPresenceCommand;
    private final CharacterSetRaceCommand characterSetRaceCommand;

    public CharacterSetCommand(PenAndPaper plugin) {
        this.characterSetNameCommand = new CharacterSetNameCommand(plugin);
        this.characterSetAgeCommand = new CharacterSetAgeCommand(plugin);
        this.characterSetHeightCommand = new CharacterSetHeightCommand(plugin);
        this.characterSetWeightCommand = new CharacterSetWeightCommand(plugin);
        this.characterSetAppearanceCommand = new CharacterSetAppearanceCommand(plugin);
        this.characterSetPresenceCommand = new CharacterSetPresenceCommand(plugin);
        this.characterSetRaceCommand = new CharacterSetRaceCommand(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "name":
                    return characterSetNameCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
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
                case "presence":
                    return characterSetPresenceCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                case "race":
                    return characterSetRaceCommand.onCommand(
                            sender,
                            command,
                            label,
                            Arrays.stream(args).skip(1).toArray(String[]::new)
                    );
                default:
                    sender.sendMessage(RED + "Usage: /" + label + " set [firstname | familyname | age | height | weight | appearance | presence | exp ]");
                    break;
            }
        } else {
            sender.sendMessage(RED + "Usage: /" + label + " set [firstname | familyname | age | height | weight | appearance | presence | exp ]");
        }
        return true;
    }
}
