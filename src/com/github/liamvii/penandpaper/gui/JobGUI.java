package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JobGUI implements InventoryHolder {

    private final Inventory inv;
    private Pen main;

    public JobGUI() {
        inv = Bukkit.createInventory(this, 36, "Class Selection");
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.IRON_SWORD, "Longsword", "§6Level 1 Fighter", "§6Difficulty: [★☆☆☆☆]", "§fFighters share an unparalleled mastery with weapons and armor,", "§fand a thorough knowledge of the skills of combat. They are well", "§facquainted with death, both meting it out and staring it defiantly in the face."));
        inv.addItem(createGuiItem(Material.BOW, "Short Bow", "§6Level 1 Ranger", "§6Difficulty: [★★☆☆☆]", "§fFar from the bustle of cities and towns, past the hedges that shelter",  "§fthe most distant farms from the terrors of the wild, amid the", "§fdense-packed trees of trackless forests and across wide", "§fand empty plains, rangers keep their unending watch."));
        inv.addItem(createGuiItem(Material.IRON_AXE, "Hunting Knife", "§6Level 1 Rogue", "§6Difficulty: [★★☆☆☆]", "§fRogues rely on skill, stealth, and their foes’ vulnerabilities to get", "§fthe upper hand in any situation. They have a knack for finding", "§fthe solution to just about any problem, demonstrating", "§fa resourcefulness and versatility that is the cornerstone", "§fof any successful adventuring party."));
        inv.addItem(createGuiItem(Material.IRON_AXE, "Twoheaded Waraxe", "§6Level 1 Barbarian", "§6Difficulty: [★★☆☆☆]", "§fFor some, their rage springs from a communion with fierce animal spirits.", "§fOthers draw from a roiling reservoir of anger at a world full of pain.", "§fFor every barbarian, rage is a power that fuels not just a battle frenzy but ", "§falso uncanny reflexes, resilience, and feats of strength."));
        inv.addItem(createGuiItem(Material.IRON_AXE, "Handsaw", "§6Level 1 Monk", "§6Difficulty: [★★★☆☆]", "§fMonks are united in their ability to magically harness", "§fthe energy that flows in their bodies. Whether channeled", "§fas a striking display of combat prowess or a subtler focus", "§fof defensive ability and speed, this energy infuses all that a monk does."));
        inv.addItem(createGuiItem(Material.MUSIC_DISC_MALL, "Lute", "§6Level 1 Bard", "§6Difficulty: [★★★☆☆]", "§fWhether scholar, skald, or scoundrel, a bard weaves magic", "§fthrough words and music to inspire allies, demoralize foes,", "§fmanipulate minds, create illusions, and even heal wounds.", "§fThe bard is a master of song, speech, and the magic they contain."));
        inv.addItem(createGuiItem(Material.BOOK, "Tome of Manaflow", "§6Level 1 Wizard", "§6Difficulty: [★★★☆☆]", "§fWizards are supreme magic-users, defined and united", "§fas a class by the spells they cast. Drawing on the subtle weave", "§fof magic that permeates the cosmos, wizards cast spells", "§fof explosive fire, arcing lightning, subtle deception,", "§fbrute-force mind control, and much more."));
        inv.addItem(createGuiItem(Material.BOOK, "Tome of Fire", "§6Level 1 Sorcerer", "§6Difficulty: [★★☆☆☆]", "§fSorcerers carry a magical birthright conferred upon", "§fthem by an exotic bloodline, some otherworldly influence,", "§for exposure to unknown cosmic forces.", "§fNo one chooses sorcery; the power chooses the sorcerer."));
        inv.addItem(createGuiItem(Material.ENDER_EYE, "Patron's Mark", "§6Level 1 Warlock", "§6Difficulty: [★★★★☆]", "§fWarlocks are seekers of the knowledge that lies hidden", "§fin the fabric of the multiverse. Through pacts", "§fmade with mysterious beings of supernatural power, ", "§fwarlocks unlock magical effects both subtle and spectacular."));
        inv.setItem(12, createGuiItem(Material.SUNFLOWER, "Nature's Blessing", "§6Level 1 Druid", "§6Difficulty: [★★★★☆]", "§fWhether calling on the elemental forces of nature", "§for emulating the creatures of the animal world, druids are", "§fan embodiment of nature's resilience, cunning, and fury.", "§fThey claim no mastery over nature, but see themselves", "§fas extensions of nature's indomitable will."));
        inv.setItem(13, createGuiItem(Material.CHAINMAIL_CHESTPLATE, "Basic Chainmail", "§6Level 1 Cleric", "§6Difficulty: [★★★★☆]", "§fClerics are intermediaries between the mortal world", "§fand the distant planes of the gods. As varied as", "§fthe gods they serve, clerics strive to embody the", "§fhandiwork of their deities. No ordinary priest,", "§fa cleric is imbued with divine magic."));
        inv.setItem(14, createGuiItem(Material.IRON_CHESTPLATE, "English Knight", "§6Level 1 Paladin", "§6Difficulty: [★★☆☆☆]", "§fWhether sworn before a god's altar and the witness", "§fof a priest, in a sacred glade before nature spirits and", "§ffey beings, or in a moment of desperation and grief", "§fwith the dead as the only witness, a paladin's oath is a powerful bond."));
        inv.setItem(31, createGuiItem(Material.PAPER, "Class Selection Menu", "", "§6Please select your character's class from the above."));
    }

    // Custom method to insert an item into the gui.
    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1); // Creates the ItemStack.
        ItemMeta meta = item.getItemMeta(); // Retrieves the item's default metadata.
        meta.setDisplayName("§f" + name); // The below methods are designed to update the item's name and lore.
        ArrayList<String> metaLore = new ArrayList<String>();
        for (String loreComments : lore) {
            metaLore.add(loreComments);
        }
        meta.setLore(metaLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }



    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }

}
