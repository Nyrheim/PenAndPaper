package com.github.liamvii.penandpaper.rpkit.experience;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.rpkit.character.PenRPKCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.experience.bukkit.experience.RPKExperienceProvider;
import org.jetbrains.annotations.NotNull;

import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.getExperienceRequiredForLevel;
import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.getLevelAtExperience;

public final class PenRPKExperienceProvider implements RPKExperienceProvider {

    private final Pen plugin;

    public PenRPKExperienceProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public int getExperience(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = wrapper.getPnPCharacter();
            return pnpCharacter.getExperience();
        }
        return 0;
    }

    @Override
    public int getExperienceNeededForLevel(int level) {
        return getExperienceRequiredForLevel(level);
    }

    @Override
    public int getLevel(@NotNull RPKCharacter character) {
        return getLevelAtExperience(getExperience(character));
    }

    @Override
    public void setExperience(@NotNull RPKCharacter character, int experience) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = wrapper.getPnPCharacter();
            pnpCharacter.setExperience(experience);
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.updateCharacter(pnpCharacter);
        }
    }

    @Override
    public void setLevel(@NotNull RPKCharacter character, int level) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = wrapper.getPnPCharacter();
            pnpCharacter.setExperience(getExperienceNeededForLevel(level));
            PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
            characterService.updateCharacter(pnpCharacter);
        }
    }
}
