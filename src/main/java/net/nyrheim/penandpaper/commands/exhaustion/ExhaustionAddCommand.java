package net.nyrheim.penandpaper.commands.exhaustion;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.exhaustion.ExhaustionTier;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class ExhaustionAddCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public ExhaustionAddCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!sender.hasPermission("penandpaper.command.exhaustion.add")) {
            sender.sendMessage(RED + "You do not have permission to add exhaustion.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage(RED + "You must specify a target and an amount to increase exhaustion by.");
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
        int oldExhaustion = character.getExhaustion();
        character.setExhaustion(character.getExhaustion() + amount);
        characterService.updateCharacter(character);
        int newExhaustion = character.getExhaustion();
        sender.sendMessage(GREEN + "Exhaustion increased.");
        ExhaustionTier oldExhaustionTier = ExhaustionTier.forExhaustionValue(oldExhaustion);
        ExhaustionTier newExhaustionTier = ExhaustionTier.forExhaustionValue(newExhaustion);
        if (oldExhaustionTier != newExhaustionTier) {
            target.sendMessage(newExhaustionTier.getMessageSelf());
            if (sender != target) {
                sender.sendMessage(newExhaustionTier.getMessageOther(character));
            }
        }
        return true;
    }
}
