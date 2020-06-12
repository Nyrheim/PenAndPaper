package net.nyrheim.penandpaper.commands.roll;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.dice.RollPartResult;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        if (args.length == 1) {
            try {
                Roll input = Roll.parse(args[0]);
                List<RollPartResult> partResultList = input.roll();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().distance(commandSender.getLocation()) < 20) {
                        int result = partResultList.stream()
                                .mapToInt(RollPartResult::getResult)
                                .sum();
                        player.sendMessage(ChatColor.GOLD + character.getName() + ChatColor.WHITE +  " rolled " + args[0]);
                        player.sendMessage(ChatColor.GOLD + "Result: " +
                                partResultList.stream()
                                .map(RollPartResult::toString)
                                .reduce((a, b) -> a+"+"+b)
                                .orElse("")
                        + " = " + result);
                    }
                }
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Please enter your dice rolls in the following example format: 1d10+3d10+5.");
                return true;
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "Please enter your dice rolls in the following example format: 1d10+3d10+5.");
            return true;
        }

    }
}
