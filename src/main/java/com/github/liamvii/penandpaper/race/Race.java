package com.github.liamvii.penandpaper.race;

import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.distance.Distance;
import com.github.liamvii.penandpaper.item.armor.ArmorType;
import com.github.liamvii.penandpaper.item.weapon.WeaponType;
import com.github.liamvii.penandpaper.skill.Skill;
import com.github.liamvii.penandpaper.weight.Weight;
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
