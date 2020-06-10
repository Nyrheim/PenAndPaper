package net.nyrheim.penandpaper.commands.dhp;

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

public final class DHPCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public DHPCommand(PenAndPaper plugin) {
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
            if (sender.hasPermission("penandpaper.command.dhp.other")) {
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
            sender.sendMessage(RED + "You must specify how many HP points to take as damage.");
            return true;
        }
        int damage;
        try {
            damage = Integer.parseInt(args[argOffset]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "The amount of HP points to remove must be an integer.");
            return true;
        }
        if (damage <= 0) {
            sender.sendMessage(RED + "You may not " + (sender == target ? "damage yourself with" : "deal") + " negative damage.");
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
        int remainingDamage = damage;
        if (character.getTempHP() > 0) {
            if (damage >= character.getTempHP()) {
                remainingDamage = damage - character.getTempHP();
                character.setTempHP(0);
            } else {
                remainingDamage = 0;
                character.setTempHP(character.getTempHP() - damage);
            }
        }
        character.setHP(character.getHP() - remainingDamage);
        characterService.updateCharacter(character);
        target.sendMessage(GREEN + "Took " + damage + " damage"
                + (damage != remainingDamage ? " (" + (damage - remainingDamage) + " absorbed by temp HP)" : "") + ".");
        if (target != sender) {
            sender.sendMessage(GREEN + "Dealt " + damage + " damage to " + character.getName()
                    + (damage != remainingDamage ? " (" + (damage - remainingDamage) + " absorbed by temp HP)" : "") + ".");
        }
        return true;
    }
}
