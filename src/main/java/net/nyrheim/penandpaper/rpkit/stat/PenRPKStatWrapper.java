package net.nyrheim.penandpaper.rpkit.stat;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.rpkit.character.PenRPKCharacterWrapper;
import com.rpkit.characters.bukkit.character.RPKCharacter;
import com.rpkit.stats.bukkit.stat.RPKStat;
import com.rpkit.stats.bukkit.stat.RPKStatVariable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PenRPKStatWrapper implements RPKStat {

    private final Ability ability;

    public PenRPKStatWrapper(Ability ability) {
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
        if (character instanceof PenRPKCharacterWrapper) {
            PenRPKCharacterWrapper characterWrapper = (PenRPKCharacterWrapper) character;
            PenCharacter pnpCharacter = characterWrapper.getPnPCharacter();
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
