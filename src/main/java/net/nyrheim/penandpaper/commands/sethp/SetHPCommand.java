package net.nyrheim.penandpaper.commands.sethp;

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

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class SetHPCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public SetHPCommand(PenAndPaper plugin) {
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
        int argOffset = 0;
        if (args.length > 1) {
            if (sender.hasPermission("penandpaper.command.sethp.other")) {
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
        if (args.length < argOffset + 1) {
            sender.sendMessage(RED + "You must specify how many HP points to set.");
            return true;
        }
        int hp;
        try {
            hp = Integer.parseInt(args[argOffset]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "The amount of HP points to set must be an integer.");
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
        if (hp > character.getMaxHP()) {
            hp = character.getMaxHP();
        }
        character.setHP(hp);
        characterService.updateCharacter(character);
        target.sendMessage(GREEN + "HP set to " + hp + ".");
        if (target != sender) {
            sender.sendMessage(GREEN + "Set " + character.getName() + "'s HP to " + hp + ".");
        }
        return true;
    }
}
