package net.nyrheim.penandpaper.race;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.distance.Distance;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import net.nyrheim.penandpaper.item.weapon.WeaponType;
import net.nyrheim.penandpaper.skill.Skill;
import net.nyrheim.penandpaper.weight.Weight;

import java.util.*;

public final class Subrace extends Race {

    private final String name;
    private final List<String> aliases = new ArrayList<>();
    private final Race baseRace;
    private final Map<Ability, Integer> abilityScoreModifiers = new EnumMap<>(Ability.class);
    private final int minimumAge;
    private final int maximumAge;
    private final Distance speed;
    private final List<WeaponType> weaponProficiencies = new ArrayList<>();
    private final List<ArmorType> armorProficiencies = new ArrayList<>();
    private final Distance minimumHeight;
    private final Distance maximumHeight;
    private final Weight minimumWeight;
    private final Weight maximumWeight;
    private final Distance darkVision;
    private final List<Skill> skillProficiencies = new ArrayList<>();
    private final List<RaceTrait> traits = new ArrayList<>();
    private final HPBonusFunction hpBonusFunction;

    private Subrace(Race baseRace,
                    String name,
                    List<String> aliases,
                    Map<Ability, Integer> abilityScoreModifiers,
                    int minimumAge,
                    int maximumAge,
                    Distance speed,
                    Collection<WeaponType> weaponProficiencies,
                    Collection<ArmorType> armorProficiencies,
                    Distance minimumHeight,
                    Distance maximumHeight,
                    Weight minimumWeight,
                    Weight maximumWeight,
                    Distance darkVision,
                    Collection<Skill> skillProficiencies,
                    Collection<RaceTrait> traits,
                    HPBonusFunction hpBonusFunction) {
        this.baseRace = baseRace;
        this.name = name;
        this.aliases.addAll(aliases);
        this.abilityScoreModifiers.putAll(abilityScoreModifiers);
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
        this.speed = speed;
        this.weaponProficiencies.addAll(weaponProficiencies);
        this.armorProficiencies.addAll(armorProficiencies);
        this.minimumHeight = minimumHeight;
        this.maximumHeight = maximumHeight;
        this.minimumWeight = minimumWeight;
        this.maximumWeight = maximumWeight;
        this.darkVision = darkVision;
        this.skillProficiencies.addAll(skillProficiencies);
        this.traits.addAll(traits);
        this.hpBonusFunction = hpBonusFunction;
    }

    static class Builder {
        private final Race baseRace;
        private final String name;
        private final List<String> aliases = new ArrayList<>();
        private final Map<Ability, Integer> abilityScoreModifiers = new EnumMap<>(Ability.class);
        private int minimumAge = -1;
        private int maximumAge = -1;
        private Distance speed = null;
        private final List<WeaponType> weaponProficiencies = new ArrayList<>();
        private final List<ArmorType> armorProficiencies = new ArrayList<>();
        private Distance minimumHeight = null;
        private Distance maximumHeight = null;
        private Weight minimumWeight = null;
        private Weight maximumWeight = null;
        private Distance darkVision = null;
        private final List<Skill> skillProficiencies = new ArrayList<>();
        private final List<RaceTrait> traits = new ArrayList<>();
        private HPBonusFunction hpBonusFunction;

        public Builder(Race baseRace, String name) {
            this.baseRace = baseRace;
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

        public Builder hpBonus(HPBonusFunction hpBonusFunction) {
            this.hpBonusFunction = hpBonusFunction;
            return this;
        }

        public Subrace build() {
            return new Subrace(
                    baseRace,
                    name,
                    aliases,
                    abilityScoreModifiers,
                    minimumAge,
                    maximumAge,
                    speed,
                    weaponProficiencies,
                    armorProficiencies,
                    minimumHeight,
                    maximumHeight,
                    minimumWeight,
                    maximumWeight,
                    darkVision,
                    skillProficiencies,
                    traits,
                    hpBonusFunction
            );
        }
    }

    public Race getBaseRace() {
        return baseRace;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public int getAbilityScoreModifier(Ability ability) {
        return abilityScoreModifiers.getOrDefault(ability, getBaseRace().getAbilityScoreModifier(ability));
    }

    @Override
    public int getMinimumAge() {
        if (minimumAge > 0) return minimumAge;
        return getBaseRace().getMinimumAge();
    }

    @Override
    public int getMaximumAge() {
        if (maximumAge > 0) return maximumAge;
        return getBaseRace().getMaximumAge();
    }

    @Override
    public Distance getSpeed() {
        if (speed != null) return speed;
        return getBaseRace().getSpeed();
    }

    @Override
    public List<WeaponType> getWeaponProficiencies() {
        List<WeaponType> weaponProficiencies = new ArrayList<>();
        weaponProficiencies.addAll(getBaseRace().getWeaponProficiencies());
        weaponProficiencies.addAll(this.weaponProficiencies);
        return weaponProficiencies;
    }

    @Override
    public List<ArmorType> getArmorProficiencies() {
        List<ArmorType> armorProficiencies = new ArrayList<>();
        armorProficiencies.addAll(getBaseRace().getArmorProficiencies());
        armorProficiencies.addAll(this.armorProficiencies);
        return armorProficiencies;
    }

    @Override
    public Distance getMinimumHeight() {
        if (minimumHeight != null) return minimumHeight;
        return getBaseRace().getMinimumHeight();
    }

    @Override
    public Distance getMaximumHeight() {
        if (maximumHeight != null) return maximumHeight;
        return getBaseRace().getMaximumHeight();
    }

    @Override
    public Weight getMinimumWeight() {
        if (minimumWeight != null) return minimumWeight;
        return getBaseRace().getMinimumWeight();
    }

    @Override
    public Weight getMaximumWeight() {
        if (maximumWeight != null) return maximumWeight;
        return getBaseRace().getMaximumWeight();
    }

    @Override
    public Distance getDarkVision() {
        if (darkVision != null) return darkVision;
        return getBaseRace().getDarkVision();
    }

    @Override
    public List<Skill> getSkillProficiencies() {
        List<Skill> skillProficiencies = new ArrayList<>();
        skillProficiencies.addAll(getBaseRace().getSkillProficiencies());
        skillProficiencies.addAll(this.skillProficiencies);
        return skillProficiencies;
    }

    @Override
    public List<RaceTrait> getTraits() {
        List<RaceTrait> traits = new ArrayList<>();
        traits.addAll(getBaseRace().getTraits());
        traits.addAll(this.traits);
        return traits;
    }

    @Override
    public int getHPBonus(int level) {
        if (hpBonusFunction != null) return hpBonusFunction.getHPBonus(level);
        return getBaseRace().getHPBonus(level);
    }
}
