package net.nyrheim.penandpaper.commands.roll;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.dice.Roll.Die;
import net.nyrheim.penandpaper.dice.Roll.Modifier;
import net.nyrheim.penandpaper.dice.RollPartResult;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.bukkit.ChatColor.*;

public class RollCommand  implements CommandExecutor {

    private final PenAndPaper plugin;
    public RollCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player commandSender = (Player) sender;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(commandSender);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        if (args.length == 1) {
            try {
                Roll input = Roll.parse(args[0]);
                List<RollPartResult> partResultList = input.roll();
                for (Player player : commandSender.getWorld().getPlayers()) {
                    if (player.getLocation().distanceSquared(commandSender.getLocation()) <= 400) {
                        int result = partResultList.stream()
                                .mapToInt(RollPartResult::getResult)
                                .sum();
                        player.sendMessage(ChatColor.GOLD + character.getName() + ChatColor.WHITE + " rolled " + input.toDisplayString());
                        player.sendMessage(ChatColor.GOLD + "Result: " +
                                partResultList.stream()
                                        .map(rollPartResult -> {
                                            if (rollPartResult.getRollPart() instanceof Die) {
                                                return AQUA + rollPartResult.toString() + WHITE;
                                            } else if (rollPartResult.getRollPart() instanceof Modifier) {
                                                return YELLOW + rollPartResult.toString() + WHITE;
                                            } else {
                                                return rollPartResult.toString();
                                            }
                                        })
                                        .reduce((a, b) -> a + "+" + b)
                                        .orElse("")
                                + " = " + result);
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage(RED + "Please enter your dice rolls in the following example format: 1d10+3d10+5.");
                return true;
            }
        }
        else {
            sender.sendMessage(RED + "Please enter your dice rolls in the following example format: 1d10+3d10+5.");
            return true;
        }

    }
}
