package net.nyrheim.penandpaper.rpkit.clazz;

import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.classes.bukkit.classes.RPKClass;
import com.rpkit.skills.bukkit.skills.RPKSkillType;
import com.rpkit.stats.bukkit.stat.RPKStatVariable;
import net.nyrheim.penandpaper.experience.ExperienceLookupTable;
import org.jetbrains.annotations.NotNull;

public final class PenRPKClassWrapper implements RPKClass {

    private final PenClass pnpClass;

    public PenRPKClassWrapper(PenClass pnpClass) {
        this.pnpClass = pnpClass;
    }

    public PenClass getPnPClass() {
        return pnpClass;
    }

    @Override
    public int getMaxLevel() {
        return ExperienceLookupTable.getLevelAtExperience(ExperienceLookupTable.MAX_EXPERIENCE);
    }

    @NotNull
    @Override
    public String getName() {
        return pnpClass.getName();
    }

    @Override
    public int getSkillPoints(@NotNull RPKSkillType skillType, int points) {
        return 0;
    }

    @Override
    public boolean hasPrerequisites(@NotNull RPKCharacter character) {
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper wrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = wrapper.getPnPCharacter();
            return pnpCharacter.classes().stream().allMatch(requirementsClass ->
                    requirementsClass.getClazz().getMulticlassingRequirement().meets(pnpCharacter))
                    && pnpClass.getMulticlassingRequirement().meets(pnpCharacter);
        }
        return false;
    }

    @Override
    public int getStatVariableValue(@NotNull RPKStatVariable statVariable, int level) {
        return 0;
    }

}
