package net.nyrheim.penandpaper.database.table;

import net.nyrheim.penandpaper.character.CharacterId;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.clazz.CharacterClass;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.database.Database;
import net.nyrheim.penandpaper.database.jooq.Tables;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.constraint;

public final class CharacterClassTable implements Table {

    private final Database database;

    private final Cache<Integer, List> cache;

    public CharacterClassTable(Database database) {
        this.database = database;
        this.cache = database.getCacheManager().createCache("penandpaper.character_class.character_id",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, List.class,
                        ResourcePoolsBuilder.heap(25)));
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(Tables.CHARACTER_CLASS)
                .column(Tables.CHARACTER_CLASS.CHARACTER_ID)
                .column(Tables.CHARACTER_CLASS.CLASS_NAME)
                .column(Tables.CHARACTER_CLASS.LEVEL)
                .constraints(
                        constraint("character_class_pk").primaryKey(
                                Tables.CHARACTER_CLASS.CHARACTER_ID,
                                Tables.CHARACTER_CLASS.CLASS_NAME
                        ),
                        constraint("character_class_character_id_fk")
                                .foreignKey(Tables.CHARACTER_CLASS.CHARACTER_ID)
                                .references(Tables.CHARACTER, Tables.CHARACTER.ID)
                                .onDeleteCascade()
                                .onUpdateCascade()
                )
                .execute();
    }

    public List<CharacterClass> get(CharacterId characterId) {
        if (cache.containsKey(characterId.getValue())) {
            return cache.get(characterId.getValue());
        }
        List<CharacterClass> classes = database.create()
                .select(
                        Tables.CHARACTER_CLASS.CLASS_NAME,
                        Tables.CHARACTER_CLASS.LEVEL
                )
                .from(Tables.CHARACTER_CLASS)
                .where(Tables.CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .fetch()
                .stream()
                .map(result -> new CharacterClass(
                        PenClass.getByName(result.get(Tables.CHARACTER_CLASS.CLASS_NAME)),
                        result.get(Tables.CHARACTER_CLASS.LEVEL)
                ))
                .collect(Collectors.toList());
        cache.put(characterId.getValue(), classes);
        return classes;
    }

    public CharacterClass get(CharacterId characterId, PenClass clazz) {
        return get(characterId).stream()
                .filter(characterClass -> characterClass.getClazz() == clazz)
                .findFirst()
                .orElse(null);
    }

    public void insert(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .insertInto(
                        Tables.CHARACTER_CLASS,
                        Tables.CHARACTER_CLASS.CHARACTER_ID,
                        Tables.CHARACTER_CLASS.CLASS_NAME,
                        Tables.CHARACTER_CLASS.LEVEL
                )
                .values(
                        characterId.getValue(),
                        characterClass.getClazz().getName(),
                        characterClass.getLevel()
                )
                .execute();

        List<CharacterClass> classes = cache.get(characterId.getValue());
        if (classes == null) classes = new ArrayList<>();
        classes.add(characterClass);
        cache.put(characterId.getValue(), classes);
    }

    public void update(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .update(Tables.CHARACTER_CLASS)
                .set(Tables.CHARACTER_CLASS.LEVEL, characterClass.getLevel())
                .where(Tables.CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .and(Tables.CHARACTER_CLASS.CLASS_NAME.eq(characterClass.getClass().getName()))
                .execute();
        List<CharacterClass> classes = cache.get(characterId.getValue());
        if (classes == null) classes = new ArrayList<>();
        if (classes.stream().noneMatch(existingClass -> existingClass.getClazz() == characterClass.getClazz())) {
            classes.add(characterClass);
        } else {
            classes.stream()
                    .filter(existingClass -> existingClass.getClazz() == characterClass.getClazz())
                    .forEach(existingClass -> existingClass.setLevel(characterClass.getLevel()));
        }
        cache.put(characterId.getValue(), classes);
    }

    public void delete(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .deleteFrom(Tables.CHARACTER_CLASS)
                .where(Tables.CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .and(Tables.CHARACTER_CLASS.CLASS_NAME.eq(characterClass.getClass().getName()))
                .execute();
        List<CharacterClass> classes = cache.get(characterId.getValue());
        if (classes == null) classes = new ArrayList<>();
        classes = classes.stream()
                .filter(existingClass -> existingClass.getClazz() != characterClass.getClazz())
                .collect(Collectors.toList());
        cache.put(characterId.getValue(), classes);
    }

    public void delete(CharacterId characterId) {
        database.create()
                .deleteFrom(Tables.CHARACTER_CLASS)
                .where(Tables.CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
        cache.remove(characterId.getValue());
    }

    public void insertOrUpdateClasses(PenCharacter character) {
        character.classes().forEach(characterClass -> {
            if (get(character.getId(), characterClass.getClazz()) == null) {
                insert(character.getId(), characterClass);
            } else {
                update(character.getId(), characterClass);
            }
        });
    }
}