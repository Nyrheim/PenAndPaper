package com.github.liamvii.penandpaper.database.table;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.database.Database;
import com.github.liamvii.penandpaper.utils.ItemStackUtils;
import org.bukkit.inventory.ItemStack;
import org.jooq.Record;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.github.liamvii.penandpaper.database.jooq.nyrheim.Tables.CHARACTER;
import static java.util.logging.Level.SEVERE;
import static org.jooq.impl.DSL.constraint;

public final class CharacterTable implements Table {

    private final Pen plugin;
    private final Database database;

    public CharacterTable(Pen plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(CHARACTER)
                .column(CHARACTER.ID)
                .column(CHARACTER.PLAYER_UUID)
                .column(CHARACTER.FIRST_NAME)
                .column(CHARACTER.FAMILY_NAME)
                .column(CHARACTER.HEIGHT)
                .column(CHARACTER.WEIGHT)
                .column(CHARACTER.APPEARANCE)
                .column(CHARACTER.PRESENCE)
                .column(CHARACTER.AGE)
                .column(CHARACTER.EXPERIENCE)
                .column(CHARACTER.EXHAUSTION)
                .column(CHARACTER.RACE)
                .column(CHARACTER.HELMET)
                .column(CHARACTER.CHESTPLATE)
                .column(CHARACTER.LEGGINGS)
                .column(CHARACTER.BOOTS)
                .column(CHARACTER.INVENTORY_CONTENTS)
                .constraints(
                        constraint("character_pk").primaryKey(CHARACTER.ID)
                )
                .execute();
    }

    public void insert(PlayerCharacter character) {
        byte[] serializedHelmet = null;
        if (character.getHelmet() != null) {
            try {
                serializedHelmet = ItemStackUtils.serializeItemStack(character.getHelmet());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize helmet", exception);
                return;
            }
        }
        byte[] serializedChestplate = null;
        if (character.getChestplate() != null) {
            try {
                serializedChestplate = ItemStackUtils.serializeItemStack(character.getChestplate());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize chestplate", exception);
                return;
            }
        }
        byte[] serializedLeggings = null;
        if (character.getLeggings() != null) {
            try {
                serializedLeggings = ItemStackUtils.serializeItemStack(character.getLeggings());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize leggings", exception);
                return;
            }
        }
        byte[] serializedBoots = null;
        if (character.getBoots() != null) {
            try {
                serializedBoots = ItemStackUtils.serializeItemStack(character.getBoots());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize boots", exception);
                return;
            }
        }
        byte[] serializedInventory = null;
        try {
            serializedInventory = ItemStackUtils.serializeItemStackArray(character.getInventoryContents());
        } catch (IOException exception) {
            plugin.getLogger().log(SEVERE, "Failed to serialize inventory", exception);
            return;
        }
        database.create()
                .insertInto(
                        CHARACTER,
                        CHARACTER.PLAYER_UUID,
                        CHARACTER.FIRST_NAME,
                        CHARACTER.FAMILY_NAME,
                        CHARACTER.HEIGHT,
                        CHARACTER.WEIGHT,
                        CHARACTER.APPEARANCE,
                        CHARACTER.PRESENCE,
                        CHARACTER.AGE,
                        CHARACTER.EXPERIENCE,
                        CHARACTER.EXHAUSTION,
                        CHARACTER.RACE,
                        CHARACTER.HELMET,
                        CHARACTER.CHESTPLATE,
                        CHARACTER.LEGGINGS,
                        CHARACTER.BOOTS,
                        CHARACTER.INVENTORY_CONTENTS
                )
                .values(
                        character.getUUID().toString(),
                        character.getFirstName(),
                        character.getFamilyName(),
                        character.getHeight(),
                        character.getWeight(),
                        character.getAppearance(),
                        character.getPresence(),
                        character.getAge(),
                        character.getExperience(),
                        character.getExhaustion(),
                        character.getRace(),
                        serializedHelmet,
                        serializedChestplate,
                        serializedLeggings,
                        serializedBoots,
                        serializedInventory
                )
                .execute();
        int id = database.create().lastID().intValue();
        character.setId(new CharacterId(id));

        database.getTable(CharacterAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterTempAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterClassTable.class).insertOrUpdateClasses(character);
    }

    public void update(PlayerCharacter character) {
        byte[] serializedHelmet = null;
        if (character.getHelmet() != null) {
            try {
                serializedHelmet = ItemStackUtils.serializeItemStack(character.getHelmet());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize helmet", exception);
                return;
            }
        }
        byte[] serializedChestplate = null;
        if (character.getChestplate() != null) {
            try {
                serializedChestplate = ItemStackUtils.serializeItemStack(character.getChestplate());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize chestplate", exception);
                return;
            }
        }
        byte[] serializedLeggings = null;
        if (character.getLeggings() != null) {
            try {
                serializedLeggings = ItemStackUtils.serializeItemStack(character.getLeggings());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize leggings", exception);
                return;
            }
        }
        byte[] serializedBoots = null;
        if (character.getBoots() != null) {
            try {
                serializedBoots = ItemStackUtils.serializeItemStack(character.getBoots());
            } catch (IOException exception) {
                plugin.getLogger().log(SEVERE, "Failed to serialize boots", exception);
                return;
            }
        }
        byte[] serializedInventory = null;
        try {
            serializedInventory = ItemStackUtils.serializeItemStackArray(character.getInventoryContents());
        } catch (IOException exception) {
            plugin.getLogger().log(SEVERE, "Failed to serialize inventory", exception);
            return;
        }
        database.create()
                .update(CHARACTER)
                .set(CHARACTER.PLAYER_UUID, character.getUUID().toString())
                .set(CHARACTER.FIRST_NAME, character.getFirstName())
                .set(CHARACTER.FAMILY_NAME, character.getFamilyName())
                .set(CHARACTER.HEIGHT, character.getHeight())
                .set(CHARACTER.WEIGHT, character.getWeight())
                .set(CHARACTER.APPEARANCE, character.getAppearance())
                .set(CHARACTER.PRESENCE, character.getPresence())
                .set(CHARACTER.AGE, character.getAge())
                .set(CHARACTER.EXPERIENCE, character.getExperience())
                .set(CHARACTER.EXHAUSTION, character.getExhaustion())
                .set(CHARACTER.RACE, character.getRace())
                .set(CHARACTER.HELMET, serializedHelmet)
                .set(CHARACTER.CHESTPLATE, serializedChestplate)
                .set(CHARACTER.LEGGINGS, serializedLeggings)
                .set(CHARACTER.BOOTS, serializedBoots)
                .set(CHARACTER.INVENTORY_CONTENTS, serializedInventory)
                .where(CHARACTER.ID.eq(character.getId().getValue()))
                .execute();

        database.getTable(CharacterAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterTempAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterClassTable.class).insertOrUpdateClasses(character);
    }

    public void delete(PlayerCharacter character) {
        database.getTable(CharacterAbilityScoreTable.class)
                .delete(character.getId());

        database.getTable(CharacterTempAbilityScoreTable.class)
                .delete(character.getId());

        database.getTable(CharacterClassTable.class)
                .delete(character.getId());

        database.create()
                .deleteFrom(CHARACTER)
                .where(CHARACTER.ID.eq(character.getId().getValue()))
                .execute();
    }

    public PlayerCharacter get(CharacterId id) {
        Record result = database.create()
                .select(
                        CHARACTER.PLAYER_UUID,
                        CHARACTER.FIRST_NAME,
                        CHARACTER.FAMILY_NAME,
                        CHARACTER.HEIGHT,
                        CHARACTER.WEIGHT,
                        CHARACTER.APPEARANCE,
                        CHARACTER.PRESENCE,
                        CHARACTER.AGE,
                        CHARACTER.EXPERIENCE,
                        CHARACTER.EXHAUSTION,
                        CHARACTER.RACE,
                        CHARACTER.HELMET,
                        CHARACTER.CHESTPLATE,
                        CHARACTER.LEGGINGS,
                        CHARACTER.BOOTS,
                        CHARACTER.INVENTORY_CONTENTS
                )
                .from(CHARACTER)
                .where(CHARACTER.ID.eq(id.getValue()))
                .fetchOne();
        if (result == null) return null;
        List<CharacterClass> classes = database.getTable(CharacterClassTable.class).get(id);
        DnDClass firstClass = classes.size() > 0 ? classes.get(0).getClazz() : null;
        ItemStack helmet = null;
        if (result.get(CHARACTER.HELMET) != null) {
            try {
                helmet = ItemStackUtils.deserializeItemStack(result.get(CHARACTER.HELMET));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize helmet", exception);
            }
        }
        ItemStack chestplate = null;
        if (result.get(CHARACTER.CHESTPLATE) != null) {
            try {
                chestplate = ItemStackUtils.deserializeItemStack(result.get(CHARACTER.CHESTPLATE));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize chestplate", exception);
            }
        }
        ItemStack leggings = null;
        if (result.get(CHARACTER.LEGGINGS) != null) {
            try {
                leggings = ItemStackUtils.deserializeItemStack(result.get(CHARACTER.LEGGINGS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize leggings", exception);
            }
        }
        ItemStack boots = null;
        if (result.get(CHARACTER.BOOTS) != null) {
            try {
                boots = ItemStackUtils.deserializeItemStack(result.get(CHARACTER.BOOTS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize boots", exception);
            }
        }
        ItemStack[] inventoryContents = new ItemStack[36];
        if (result.get(CHARACTER.INVENTORY_CONTENTS) != null) {
            try {
                ItemStackUtils.deserializeItemStackArray(result.get(CHARACTER.INVENTORY_CONTENTS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize inventory contents", exception);
            }
        }
        return new PlayerCharacter(
                plugin,
                id,
                UUID.fromString(result.get(CHARACTER.PLAYER_UUID)),
                result.get(CHARACTER.FIRST_NAME),
                result.get(CHARACTER.FAMILY_NAME),
                result.get(CHARACTER.HEIGHT),
                result.get(CHARACTER.WEIGHT),
                result.get(CHARACTER.APPEARANCE),
                result.get(CHARACTER.PRESENCE),
                result.get(CHARACTER.AGE),
                result.get(CHARACTER.EXPERIENCE),
                result.get(CHARACTER.EXHAUSTION),
                database.getTable(CharacterAbilityScoreTable.class).get(id),
                database.getTable(CharacterTempAbilityScoreTable.class).get(id),
                firstClass,
                classes,
                result.get(CHARACTER.RACE),
                helmet,
                chestplate,
                leggings,
                boots,
                inventoryContents
        );
    }

}
