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
import net.nyrheim.penandpaper.database.jooq.Tables;
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
import static org.jooq.impl.DSL.constraint;

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
                .createTableIfNotExists(Tables.CHARACTER)
                .column(Tables.CHARACTER.ID)
                .column(Tables.CHARACTER.PLAYER_ID)
                .column(Tables.CHARACTER.FIRST_NAME)
                .column(Tables.CHARACTER.FAMILY_NAME)
                .column(Tables.CHARACTER.HEIGHT)
                .column(Tables.CHARACTER.WEIGHT)
                .column(Tables.CHARACTER.APPEARANCE)
                .column(Tables.CHARACTER.PRESENCE)
                .column(Tables.CHARACTER.AGE)
                .column(Tables.CHARACTER.EXPERIENCE)
                .column(Tables.CHARACTER.EXHAUSTION)
                .column(Tables.CHARACTER.RACE)
                .column(Tables.CHARACTER.HELMET)
                .column(Tables.CHARACTER.CHESTPLATE)
                .column(Tables.CHARACTER.LEGGINGS)
                .column(Tables.CHARACTER.BOOTS)
                .column(Tables.CHARACTER.INVENTORY_CONTENTS)
                .column(Tables.CHARACTER.HEALTH)
                .column(Tables.CHARACTER.FOOD_LEVEL)
                .column(Tables.CHARACTER.SATURATION)
                .column(Tables.CHARACTER.FOOD_EXHAUSTION)
                .column(Tables.CHARACTER.WORLD)
                .column(Tables.CHARACTER.X)
                .column(Tables.CHARACTER.Y)
                .column(Tables.CHARACTER.Z)
                .column(Tables.CHARACTER.PITCH)
                .column(Tables.CHARACTER.YAW)
                .constraints(
                        constraint("character_pk").primaryKey(Tables.CHARACTER.ID)
                )
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
                        Tables.CHARACTER,
                        Tables.CHARACTER.PLAYER_ID,
                        Tables.CHARACTER.FIRST_NAME,
                        Tables.CHARACTER.FAMILY_NAME,
                        Tables.CHARACTER.HEIGHT,
                        Tables.CHARACTER.WEIGHT,
                        Tables.CHARACTER.APPEARANCE,
                        Tables.CHARACTER.PRESENCE,
                        Tables.CHARACTER.AGE,
                        Tables.CHARACTER.EXPERIENCE,
                        Tables.CHARACTER.EXHAUSTION,
                        Tables.CHARACTER.RACE,
                        Tables.CHARACTER.HELMET,
                        Tables.CHARACTER.CHESTPLATE,
                        Tables.CHARACTER.LEGGINGS,
                        Tables.CHARACTER.BOOTS,
                        Tables.CHARACTER.INVENTORY_CONTENTS,
                        Tables.CHARACTER.HEALTH,
                        Tables.CHARACTER.FOOD_LEVEL,
                        Tables.CHARACTER.SATURATION,
                        Tables.CHARACTER.FOOD_EXHAUSTION,
                        Tables.CHARACTER.WORLD,
                        Tables.CHARACTER.X,
                        Tables.CHARACTER.Y,
                        Tables.CHARACTER.Z,
                        Tables.CHARACTER.PITCH,
                        Tables.CHARACTER.YAW
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
                        character.getLocation().getYaw()
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
                .update(Tables.CHARACTER)
                .set(Tables.CHARACTER.PLAYER_ID, character.getPlayerId().getValue())
                .set(Tables.CHARACTER.FIRST_NAME, character.getFirstName())
                .set(Tables.CHARACTER.FAMILY_NAME, character.getFamilyName())
                .set(Tables.CHARACTER.HEIGHT, character.getHeight())
                .set(Tables.CHARACTER.WEIGHT, character.getWeight())
                .set(Tables.CHARACTER.APPEARANCE, character.getAppearance())
                .set(Tables.CHARACTER.PRESENCE, character.getPresence())
                .set(Tables.CHARACTER.AGE, character.getAge())
                .set(Tables.CHARACTER.EXPERIENCE, character.getExperience())
                .set(Tables.CHARACTER.EXHAUSTION, character.getExhaustion())
                .set(Tables.CHARACTER.RACE, character.getRace() == null ? null : character.getRace().getName())
                .set(Tables.CHARACTER.HELMET, serializedHelmet)
                .set(Tables.CHARACTER.CHESTPLATE, serializedChestplate)
                .set(Tables.CHARACTER.LEGGINGS, serializedLeggings)
                .set(Tables.CHARACTER.BOOTS, serializedBoots)
                .set(Tables.CHARACTER.INVENTORY_CONTENTS, serializedInventory)
                .set(Tables.CHARACTER.HEALTH, character.getHealth())
                .set(Tables.CHARACTER.FOOD_LEVEL, character.getFoodLevel())
                .set(Tables.CHARACTER.SATURATION, (double) character.getSaturation())
                .set(Tables.CHARACTER.FOOD_EXHAUSTION, (double) character.getFoodExhaustion())
                .set(Tables.CHARACTER.WORLD, character.getLocation().getWorld().getName())
                .set(Tables.CHARACTER.X, character.getLocation().getX())
                .set(Tables.CHARACTER.Y, character.getLocation().getY())
                .set(Tables.CHARACTER.Z, character.getLocation().getZ())
                .set(Tables.CHARACTER.PITCH, (double) character.getLocation().getPitch())
                .set(Tables.CHARACTER.YAW, (double) character.getLocation().getYaw())
                .where(Tables.CHARACTER.ID.eq(character.getId().getValue()))
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
                .deleteFrom(Tables.CHARACTER)
                .where(Tables.CHARACTER.ID.eq(character.getId().getValue()))
                .execute();

        cache.remove(character.getId().getValue());
    }

    public PenCharacter get(CharacterId id) {
        if (cache.containsKey(id.getValue())) {
            return cache.get(id.getValue());
        }
        Record result = database.create()
                .select(
                        Tables.CHARACTER.PLAYER_ID,
                        Tables.CHARACTER.FIRST_NAME,
                        Tables.CHARACTER.FAMILY_NAME,
                        Tables.CHARACTER.HEIGHT,
                        Tables.CHARACTER.WEIGHT,
                        Tables.CHARACTER.APPEARANCE,
                        Tables.CHARACTER.PRESENCE,
                        Tables.CHARACTER.AGE,
                        Tables.CHARACTER.EXPERIENCE,
                        Tables.CHARACTER.EXHAUSTION,
                        Tables.CHARACTER.RACE,
                        Tables.CHARACTER.HELMET,
                        Tables.CHARACTER.CHESTPLATE,
                        Tables.CHARACTER.LEGGINGS,
                        Tables.CHARACTER.BOOTS,
                        Tables.CHARACTER.INVENTORY_CONTENTS,
                        Tables.CHARACTER.HEALTH,
                        Tables.CHARACTER.FOOD_LEVEL,
                        Tables.CHARACTER.SATURATION,
                        Tables.CHARACTER.FOOD_EXHAUSTION,
                        Tables.CHARACTER.WORLD,
                        Tables.CHARACTER.X,
                        Tables.CHARACTER.Y,
                        Tables.CHARACTER.Z,
                        Tables.CHARACTER.YAW,
                        Tables.CHARACTER.PITCH
                )
                .from(Tables.CHARACTER)
                .where(Tables.CHARACTER.ID.eq(id.getValue()))
                .fetchOne();
        if (result == null) return null;
        List<CharacterClass> classes = database.getTable(CharacterClassTable.class).get(id);
        PenClass firstClass = classes.size() > 0 ? classes.get(0).getClazz() : null;
        ItemStack helmet = null;
        if (result.get(Tables.CHARACTER.HELMET) != null) {
            try {
                helmet = ItemStackUtils.deserializeItemStack(result.get(Tables.CHARACTER.HELMET));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize helmet", exception);
            }
        }
        ItemStack chestplate = null;
        if (result.get(Tables.CHARACTER.CHESTPLATE) != null) {
            try {
                chestplate = ItemStackUtils.deserializeItemStack(result.get(Tables.CHARACTER.CHESTPLATE));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize chestplate", exception);
            }
        }
        ItemStack leggings = null;
        if (result.get(Tables.CHARACTER.LEGGINGS) != null) {
            try {
                leggings = ItemStackUtils.deserializeItemStack(result.get(Tables.CHARACTER.LEGGINGS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize leggings", exception);
            }
        }
        ItemStack boots = null;
        if (result.get(Tables.CHARACTER.BOOTS) != null) {
            try {
                boots = ItemStackUtils.deserializeItemStack(result.get(Tables.CHARACTER.BOOTS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize boots", exception);
            }
        }
        ItemStack[] inventoryContents = new ItemStack[36];
        if (result.get(Tables.CHARACTER.INVENTORY_CONTENTS) != null) {
            try {
                ItemStackUtils.deserializeItemStackArray(result.get(Tables.CHARACTER.INVENTORY_CONTENTS));
            } catch (IOException | ClassNotFoundException exception) {
                plugin.getLogger().log(SEVERE, "Failed to deserialize inventory contents", exception);
            }
        }
        PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
        String raceName = result.get(Tables.CHARACTER.RACE);
        Race race = raceName == null ? null : raceService.getRace(raceName);
        PenCharacter character = new PenCharacter(
                plugin,
                id,
                new PlayerId(result.get(Tables.CHARACTER.PLAYER_ID)),
                result.get(Tables.CHARACTER.FIRST_NAME),
                result.get(Tables.CHARACTER.FAMILY_NAME),
                result.get(Tables.CHARACTER.HEIGHT),
                result.get(Tables.CHARACTER.WEIGHT),
                result.get(Tables.CHARACTER.APPEARANCE),
                result.get(Tables.CHARACTER.PRESENCE),
                result.get(Tables.CHARACTER.AGE),
                result.get(Tables.CHARACTER.EXPERIENCE),
                result.get(Tables.CHARACTER.EXHAUSTION),
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
                result.get(Tables.CHARACTER.HEALTH),
                result.get(Tables.CHARACTER.FOOD_LEVEL),
                (float) (double) result.get(Tables.CHARACTER.SATURATION),
                (float) (double) result.get(Tables.CHARACTER.FOOD_EXHAUSTION),
                new Location(
                        plugin.getServer().getWorld(result.get(Tables.CHARACTER.WORLD)),
                        result.get(Tables.CHARACTER.X),
                        result.get(Tables.CHARACTER.Y),
                        result.get(Tables.CHARACTER.Z),
                        (float) (double) result.get(Tables.CHARACTER.PITCH),
                        (float) (double) result.get(Tables.CHARACTER.YAW)
                )
        );
        cache.put(id.getValue(), character);
        return character;
    }

    public List<PenCharacter> get(PlayerId playerId) {
        List<? extends Record> results = database.create()
                .select(Tables.CHARACTER.ID)
                .from(Tables.CHARACTER)
                .where(Tables.CHARACTER.PLAYER_ID.eq(playerId.getValue()))
                .fetch();
        return results.stream()
                .map(result -> get(new CharacterId(result.get(Tables.CHARACTER.ID))))
                .collect(Collectors.toList());
    }

}
