package com.github.liamvii.penandpaper.rpkit.clazz;

import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.clazz.DnDClass;
import com.github.liamvii.penandpaper.rpkit.character.PnPCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.classes.bukkit.classes.RPKClass;
import com.rpkit.skills.bukkit.skills.RPKSkillType;
import org.jetbrains.annotations.NotNull;

import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.MAX_EXPERIENCE;
import static com.github.liamvii.penandpaper.experience.ExperienceLookupTable.getLevelAtExperience;

public final class PnPClassWrapper implements RPKClass {

    private final DnDClass pnpClass;

    public PnPClassWrapper(DnDClass pnpClass) {
        this.pnpClass = pnpClass;
    }

    public DnDClass getPnPClass() {
        return pnpClass;
    }

    @Override
    public int getMaxLevel() {
        return getLevelAtExperience(MAX_EXPERIENCE);
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
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper wrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = wrapper.getPnPCharacter();
            return pnpCharacter.classes().stream().allMatch(requirementsClass ->
                    requirementsClass.getClazz().getMulticlassingRequirement().meets(pnpCharacter))
                    && pnpClass.getMulticlassingRequirement().meets(pnpCharacter);
        }
        return false;
    }
}
