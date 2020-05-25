package com.github.liamvii.penandpaper.rpkit.experience;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.database.table.CharacterTable;
import com.github.liamvii.penandpaper.rpkit.character.PnPCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.experience.bukkit.experience.RPKExperienceProvider;
import org.jetbrains.annotations.NotNull;

import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.getExperienceRequiredForLevel;
import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.getLevelAtExperience;

public final class PnPExperienceProvider implements RPKExperienceProvider {

    private final Pen plugin;

    public PnPExperienceProvider(Pen plugin) {
        this.plugin = plugin;
    }

    @Override
    public int getExperience(@NotNull RPKCharacter character) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = wrapper.getPnPCharacter();
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
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = wrapper.getPnPCharacter();
            pnpCharacter.setExperience(experience);
            CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
            characterTable.update(pnpCharacter);
        }
    }

    @Override
    public void setLevel(@NotNull RPKCharacter character, int level) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = wrapper.getPnPCharacter();
            pnpCharacter.setExperience(getExperienceNeededForLevel(level));
            CharacterTable characterTable = plugin.getDatabase().getTable(CharacterTable.class);
            characterTable.update(pnpCharacter);
        }
    }
}
