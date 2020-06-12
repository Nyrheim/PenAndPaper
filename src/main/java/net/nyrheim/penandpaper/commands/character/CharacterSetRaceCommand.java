package net.nyrheim.penandpaper.commands.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.gui.RaceGUI;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
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
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character.getRace() != null) {
            player.sendMessage(ChatColor.RED + "You have already set your race.");
        } else {
            RaceGUI gui = new RaceGUI(plugin);
            gui.initializeItems(player);
            gui.openInventory(player);
        }
        return true;
    }
}
