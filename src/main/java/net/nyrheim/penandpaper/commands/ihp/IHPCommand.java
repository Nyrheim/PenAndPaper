package net.nyrheim.penandpaper.commands.ihp;

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

public class IHPCommand implements CommandExecutor {

    private final PenAndPaper plugin;

    public IHPCommand(PenAndPaper plugin) {
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
        if (args.length < 1) {
            sender.sendMessage(RED + "You must specify how many HP points to take as damage.");
            return true;
        }
        int health;
        try {
            health = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(RED + "The amount of HP points to heal must be an integer.");
            return true;
        }
        if (health <= 0) {
            sender.sendMessage(RED + "You may not heal yourself with negative health.");
            return true;
        }
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        if (character.getHP() + health > character.getMaxHP()) {
            health = character.getMaxHP() - character.getHP();
        }
        character.setHP(character.getHP() + health);
        characterService.updateCharacter(character);
        sender.sendMessage(GREEN + "Healed " + health + " health.");
        return true;
    }

}
