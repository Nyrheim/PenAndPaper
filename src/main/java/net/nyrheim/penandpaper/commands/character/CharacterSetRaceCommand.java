package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.gui.RaceGUI;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.PenRaceService;
import net.nyrheim.penandpaper.race.Race;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;

public final class CharacterSetRaceCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public CharacterSetRaceCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            PenPlayer penPlayer = playerService.getPlayer(player);
            PenCharacter character = characterService.getActiveCharacter(penPlayer);
            if (character == null) {
                player.sendMessage(ChatColor.RED + "You do not have an active character.");
                return true;
            }
            PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
            Race human = raceService.getRace("HUMAN");
            if (character.getRace() != null && character.getRace() != human) {
                player.sendMessage(ChatColor.RED + "You have already set your race.");
                return true;
            } else {
                RaceGUI gui = new RaceGUI(plugin);
                gui.initializeItems(player);
                if (character.getRace() == human) {
                    gui.onClick(player, 3);
                }
                gui.openInventory(player);
                return true;
            }
        }
        if (args.length == 1) {
            if (player.hasPermission("penandpaper.command.character.set.race.other")) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    RaceGUI gui = new RaceGUI(plugin);
                    gui.initializeItems(target);
                    gui.openInventory(target);
                    return true;
                }
                else {
                    player.sendMessage(ChatColor.RED + "That player does not exist or is offline!");
                    return true;
                }
            }
            return true;
        }
        player.sendMessage("You must specify a player.");
        return true;
    }
}
