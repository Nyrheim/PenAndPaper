package net.nyrheim.penandpaper.race;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.distance.Distance;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import net.nyrheim.penandpaper.item.weapon.WeaponType;
import net.nyrheim.penandpaper.skill.Skill;
import net.nyrheim.penandpaper.weight.Weight;
import com.rpkit.languages.bukkit.language.RPKLanguage;

import java.util.*;

public final class BaseRace extends Race {

    private final String name;
    private final List<String> aliases = new ArrayList<>();
    private final Map<Ability, Integer> abilityScoreModifiers = new EnumMap<>(Ability.class);
    private final int minimumAge;
    private final int maximumAge;
    private final Distance speed;
    private final List<RPKLanguage> languages = new ArrayList<>();
    private final List<WeaponType> weaponProficiencies = new ArrayList<>();
    private final List<ArmorType> armorProficiencies = new ArrayList<>();
    private final Distance minimumHeight;
    private final Distance maximumHeight;
    private final Weight minimumWeight;
    private final Weight maximumWeight;
    private final Distance darkVision;
    private final List<Skill> skillProficiencies = new ArrayList<>();
    private final List<RaceTrait> traits = new ArrayList<>();

    private BaseRace(String name,
                List<String> aliases,
                Map<Ability, Integer> abilityScoreModifiers,
                int minimumAge,
                int maximumAge,
                Distance speed,
                Collection<RPKLanguage> languages,
                Collection<WeaponType> weaponProficiencies,
                Collection<ArmorType> armorProficiencies,
                Distance minimumHeight,
                Distance maximumHeight,
                Weight minimumWeight,
                Weight maximumWeight,
                Distance darkVision,
                Collection<Skill> skillProficiencies,
                Collection<RaceTrait> traits) {
        this.name = name;
        this.aliases.addAll(aliases);
        this.abilityScoreModifiers.putAll(abilityScoreModifiers);
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
        this.speed = speed;
        this.languages.addAll(languages);
        this.weaponProficiencies.addAll(weaponProficiencies);
        this.armorProficiencies.addAll(armorProficiencies);
        this.minimumHeight = minimumHeight;
        this.maximumHeight = maximumHeight;
        this.minimumWeight = minimumWeight;
        this.maximumWeight = maximumWeight;
        this.darkVision = darkVision;
        this.skillProficiencies.addAll(skillProficiencies);
        this.traits.addAll(traits);
    }

    static class Builder {
        private final String name;
        private final List<String> aliases = new ArrayList<>();
        private final Map<Ability, Integer> abilityScoreModifiers = new EnumMap<>(Ability.class);
        private int minimumAge = -1;
        private int maximumAge = -1;
        private Distance speed = null;
        private final List<RPKLanguage> languages = new ArrayList<>();
        private final List<WeaponType> weaponProficiencies = new ArrayList<>();
        private final List<ArmorType> armorProficiencies = new ArrayList<>();
        private Distance minimumHeight = null;
        private Distance maximumHeight = null;
        private Weight minimumWeight = null;
        private Weight maximumWeight = null;
        private Distance darkVision = null;
        private final List<Skill> skillProficiencies = new ArrayList<>();
        private final List<RaceTrait> traits = new ArrayList<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder alias(String alias) {
            aliases.add(alias);
            return this;
        }

        public Builder aliases(String... aliases) {
            this.aliases.addAll(Arrays.asList(aliases));
            return this;
        }

        public Builder abilityScoreModifier(Ability ability, int modifier) {
            abilityScoreModifiers.put(ability, modifier);
            return this;
        }

        public Builder minimumAge(int minimumAge) {
            this.minimumAge = minimumAge;
            return this;
        }

        public Builder maximumAge(int maximumAge) {
            this.maximumAge = maximumAge;
            return this;
        }

        public Builder speed(Distance speed) {
            this.speed = speed;
            return this;
        }

        public Builder language(RPKLanguage language) {
            languages.add(language);
            return this;
        }

        public Builder languages(RPKLanguage... languages) {
            this.languages.addAll(Arrays.asList(languages));
            return this;
        }

        public Builder weaponProficiency(WeaponType weaponType) {
            weaponProficiencies.add(weaponType);
            return this;
        }

        public Builder weaponProficiencies(WeaponType... weaponTypes) {
            weaponProficiencies.addAll(Arrays.asList(weaponTypes));
            return this;
        }

        public Builder armorProficiency(ArmorType armorType) {
            armorProficiencies.add(armorType);
            return this;
        }

        public Builder armorProficiencies(ArmorType... armorTypes) {
            armorProficiencies.addAll(Arrays.asList(armorTypes));
            return this;
        }

        public Builder minimumHeight(Distance minimumHeight) {
            this.minimumHeight = minimumHeight;
            return this;
        }

        public Builder maximumHeight(Distance maximumHeight) {
            this.maximumHeight = maximumHeight;
            return this;
        }

        public Builder minimumWeight(Weight minimumWeight) {
            this.minimumWeight = minimumWeight;
            return this;
        }

        public Builder maximumWeight(Weight maximumWeight) {
            this.maximumWeight = maximumWeight;
            return this;
        }

        public Builder darkVision(Distance darkVision) {
            this.darkVision = darkVision;
            return this;
        }

        public Builder skillProficiency(Skill skill) {
            skillProficiencies.add(skill);
            return this;
        }

        public Builder skillProficiencies(Skill... skills) {
            skillProficiencies.addAll(Arrays.asList(skills));
            return this;
        }

        public Builder trait(RaceTrait trait) {
            traits.add(trait);
            return this;
        }

        public Builder traits(RaceTrait... traits) {
            this.traits.addAll(Arrays.asList(traits));
            return this;
        }

        public BaseRace build() {
            return new BaseRace(
                    name,
                    aliases,
                    abilityScoreModifiers,
                    minimumAge,
                    maximumAge,
                    speed,
                    languages,
                    weaponProficiencies,
                    armorProficiencies,
                    minimumHeight,
                    maximumHeight,
                    minimumWeight,
                    maximumWeight,
                    darkVision,
                    skillProficiencies,
                    traits
            );
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public int getAbilityScoreModifier(Ability ability) {
        return abilityScoreModifiers.getOrDefault(ability, 0);
    }


    @Override
    public int getMinimumAge() {
        return minimumAge;
    }

    @Override
    public int getMaximumAge() {
        return maximumAge;
    }

    @Override
    public Distance getSpeed() {
        return speed;
    }

    @Override
    public List<RPKLanguage> getLanguages() {
        return languages;
    }

    @Override
    public List<WeaponType> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    @Override
    public List<ArmorType> getArmorProficiencies() {
        return armorProficiencies;
    }

    @Override
    public Distance getMinimumHeight() {
        return minimumHeight;
    }

    @Override
    public Distance getMaximumHeight() {
        return maximumHeight;
    }

    @Override
    public Weight getMinimumWeight() {
        return minimumWeight;
    }

    @Override
    public Weight getMaximumWeight() {
        return maximumWeight;
    }

    @Override
    public Distance getDarkVision() {
        return darkVision;
    }

    @Override
    public List<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    @Override
    public List<RaceTrait> getTraits() {
        return traits;
    }

}
