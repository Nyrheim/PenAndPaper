package net.nyrheim.penandpaper.armorclass;

import net.nyrheim.penandpaper.ability.Ability;
import net.nyrheim.penandpaper.character.PenCharacter;

import java.util.Arrays;
import java.util.List;

public final class ArmorClassCalculation {

    private List<Component> components;

    public ArmorClassCalculation(ArmorClassCalculation.Component... components) {
        this.components = Arrays.asList(components);
    }

    public interface Component {
        public int calculateValue(PenCharacter character);
    }

    public static final class BaseArmorClassComponent implements Component {

        private final int value;

        public BaseArmorClassComponent(int value) {
                this.value = value;
        }

        @Override
        public int calculateValue(PenCharacter character) {
            return value;
        }

    }

    public static final class AbilityModifierArmorClassComponent implements Component {

        private final Ability ability;

        public AbilityModifierArmorClassComponent(Ability ability) {
            this.ability = ability;
        }

        @Override
        public int calculateValue(PenCharacter character) {
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
        public int calculateValue(PenCharacter character) {
            return Math.min(cap, character.getModifier(ability));
        }

    }

    public ArmorClass calculate(PenCharacter character) {
        return new ArmorClass(components.stream()
                .map(component -> component.calculateValue(character))
                .reduce(0, Integer::sum));
    }

}
