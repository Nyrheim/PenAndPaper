package net.nyrheim.penandpaper.rpkit.clazz;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.classes.bukkit.classes.RPKClass;
import com.rpkit.classes.bukkit.classes.RPKClassProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PenRPKClassProvider implements RPKClassProvider {

    private final PenAndPaper plugin;

    public PenRPKClassProvider(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @NotNull
    @Override
    public List<RPKClass> getClasses() {
        return Arrays.stream(PenClass.values())
                .map(PenRPKClassWrapper::new)
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public RPKClass getClass(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = wrapper.getPnPCharacter();
            return new PenRPKClassWrapper(pnpCharacter.getFirstClass());
        }
        return null;
    }

    @Nullable
    @Override
    public RPKClass getClass(@NotNull String name) {
        PenClass dndClass = PenClass.getByName(name);
        if (dndClass == null) return null;
        return new PenRPKClassWrapper(dndClass);
    }

    @Override
    public int getExperience(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        return 0;
    }

    @Override
    public int getLevel(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper characterWrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PenRPKClassWrapper) {
                PenRPKClassWrapper classWrapper = (PenRPKClassWrapper) clazz;
                return pnpCharacter.clazz(classWrapper.getPnPClass()).getLevel();
            }
        }
        return 0;
    }

    @Override
    public void setClass(@NotNull RPKCharacter character, @NotNull RPKClass clazz) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper characterWrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PenRPKClassWrapper) {
                PenRPKClassWrapper classWrapper = (PenRPKClassWrapper) clazz;
                PenClass pnpClass = classWrapper.getPnPClass();
                pnpCharacter.addClass(pnpClass);
                PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
                characterService.updateClasses(pnpCharacter);
            }
        }
    }

    @Override
    public void setExperience(@NotNull RPKCharacter character, @NotNull RPKClass clazz, int experience) {

    }

    @Override
    public void setLevel(@NotNull RPKCharacter character, @NotNull RPKClass clazz, int level) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper characterWrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            if (clazz instanceof PenRPKClassWrapper) {
                PenRPKClassWrapper classWrapper = (PenRPKClassWrapper) clazz;
                pnpCharacter.clazz(classWrapper.getPnPClass()).setLevel(level);
                PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
                characterService.updateClasses(pnpCharacter);
            }
        }
    }
}
