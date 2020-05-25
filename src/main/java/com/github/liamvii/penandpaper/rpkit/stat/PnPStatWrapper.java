package com.github.liamvii.penandpaper.rpkit.stat;

import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.rpkit.character.PnPCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.stats.bukkit.stat.RPKStat;
import com.rpkit.stats.bukkit.stat.RPKStatVariable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PnPStatWrapper implements RPKStat {

    private final Ability ability;

    public PnPStatWrapper(Ability ability) {
        this.ability = ability;
    }

    @NotNull
    @Override
    public String getName() {
        return ability.getName();
    }

    @NotNull
    @Override
    public String getScript() {
        return "";
    }

    @Override
    public int get(@NotNull RPKCharacter character, @NotNull List<? extends RPKStatVariable> statVariables) {
        if (character instanceof PnPCharacterWrapper) {
            PnPCharacterWrapper characterWrapper = (PnPCharacterWrapper) character;
            PlayerCharacter pnpCharacter = characterWrapper.getPnPCharacter();
            return pnpCharacter.getAbilityScore(ability);
        }
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int i) {

    }
}
