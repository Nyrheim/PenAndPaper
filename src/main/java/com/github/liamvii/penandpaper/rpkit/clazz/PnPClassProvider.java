package com.github.liamvii.penandpaper.rpkit.clazz;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.database.table.CharacterClassTable;
import com.github.liamvii.penandpaper.rpkit.character.PnPCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.classes.bukkit.classes.RPKClass;
import com.rpkit.classes.bukkit.classes.RPKClassProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PnPClassProvider implements RPKClassProvider {

    private final Pen plugin;

    public PnPClassProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @NotNull
    @Override
    public List<RPKClass> getClasses() {
        return Arrays.stream(DnDClass.values())
                .map(PnPClassWrapper::new)
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public RPKClass getClass(@NotNull RPKCharacter character) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = wrapper.getPnPCharacter();
            return new PnPClassWrapper(pnpCharacter.getFirstClass());
        }
        return null;
    }

    @Nullable
    @Override
    public RPKClass getClass(@NotNull String name) {
        DnDClass dndClass = DnDClass.getByName(name);
        if (dndClass == null) return null;
        return new PnPClassWrapper(dndClass);
    }

    @Override
    public int getExperience(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        return 0;
    }

    @Override
    public int getLevel(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper characterWrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PnPClassWrapper) {
                PnPClassWrapper classWrapper = (PnPClassWrapper) clazz;
                return pnpCharacter.clazz(classWrapper.getPnPClass()).getLevel();
            }
        }
        return 0;
    }

    @Override
    public void setClass(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper characterWrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PnPClassWrapper) {
                PnPClassWrapper classWrapper = (PnPClassWrapper) clazz;
                DnDClass pnpClass = classWrapper.getPnPClass();
                pnpCharacter.addClass(pnpClass);
                plugin.getDatabase().getTable(CharacterClassTable.class)
                        .insertOrUpdateClasses(pnpCharacter);
            }
        }
    }

    @Override
    public void setExperience(@NotNull RPKCharacter character, @NotNull RPKClass clazz, int experience) {

    }

    @Override
    public void setLevel(@NotNull RPKCharacter character, @NotNull RPKClass clazz, int level) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper characterWrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PnPClassWrapper) {
                PnPClassWrapper classWrapper = (PnPClassWrapper) clazz;
                pnpCharacter.clazz(classWrapper.getPnPClass()).setLevel(level);
                plugin.getDatabase().getTable(CharacterClassTable.class)
                        .insertOrUpdateClasses(pnpCharacter);
            }
        }
    }
}
