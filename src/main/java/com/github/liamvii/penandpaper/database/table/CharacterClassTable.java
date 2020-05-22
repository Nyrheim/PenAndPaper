package com.github.liamvii.penandpaper.database.table;

import com.github.liamvii.penandpaper.character.CharacterId;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.CharacterClass;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.database.Database;
import org.jooq.Record;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.liamvii.penandpaper.database.jooq.nyrheim.Tables.CHARACTER_CLASS;
import static org.jooq.impl.DSL.constraint;

public final class CharacterClassTable implements Table {

    private final Database database;

    public CharacterClassTable(Database database) {
        this.database = database;
    }

    @Override
    public void create() {
        database.create()
                .createTableIfNotExists(CHARACTER_CLASS)
                .column(CHARACTER_CLASS.CHARACTER_ID)
                .column(CHARACTER_CLASS.CLASS_NAME)
                .column(CHARACTER_CLASS.LEVEL)
                .constraints(
                        constraint("character_class_pk").primaryKey(
                                CHARACTER_CLASS.CHARACTER_ID,
                                CHARACTER_CLASS.CLASS_NAME
                        )
                )
                .execute();
    }

    public List<CharacterClass> get(CharacterId characterId) {
        return database.create()
                .select(
                        CHARACTER_CLASS.CLASS_NAME,
                        CHARACTER_CLASS.LEVEL
                )
                .from(CHARACTER_CLASS)
                .where(CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .fetch()
                .stream()
                .map(result -> new CharacterClass(
                        DnDClass.valueOf(result.get(CHARACTER_CLASS.CLASS_NAME)),
                        result.get(CHARACTER_CLASS.LEVEL)
                ))
                .collect(Collectors.toList());
    }

    public CharacterClass get(CharacterId characterId, DnDClass clazz) {
        Record result = database.create()
                .select(CHARACTER_CLASS.LEVEL)
                .from(CHARACTER_CLASS)
                .where(CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .fetchOne();
        if (result == null) return null;
        return new CharacterClass(clazz, result.get(CHARACTER_CLASS.LEVEL));
    }

    public void insert(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .insertInto(
                        CHARACTER_CLASS,
                        CHARACTER_CLASS.CHARACTER_ID,
                        CHARACTER_CLASS.CLASS_NAME,
                        CHARACTER_CLASS.LEVEL
                )
                .values(
                        characterId.getValue(),
                        characterClass.getClazz().getName(),
                        characterClass.getLevel()
                )
                .execute();
    }

    public void update(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .update(CHARACTER_CLASS)
                .set(CHARACTER_CLASS.LEVEL, characterClass.getLevel())
                .where(CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_CLASS.CLASS_NAME.eq(characterClass.getClass().getName()))
                .execute();
    }

    public void delete(CharacterId characterId, CharacterClass characterClass) {
        database.create()
                .deleteFrom(CHARACTER_CLASS)
                .where(CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .and(CHARACTER_CLASS.CLASS_NAME.eq(characterClass.getClass().getName()))
                .execute();
    }

    public void delete(CharacterId characterId) {
        database.create()
                .deleteFrom(CHARACTER_CLASS)
                .where(CHARACTER_CLASS.CHARACTER_ID.eq(characterId.getValue()))
                .execute();
    }

    public void insertOrUpdateClasses(PlayerCharacter character) {
        character.classes().forEach(characterClass -> {
            if (get(character.getId(), characterClass.getClazz()) == null) {
                insert(character.getId(), characterClass);
            } else {
                update(character.getId(), characterClass);
            }
        });
    }
}
