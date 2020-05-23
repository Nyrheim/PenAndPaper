package com.github.liamvii.penandpaper.player;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.ActiveCharacterTable;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public final class PenPlayer {

    private final Pen plugin;
    private final PlayerId playerId;

    public PenPlayer(Pen plugin, PlayerId playerId) {
        this.plugin = plugin;
        this.playerId = playerId;
    }

    public PenPlayer(Pen plugin, Player player) {
        this(plugin, new PlayerId(player.getUniqueId()));
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public boolean switchCharacter(PlayerCharacter newCharacter) {
        Player player = plugin.getServer().getPlayer(getPlayerId().getValue());
        if (player == null) {
            return false;
        }
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId oldCharacterId = activeCharacterTable.get(getPlayerId());
        PlayerCharacter oldCharacter;
        if (oldCharacterId == null) {
            oldCharacter = null;
        } else {
            oldCharacter = characterTable.get(oldCharacterId);
        }
        if (oldCharacter != null) {
            oldCharacter.setHelmet(player.getInventory().getHelmet());
            oldCharacter.setChestplate(player.getInventory().getChestplate());
            oldCharacter.setLeggings(player.getInventory().getLeggings());
            oldCharacter.setBoots(player.getInventory().getBoots());
            oldCharacter.setInventoryContents(player.getInventory().getContents());
            oldCharacter.setHealth(player.getHealth());
            oldCharacter.setFoodLevel(player.getFoodLevel());
            oldCharacter.setSaturation(player.getSaturation());
            oldCharacter.setFoodExhaustion(player.getExhaustion());
            oldCharacter.setLocation(player.getLocation());
            characterTable.update(oldCharacter);
        }
        if (newCharacter != null) {
            player.getInventory().setHelmet(newCharacter.getHelmet());
            player.getInventory().setChestplate(newCharacter.getChestplate());
            player.getInventory().setLeggings(newCharacter.getLeggings());
            player.getInventory().setBoots(newCharacter.getBoots());
            player.getInventory().setContents(newCharacter.getInventoryContents());
            player.setHealth(newCharacter.getHealth());
            player.setFoodLevel(newCharacter.getFoodLevel());
            player.setSaturation(newCharacter.getSaturation());
            player.setExhaustion(newCharacter.getExhaustion());
            player.teleport(newCharacter.getLocation());
            activeCharacterTable.insertOrUpdate(getPlayerId(), newCharacter.getId());
        } else {
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.getInventory().clear();
            AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (maxHealth != null) {
                player.setHealth(maxHealth.getValue());
            } else {
                player.setHealth(20);
            }
            player.setFoodLevel(20);
            player.setSaturation(5);
            player.setExhaustion(0);
            player.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
            activeCharacterTable.delete(getPlayerId());
        }
        return true;
    }

}
