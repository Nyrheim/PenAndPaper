package net.nyrheim.penandpaper.commands.exp;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ExperienceCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    private final ExperienceAddCommand experienceAddCommand;
    private final ExperienceSetCommand experienceSetCommand;

    public ExperienceCommand(PenAndPaper plugin) {
        this.plugin = plugin;
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
                    if (!sender.hasPermission("penandpaper.command.exp")) {
                        sender.sendMessage(RED + "You do not have permission to view experience.");
                        return true;
                    }
                    Player target = plugin.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage(RED + "There is no player online by that name.");
                        return true;
                    }
                    showExperience(sender, target);
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(RED + "You must specify a player when running this command from console.");
                return true;
            }
            Player player = (Player) sender;
            showExperience(sender, player);
        }
        return true;
    }

    private void showExperience(CommandSender sender, Player target) {
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return;
        }
        sender.sendMessage(GREEN + (target == sender ? "You have" : (character.getName() + "has")) + " "
                + character.getExperience() + " experience (level "
                + ExperienceLookupTable.getLevelAtExperience(character.getExperience()) + ").");
    }
}
