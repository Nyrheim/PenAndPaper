package net.nyrheim.penandpaper.commands.exhaustion;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.GREEN;

public final class ExhaustionSetCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public ExhaustionSetCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("penandpaper.command.exhaustion.set")) {
            sender.sendMessage(RED + "You do not have permission to set exhaustion.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "You must specify a target and an amount to set exhaustion to.");
            return true;
        }
        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(RED + "There is no player by that name.");
            return true;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "The amount specified must be an integer.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "That player does not currently have an active character.");
            return true;
        }
        character.setExhaustion(amount);
        characterService.updateCharacter(character);
        sender.sendMessage(GREEN + "Exhaustion set.");
        return true;
    }
}
