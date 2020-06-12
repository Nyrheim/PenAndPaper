package net.nyrheim.penandpaper.commands.exhaustion;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.*;

public class ExhaustionPlayerCheckCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public ExhaustionPlayerCheckCommand(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player target = (Player) sender;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(target);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        int exhaustion = character.getExhaustion();
        if (exhaustion <= 20) {
            target.sendMessage(GREEN + "You feel energized.");
        }
        if (exhaustion > 20 && exhaustion <= 40) {
            target.sendMessage(DARK_AQUA + "You feel fine.");
        }
        if (exhaustion > 40 && exhaustion <= 60) {
            target.sendMessage(YELLOW + "You're starting to tire. Perhaps you should take a break.");
        }
        if (exhaustion > 60 && exhaustion <= 80) {
            target.sendMessage(GRAY + "You're absolutely exhausted, you can't carry on for much longer.");
        }
        if (exhaustion > 80 && exhaustion < 100) {
            target.sendMessage(RED + "You feel like you're about to collapse.");
        }
        if (exhaustion == 100) {
            target.sendMessage(DARK_RED + "You are physically incapable of moving.");
        }
        return true;
        }

    }
