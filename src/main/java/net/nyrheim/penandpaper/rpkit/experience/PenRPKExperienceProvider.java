package net.nyrheim.penandpaper.rpkit.experience;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.experience.bukkit.experience.RPKExperienceProvider;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import org.jetbrains.annotations.NotNull;

public final class PenRPKExperienceProvider implements RPKExperienceProvider {

    private final PenAndPaper plugin;

    public PenRPKExperienceProvider(PenAndPaper plugin) {
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
        return ExperienceLookupTable.getExperienceRequiredForLevel(level);
    }

    @Override
    public int getLevel(@NotNull RPKCharacter character) {
        return ExperienceLookupTable.getLevelAtExperience(getExperience(character));
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
