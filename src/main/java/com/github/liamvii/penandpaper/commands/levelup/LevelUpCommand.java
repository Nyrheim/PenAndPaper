package com.github.liamvii.penandpaper.commands.levelup;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.gui.LevelGUI;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.RED;

public final class LevelUpCommand implements CommandExecutor {

    private final Pen plugin;

    public LevelUpCommand(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(RED + "You must be a player to perform this command.");
            return true;
        }
        Player player = (Player) sender;
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            sender.sendMessage(RED + "You do not currently have an active character.");
            return true;
        }
        if (character.classes().stream()
                .map(CharacterClass::getLevel)
                .reduce(0, Integer::sum) >= character.getLevel()) {
            player.sendMessage(RED + "You do not have enough levels to level up in a class at this time.");
            return true;
        }
        LevelGUI gui = new LevelGUI(plugin);
        gui.initializeItems(player);
        gui.openInventory(player);
        return true;
    }
}
