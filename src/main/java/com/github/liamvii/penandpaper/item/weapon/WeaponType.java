package com.github.liamvii.penandpaper.item.weapon;

import com.github.liamvii.penandpaper.dice.Roll;
import com.github.liamvii.penandpaper.dice.Roll.Die;
import com.github.liamvii.penandpaper.dice.Roll.Modifier;
import com.github.liamvii.penandpaper.distance.Distance;
import com.github.liamvii.penandpaper.item.weapon.property.WeaponProperty;
import com.github.liamvii.penandpaper.money.Money;
import com.github.liamvii.penandpaper.weight.Weight;

import java.util.Arrays;
import java.util.List;

import static com.github.liamvii.penandpaper.damage.DamageType.*;
import static com.github.liamvii.penandpaper.distance.DistanceUnit.FEET;
import static com.github.liamvii.penandpaper.item.weapon.WeaponCategory.*;
import static com.github.liamvii.penandpaper.money.Currency.*;
import static com.github.liamvii.penandpaper.weight.WeightUnit.LB;

public enum WeaponType {

    CLUB(
            "Club",
            SIMPLE_MELEE,
            new Money(1, SP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    BLUDGEONING
            ),
            new Weight(2, LB),
            new WeaponProperty.Light()
    ),
    DAGGER(
            "Dagger",
            SIMPLE_MELEE,
            new Money(2, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    PIERCING
            ),
            new Weight(1, LB),
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            )
    ),
    GREATCLUB(
            "Greatclub",
            SIMPLE_MELEE,
            new Money(2, SP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    BLUDGEONING
            ),
            new Weight(10, LB),
            new WeaponProperty.TwoHanded()
    ),
    HANDAXE(
            "Handaxe",
            SIMPLE_MELEE,
            new Money(5, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    SLASHING
            ),
            new Weight(2, LB),
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            )
    ),
    JAVELIN(
            "Javelin",
            SIMPLE_MELEE,
            new Money(5, SP),
            new WeaponDamage(
                new Roll(new Die(1, 6)),
                PIERCING
            ),
            new Weight(2, LB),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(30, FEET),
                    new Distance(120, FEET)
            )
    ),
    LIGHT_HAMMER(
            "Light hammer",
            SIMPLE_MELEE,
            new Money(2, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    BLUDGEONING
            ),
            new Weight(2, LB),
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            )
    ),
    MACE(
            "Mace",
            SIMPLE_MELEE,
            new Money(5, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    BLUDGEONING
            ),
            new Weight(4, LB)
    ),
    QUARTERSTAFF(
            "Quarterstaff",
            SIMPLE_MELEE,
            new Money(2, SP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    BLUDGEONING
            ),
            new Weight(4, LB),
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    SICKLE(
            "Sickle",
            SIMPLE_MELEE,
            new Money(1, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    SLASHING
            ),
            new Weight(2, LB),
            new WeaponProperty.Light()
    ),
    SPEAR(
            "Spear",
            SIMPLE_MELEE,
            new Money(1, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    PIERCING
            ),
            new Weight(3, LB),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            ),
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    LIGHT_CROSSBOW(
            "Crossbow, light",
            SIMPLE_RANGED,
            new Money(25, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    PIERCING
            ),
            new Weight(5, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(80, FEET),
                    new Distance(320, FEET)
            ),
            new WeaponProperty.Loading(),
            new WeaponProperty.TwoHanded()
    ),
    DART(
            "Dart",
            SIMPLE_RANGED,
            new Money(5, CP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    PIERCING
            ),
            new Weight(0.25, LB),
            new WeaponProperty.Finesse(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            )
    ),
    SHORTBOW(
            "Shortbow",
            SIMPLE_RANGED,
            new Money(25, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    PIERCING
            ),
            new Weight(2, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(80, FEET),
                    new Distance(320, FEET)
            ),
            new WeaponProperty.TwoHanded()
    ),
    SLING(
            "Sling",
            SIMPLE_RANGED,
            new Money(1, SP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    BLUDGEONING
            ),
            new Weight(0, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(30, FEET),
                    new Distance(120, FEET)
            )
    ),
    BATTLEAXE(
            "Battleaxe",
            MARTIAL_MELEE,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    SLASHING
            ),
            new Weight(4, LB),
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    FLAIL(
            "Flail",
            MARTIAL_MELEE,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    BLUDGEONING
            ),
            new Weight(2, LB)
    ),
    GLAIVE(
            "Glaive",
            MARTIAL_MELEE,
            new Money(20, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    SLASHING
            ),
            new Weight(6, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    GREATAXE(
            "Greataxe",
            MARTIAL_MELEE,
            new Money(30, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 12)),
                    SLASHING
            ),
            new Weight(7, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    GREATSWORD(
            "Greatsword",
            MARTIAL_MELEE,
            new Money(50, GP),
            new WeaponDamage(
                    new Roll(new Die(2, 6)),
                    SLASHING
            ),
            new Weight(6, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    HALBERD(
            "Halberd",
            MARTIAL_MELEE,
            new Money(20, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    SLASHING
            ),
            new Weight(6, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    LANCE(
            "Lance",
            MARTIAL_MELEE,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 12)),
                    PIERCING
            ),
            new Weight(6, LB),
            new WeaponProperty.Reach(),
            new WeaponProperty.Special("You have a disadvantage when you use a lance to attack a target within 5 feet" +
                    " of you. Also, a lance requires two hands to wield when you aren't mounted.")
    ),
    LONGSWORD(
            "Longsword",
            MARTIAL_MELEE,
            new Money(15, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    SLASHING
            ),
            new Weight(3, LB),
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    MAUL(
            "Maul",
            MARTIAL_MELEE,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Die(2, 6)),
                    BLUDGEONING
            ),
            new Weight(10, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    MORNINGSTAR(
            "Morningstar",
            MARTIAL_MELEE,
            new Money(15, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    PIERCING
            ),
            new Weight(4, LB)
    ),
    PIKE(
            "Pike",
            MARTIAL_MELEE,
            new Money(5, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    PIERCING
            ),
            new Weight(18, LB),
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    RAPIER(
            "Rapier",
            MARTIAL_MELEE,
            new Money(25, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    PIERCING
            ),
            new Weight(2, LB),
            new WeaponProperty.Finesse()
    ),
    SCIMITAR(
            "Scimitar",
            MARTIAL_MELEE,
            new Money(25, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    SLASHING
            ),
            new Weight(3, LB),
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light()
    ),
    SHORTSWORD(
            "Shortsword",
            MARTIAL_MELEE,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    PIERCING
            ),
            new Weight(2, LB),
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light()
    ),
    TRIDENT(
            "Trident",
            MARTIAL_MELEE,
            new Money(5, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    PIERCING
            ),
            new Weight(4, LB),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, FEET),
                    new Distance(60, FEET)
            ),
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    WAR_PICK(
            "War pick",
            MARTIAL_MELEE,
            new Money(5, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    PIERCING
            ),
            new Weight(2, LB)
    ),
    WARHAMMER(
            "Warhammer",
            MARTIAL_MELEE,
            new Money(15, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    BLUDGEONING
            ),
            new Weight(2, LB),
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    WHIP(
            "Whip",
            MARTIAL_MELEE,
            new Money(2, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    SLASHING
            ),
            new Weight(3, LB),
            new WeaponProperty.Finesse(),
            new WeaponProperty.Reach()
    ),
    BLOWGUN(
            "Blowgun",
            MARTIAL_RANGED,
            new Money(10, GP),
            new WeaponDamage(
                    new Roll(new Modifier(1)),
                    PIERCING
            ),
            new Weight(1, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(25, FEET),
                    new Distance(100, FEET)
            ),
            new WeaponProperty.Loading()
    ),
    HAND_CROSSBOW(
            "Crossbow, hand",
            MARTIAL_RANGED,
            new Money(75, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    PIERCING
            ),
            new Weight(3, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(30, FEET),
                    new Distance(120, FEET)
            ),
            new WeaponProperty.Light(),
            new WeaponProperty.Loading()
    ),
    HEAVY_CROSSBOW(
            "Crossbow, heavy",
            MARTIAL_RANGED,
            new Money(50, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    PIERCING
            ),
            new Weight(18, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(100, FEET),
                    new Distance(400, FEET)
            ),
            new WeaponProperty.Heavy(),
            new WeaponProperty.Loading(),
            new WeaponProperty.TwoHanded()
    ),
    LONGBOW(
            "Longbow",
            MARTIAL_RANGED,
            new Money(50, GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    PIERCING
            ),
            new Weight(2, LB),
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(150, FEET),
                    new Distance(600, FEET)
            ),
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    NET(
            "Net",
            MARTIAL_RANGED,
            new Money(1, GP),
            null,
            new Weight(3, LB),
            new WeaponProperty.Special("A Large or smaller creature hit by a net is restrained until it is " +
                    "freed. A net has no effect on creatures that are formless, or creatures that are Huge or larger." +
                    " A creature can use its action to make a DC 10 Strength check, freeing itself or another " +
                    "creature within its reach on a success. Dealing 5 slashing damage to the net (AC 10) also frees " +
                    "the creature without harming it, ending the effect and destroying the net. When you use an " +
                    "action, bonus action, or reaction to attack with a net, you can make only one attack regardless " +
                    "of the number of attacks you can normally make.")
    );

    private final String name;
    private final WeaponCategory category;
    private final Money cost;
    private final WeaponDamage damage;
    private final Weight weight;
    private final List<WeaponProperty> properties;

    WeaponType(
            String name,
            WeaponCategory category,
            Money cost,
            WeaponDamage damage,
            Weight weight,
            WeaponProperty... properties
    ) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.damage = damage;
        this.weight = weight;
        this.properties = Arrays.asList(properties);
    }

    public String getName() {
        return name;
    }

    public WeaponCategory getCategory() {
        return category;
    }

    public Money getCost() {
        return cost;
    }

    public WeaponDamage getDamage() {
        return damage;
    }

    public Weight getWeight() {
        return weight;
    }

    public List<WeaponProperty> getProperties() {
        return properties;
    }
}
