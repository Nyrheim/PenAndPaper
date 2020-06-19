package net.nyrheim.penandpaper.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class IPlayerTagLine implements ITagController.TagLine {

    private PenAndPaper plugin;

    public IPlayerTagLine(PenAndPaper plugin) { this.plugin = plugin; };

    @Override
    public String getText(Entity entity, Player viewer) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
            PenPlayer penPlayer = playerService.getPlayer(player);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            PenCharacter character = characterService.getActiveCharacter(penPlayer);
            if (character == null) {
                return player.getName();
            }
            else {
                if (player.hasPermission("penandpaper.admin")) {
                    return ChatColor.RED + character.getName();
                }
                else if (player.hasPermission("penandpaper.gamemaster")) {
                    return ChatColor.BLUE + character.getName();
                }
                else if (player.hasPermission("penandpaper.dungeonmaster")) {
                    return ChatColor.DARK_PURPLE + character.getName();
                }
                else if (player.hasPermission("penandpaper.platinum")) {
                    return ChatColor.GRAY + character.getName();
                }
                else if (player.hasPermission("penandpaper.gold")) {
                    return ChatColor.YELLOW + character.getName();
                }
                else if (player.hasPermission("penandpaper.silver")) {
                    return ChatColor.DARK_GRAY + character.getName();
                }
                else if (player.hasPermission("penandpaper.copper")) {
                    return ChatColor.GOLD + character.getName();
                }
                return character.getName();
            }
        }
        return null;
    }

    @Override
    public boolean keepSpaceWhenNull(Entity entity) {
        return false;
    }
}
