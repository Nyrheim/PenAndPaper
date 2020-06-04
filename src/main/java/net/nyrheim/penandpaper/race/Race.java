package net.nyrheim.penandpaper.race;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.distance.Distance;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import net.nyrheim.penandpaper.item.weapon.WeaponType;
import net.nyrheim.penandpaper.skill.Skill;
import net.nyrheim.penandpaper.weight.Weight;
import com.rpkit.languages.bukkit.language.RPKLanguage;

import java.util.List;

public abstract class Race {

    public abstract String getName();
    public abstract List<String> getAliases();
    public abstract int getAbilityScoreModifier(Ability ability);
    public abstract int getMinimumAge();
    public abstract int getMaximumAge();
    public abstract Distance getSpeed();
    public abstract List<RPKLanguage> getLanguages();
    public abstract List<WeaponType> getWeaponProficiencies();
    public abstract List<ArmorType> getArmorProficiencies();
    public abstract Distance getMinimumHeight();
    public abstract Distance getMaximumHeight();
    public abstract Weight getMinimumWeight();
    public abstract Weight getMaximumWeight();
    public abstract Distance getDarkVision();
    public abstract List<Skill> getSkillProficiencies();
    public abstract List<RaceTrait> getTraits();

}
