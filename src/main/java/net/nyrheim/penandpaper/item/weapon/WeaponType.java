package net.nyrheim.penandpaper.item.weapon;

import net.nyrheim.penandpaper.damage.DamageType;
import net.nyrheim.penandpaper.dice.Roll;
import net.nyrheim.penandpaper.dice.Roll.Die;
import net.nyrheim.penandpaper.dice.Roll.Modifier;
import net.nyrheim.penandpaper.distance.Distance;
import net.nyrheim.penandpaper.distance.DistanceUnit;
import net.nyrheim.penandpaper.item.ItemStackInitializer;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.weapon.property.WeaponProperty;
import net.nyrheim.penandpaper.money.Currency;
import net.nyrheim.penandpaper.money.Money;
import net.nyrheim.penandpaper.weight.Weight;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.nyrheim.penandpaper.item.weapon.WeaponCategory.*;
import static net.nyrheim.penandpaper.weight.WeightUnit.LB;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.Material.*;

public enum WeaponType implements ItemType {

    CLUB(
            "Club",
            SIMPLE_MELEE,
            new Money(1, Currency.SP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.BLUDGEONING
            ),
            new Weight(2, LB),
            WOODEN_SWORD,
            new WeaponProperty.Light()
    ),
    DAGGER(
            "Dagger",
            SIMPLE_MELEE,
            new Money(2, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.PIERCING
            ),
            new Weight(1, LB),
            IRON_SWORD,
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            )
    ),
    GREATCLUB(
            "Greatclub",
            SIMPLE_MELEE,
            new Money(2, Currency.SP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.BLUDGEONING
            ),
            new Weight(10, LB),
            WOODEN_SWORD,
            new WeaponProperty.TwoHanded()
    ),
    HANDAXE(
            "Handaxe",
            SIMPLE_MELEE,
            new Money(5, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.SLASHING
            ),
            new Weight(2, LB),
            IRON_AXE,
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            )
    ),
    JAVELIN(
            "Javelin",
            SIMPLE_MELEE,
            new Money(5, Currency.SP),
            new WeaponDamage(
                new Roll(new Die(1, 6)),
                DamageType.PIERCING
            ),
            new Weight(2, LB),
            Material.TRIDENT,
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(30, DistanceUnit.FEET),
                    new Distance(120, DistanceUnit.FEET)
            )
    ),
    LIGHT_HAMMER(
            "Light hammer",
            SIMPLE_MELEE,
            new Money(2, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.BLUDGEONING
            ),
            new Weight(2, LB),
            WOODEN_AXE,
            new WeaponProperty.Light(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            )
    ),
    MACE(
            "Mace",
            SIMPLE_MELEE,
            new Money(5, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.BLUDGEONING
            ),
            new Weight(4, LB),
            IRON_AXE
    ),
    QUARTERSTAFF(
            "Quarterstaff",
            SIMPLE_MELEE,
            new Money(2, Currency.SP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.BLUDGEONING
            ),
            new Weight(4, LB),
            STICK,
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    SICKLE(
            "Sickle",
            SIMPLE_MELEE,
            new Money(1, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.SLASHING
            ),
            new Weight(2, LB),
            IRON_HOE,
            new WeaponProperty.Light()
    ),
    SPEAR(
            "Spear",
            SIMPLE_MELEE,
            new Money(1, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.PIERCING
            ),
            new Weight(3, LB),
            Material.TRIDENT,
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            ),
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    LIGHT_CROSSBOW(
            "Crossbow, light",
            SIMPLE_RANGED,
            new Money(25, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.PIERCING
            ),
            new Weight(5, LB),
            CROSSBOW,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(80, DistanceUnit.FEET),
                    new Distance(320, DistanceUnit.FEET)
            ),
            new WeaponProperty.Loading(),
            new WeaponProperty.TwoHanded()
    ),
    DART(
            "Dart",
            SIMPLE_RANGED,
            new Money(5, Currency.CP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.PIERCING
            ),
            new Weight(0.25, LB),
            ARROW,
            new WeaponProperty.Finesse(),
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            )
    ),
    SHORTBOW(
            "Shortbow",
            SIMPLE_RANGED,
            new Money(25, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.PIERCING
            ),
            new Weight(2, LB),
            BOW,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(80, DistanceUnit.FEET),
                    new Distance(320, DistanceUnit.FEET)
            ),
            new WeaponProperty.TwoHanded()
    ),
    SLING(
            "Sling",
            SIMPLE_RANGED,
            new Money(1, Currency.SP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.BLUDGEONING
            ),
            new Weight(0, LB),
            LEATHER,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(30, DistanceUnit.FEET),
                    new Distance(120, DistanceUnit.FEET)
            )
    ),
    BATTLEAXE(
            "Battleaxe",
            MARTIAL_MELEE,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.SLASHING
            ),
            new Weight(4, LB),
            IRON_AXE,
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    FLAIL(
            "Flail",
            MARTIAL_MELEE,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.BLUDGEONING
            ),
            new Weight(2, LB),
            IRON_AXE
    ),
    GLAIVE(
            "Glaive",
            MARTIAL_MELEE,
            new Money(20, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    DamageType.SLASHING
            ),
            new Weight(6, LB),
            Material.TRIDENT,
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    GREATAXE(
            "Greataxe",
            MARTIAL_MELEE,
            new Money(30, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 12)),
                    DamageType.SLASHING
            ),
            new Weight(7, LB),
            IRON_AXE,
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    GREATSWORD(
            "Greatsword",
            MARTIAL_MELEE,
            new Money(50, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(2, 6)),
                    DamageType.SLASHING
            ),
            new Weight(6, LB),
            IRON_SWORD,
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    HALBERD(
            "Halberd",
            MARTIAL_MELEE,
            new Money(20, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    DamageType.SLASHING
            ),
            new Weight(6, LB),
            Material.TRIDENT,
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    LANCE(
            "Lance",
            MARTIAL_MELEE,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 12)),
                    DamageType.PIERCING
            ),
            new Weight(6, LB),
            Material.TRIDENT,
            new WeaponProperty.Reach(),
            new WeaponProperty.Special("You have a disadvantage when you use a lance to attack a target within 5 feet" +
                    " of you. Also, a lance requires two hands to wield when you aren't mounted.")
    ),
    LONGSWORD(
            "Longsword",
            MARTIAL_MELEE,
            new Money(15, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.SLASHING
            ),
            new Weight(3, LB),
            IRON_SWORD,
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    MAUL(
            "Maul",
            MARTIAL_MELEE,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(2, 6)),
                    DamageType.BLUDGEONING
            ),
            new Weight(10, LB),
            IRON_AXE,
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    MORNINGSTAR(
            "Morningstar",
            MARTIAL_MELEE,
            new Money(15, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.PIERCING
            ),
            new Weight(4, LB),
            IRON_AXE
    ),
    PIKE(
            "Pike",
            MARTIAL_MELEE,
            new Money(5, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    DamageType.PIERCING
            ),
            new Weight(18, LB),
            Material.TRIDENT,
            new WeaponProperty.Heavy(),
            new WeaponProperty.Reach(),
            new WeaponProperty.TwoHanded()
    ),
    RAPIER(
            "Rapier",
            MARTIAL_MELEE,
            new Money(25, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.PIERCING
            ),
            new Weight(2, LB),
            IRON_SWORD,
            new WeaponProperty.Finesse()
    ),
    SCIMITAR(
            "Scimitar",
            MARTIAL_MELEE,
            new Money(25, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.SLASHING
            ),
            new Weight(3, LB),
            IRON_SWORD,
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light()
    ),
    SHORTSWORD(
            "Shortsword",
            MARTIAL_MELEE,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.PIERCING
            ),
            new Weight(2, LB),
            IRON_SWORD,
            new WeaponProperty.Finesse(),
            new WeaponProperty.Light()
    ),
    TRIDENT(
            "Trident",
            MARTIAL_MELEE,
            new Money(5, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.PIERCING
            ),
            new Weight(4, LB),
            Material.TRIDENT,
            new WeaponProperty.Thrown(),
            new WeaponProperty.Range(
                    new Distance(20, DistanceUnit.FEET),
                    new Distance(60, DistanceUnit.FEET)
            ),
            new WeaponProperty.Versatile(new Roll(new Die(1, 8)))
    ),
    WAR_PICK(
            "War pick",
            MARTIAL_MELEE,
            new Money(5, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.PIERCING
            ),
            new Weight(2, LB),
            IRON_PICKAXE
    ),
    WARHAMMER(
            "Warhammer",
            MARTIAL_MELEE,
            new Money(15, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.BLUDGEONING
            ),
            new Weight(2, LB),
            IRON_AXE,
            new WeaponProperty.Versatile(new Roll(new Die(1, 10)))
    ),
    WHIP(
            "Whip",
            MARTIAL_MELEE,
            new Money(2, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 4)),
                    DamageType.SLASHING
            ),
            new Weight(3, LB),
            STRING,
            new WeaponProperty.Finesse(),
            new WeaponProperty.Reach()
    ),
    BLOWGUN(
            "Blowgun",
            MARTIAL_RANGED,
            new Money(10, Currency.GP),
            new WeaponDamage(
                    new Roll(new Modifier(1)),
                    DamageType.PIERCING
            ),
            new Weight(1, LB),
            BAMBOO,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(25, DistanceUnit.FEET),
                    new Distance(100, DistanceUnit.FEET)
            ),
            new WeaponProperty.Loading()
    ),
    HAND_CROSSBOW(
            "Crossbow, hand",
            MARTIAL_RANGED,
            new Money(75, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 6)),
                    DamageType.PIERCING
            ),
            new Weight(3, LB),
            CROSSBOW,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(30, DistanceUnit.FEET),
                    new Distance(120, DistanceUnit.FEET)
            ),
            new WeaponProperty.Light(),
            new WeaponProperty.Loading()
    ),
    HEAVY_CROSSBOW(
            "Crossbow, heavy",
            MARTIAL_RANGED,
            new Money(50, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 10)),
                    DamageType.PIERCING
            ),
            new Weight(18, LB),
            CROSSBOW,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(100, DistanceUnit.FEET),
                    new Distance(400, DistanceUnit.FEET)
            ),
            new WeaponProperty.Heavy(),
            new WeaponProperty.Loading(),
            new WeaponProperty.TwoHanded()
    ),
    LONGBOW(
            "Longbow",
            MARTIAL_RANGED,
            new Money(50, Currency.GP),
            new WeaponDamage(
                    new Roll(new Die(1, 8)),
                    DamageType.PIERCING
            ),
            new Weight(2, LB),
            BOW,
            new WeaponProperty.Ammunition(),
            new WeaponProperty.Range(
                    new Distance(150, DistanceUnit.FEET),
                    new Distance(600, DistanceUnit.FEET)
            ),
            new WeaponProperty.Heavy(),
            new WeaponProperty.TwoHanded()
    ),
    NET(
            "Net",
            MARTIAL_RANGED,
            new Money(1, Currency.GP),
            null,
            new Weight(3, LB),
            COBWEB,
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
    private final ItemStackInitializer itemStackInitializer;
    private final List<WeaponProperty> properties;

    WeaponType(
            String name,
            WeaponCategory category,
            Money cost,
            WeaponDamage damage,
            Weight weight,
            ItemStackInitializer itemStackInitializer,
            WeaponProperty... properties
    ) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.damage = damage;
        this.weight = weight;
        this.itemStackInitializer = itemStackInitializer;
        this.properties = Arrays.asList(properties);
    }

    WeaponType(
            String name,
            WeaponCategory category,
            Money cost,
            WeaponDamage damage,
            Weight weight,
            Material minecraftType,
            WeaponProperty... properties
    ) {
        this(
                name,
                category,
                cost,
                damage,
                weight,
                (amount) -> new ItemStack(minecraftType, amount),
                properties
        );
    }

    @Override
    public String getName() {
        return name;
    }

    public WeaponCategory getCategory() {
        return category;
    }

    @Override
    public Money getCost() {
        return cost;
    }

    public WeaponDamage getDamage() {
        return damage;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public ItemStack createItemStack(int amount) {
        return itemStackInitializer.invoke(amount);
    }

    public List<WeaponProperty> getProperties() {
        return properties;
    }

    @Override
    public List<String> createLore() {
        List<String> lore = new ArrayList<>();
        lore.add(WHITE + "Weight: " + getWeight().toString());
        lore.add(WHITE + "Category: " + getCategory().getName());
        lore.add(WHITE + "Damage: " + getDamage().getRoll().toString() + " " + getDamage().getDamageType().getName());
        lore.add(WHITE + "Properties: ");
        lore.addAll(getProperties().stream().map(property -> WHITE + "\u2022 " + property.toString()).collect(Collectors.toList()));
        return lore;
    }

    public static WeaponType getByName(String name) {
        return Arrays.stream(WeaponType.values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
