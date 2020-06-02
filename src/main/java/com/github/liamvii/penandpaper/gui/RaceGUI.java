package com.github.liamvii.penandpaper.gui;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.character.PenCharacter;
import com.github.liamvii.penandpaper.character.PenCharacterService;
import com.github.liamvii.penandpaper.player.PenPlayer;
import com.github.liamvii.penandpaper.player.PenPlayerService;
import com.github.liamvii.penandpaper.race.PenRaceService;
import com.github.liamvii.penandpaper.race.Race;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.ChatColor.*;

public final class RaceGUI extends GUI {

    private final Pen plugin;

    private Race baseRace = null;

    public RaceGUI(Pen plugin) {
        super("Race");
        this.plugin = plugin;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems(Player player) {
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("6f827755-6c97-4108-bc90-5fd6dfd2de95")),
                WHITE + "Dwarf",
                GOLD + "Kingdoms rich in ancient grandeur, ",
                GOLD + "halls carved into the roots of mountains.",
                GOLD + "The echoing of picks and hammers in deep ",
                GOLD + "mines and blazing forges.",
                GOLD + "A commitment to clan and tradition, and a ",
                GOLD + "burning hatred of goblins and orcs.",
                GOLD + "These common threads unite all dwarves."
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("f4bfcc8f-6fee-41ba-9ceb-b2471dca24ed")),
                WHITE + "Elf",
                GOLD + "Elves are a magical people of ",
                GOLD + "otherworldly grace, living in the world ",
                GOLD + "but not entirely part of it.",
                GOLD + "They live in places of ethereal beauty, in ",
                GOLD + "the midst of ancient forests or in silvery ",
                GOLD + "spires glittering with faerie light, where ",
                GOLD + "soft music drifts through the air and gentle",
                GOLD + "fragrances waft on the breeze. Elves love ",
                GOLD + "nature and magic, art and artistry, music ",
                GOLD + "and poetry, and the good things of the world."
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("8fdddb7d-6e68-4069-b87c-4a9659a9b782")),
                WHITE + "Halfling",
                GOLD + "The comforts of the home are the goals",
                GOLD + "of most halflings' lives: a place to settle ",
                GOLD + "in peace and quiet, far from marauding monsters",
                GOLD + "and clashing armies; a blazing fire and a generous",
                GOLD + "meal; fine drink and fine conversation. Though some",
                GOLD + "halflings live out their days in remote agricultural",
                GOLD + "communities, others form nomadic bands that travel",
                GOLD + "constantly, lured by the open road and the wide",
                GOLD + "horizon to discover the wonders of lands and peoples.",
                GOLD + "But even these wanderers love peace, food, hearth and ",
                GOLD + "home, though home might be a wagon jostling along a",
                GOLD + "dirt road or a raft floating downriver."
        ));
        getInventory().setItem(3, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("3294f850-1236-4b5d-81be-9b084766d071")),
                WHITE + "Human",
                GOLD + "The most widespread of all races, humans ",
                GOLD + "and human civilization reaches nearly every corner of ",
                GOLD + "the world. Though quite unremarkable physically, with a",
                GOLD + "shorter lifespan than many races, humans show tenacity ",
                GOLD + "and drive that many races lack, compelled to spread the ",
                GOLD + "race as far as possible and to even the most inhospitable",
                GOLD + "lands. Humans are mostly liked by other races, and in general",
                GOLD + "don’t have hatred toward any specific race, though they ",
                GOLD + "can be racist towards many of the more monstrous-looking humanoids."
        ));
        getInventory().setItem(4, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("8c561166-26cc-4882-8458-b4dc9ff4e80b")),
                WHITE + "Gnome",
                GOLD + "A constant hum of busy activity pervades ",
                GOLD + "the warrens and neighbourhoods where gnomes form",
                GOLD + "their close-knit communities. Louder sounds punctuate",
                GOLD + "the hum: a crunch of grinding gears here, a minor",
                GOLD + "explosion there, a yelp of surprise or triumph, and ",
                GOLD + "especially bursts of laughter. Gnomes take delight in",
                GOLD + "life, enjoying every moment of invention, exploration,",
                GOLD + "investigation, creation, and play."
        ));
        getInventory().setItem(5, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("536c4a27-c503-48f3-b5c0-1168ccba50c7")),
                WHITE + "Half-Elf",
                GOLD + "Walking in two worlds but truly belonging ",
                GOLD + "to neither, half-elves combine what some say are ",
                GOLD + "the best qualities of their elf and human parents: ",
                GOLD + "human curiosity, inventiveness, and ambition tempered",
                GOLD + "by the refined senses, love of nature, and artistic ",
                GOLD + "tastes of the elves. Some half-elves live among humans,",
                GOLD + "set apart by their emotional and physical differences,",
                GOLD + "watching friends and loved one's age while time barely",
                GOLD + "touches them. Others live with the elves, growing ",
                GOLD + "restless as they reach adulthood in the timeless ",
                GOLD + "Elven realms, while their peers continue to live ",
                GOLD + "as children. Many half-elves, unable to fit into ",
                GOLD + "either society, choose lives of solitary wandering",
                GOLD + "or join with other misfits and outcasts in the ",
                GOLD + "adventuring life."
        ));
        getInventory().setItem(6, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("0dfd0dd8-7efb-4a78-946b-c183e7ae8eec")),
                WHITE + "Half-Orc",
                GOLD + "Whether united under the leadership of a ",
                GOLD + "mighty warlock or having fought to a standstill ",
                GOLD + "after years of conflict, orc and human tribes ",
                GOLD + "sometimes form alliances, joining forces into a",
                GOLD + "larger horde to the terror of civilized lands nearby.",
                GOLD + "When these alliances are sealed by marriages, half-orcs",
                GOLD + "are born. Some half-orcs rise to become proud chiefs of",
                GOLD + "orc tribes, their human blood giving them an edge ",
                GOLD + "over their full-blooded orc rivals. Some venture into",
                GOLD + "the world to prove their worth among humans and other",
                GOLD + "more civilized races. Many of these become adventurers,",
                GOLD + "achieving greatness for their mighty deeds and notoriety",
                GOLD + "for their barbaric customs and savage fury."
        ));
        getInventory().setItem(7, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Tiefling",
                GOLD + "To be greeted with stares and whispers, to suffer ",
                GOLD + "violence and insult on the street, to see mistrust and ",
                GOLD + "fear in every eye: this is the lot of the Tiefling. And ",
                GOLD + "to twist the knife, Tieflings know that this is because ",
                GOLD + "a pact struck generations ago infused the essence of ",
                GOLD + "Asmodeus—overlord of the Nine Hells—into their bloodline.",
                GOLD + "Their appearance and their nature are not their fault but",
                GOLD + "the result of ancient sin, for which they and their children",
                GOLD + "and their children’s children will always be held accountable."
        ));
        getInventory().setItem(8, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("91bc471e-d852-4160-84ef-5ef7a3d60cfa")),
                WHITE + "Storaman",
                GOLD + "With heavy set, broad features, the Dwarven ",
                GOLD + "admixture in the men of Stora is clear from a cursory ",
                GOLD + "glance. Stouter than most humans, though taller than ",
                GOLD + "most Dwarves, they make an odd sight indeed for the ",
                GOLD + "unaware merchant who comes to Brochvik for trade. ",
                GOLD + "Xenophobic and distrustful to outsiders, the Storans ",
                GOLD + "are highly superstitious and traditional people and ",
                GOLD + "are stubborn in their thinking and beliefs. ",
                GOLD + "Most (but not all) Storamen have a distaste for ",
                GOLD + "arcane magic and are distrustful of any Wizards, ",
                GOLD + "Bards and Sorcerors who find their way to Nyrheim."
        ));
    }

    @Override
    public void onClick(Player player, int slot) {
        PenRaceService raceService = plugin.getServices().get(PenRaceService.class);
        Race dwarf = raceService.getRace("DWARF");
        Race elf = raceService.getRace("ELF");
        Race halfling = raceService.getRace("HALFLING");
        Race human = raceService.getRace("HUMAN");
        Race gnome = raceService.getRace("GNOME");
        Race halfElf = raceService.getRace("HALF-ELF");
        Race halfOrc = raceService.getRace("HALF-ORC");
        Race tiefling = raceService.getRace("TIEFLING");
        Race storaman = raceService.getRace("STORAMAN");
        PenPlayerService playerService = plugin.getServices().get(PenPlayerService.class);
        PenCharacterService characterService = plugin.getServices().get(PenCharacterService.class);
        PenPlayer penPlayer = playerService.getPlayer(player);
        PenCharacter character = characterService.getActiveCharacter(penPlayer);
        if (character == null) {
            player.closeInventory();
            player.sendMessage(RED + "You do not have an active character.");
            return;
        }
        if (baseRace == null) {
            switch (slot) {
                case 0:
                    baseRace = dwarf;
                    initializeDwarfSubraceItems();
                    break;
                case 1:
                    baseRace = elf;
                    initializeElfSubraceItems();
                    break;
                case 2:
                    baseRace = halfling;
                    initializeHalflingSubraceItems();
                    break;
                case 3:
                    setRace(player, characterService, character, human);
                    break;
                case 4:
                    baseRace = gnome;
                    initializeGnomeSubraceItems();
                    break;
                case 5:
                    baseRace = halfElf;
                    initializeHalfElfSubraceItems();
                    break;
                case 6:
                    setRace(player, characterService, character, halfOrc);
                    break;
                case 7:
                    baseRace = tiefling;
                    initializeTieflingSubraceItems();
                    break;
                case 8:
                    setRace(player, characterService, character, storaman);
            }
        } else if (baseRace == dwarf) {
            Race goldDwarf = raceService.getRace("GOLD_DWARF");
            Race shieldDwarf = raceService.getRace("SHIELD_DWARF");
            if (slot == 0) {
                setRace(player, characterService, character, goldDwarf);
            } else if (slot == 1) {
                setRace(player, characterService, character, shieldDwarf);
            }
        } else if (baseRace == elf) {
            Race woodElf = raceService.getRace("WOOD_ELF");
            Race highElf = raceService.getRace("HIGH_ELF");
            if (slot == 0) {
                setRace(player, characterService, character, woodElf);
            } else if (slot == 1) {
                setRace(player, characterService, character, highElf);
            }
        } else if (baseRace == halfling) {
            Race lightfootHalfling = raceService.getRace("LIGHTFOOT_HALFLING");
            Race stoutHalfling = raceService.getRace("STOUT_HALFLING");
            Race ghostwiseHalfling = raceService.getRace("GHOSTWISE_HALFLING");
            if (slot == 0) {
                setRace(player, characterService, character, lightfootHalfling);
            } else if (slot == 1) {
                setRace(player, characterService, character, stoutHalfling);
            } else if (slot == 2) {
                setRace(player, characterService, character, ghostwiseHalfling);
            }
        } else if (baseRace == gnome) {
            Race forestGnome = raceService.getRace("FOREST_GNOME");
            Race rockGnome = raceService.getRace("ROCK_GNOME");
            if (slot == 0) {
                setRace(player, characterService, character, forestGnome);
            } else if (slot == 1) {
                setRace(player, characterService, character, rockGnome);
            }
        } else if (baseRace == halfElf) {
            Race halfMoonElf = raceService.getRace("HALF_MOON_ELF");
            Race halfSunElf = raceService.getRace("HALF_SUN_ELF");
            Race halfWoodElf = raceService.getRace("HALF_WOOD_ELF");
            if (slot == 0) {
                setRace(player, characterService, character, halfMoonElf);
            } else if (slot == 1) {
                setRace(player, characterService, character, halfSunElf);
            } else if (slot == 2) {
                setRace(player, characterService, character, halfWoodElf);
            }
        } else if (baseRace == tiefling) {
            Race infernalAsmodeusTiefling = raceService.getRace("INFERNAL_TIEFLING_ASMODEUS");
            Race infernalBaalzebulTiefling = raceService.getRace("INFERNAL_TIEFLING_BAALZEBUL");
            Race infernalDispaterTiefling = raceService.getRace("INFERNAL_TIEFLING_DISPATER");
            Race infernalFiernaTiefling = raceService.getRace("INFERNAL_TIEFLING_FIERNA");
            Race infernalGlasyaTiefling = raceService.getRace("INFERNAL_TIEFLING_GLASYA");
            Race infernalLevistusTiefling = raceService.getRace("INFERNAL_TIEFLING_LEVISTUS");
            Race infernalMammonTiefling = raceService.getRace("INFERNAL_TIEFLING_MAMMON");
            Race infernalMephistophelesTiefling = raceService.getRace("INFERNAL_TIEFLING_MEPHISTOPHELES");
            Race infernalZarielTiefling = raceService.getRace("INFERNAL_TIEFLING_ZARIEL");
            Race abyssalTiefling = raceService.getRace("ABYSSAL_TIEFLING");
            switch (slot) {
                case 0:
                    setRace(player, characterService, character, infernalAsmodeusTiefling);
                    break;
                case 1:
                    setRace(player, characterService, character, infernalBaalzebulTiefling);
                    break;
                case 2:
                    setRace(player, characterService, character, infernalDispaterTiefling);
                    break;
                case 3:
                    setRace(player, characterService, character, infernalFiernaTiefling);
                    break;
                case 4:
                    setRace(player, characterService, character, infernalGlasyaTiefling);
                    break;
                case 5:
                    setRace(player, characterService, character, infernalLevistusTiefling);
                    break;
                case 6:
                    setRace(player, characterService, character, infernalMammonTiefling);
                    break;
                case 7:
                    setRace(player, characterService, character, infernalMephistophelesTiefling);
                    break;
                case 8:
                    setRace(player, characterService, character, infernalZarielTiefling);
                    break;
                case 9:
                    setRace(player, characterService, character, abyssalTiefling);
                    break;
            }
        }
    }

    private void setRace(Player player, PenCharacterService characterService, PenCharacter character, Race race) {
        character.setRace(race);
        characterService.updateCharacter(character);
        player.closeInventory();
        player.sendMessage(GREEN + "Race set to " + race.getName() + ".");
    }

    private void initializeDwarfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("6f827755-6c97-4108-bc90-5fd6dfd2de95")),
                WHITE + "Gold Dwarf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("6f827755-6c97-4108-bc90-5fd6dfd2de95")),
                WHITE + "Shield Dwarf"
        ));
    }

    private void initializeElfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("bf716d95-4c5e-49db-9aef-ed9f9099e9ac")),
                WHITE + "Wood Elf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("9e6ca649-d5e2-4696-b945-f819078f9c8e")),
                WHITE + "High Elf"
        ));
    }

    private void initializeHalflingSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("8fdddb7d-6e68-4069-b87c-4a9659a9b782")),
                WHITE + "Lightfoot Halfling"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("8fdddb7d-6e68-4069-b87c-4a9659a9b782")),
                WHITE + "Stout Halfling"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("8fdddb7d-6e68-4069-b87c-4a9659a9b782")),
                WHITE + "Ghostwise Halfling"
        ));
    }

    private void initializeGnomeSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("a1326407-6584-449b-a404-1fba57b12f86")),
                WHITE + "Forest Gnome"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("a1fbd84a-0c2b-4508-86c0-194471c183a1")),
                WHITE + "Rock Gnome"
        ));
    }

    private void initializeHalfElfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("536c4a27-c503-48f3-b5c0-1168ccba50c7")),
                WHITE + "Half-Moon-Elf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("536c4a27-c503-48f3-b5c0-1168ccba50c7")),
                WHITE + "Half-Sun-Elf"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("bf716d95-4c5e-49db-9aef-ed9f9099e9ac")),
                WHITE + "Half-Wood-Elf"
        ));
    }

    private void initializeTieflingSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Asmodeus"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Baalzebul"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Dispater"
        ));
        getInventory().setItem(3, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Fierna"
        ));
        getInventory().setItem(4, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Glasya"
        ));
        getInventory().setItem(5, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Levistus"
        ));
        getInventory().setItem(6, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Mammon"
        ));
        getInventory().setItem(7, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Mephistopheles"
        ));
        getInventory().setItem(8, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Zariel"
        ));
        getInventory().setItem(9, stealFaceForGuiItem(
                plugin.getServer().getOfflinePlayer(UUID.fromString("c3c88c33-f305-4c10-9303-ce658b2fbde7")),
                WHITE + "Infernal Tiefling: Abyssal"
        ));
    }

}
