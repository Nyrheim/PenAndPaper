package com.github.liamvii.penandpaper.listener;

import com.github.liamvii.penandpaper.Pen;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Pen plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    }
}
