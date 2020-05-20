package com.github.liamvii.penandpaper.listener;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private Pen plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CharacterHolder holder = new CharacterHolder(player);
        if (holder.getActive() != -2) {
            PlayerCharacter playerCharacter = new PlayerCharacter(player);
            Pen.addCharacter(holder.getActive(), playerCharacter);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CharacterHolder holder = Pen.getHolder(event.getPlayer());
        if(holder.getActive() != -2) {
            if(Pen.getCharacter(holder.getActive()).getFirstName() == null) {
                Pen.getCharacter(holder.getActive()).delChar(holder.getActive(), holder);
            }
        }
    }

}
