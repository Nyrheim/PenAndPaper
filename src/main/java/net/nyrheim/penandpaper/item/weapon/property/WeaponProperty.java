package net.nyrheim.penandpaper.item.weapon.property;

import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.distance.Distance;

public interface WeaponProperty {

    final class Ammunition implements WeaponProperty {
    }

    final class Finesse implements WeaponProperty {
    }

    final class Heavy implements WeaponProperty {
    }

    final class Light implements WeaponProperty {
    }

    final class Loading implements WeaponProperty {
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

    }

    final class Reach implements WeaponProperty {
    }

    final class Special implements WeaponProperty {

        private final String description;

        public Special(String description) {
            this.description = description;
        }

    }

    final class Thrown implements WeaponProperty {
    }

    final class TwoHanded implements WeaponProperty {
    }

    final class Versatile implements WeaponProperty {

        private final Roll twoHandedRoll;

        public Versatile(Roll twoHandedRoll) {
            this.twoHandedRoll = twoHandedRoll;
        }

        public Roll getTwoHandedRoll() {
            return twoHandedRoll;
        }
    }
}
