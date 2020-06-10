package net.nyrheim.penandpaper.database.table;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.clazz.CharacterClass;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.player.PlayerId;
import net.nyrheim.penandpaper.race.PenRaceService;
import net.nyrheim.penandpaper.race.Race;
import net.nyrheim.penandpaper.utils.ItemStackUtils;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.jooq.Record;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.logging.Level.SEVERE;
import static net.nyrheim.penandpaper.database.jooq.Tables.CHARACTER;
import static org.jooq.impl.DSL.*;

public final class CharacterTable implements Table {

    private final PenAndPaper plugin;
    private final Database database;

    private final Cache<Integer, PenCharacter> cache;

    public CharacterTable(PenAndPaper plugin, Database database) {
        this.plugin = plugin;
        this.database = database;
        cache = database.getCacheManager().createCache("penandpaper.character.id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, PenCharacter.class,
                        ResourcePoolsBuilder.heap(25)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(CHARACTER)
                .column(CHARACTER.ID)
                .column(CHARACTER.PLAYER_ID)
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
                .column(CHARACTER.HEALTH)
                .column(CHARACTER.FOOD_LEVEL)
                .column(CHARACTER.SATURATION)
                .column(CHARACTER.FOOD_EXHAUSTION)
                .column(CHARACTER.WORLD)
                .column(CHARACTER.X)
                .column(CHARACTER.Y)
                .column(CHARACTER.Z)
                .column(CHARACTER.PITCH)
                .column(CHARACTER.YAW)
                .constraints(
                        constraint("character_pk").primaryKey(CHARACTER.ID)
                )
                .execute();

        database.create()
                .alterTable(CHARACTER)
                .addColumnIfNotExists(CHARACTER.HP)
                .execute();

        database.create()
                .alterTable(CHARACTER)
                .addColumnIfNotExists(CHARACTER.TEMP_HP)
                .execute();
    }

    public void insert(PenCharacter character) {
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
        byte[] serializedInventory;
        try {
            serializedInventory = ItemStackUtils.serializeItemStackArray(character.getInventoryContents());
        } catch (IOException exception) {
            plugin.getLogger().log(SEVERE, "Failed to serialize inventory", exception);
            return;
        }
        database.create()
                .insertInto(
                        CHARACTER,
                        CHARACTER.PLAYER_ID,
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
                        CHARACTER.INVENTORY_CONTENTS,
                        CHARACTER.HEALTH,
                        CHARACTER.FOOD_LEVEL,
                        CHARACTER.SATURATION,
                        CHARACTER.FOOD_EXHAUSTION,
                        CHARACTER.WORLD,
                        CHARACTER.X,
                        CHARACTER.Y,
                        CHARACTER.Z,
                        CHARACTER.PITCH,
                        CHARACTER.YAW,
                        CHARACTER.HP,
                        CHARACTER.TEMP_HP
                )
                .values(
                        character.getPlayerId().getValue(),
                        character.getFirstName(),
                        character.getFamilyName(),
                        character.getHeight(),
                        character.getWeight(),
                        character.getAppearance(),
                        character.getPresence(),
                        character.getAge(),
                        character.getExperience(),
                        character.getExhaustion(),
                        character.getRace() == null ? null : character.getRace().getName(),
                        serializedHelmet,
                        serializedChestplate,
                        serializedLeggings,
                        serializedBoots,
                        serializedInventory,
                        character.getHealth(),
                        character.getFoodLevel(),
                        (double) character.getSaturation(),
                        (double) character.getFoodExhaustion(),
                        character.getLocation().getWorld().getName(),
                        character.getLocation().getX(),
                        character.getLocation().getY(),
                        character.getLocation().getZ(),
                        character.getLocation().getPitch(),
                        character.getLocation().getYaw(),
                        character.getHP(),
                        character.getTempHP()
                )
                .execute();
        int id = database.create().lastID().intValue();
        character.setId(new CharacterId(id));

        database.getTable(CharacterAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterTempAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterAbilityScoreChoiceTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterClassTable.class).insertOrUpdateClasses(character);

        cache.put(id, character);
    }

    public void update(PenCharacter character) {
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
        byte[] serializedInventory;
        try {
            serializedInventory = ItemStackUtils.serializeItemStackArray(character.getInventoryContents());
        } catch (IOException exception) {
            plugin.getLogger().log(SEVERE, "Failed to serialize inventory", exception);
            return;
        }
        database.create()
                .update(CHARACTER)
                .set(CHARACTER.PLAYER_ID, character.getPlayerId().getValue())
                .set(CHARACTER.FIRST_NAME, character.getFirstName())
                .set(CHARACTER.FAMILY_NAME, character.getFamilyName())
                .set(CHARACTER.HEIGHT, character.getHeight())
                .set(CHARACTER.WEIGHT, character.getWeight())
                .set(CHARACTER.APPEARANCE, character.getAppearance())
                .set(CHARACTER.PRESENCE, character.getPresence())
                .set(CHARACTER.AGE, character.getAge())
                .set(CHARACTER.EXPERIENCE, character.getExperience())
                .set(CHARACTER.EXHAUSTION, character.getExhaustion())
                .set(CHARACTER.RACE, character.getRace() == null ? null : character.getRace().getName())
                .set(CHARACTER.HELMET, serializedHelmet)
                .set(CHARACTER.CHESTPLATE, serializedChestplate)
                .set(CHARACTER.LEGGINGS, serializedLeggings)
                .set(CHARACTER.BOOTS, serializedBoots)
                .set(CHARACTER.INVENTORY_CONTENTS, serializedInventory)
                .set(CHARACTER.HEALTH, character.getHealth())
                .set(CHARACTER.FOOD_LEVEL, character.getFoodLevel())
                .set(CHARACTER.SATURATION, (double) character.getSaturation())
                .set(CHARACTER.FOOD_EXHAUSTION, (double) character.getFoodExhaustion())
                .set(CHARACTER.WORLD, character.getLocation().getWorld().getName())
                .set(CHARACTER.X, character.getLocation().getX())
                .set(CHARACTER.Y, character.getLocation().getY())
                .set(CHARACTER.Z, character.getLocation().getZ())
                .set(CHARACTER.PITCH, (double) character.getLocation().getPitch())
                .set(CHARACTER.YAW, (double) character.getLocation().getYaw())
                .set(CHARACTER.HP, character.getHP())
                .set(CHARACTER.TEMP_HP, character.getTempHP())
                .where(CHARACTER.ID.eq(character.getId().getValue()))
                .execute();

        database.getTable(CharacterAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterTempAbilityScoreTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterAbilityScoreChoiceTable.class).insertOrUpdateAbilityScores(character);
        database.getTable(CharacterClassTable.class).insertOrUpdateClasses(character);

        cache.put(character.getId().getValue(), character);
    }

    public void delete(PenCharacter character) {
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

        cache.remove(character.getId().getValue());
    }

    public PenCharacter get(CharacterId id) {
        if (cache.containsKey(id.getValue())) {
            return cache.get(id.getValue());
        }
        Record result = database.create()
                .select(
                        CHARACTER.PLAYER_ID,
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
                        CHARACTER.INVENTORY_CONTENTS,
                        CHARACTER.HEALTH,
                        CHARACTER.FOOD_LEVEL,
                        CHARACTER.SATURATION,
                        CHARACTER.FOOD_EXHAUSTION,
                        CHARACTER.WORLD,
                        CHARACTER.X,
                        CHARACTER.Y,
                        CHARACTER.Z,
                        CHARACTER.YAW,
                        CHARACTER.PITCH,
                        CHARACTER.HP,
                        CHARACTER.TEMP_HP
                )
                .from(CHARACTER)
                .where(CHARACTER.ID.eq(id.getValue()))
                .fetchOne();
        if (result == null) return null;
        List<CharacterClass> classes = database.getTable(CharacterClassTable.class).get(id);
        PenClass firstClass = classes.size() > 0 ? classes.get(0).getClazz() : null;
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
        PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
        String raceName = result.get(CHARACTER.RACE);
        Race race = raceName == null ? null : raceService.getRace(raceName);
        PenCharacter character = new PenCharacter(
                plugin,
                id,
                new PlayerId(result.get(CHARACTER.PLAYER_ID)),
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
                database.getTable(CharacterAbilityScoreChoiceTable.class).get(id),
                firstClass,
                classes,
                race,
                helmet,
                chestplate,
                leggings,
                boots,
                inventoryContents,
                result.get(CHARACTER.HEALTH),
                result.get(CHARACTER.FOOD_LEVEL),
                (float) (double) result.get(CHARACTER.SATURATION),
                (float) (double) result.get(CHARACTER.FOOD_EXHAUSTION),
                new Location(
                        plugin.getServer().getWorld(result.get(CHARACTER.WORLD)),
                        result.get(CHARACTER.X),
                        result.get(CHARACTER.Y),
                        result.get(CHARACTER.Z),
                        (float) (double) result.get(CHARACTER.PITCH),
                        (float) (double) result.get(CHARACTER.YAW)
                ),
                result.get(CHARACTER.HP),
                result.get(CHARACTER.TEMP_HP)
        );
        cache.put(id.getValue(), character);
        return character;
    }

    public List<PenCharacter> get(PlayerId playerId) {
        List<? extends Record> results = database.create()
                .select(CHARACTER.ID)
                .from(CHARACTER)
                .where(CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .fetch();
        return results.stream()
                .map(result -> get(new CharacterId(result.get(CHARACTER.ID))))
                .collect(Collectors.toList());
    }

    public void updateExhaustion() {
        database.create()
                .update(CHARACTER)
                .set(CHARACTER.EXHAUSTION, greatest(value(11).minus(CHARACTER.EXHAUSTION.multiply(CHARACTER.EXHAUSTION).divide(value(1000))), value(0)))
                .where(CHARACTER.EXHAUSTION.greaterThan(0))
                .execute();
        cache.clear();
    }

}
