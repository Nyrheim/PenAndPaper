package net.nyrheim.penandpaper.item.weapon.property;

import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.distance.Distance;

public interface WeaponProperty {

    final class Ammunition implements WeaponProperty {
        @Override
        public String toString() {
            return "Ammunition";
        }
    }

    final class Finesse implements WeaponProperty {
        @Override
        public String toString() {
            return "Finesse";
        }
    }

    final class Heavy implements WeaponProperty {
        @Override
        public String toString() {
            return "Heavy";
        }
    }

    final class Light implements WeaponProperty {
        @Override
        public String toString() {
            return "Light";
        }
    }

    final class Loading implements WeaponProperty {
        @Override
        public String toString() {
            return "Loading";
        }
    }

    final class Range implements WeaponProperty {

        private final Distance normalRange;
        private final Distance longRange;

        public Range(Distance normalRange, Distance longRange) {
            this.normalRange = normalRange;
            this.longRange = longRange;
        }

        public Distance getNormalRange() {
            return normalRange;
        }

        public Distance getLongRange() {
            return longRange;
        }

        @Override
        public String toString() {
            return "Range (" +
                    "normal " + getNormalRange().toString() +
                    ", long " + getLongRange().toString() +
                    ')';
        }
    }

    final class Reach implements WeaponProperty {
        @Override
        public String toString() {
            return "Reach";
        }
    }

    final class Special implements WeaponProperty {

        private final String description;

        public Special(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Special (" + getDescription() + ')';
        }
    }

    final class Thrown implements WeaponProperty {
        @Override
        public String toString() {
            return "Thrown";
        }
    }

    final class TwoHanded implements WeaponProperty {
        @Override
        public String toString() {
            return "Two-handed";
        }
    }

    final class Versatile implements WeaponProperty {

        private final Roll twoHandedRoll;

        public Versatile(Roll twoHandedRoll) {
            this.twoHandedRoll = twoHandedRoll;
        }

        public Roll getTwoHandedRoll() {
            return twoHandedRoll;
        }

        @Override
        public String toString() {
            return "Versatile (" +
                    "two-handed roll " + getTwoHandedRoll().toString() +
                    ')';
        }
    }
}
