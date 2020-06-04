package net.nyrheim.penandpaper.character;

import net.nyrheim.penandpaper.PenAndPaper;
import com.github.liamvii.penandpaper.database.table.*;
import net.nyrheim.penandpaper.database.table.*;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PlayerId;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import java.util.List;

public final class PenCharacterService {

    private final PenAndPaper plugin;

    public PenCharacterService(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    public List<PenCharacter> getCharacters(PenPlayer player) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        return characterTable.get(player.getPlayerId());
    }

    public PenCharacter getActiveCharacter(PenPlayer player) {
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId characterId = activeCharacterTable.get(player.getPlayerId());
        if (characterId == null) return null;
        return characterTable.get(characterId);
    }

    public boolean setActiveCharacter(PenPlayer player, PenCharacter character) {
        Player bukkitPlayer = plugin.getServer().getPlayer(player.getPlayerUUID().getValue());
        if (bukkitPlayer == null) {
            return false;
        }
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        CharacterId oldCharacterId = activeCharacterTable.get(player.getPlayerId());
        PenCharacter oldCharacter;
        if (oldCharacterId == null) {
            oldCharacter = null;
        } else {
            if (character != null) {
                if (oldCharacterId.getValue() == character.getId().getValue()) return true;
            }
            oldCharacter = characterTable.get(oldCharacterId);
        }
        if (oldCharacter != null) {
            oldCharacter.setHelmet(bukkitPlayer.getInventory().getHelmet());
            oldCharacter.setChestplate(bukkitPlayer.getInventory().getChestplate());
            oldCharacter.setLeggings(bukkitPlayer.getInventory().getLeggings());
            oldCharacter.setBoots(bukkitPlayer.getInventory().getBoots());
            oldCharacter.setInventoryContents(bukkitPlayer.getInventory().getContents());
            oldCharacter.setHealth(bukkitPlayer.getHealth());
            oldCharacter.setFoodLevel(bukkitPlayer.getFoodLevel());
            oldCharacter.setSaturation(bukkitPlayer.getSaturation());
            oldCharacter.setFoodExhaustion(bukkitPlayer.getExhaustion());
            oldCharacter.setLocation(bukkitPlayer.getLocation());
            characterTable.update(oldCharacter);
        }
        if (character != null) {
            bukkitPlayer.getInventory().setHelmet(character.getHelmet());
            bukkitPlayer.getInventory().setChestplate(character.getChestplate());
            bukkitPlayer.getInventory().setLeggings(character.getLeggings());
            bukkitPlayer.getInventory().setBoots(character.getBoots());
            bukkitPlayer.getInventory().setContents(character.getInventoryContents());
            bukkitPlayer.setHealth(character.getHealth());
            bukkitPlayer.setFoodLevel(character.getFoodLevel());
            bukkitPlayer.setSaturation(character.getSaturation());
            bukkitPlayer.setExhaustion(character.getExhaustion());
            bukkitPlayer.teleport(character.getLocation());
            activeCharacterTable.insertOrUpdate(player.getPlayerId(), character.getId());
        } else {
            bukkitPlayer.getInventory().setHelmet(null);
            bukkitPlayer.getInventory().setChestplate(null);
            bukkitPlayer.getInventory().setLeggings(null);
            bukkitPlayer.getInventory().setBoots(null);
            bukkitPlayer.getInventory().clear();
            AttributeInstance maxHealth = bukkitPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (maxHealth != null) {
                bukkitPlayer.setHealth(maxHealth.getValue());
            } else {
                bukkitPlayer.setHealth(20);
            }
            bukkitPlayer.setFoodLevel(20);
            bukkitPlayer.setSaturation(5);
            bukkitPlayer.setExhaustion(0);
            bukkitPlayer.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
            activeCharacterTable.delete(player.getPlayerId());
        }
        return true;
    }

    public PenCharacter getCharacter(CharacterId id) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        return characterTable.get(id);
    }

    public void addCharacter(PenCharacter character) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        characterTable.insert(character);
    }

    public void deleteCharacter(PenCharacter character) {
        ActiveCharacterTable activeCharacterTable = plugin.getDatabase().getTable(ActiveCharacterTable.class);
        PlayerId activePlayerId = activeCharacterTable.get(character.getId());
        if (activePlayerId != null) {
            PlayerTable playerTable = plugin.getDatabase().getTable(PlayerTable.class);
            PenPlayer penPlayer = playerTable.get(activePlayerId);
            if (penPlayer != null) {
                setActiveCharacter(penPlayer, null);
            }
        }
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        characterTable.delete(character);
    }

    public void updateCharacter(PenCharacter character) {
        CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
        characterTable.update(character);
    }

    public void updateAbilityScores(PenCharacter character) {
        CharacterAbilityScoreTable abilityScoreTable = plugin.getDatabase().getTable(CharacterAbilityScoreTable.class);
        abilityScoreTable.insertOrUpdateAbilityScores(character);
    }

    public void updateTempAbilityScores(PenCharacter character) {
        CharacterTempAbilityScoreTable tempAbilityScoreTable = plugin.getDatabase().getTable(CharacterTempAbilityScoreTable.class);
        tempAbilityScoreTable.insertOrUpdateAbilityScores(character);
    }

    public void updateAbilityScoreChoices(PenCharacter character) {
        CharacterAbilityScoreChoiceTable abilityScoreChoiceTable = plugin.getDatabase().getTable(CharacterAbilityScoreChoiceTable.class);
        abilityScoreChoiceTable.insertOrUpdateAbilityScores(character);
    }

    public void updateClasses(PenCharacter character) {
        CharacterClassTable characterClassTable = plugin.getDatabase().getTable(CharacterClassTable.class);
        characterClassTable.insertOrUpdateClasses(character);
    }

}
