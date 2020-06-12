package net.nyrheim.penandpaper.item.adventuringgear;

import net.nyrheim.penandpaper.item.ItemStackInitializer;
import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.money.Money;
import net.nyrheim.penandpaper.weight.Weight;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.nyrheim.penandpaper.money.Currency.*;
import static net.nyrheim.penandpaper.weight.WeightUnit.LB;
import static net.nyrheim.penandpaper.weight.WeightUnit.OZ;
import static org.bukkit.Material.*;
import static org.bukkit.block.banner.PatternType.CROSS;
import static org.bukkit.potion.PotionEffectType.POISON;

public enum AdventuringGearType implements ItemType {

    // 5e Player's Handbook Items
    ABACUS(
            "Abacus",
            new Money(2, GP),
            new Weight(2, LB),
            OAK_WOOD
    ),
    ACID_VIAL(
            "Acid (vial)",
            new Money(25, GP),
            new Weight(1, LB),
            (amount) -> {
                ItemStack stack = new ItemStack(POTION, amount);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    if (meta instanceof PotionMeta) {
                        PotionMeta potionMeta = (PotionMeta) meta;
                        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 1, 0, false), true);
                        stack.setItemMeta(potionMeta);
                    }
                }
                return stack;
            }
    ),
    ALCHEMISTS_FIRE_FLASK(
            "Alchemist's fire (flask)",
            new Money(50, GP),
            new Weight(1, LB),
            FIRE_CHARGE
    ),
    ARROW(
            "Arrow",
            new Money(5, CP),
            new Weight(0.05, LB),
            Material.ARROW
    ),
    BLOWGUN_NEEDLE(
            "Blowgun needle",
            new Money(2, CP),
            new Weight(0.02, LB),
            Material.ARROW
    ),
    CROSSBOW_BOLT(
            "Crossbow bolt",
            new Money(5, CP),
            new Weight(0.075, LB),
            Material.ARROW
    ),
    SLING_BULLET(
            "Sling bullet",
            new Money(1, CP),
            new Weight(0.075, LB),
            ENDER_PEARL
    ),
    ANTITOXIN_VIAL(
            "Antitoxin (vial)",
            new Money(50, GP),
            new Weight(0, LB),
            MILK_BUCKET
    ),
    CRYSTAL(
            "Crystal",
            new Money(10, GP),
            new Weight(1, LB),
            DIAMOND
    ),
    ORB(
            "Orb",
            new Money(20, GP),
            new Weight(3, LB),
            ENDER_PEARL
    ),
    ROD(
            "Rod",
            new Money(10, GP),
            new Weight(2, LB),
            BLAZE_ROD
    ),
    STAFF(
            "Staff",
            new Money(5, GP),
            new Weight(4, LB),
            STICK
    ),
    WAND(
            "Wand",
            new Money(10, GP),
            new Weight(1, LB),
            STICK
    ),
    BACKPACK(
            "Backpack",
            new Money(2, GP),
            new Weight(5, LB),
            SHULKER_BOX
    ),
    BALL_BEARINGS(
            "Ball bearings (pack of 1000)",
            new Money(1, GP),
            new Weight(2, LB),
            MELON_SEEDS
    ),
    BARREL(
            "Barrel",
            new Money(2, GP),
            new Weight(70, LB),
            Material.BARREL
    ),
    BASKET(
            "Basket",
            new Money(4, SP),
            new Weight(2, LB),
            CHEST_MINECART
    ),
    BEDROLL(
            "Bedroll",
            new Money(1, GP),
            new Weight(7, LB),
            RED_BED
    ),
    BELL(
            "Bell",
            new Money(1, GP),
            new Weight(0, LB),
            Material.BELL
    ),
    BLANKET(
            "Blanket",
            new Money(5, SP),
            new Weight(3, LB),
            RED_CARPET
    ),
    BLOCK_AND_TACKLE(
            "Block and Tackle",
            new Money(1, GP),
            new Weight(5, LB),
            TRIPWIRE_HOOK
    ),
    BOOK(
            "Book",
            new Money(25, GP),
            new Weight(5, LB),
            Material.BOOK
    ),
    GLASS_BOTTLE(
            "Bottle, glass",
            new Money(2, GP),
            new Weight(2, LB),
            Material.GLASS_BOTTLE
    ),
    BUCKET(
            "Bucket",
            new Money(5, CP),
            new Weight(2, LB),
            Material.BUCKET
    ),
    CALTROPS(
            "Caltrops (bag of 20)",
            new Money(1, GP),
            new Weight(2, LB),
            STONE_PRESSURE_PLATE
    ),
    CANDLE(
            "Candle",
            new Money(1, CP),
            new Weight(0, LB),
            END_ROD
    ),
    CROSSBOW_BOLT_CASE(
            "Case, crossbow bolt",
            new Money(1, GP),
            new Weight(1, LB),
            SHULKER_BOX
    ),
    MAP_OR_SCROLL_CASE(
            "Case, map or scroll",
            new Money(1, GP),
            new Weight(1, LB),
            SHULKER_BOX
    ),
    CHAIN(
            "Chain (10 feet)",
            new Money(5, GP),
            new Weight(10, LB),
            Material.STRING
    ),
    CHALK(
            "Chalk (1 piece)",
            new Money(1, CP),
            new Weight(0, LB),
            QUARTZ
    ),
    CHEST(
            "Chest",
            new Money(5, GP),
            new Weight(25, LB),
            Material.CHEST
    ),
    CLIMBERS_KIT(
            "Climber's kit",
            new Money(25, GP),
            new Weight(12, LB),
            Material.STRING
    ),
    COMMON_CLOTHES(
            "Clothes, common",
            new Money(25, SP),
            new Weight(3, LB),
            LEATHER_CHESTPLATE
    ),
    COSTUME_CLOTHES(
            "Clothes, costume",
            new Money(5, GP),
            new Weight(4, LB),
            LEATHER_CHESTPLATE
    ),
    FINE_CLOTHES(
            "Clothes, fine",
            new Money(15, GP),
            new Weight(6, LB),
            LEATHER_CHESTPLATE
    ),
    TRAVELERS_CLOTHES(
            "Clothes, traveler's",
            new Money(2, GP),
            new Weight(4, LB),
            LEATHER_CHESTPLATE
    ),
    COMPONENT_POUCH(
            "Component pouch",
            new Money(25, GP),
            new Weight(2, LB),
            SHULKER_BOX
    ),
    CROWBAR(
            "Crowbar",
            new Money(2, GP),
            new Weight(5, LB),
            IRON_HOE
    ),
    SPRIG_OF_MISTLETOE(
            "Sprig of Mistletoe",
            new Money(1, GP),
            new Weight(0, LB),
            OAK_LEAVES
    ),
    TOTEM(
            "Totem",
            new Money(1, GP),
            new Weight(0, LB),
            FEATHER
    ),
    WOODEN_STAFF(
            "Wooden staff",
            new Money(5, GP),
            new Weight(4, LB),
            STICK
    ),
    YEW_WAND(
            "Yew wand",
            new Money(10, GP),
            new Weight(1, LB),
            STICK
    ),
    FISHING_TACKLE(
            "Fishing tackle",
            new Money(1, GP),
            new Weight(4, LB),
            Material.FISHING_ROD
    ),
    FLASK_OR_TANKARD(
            "Flask or tankard",
            new Money(2, CP),
            new Weight(1, LB),
            Material.GLASS_BOTTLE
    ),
    GRAPPLING_HOOK(
            "Grappling hook",
            new Money(2, GP),
            new Weight(4, LB),
            SHEARS
    ),
    HAMMER(
            "Hammer",
            new Money(1, GP),
            new Weight(3, LB),
            STONE_AXE
    ),
    SLEDGE_HAMMER(
            "Hammer, sledge",
            new Money(2, GP),
            new Weight(10, LB),
            WOODEN_AXE
    ),
    HEALERS_KIT(
            "Healer's kit",
            new Money(5, GP),
            new Weight(3, LB),
            Material.PAPER
    ),
    AMULET(
            "Amulet",
            new Money(5, GP),
            new Weight(1, LB),
            EMERALD
    ),
    EMBLEM(
            "Emblem",
            new Money(5, GP),
            new Weight(0, LB),
            (amount) -> {
                ItemStack stack = new ItemStack(BLACK_BANNER);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    if (meta instanceof BannerMeta) {
                        BannerMeta bannerMeta = (BannerMeta) meta;
                        bannerMeta.addPattern(new Pattern(DyeColor.WHITE, CROSS));
                        stack.setItemMeta(bannerMeta);
                    }
                }
                return stack;
            }
    ),
    RELIQUARY(
            "Reliquary",
            new Money(5, GP),
            new Weight(2, LB),
            (amount) -> {
                ItemStack stack = new ItemStack(BLACK_BANNER);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    if (meta instanceof BannerMeta) {
                        BannerMeta bannerMeta = (BannerMeta) meta;
                        bannerMeta.addPattern(new Pattern(DyeColor.BLUE, CROSS));
                        stack.setItemMeta(bannerMeta);
                    }
                }
                return stack;
            }
    ),
    HOLY_WATER_FLASK(
            "Holy water (flask)",
            new Money(25, GP),
            new Weight(1, LB),
            POTION
    ),
    HOLY_SYMBOL(
            "Holy Symbol",
            new Money(50, CP),
            new Weight(1, LB),
            CLOCK
    ),
    HOURGLASS(
            "Hourglass",
            new Money(25, GP),
            new Weight(1, LB),
            CLOCK
    ),
    HUNTING_TRAP(
            "Hunting trap",
            new Money(5, GP),
            new Weight(25, LB),
            STONE_PRESSURE_PLATE
    ),
    INK(
            "Ink (1 ounce bottle)",
            new Money(10, GP),
            new Weight(1, OZ),
            INK_SAC
    ),
    INK_PEN(
            "Ink pen",
            new Money(2, CP),
            new Weight(0, LB),
            FEATHER
    ),
    JUG_OR_PITCHER(
            "Jug or pitcher",
            new Money(2, CP),
            new Weight(4, LB),
            Material.GLASS_BOTTLE
    ),
    LADDER(
            "Ladder (10 foot)",
            new Money(1, SP),
            new Weight(25, LB),
            Material.LADDER
    ),
    LAMP(
            "Lamp",
            new Money(5, SP),
            new Weight(1, LB),
            REDSTONE_LAMP
    ),
    BULLSEYE_LANTERN(
            "Lantern, bullseye",
            new Money(10, GP),
            new Weight(2, LB),
            REDSTONE_LAMP
    ),
    HOODED_LANTERN(
            "Lantern, hooded",
            new Money(5, GP),
            new Weight(2, LB),
            REDSTONE_TORCH
    ),
    LOCK(
            "Lock",
            new Money(1, GP),
            new Weight(1, LB),
            Material.IRON_INGOT
    ),
    MAGNIFYING_GLASS(
            "Magnifying glass",
            new Money(100, GP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    MANACLES(
            "Manacles",
            new Money(2, GP),
            new Weight(6, LB),
            Material.IRON_INGOT
    ),
    MESS_KIT(
            "Mess kit",
            new Money(2, SP),
            new Weight(1, LB),
            Material.IRON_INGOT
    ),
    STEEL_MIRROR(
            "Mirror, steel",
            new Money(5, GP),
            new Weight(0.5, LB),
            IRON_BLOCK
    ),
    OIL_FLASK(
            "Oil (flask)",
            new Money(1, SP),
            new Weight(1, LB),
            POTION
    ),
    PAPER(
            "Paper",
            new Money(2, SP),
            new Weight(0, LB),
            Material.PAPER
    ),
    PARCHMENT(
            "Parchment",
            new Money(1, SP),
            new Weight(0, LB),
            Material.PAPER
    ),
    PERFUME(
            "Perfume (vial)",
            new Money(5, GP),
            new Weight(0, LB),
            POTION
    ),
    MINERS_PICK(
            "Pick, miner's",
            new Money(2, GP),
            new Weight(10, LB),
            IRON_PICKAXE
    ),
    PITON(
            "Piton",
            new Money(5, CP),
            new Weight(0.25, LB),
            STICK
    ),
    BASIC_POISON_VIAL(
            "Poison, basic (vial)",
            new Money(100, GP),
            new Weight(0, LB),
            (amount) -> {
                ItemStack stack = new ItemStack(POTION, amount);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    if (meta instanceof PotionMeta) {
                        PotionMeta potionMeta = (PotionMeta) meta;
                        potionMeta.addCustomEffect(new PotionEffect(POISON, 1200, 0, true), true);
                        stack.setItemMeta(potionMeta);
                    }
                }
                return stack;
            }
    ),
    POLE(
            "Pole (10-foot)",
            new Money(5, CP),
            new Weight(7, LB),
            STICK
    ),
    IRON_POT(
            "Pot, iron",
            new Money(2, GP),
            new Weight(10, LB),
            CAULDRON
    ),
    POTION_OF_HEALING(
            "Potion of healing",
            new Money(50, GP),
            new Weight(0.5, LB),
            (amount) -> {
                ItemStack stack = new ItemStack(POTION, amount);
                ItemMeta meta = stack.getItemMeta();
                if (meta != null) {
                    if (meta instanceof PotionMeta) {
                        PotionMeta potionMeta = (PotionMeta) meta;
                        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1200, 0, true), true);
                        stack.setItemMeta(potionMeta);
                    }
                }
                return stack;
            }
    ),
    POUCH(
            "Pouch",
            new Money(5, SP),
            new Weight(1, LB),
            LEATHER
    ),
    QUIVER(
            "Quiver",
            new Money(1, GP),
            new Weight(1, LB),
            SHULKER_BOX
    ),
    PORTABLE_RAM(
            "Ram, portable",
            new Money(4, GP),
            new Weight(35, LB),
            IRON_BLOCK
    ),
    RATIONS(
            "Rations (1 day)",
            new Money(5, SP),
            new Weight(2, LB),
            BREAD
    ),
    ROBES(
            "Robes",
            new Money(1, GP),
            new Weight(4, LB),
            LEATHER_CHESTPLATE
    ),
    HEMPEN_ROPE(
            "Rope, hempen (50 feet)",
            new Money(1, GP),
            new Weight(10, LB),
            Material.STRING
    ),
    SILK_ROPE(
            "Rope, silk (50 feet)",
            new Money(10, GP),
            new Weight(5, LB),
            Material.STRING
    ),
    SACK(
            "Sack",
            new Money(1, CP),
            new Weight(0.5, LB),
            SHULKER_BOX
    ),
    MERCHANTS_SCALE(
            "Scale, merchant's",
            new Money(5, GP),
            new Weight(3, LB),
            IRON_BLOCK
    ),
    SEALING_WAX(
            "Sealing wax",
            new Money(5, SP),
            new Weight(0, LB),
            SLIME_BALL
    ),
    SHOVEL(
            "Shovel",
            new Money(2, GP),
            new Weight(5, LB),
            IRON_SHOVEL
    ),
    SIGNAL_WHISTLE(
            "Signal whistle",
            new Money(5, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    SIGNET_RING(
            "Signet ring",
            new Money(5, GP),
            new Weight(0, LB),
            GOLD_INGOT
    ),
    SOAP(
            "Soap",
            new Money(2, CP),
            new Weight(0, LB),
            BRICK
    ),
    SPELLBOOK(
            "Spellbook",
            new Money(50, GP),
            new Weight(3, LB),
            Material.BOOK
    ),
    IRON_SPIKE(
            "Spike, iron",
            new Money(1, SP),
            new Weight(0.5, LB),
            Material.IRON_INGOT
    ),
    SPYGLASS(
            "Spyglass",
            new Money(1000, GP),
            new Weight(1, LB),
            Material.IRON_INGOT
    ),
    TENT(
            "Tent, two-person",
            new Money(2, GP),
            new Weight(20, LB),
            GREEN_WOOL
    ),
    TINDERBOX(
            "Tinderbox",
            new Money(5, SP),
            new Weight(1, LB),
            Material.TORCH
    ),
    TORCH(
            "Torch",
            new Money(1, CP),
            new Weight(1, LB),
            Material.TORCH
    ),
    VIAL(
            "Vial",
            new Money(1, GP),
            new Weight(0, LB),
            POTION
    ),
    WATERSKIN(
            "Waterskin",
            new Money(2, SP),
            new Weight(5, LB),
            LEATHER
    ),
    WHETSTONE(
            "Whetstone",
            new Money(1, CP),
            new Weight(1, LB),
            STONE_BUTTON
    ),
    // Crafting components
    GUARD(
            "Guard",
            new Money(0, CP),
            new Weight(0, LB),
            STICK
    ),
    BLADE(
            "Blade",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    SPEARHEAD(
            "Spearhead",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    AXEHEAD(
            "Axehead",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    HAMMER_HEAD(
            "Hammer head",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    CBOW_MECHANISM(
            "CBow mechanism",
            new Money(0, CP),
            new Weight(0, LB),
            FLINT_AND_STEEL
    ),
    POMMEL(
            "Pommel",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    ARROW_HEAD(
            "Arrow head",
            new Money(0, CP),
            new Weight(0, LB),
            FLINT
    ),
    NAILS(
            "Nails",
            new Money(0, CP),
            new Weight(0, LB),
            IRON_NUGGET
    ),
    HANDLE(
            "Handle",
            new Money(0, CP),
            new Weight(0, LB),
            LEVER
    ),
    ARROW_BODY(
            "Arrow body",
            new Money(0, CP),
            new Weight(0, LB),
            STICK
    ),
    BEAM(
            "\u0062\u00e9\u00e2\u006d",
            new Money(0, CP),
            new Weight(0, LB),
            STICK
    ),
    STORAGE(
            "Storage",
            new Money(0, CP),
            new Weight(0, LB),
            Material.CHEST
    ),
    DOOR(
            "Door",
            new Money(0, CP),
            new Weight(0, LB),
            OAK_DOOR
    ),
    SMALL_FURNITURE(
            "Furniture, small",
            new Money(0, CP),
            new Weight(0, LB),
            JUNGLE_STAIRS
    ),
    MEDIUM_FURNITURE(
            "Furniture, medium",
            new Money(0, CP),
            new Weight(0, LB),
            SPRUCE_STAIRS
    ),
    LARGE_FURNITURE(
            "Furniture, large",
            new Money(0, CP),
            new Weight(0, LB),
            BIRCH_STAIRS
    ),
    TRINKET(
            "Trinket",
            new Money(0, CP),
            new Weight(0, LB),
            BRICK
    ),
    FISHING_ROD(
            "Fishing rod",
            new Money(0, CP),
            new Weight(0, LB),
            Material.FISHING_ROD
    ),
    STRING(
            "String",
            new Money(0, CP),
            new Weight(0, LB),
            Material.STRING
    ),
    IRON_INGOT(
            "Iron ingot",
            new Money(0, CP),
            new Weight(0, LB),
            Material.IRON_INGOT
    ),
    LUMBER(
            "Lumber",
            new Money(0, CP),
            new Weight(0, LB),
            OAK_LOG
    ),
    LEATHER_STRAP(
            "Leather strap",
            new Money(0, CP),
            new Weight(0, LB),
            LEATHER
    ),
    LONGERBOW(
            "Longerbow",
            new Money(0, CP),
            new Weight(0, LB),
            BOW
    ),
    GLUE(
            "Glue",
            new Money(0, CP),
            new Weight(0, LB),
            SLIME_BALL
    ),
    GLASS(
            "Glass",
            new Money(0, CP),
            new Weight(0, LB),
            Material.GLASS
    ),
    SMITHS_TOOLS(
            "Smith's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            ANVIL
    ),
    CARPENTERS_TOOLS(
            "Carpenter's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            CRAFTING_TABLE
    ),
    ARCHITECTS_TOOLS(
            "Architect's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            STONECUTTER
    ),
    LEATHERWORKERS_TOOLS(
            "Leatherworker's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            STONE_SWORD
    ),
    MINERS_TOOLS(
            "Miner's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            IRON_PICKAXE
    ),
    WOODCUTTERS_TOOLS(
            "Woodcutter's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            IRON_AXE
    ),
    FARMERS_TOOLS(
            "Farmer's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            IRON_HOE
    ),
    ALCHEMISTS_TOOLS(
            "Alchemist's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            BREWING_STAND
    ),
    HERBALISTS_TOOLS(
            "Herbalist's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            SHEARS
    ),
    HUNTERS_TOOLS(
            "Hunter's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            BOW
    ),
    FISHERMANS_TOOLS(
            "Fisherman's Tools",
            new Money(0, CP),
            new Weight(10, LB),
            Material.FISHING_ROD
    ),
    SCAFFOLDING_KIT(
            "Scaffolding Kit",
            new Money(0, CP),
            new Weight(10, LB),
            SCAFFOLDING
    ),
    INSTRUMENT(
            "Instrument",
            new Money(0, CP),
            new Weight(1, LB),
            MUSIC_DISC_MALL
    ),
    DIVING_TOOLS(
            "Diving Tools",
            new Money(0, CP),
            new Weight(10, LB),
            COD_BUCKET
    );

    private final String name;
    private final Money cost;
    private final Weight weight;
    private final ItemStackInitializer itemStackInitializer;

    AdventuringGearType(String name, Money cost, Weight weight, ItemStackInitializer itemStackInitializer) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.itemStackInitializer = itemStackInitializer;
    }

    AdventuringGearType(String name, Money cost, Weight weight, Material minecraftType) {
        this(
                name,
                cost,
                weight,
                (amount) -> new ItemStack(minecraftType, amount)
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Money getCost() {
        return cost;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public ItemStack createItemStack(int amount) {
        return itemStackInitializer.invoke(amount);
    }

    @Override
    public List<String> createLore() {
        return Collections.singletonList(
                ChatColor.GRAY + "Weight: " + getWeight().toString()
        );
    }

    public static AdventuringGearType getByName(String name) {
        return Arrays.stream(AdventuringGearType.values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
