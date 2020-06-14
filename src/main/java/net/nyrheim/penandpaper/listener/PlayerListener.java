package net.nyrheim.penandpaper.listener;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private PenAndPaper plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("penandpaper.dungeonmaster")) {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
        }
        else if (player.hasPermission("penandpaper.gamemaster")) {
            player.setPlayerListName(ChatColor.BLUE + player.getName());
        }
        else if (player.hasPermission("penandpaper.admin")) {
            player.setPlayerListName(ChatColor.RED + player.getName());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    }
}
