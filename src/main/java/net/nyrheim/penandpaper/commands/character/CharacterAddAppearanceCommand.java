package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class CharacterAddAppearanceCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public CharacterAddAppearanceCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        int argOffset = 0;
        if (args.length > 1) {
            if (sender.hasPermission("penandpaper.command.character.add.appearance.other")) {
                target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    argOffset = 1;
                } else {
                    if (sender instanceof Player) {
                        target = (Player) sender;
                    }
                }
            }
        }
        if (target == null) {
            sender.sendMessage(RED + "You must specify a player when running this command from console.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + (target == sender ? "You do" : target.getName() + " does") + " not currently have an active character.");
            return true;
        }
        String appearance = character.getAppearance() + " " + Arrays.stream(args).skip(argOffset).reduce((a, b) -> a + " " + b).orElse("");
        if (appearance.length() > 4096) {
            sender.sendMessage(RED + (sender == target ? "Your" : (character.getName() + "'s")) + " appearance may be at most 4096 characters long.");
            return true;
        }
        character.setAppearance(appearance);
        characterService.updateCharacter(character);
        sender.sendMessage(GREEN + (sender == target ? "Your" : (character.getName() + "'s")) + " appearance is now \"" + appearance + "\".");
        if (sender != target) {
            sender.sendMessage(GREEN + "Your appearance is now \"" + appearance + "\".");
        }
        return true;
    }

}
