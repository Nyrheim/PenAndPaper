package com.github.liamvii.penandpaper.armorclass;

import com.github.liamvii.penandpaper.ability.Ability;
import com.github.liamvii.penandpaper.character.PlayerCharacter;

import java.util.Arrays;
import java.util.List;

public final class ArmorClassCalculation {

    private List<Component> components;

    public ArmorClassCalculation(ArmorClassCalculation.Component... components) {
        this.components = Arrays.asList(components);
    }

    public interface Component {
        public int calculateValue(PlayerCharacter character);
    }

    public static final class BaseArmorClassComponent implements Component {

        private final int value;

        public BaseArmorClassComponent(int value) {
                this.value = value;
        }

        @Override
        public int calculateValue(PlayerCharacter character) {
            return value;
        }

    }

    public static final class AbilityModifierArmorClassComponent implements Component {

        private final Ability ability;

        public AbilityModifierArmorClassComponent(Ability ability) {
            this.ability = ability;
        }

        @Override
        public int calculateValue(PlayerCharacter character) {
            return character.getModifier(ability);
        }

    }

    public static final class CappedAbilityModifierArmorClassComponent implements Component {

        private final Ability ability;
        private final int cap;

        public CappedAbilityModifierArmorClassComponent(Ability ability, int cap) {
            this.ability = ability;
            this.cap = cap;
        }

        @Override
        public int calculateValue(PlayerCharacter character) {
            return Math.min(cap, character.getModifier(ability));
        }

    }

    public ArmorClass calculate(PlayerCharacter character) {
        return new ArmorClass(components.stream()
                .map(component -> component.calculateValue(character))
                .reduce(0, Integer::sum));
    }

}
