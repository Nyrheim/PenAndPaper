package net.nyrheim.penandpaper.item;

import net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType;
import net.nyrheim.penandpaper.item.armor.ArmorType;
import net.nyrheim.penandpaper.item.weapon.WeaponType;
import net.nyrheim.penandpaper.money.Money;
import net.nyrheim.penandpaper.weight.Weight;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ItemType {

    String getName();
    Money getCost();
    Weight getWeight();
    ItemStack createItemStack(int amount);
    List<String> createLore();

    public static ItemType valueOf(String name) {
        try {
            return AdventuringGearType.valueOf(name);
        } catch (IllegalArgumentException ignored) {}
        try {
            return ArmorType.valueOf(name);
        } catch (IllegalArgumentException ignored) {}
        try {
            return WeaponType.valueOf(name);
        } catch (IllegalArgumentException ignored) {}
        throw new IllegalArgumentException("No enum constant "
                + AdventuringGearType.class.getCanonicalName() + "." + name + ","
                + ArmorType.class.getCanonicalName() + "." + name + ", or"
                + WeaponType.class.getCanonicalName() + "." + name);
    }

    public static ItemType getByName(String name) {
        ItemType type = AdventuringGearType.getByName(name);
        if (type != null) return type;
        type = ArmorType.getByName(name);
        if (type != null) return type;
        type = WeaponType.getByName(name);
        return type;
    }

    public static ItemType[] values() {
        List<ItemType> types = new ArrayList<>();
        types.addAll(Arrays.asList(AdventuringGearType.values()));
        types.addAll(Arrays.asList(ArmorType.values()));
        types.addAll(Arrays.asList(WeaponType.values()));
        return types.toArray(new ItemType[0]);
    }
}
