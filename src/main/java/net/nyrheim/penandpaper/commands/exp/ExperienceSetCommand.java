package net.nyrheim.penandpaper.commands.exp;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ExperienceSetCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public ExperienceSetCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("penandpaper.command.experience.set")) {
            sender.sendMessage(RED + "You do not have permission.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify who to set the experience of.");
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(RED + "There is no player online by that name.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : (target.getName() + " does")) + " not currently have an active character.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "You must specify how much experience to set.");
            return true;
        }
        int experience;
        try {
            experience = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "Experience must be an integer.");
            return true;
        }
        if (experience > ExperienceLookupTable.MAX_EXPERIENCE) {
            sender.sendMessage(RED + "You may not set experience values higher than " + ExperienceLookupTable.MAX_EXPERIENCE);
            return true;
        }
        character.setExperience(experience);
        characterService.updateCharacter(character);
        sender.sendMessage(GREEN + character.getName() + "'s experience was set to " + experience + ".");
        target.sendMessage(GREEN + "Your experience was set.");
        return true;
    }
}
