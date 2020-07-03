package net.nyrheim.penandpaper.commands.race;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.Race;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.*;

public final class RaceTraitsCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public RaceTraitsCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        Player target = null;
        if (sender instanceof Player) {
            target = (Player) sender;
        }
        if (args.length > 0) {
            target = plugin.getServer().getPlayerExact(args[0]);
            if (target == null) {
                if (sender instanceof Player) {
                    target = (Player) sender;
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
            sender.sendMessage(RED + (sender == target ? "You do" : target.getName() + " does") + " not currently have an active character.");
            return true;
        }
        Race race = character.getRace();
        if (race == null) {
            sender.sendMessage(RED + (sender == target ? "You have" : character.getName() + " has") + " not yet set a race.");
            return true;
        }
        race.getTraits().forEach(trait -> {
            sender.sendMessage(WHITE.toString() + BOLD + trait.getName());
            sender.sendMessage(WHITE + trait.getDescription());
            sender.sendMessage("");
        });
        return true;
    }
}
