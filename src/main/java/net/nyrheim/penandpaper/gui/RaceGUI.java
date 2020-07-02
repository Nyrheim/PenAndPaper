package net.nyrheim.penandpaper.gui;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.character.PenCharacterService;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PenPlayerService;
import net.nyrheim.penandpaper.race.PenRaceService;
import net.nyrheim.penandpaper.race.Race;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public final class RaceGUI extends GUI {

    private final PenAndPaper plugin;

    private Race baseRace = null;

    public RaceGUI(PenAndPaper plugin) {
        super("Race");
        this.plugin = plugin;
    }

    // Put the generated ItemStacks into the inventory.
    public void initializeItems(Player player) {
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5NjY0MTE3OCwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg2YmE1N2E1ZDljMjVlYWY2M2JmODcwYWY2ZWM1ZWQyYTc4NTI5YzZjYjg2YWQxZDM0ZWMxMjZhY2E4NjZhNSIKICAgIH0KICB9Cn0=",
                "e349b9d6-5af4-4e28-8e37-b57c572331e5",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDY5NTAyMSwKICAicHJvZmlsZUlkIiA6ICJhNzdkNmQ2YmFjOWE0NzY3YTFhNzU1NjYxOTllYmY5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwOEJFRDUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjQyYmY1MzQ5MjI1YzhiM2I2NDYzZDk1NDc0ZmYzYjNjODU3MjM1OWI5NzFkMWM3ZThjYjUyMmI1YTJlY2UyZCIKICAgIH0KICB9Cn0=",
                "e6c80e72-1247-46bf-8d41-8826b4545af4",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5OTcxMDU1MCwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQwMzVjZWIwOWEzNDQyY2Y3OTU5OGQwMjI2ZGRmMTE3YzBiZmNhZGU1MGIwNWYwNDY5NjQwOGVmMGM5YTkxNjciCiAgICB9CiAgfQp9",
                "42ba53c6-e118-43fc-859e-d83387c2a2cc",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODIwMTcwOCwKICAicHJvZmlsZUlkIiA6ICI5MWZlMTk2ODdjOTA0NjU2YWExZmMwNTk4NmRkM2ZlNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJoaGphYnJpcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lZTk5ZDllYTQ2Mzc3MWNkZWE2NzQ4YTI0ZDI0OWIzODRmNWExNjAyNjBhMWU0ZWUzNTgzYTQyMzY2NGM0ZDNlIgogICAgfQogIH0KfQ==",
                "3c676e28-5d87-461e-8abd-169730c65003",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDEzNDI2NiwKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ZWQ3Y2NmMWVkYzAzNjEzY2RhZjRiMzZmZTQ2MjE2ZTRhNTMyZWY0NjFjMDBhZTYxMDI1MGM0MWQxNDUxNzg4IgogICAgfQogIH0KfQ==",
                "83e9f6eb-0749-435f-bf09-c956618bf73a",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODI1NDQzOCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNlNTU2OTc0MjY5NjljZTU1ZjAxZTgwZGZkYWE3ZTA1MDAzMDI1NDVkYmU2YTY1MmJkZGI4NWY3YmUzMDk4NjMiCiAgICB9CiAgfQp9",
                "593e1d63-fb28-4ddd-9038-5ef84954e139",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODM1ODU0MSwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ2ZGFlMzk3ZDdkMWQyOThlZWU0MDQ1MTEwMzZjOTEzNGFiMTI2MjJjYWZjMmQ3MDMzMWVkYjU5NmZhNmRjMiIKICAgIH0KICB9Cn0=",
                "f9199c89-b78b-4aa6-a63d-690d01687e0b",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
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
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5OTEyOTY5MiwKICAicHJvZmlsZUlkIiA6ICIwNGI3MDhhMzM1NjY0ZjJmODVlYzVlZWYyN2QxNGRhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJWaW9sZXRza3l6eiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iZWEzYWQwOTg0MWU3ZGRjNzhlZmRmMzBmZjhkMzJjMDYxN2U5OGMxMDkxODkxM2FjNjhlY2Y1Y2E1ZmU2MzQ5IgogICAgfQogIH0KfQ==",
                "ddf2b2d3-a1c5-4949-a653-ae07ddfa58b7",
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
        getInventory().setItem(9, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2ODI5MzA4NSwKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0NTQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZiMmEyNzQzNjM1ZGQzYjU3YWZhMTcxOTdhMWQ1MTI1MGZmZGQxOGNjOTIzZGVlYTg2N2IyYTE2ZDBhMDgwOCIKICAgIH0KICB9Cn0=",
                "2cde8c44-ba6e-4d4d-bdaa-d5d09d2db588",
                WHITE + "Firbolg",
                GOLD + "Advanced Race."
        ));
        getInventory().setItem(10, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Goliath",
                GOLD + "Advanced Race."
        ));
        getInventory().setItem(11, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTA0MTI0NywKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ZjFlYTgwNDc4ZGI2YTU0YTFlZWI2MTg2Nzg2NGIyYjA2NGVhYWU2YWQ0MTVhNzhiODVlZDU5MmJlZDYyNDkiCiAgICB9CiAgfQp9",
                "2ace5598-50d6-4192-a934-8974898e0289",
                WHITE + "Aasimar",
                GOLD + "Advanced Race."
        ));
        getInventory().setItem(12, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTA4NzE1NCwKICAicHJvZmlsZUlkIiA6ICJkNjBmMzQ3MzZhMTI0N2EyOWI4MmNjNzE1YjAwNDhkYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCSl9EYW5pZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThkNmZkYjM2ODhlMzUyMzgxNzA2MTUyYjg5ZWY2M2I4MDNlNWZiZWI2MDNlYjM3Mzg5NjczZjk2MDFjODY3YSIKICAgIH0KICB9Cn0=",
                "c6c19455-9fbc-4332-9839-e54b779f0e3b",
                WHITE + "Dragonborn",
                GOLD + "Advanced Race."
        ));
        getInventory().setItem(13, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTE3NDU4MCwKICAicHJvZmlsZUlkIiA6ICJlM2I0NDVjODQ3ZjU0OGZiOGM4ZmEzZjFmN2VmYmE4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5pRGlnZ2VyVGVzdCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81ODQ2NGRkZWFhNjU1ODYyZjdlYWQyZDQ0MjJmMDIzZGM0ZjhhYTJmY2E0ODU1MmE1OTUwZTFjMjg0NTkwYjYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
                "f8aebc7f-4ca2-46d5-8fad-84e313c4f53e",
                WHITE + "Orc",
                GOLD + "Advanced Race."
        ));
        getInventory().setItem(14, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODcyOTEyNCwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4NTc2NDFjZTcyZWM1NzQ2MDUxNWRjN2ZjMTZjYmNlNjAwNDQxMjY1MDg3YzVkMTlhMzYxMTVjYzQxZWQwZSIKICAgIH0KICB9Cn0=",
                "7e2153ea-f0b5-4341-9910-7903531f1ee2",
                WHITE + "Genasi",
                GOLD + "Advanced Race."
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
        Race aasimar = raceService.getRace("AASIMAR");
        Race goliath = raceService.getRace("GOLIATH");
        Race firbolg = raceService.getRace("FIRBOLG");
        Race genasi = raceService.getRace("GENASI");
        Race dragonborn = raceService.getRace("DRAGONBORN");
        Race orc = raceService.getRace("ORC");
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
                    baseRace = human;
                    initializeHumanSubraceItems();
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
                    baseRace = halfOrc;
                    setRace(player, characterService, character, halfOrc);
                    break;
                case 7:
                    baseRace = tiefling;
                    initializeTieflingSubraceItems();
                    break;
                case 8:
                    baseRace = storaman;
                    setRace(player, characterService, character, storaman);
                    break;
                case 9:
                    baseRace = firbolg;
                    setRace(player, characterService, character, firbolg);
                    break;
                case 10:
                    baseRace = goliath;
                    setRace(player, characterService, character, goliath);
                    break;
                case 11:
                    baseRace = aasimar;
                    initializeAasimarSubraceItems();
                    break;
                case 12:
                    baseRace = dragonborn;
                    setRace(player, characterService, character, dragonborn);
                    break;
                case 13:
                    baseRace = orc;
                    setRace(player, characterService, character, orc);
                    break;
                case 14:
                    baseRace = genasi;
                    initializeGenasiSubraceItems();
                    break;
            }
        } else if (baseRace == dwarf) {
            Race goldDwarf = raceService.getRace("GOLD_DWARF");
            Race shieldDwarf = raceService.getRace("SHIELD_DWARF");
            if (slot == 0) {
                setRace(player, characterService, character, goldDwarf);
            } else if (slot == 1) {
                setRace(player, characterService, character, shieldDwarf);
            }
        }  else if (baseRace == elf) {
            Race woodElf = raceService.getRace("WOOD_ELF");
            Race highElf = raceService.getRace("HIGH_ELF");
            Race eladrin = raceService.getRace("ELADRIN");
            if (slot == 0) {
                setRace(player, characterService, character, woodElf);
            } else if (slot == 1) {
                setRace(player, characterService, character, highElf);
            } else if (slot == 2) {
                setRace(player, characterService, character, eladrin);
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
        } else if (baseRace == human) {
            Race baseHuman = raceService.getRace("BASE_HUMAN");
            Race variantHuman = raceService.getRace("VARIANT_HUMAN");
            if (slot == 0) {
                setRace(player, characterService, character, baseHuman);
            } else if (slot == 1) {
                setRace(player, characterService, character, variantHuman);
            }
        } else if (baseRace == gnome) {
            Race forestGnome = raceService.getRace("FOREST_GNOME");
            Race rockGnome = raceService.getRace("ROCK_GNOME");
            if (slot == 0) {
                setRace(player, characterService, character, forestGnome);
            } else if (slot == 1) {
                setRace(player, characterService, character, rockGnome);
            }
        } else if (baseRace == genasi) {
            Race fireGenasi = raceService.getRace("FIRE_GENASI");
            Race airGenasi = raceService.getRace("AIR_GENASI");
            Race waterGenasi = raceService.getRace("WATER_GENASI");
            Race earthGenasi = raceService.getRace("EARTH_GENASI");
            if (slot == 0) {
                setRace(player, characterService, character, fireGenasi);
            } else if (slot == 1) {
                setRace(player, characterService, character, airGenasi);
            } else if (slot == 2) {
                setRace(player, characterService, character, waterGenasi);
            } else if (slot == 3) {
                setRace(player, characterService, character, earthGenasi);
            }

        } else if (baseRace == aasimar) {
            Race protectorAasimar = raceService.getRace("PROTECTOR_AASIMAR");
            Race fallenAasimar = raceService.getRace("FALLEN_AASIMAR");
            Race scourgeAasimar = raceService.getRace("SCOURGE_AASIMAR");
            if (slot == 0) {
                setRace(player, characterService, character, protectorAasimar);
            } else if (slot == 1) {
                setRace(player, characterService, character, fallenAasimar);
            } else if (slot == 2) {
                setRace(player, characterService, character, scourgeAasimar);
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
        character.setHP(character.getMaxHP());
        characterService.updateCharacter(character);
        player.closeInventory();
        player.sendMessage(GREEN + "Race set to " + race.getName() + ".");
    }

    private void initializeDwarfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5OTc5MzQxOSwKICAicHJvZmlsZUlkIiA6ICIxOTI1MjFiNGVmZGI0MjVjODkzMWYwMmE4NDk2ZTExYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTZXJpYWxpemFibGUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk4NDViMjQ5NGE2ZDExMjY5MGViNTBjMGZhODg1NTdjOGFkZTFkN2JkODVkNzFmOGM1YmUzZWY4ODFiMDQ4ZiIKICAgIH0KICB9Cn0=",
                "ce7f66d5-692b-4543-996c-81e6beff1ad4",
                WHITE + "Gold Dwarf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5NjY0MTE3OCwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg2YmE1N2E1ZDljMjVlYWY2M2JmODcwYWY2ZWM1ZWQyYTc4NTI5YzZjYjg2YWQxZDM0ZWMxMjZhY2E4NjZhNSIKICAgIH0KICB9Cn0=",
                "e349b9d6-5af4-4e28-8e37-b57c572331e5",
                WHITE + "Shield Dwarf"
        ));
    }

    private void initializeElfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5OTg5NDAwOCwKICAicHJvZmlsZUlkIiA6ICI0ZDcwNDg2ZjUwOTI0ZDMzODZiYmZjOWMxMmJhYjRhZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaXJGYWJpb3pzY2hlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzUyMTdlZWQwNDU1YzMzMWUzNzhkNDNkMTlhNWE0YTcyZDQwYzg5NTYyMTc0ZDRkMzI2YWFkZDU2ZmJkMjZlYWMiCiAgICB9CiAgfQp9",
                "f1d6edfe-85ef-45b3-acc5-3b7f35e4e134",
                WHITE + "Wood Elf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODAzMzY2OSwKICAicHJvZmlsZUlkIiA6ICI3M2ZkNzU2NWJkZTY0MGQzYWE4MGUxMWUwMTMwMjc3OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYUJySWVMVnR6IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY2NjRkZjdmYjg1ZWFkMzM2ZDRlNzZkM2NjMmRjYjI0Zjg5NmY2OTAzMWJlYjE1NDY4ZGJjNzViM2JiMTEzMiIKICAgIH0KICB9Cn0=",
                "bff869f0-49d3-4e8e-946b-d8f4acc5b369",
                WHITE + "High Elf"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjE0ODU4NTYwOCwKICAicHJvZmlsZUlkIiA6ICI3M2ZkNzU2NWJkZTY0MGQzYWE4MGUxMWUwMTMwMjc3OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYUJySWVMVnR6IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkZGU4ZmY2NzNlMGI5OTg1NzFkZDdjY2Q5NDdiOTQzZTcxMjJkN2JmYzdiYmFkZjE0N2YxM2ZhZTk0ODkzMDAiCiAgICB9CiAgfQp9",
                "1c59235b-ceae-4887-b466-155ba230b046",
                WHITE + "Eladrin"
        ));
    }

    private void initializeHalflingSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5OTcxMDU1MCwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQwMzVjZWIwOWEzNDQyY2Y3OTU5OGQwMjI2ZGRmMTE3YzBiZmNhZGU1MGIwNWYwNDY5NjQwOGVmMGM5YTkxNjciCiAgICB9CiAgfQp9",
                "42ba53c6-e118-43fc-859e-d83387c2a2cc",
                WHITE + "Lightfoot Halfling"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMTAzNDQ1NSwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzUwYTgzYzQwN2Q5OGQ4NTkzMjI0NzllNzBmNzQwYzNlMzI5MGQ2NTc4N2ExZDYyZTRlYjJlNTQ0YjY0MjY0ZSIKICAgIH0KICB9Cn0=",
                "5bfbd2bc-f3c4-4345-ad10-b9a442dfc8f0",
                WHITE + "Stout Halfling"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMTEyNjg5MSwKICAicHJvZmlsZUlkIiA6ICI3M2ZkNzU2NWJkZTY0MGQzYWE4MGUxMWUwMTMwMjc3OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYUJySWVMVnR6IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U0YzQ3NjQ5OGUwODk5NWE0YzE5ZjM1ZDdmMDVlMjJkZDdiYWExYTc4MDgwMjY5NGViM2RlYWE4YjRmYmU3N2EiCiAgICB9CiAgfQp9",
                "fff66771-faad-477a-9264-eef5481ab924",
                WHITE + "Ghostwise Halfling"
        ));
    }

    private void initializeHumanSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODIwMTcwOCwKICAicHJvZmlsZUlkIiA6ICI5MWZlMTk2ODdjOTA0NjU2YWExZmMwNTk4NmRkM2ZlNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJoaGphYnJpcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lZTk5ZDllYTQ2Mzc3MWNkZWE2NzQ4YTI0ZDI0OWIzODRmNWExNjAyNjBhMWU0ZWUzNTgzYTQyMzY2NGM0ZDNlIgogICAgfQogIH0KfQ==",
                "3c676e28-5d87-461e-8abd-169730c65003",
                WHITE + "Base Human"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODIwMTcwOCwKICAicHJvZmlsZUlkIiA6ICI5MWZlMTk2ODdjOTA0NjU2YWExZmMwNTk4NmRkM2ZlNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJoaGphYnJpcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lZTk5ZDllYTQ2Mzc3MWNkZWE2NzQ4YTI0ZDI0OWIzODRmNWExNjAyNjBhMWU0ZWUzNTgzYTQyMzY2NGM0ZDNlIgogICAgfQogIH0KfQ==",
                "3c676e28-5d87-461e-8abd-169730c65003",
                WHITE + "Variant Human"
        ));
    }

    private void initializeGnomeSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDEzNDI2NiwKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85ZWQ3Y2NmMWVkYzAzNjEzY2RhZjRiMzZmZTQ2MjE2ZTRhNTMyZWY0NjFjMDBhZTYxMDI1MGM0MWQxNDUxNzg4IgogICAgfQogIH0KfQ==",
                "83e9f6eb-0749-435f-bf09-c956618bf73a",
                WHITE + "Forest Gnome"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDA3NDI2MywKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0NTQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU5M2E3YWJlMjU1NDI4ZmM1ZTNiODNkNWM5ODdhNjllM2I0ODczN2MyOGU1Yzg2NTkxZGJhMjQ1NjVmNDZjYiIKICAgIH0KICB9Cn0=",
                "795e810e-f041-4d97-95c8-b11bcc798f72",
                WHITE + "Rock Gnome"
        ));
    }

    private void initializeHalfElfSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDgyMjAyNiwKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RlNWNlZTkxMGM0Njc5OTBkMTVkNDI1MGJlMjU1ZDk2ZDE2MDA5ZWRjNDY5ZjgyYmY2ZmJhMWE4MjBhYWRiYTMiCiAgICB9CiAgfQp9",
                "f6f2341e-ed5c-4d0a-a463-76b7bad9a7f5",
                WHITE + "Half-Moon-Elf"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDY1MDM4OSwKICAicHJvZmlsZUlkIiA6ICJiMGQ0YjI4YmMxZDc0ODg5YWYwZTg2NjFjZWU5NmFhYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5lU2tpbl9vcmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTcxZGU5YjkxNjc2NGRjODViOWVhZjIwZTM2MDdiNDAzNzE4MjVhZjQ2NDUzMWQ4NGY4Nzg4N2EzNjAzZDY5OCIKICAgIH0KICB9Cn0=",
                "cb169859-3e96-423a-8b3e-8b0ec0a411f5",
                WHITE + "Half-Sun-Elf"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjAwMDk0NzA3MCwKICAicHJvZmlsZUlkIiA6ICI1NjY3NWIyMjMyZjA0ZWUwODkxNzllOWM5MjA2Y2ZlOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVJbmRyYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xZTFlZDFjNjQ4MzczZjM1NDYzNDFkNTcwOWVlYzU4ODE5YzFlNTIyZjAyMTIxZWIwZDUzYzNhM2JiYjFmOTQxIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
                "e0cb1069-9dee-4d07-ac28-92bb4d1acdd0",
                WHITE + "Half-Wood-Elf"
        ));
    }

    private void initializeAasimarSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTA0MTI0NywKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ZjFlYTgwNDc4ZGI2YTU0YTFlZWI2MTg2Nzg2NGIyYjA2NGVhYWU2YWQ0MTVhNzhiODVlZDU5MmJlZDYyNDkiCiAgICB9CiAgfQp9",
                "2ace5598-50d6-4192-a934-8974898e0289",
                WHITE + "Protector Aasimar"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTA0MTI0NywKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ZjFlYTgwNDc4ZGI2YTU0YTFlZWI2MTg2Nzg2NGIyYjA2NGVhYWU2YWQ0MTVhNzhiODVlZDU5MmJlZDYyNDkiCiAgICB9CiAgfQp9",
                "2ace5598-50d6-4192-a934-8974898e0289",
                WHITE + "Fallen Aasimar"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MjA2OTA0MTI0NywKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ZjFlYTgwNDc4ZGI2YTU0YTFlZWI2MTg2Nzg2NGIyYjA2NGVhYWU2YWQ0MTVhNzhiODVlZDU5MmJlZDYyNDkiCiAgICB9CiAgfQp9",
                "2ace5598-50d6-4192-a934-8974898e0289",
                WHITE + "Scourge Aasimar"
        ));
    }

    private void initializeGenasiSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODcyOTEyNCwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4NTc2NDFjZTcyZWM1NzQ2MDUxNWRjN2ZjMTZjYmNlNjAwNDQxMjY1MDg3YzVkMTlhMzYxMTVjYzQxZWQwZSIKICAgIH0KICB9Cn0=",
                "7e2153ea-f0b5-4341-9910-7903531f1ee2",
                WHITE + "Fire Genasi"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODcyOTEyNCwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4NTc2NDFjZTcyZWM1NzQ2MDUxNWRjN2ZjMTZjYmNlNjAwNDQxMjY1MDg3YzVkMTlhMzYxMTVjYzQxZWQwZSIKICAgIH0KICB9Cn0=",
                "7e2153ea-f0b5-4341-9910-7903531f1ee2",
                WHITE + "Air Genasi"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODcyOTEyNCwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4NTc2NDFjZTcyZWM1NzQ2MDUxNWRjN2ZjMTZjYmNlNjAwNDQxMjY1MDg3YzVkMTlhMzYxMTVjYzQxZWQwZSIKICAgIH0KICB9Cn0=",
                "7e2153ea-f0b5-4341-9910-7903531f1ee2",
                WHITE + "Water Genasi"
        ));
        getInventory().setItem(3, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODcyOTEyNCwKICAicHJvZmlsZUlkIiA6ICJiNzQ3OWJhZTI5YzQ0YjIzYmE1NjI4MzM3OGYwZTNjNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTeWxlZXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4NTc2NDFjZTcyZWM1NzQ2MDUxNWRjN2ZjMTZjYmNlNjAwNDQxMjY1MDg3YzVkMTlhMzYxMTVjYzQxZWQwZSIKICAgIH0KICB9Cn0=",
                "7e2153ea-f0b5-4341-9910-7903531f1ee2",
                WHITE + "Earth Genasi"
        ));
    }

    private void initializeTieflingSubraceItems() {
        getInventory().clear();
        getInventory().setItem(0, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Asmodeus"
        ));
        getInventory().setItem(1, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Baalzebul"
        ));
        getInventory().setItem(2, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Dispater"
        ));
        getInventory().setItem(3, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Fierna"
        ));
        getInventory().setItem(4, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Glasya"
        ));
        getInventory().setItem(5, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Levistus"
        ));
        getInventory().setItem(6, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Mammon"
        ));
        getInventory().setItem(7, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Mephistopheles"
        ));
        getInventory().setItem(8, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Zariel"
        ));
        getInventory().setItem(9, stealFaceForGuiItem(
                "ewogICJ0aW1lc3RhbXAiIDogMTU5MTk5ODQxMDYwNywKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxMGY1OGMzYjQyMjY0NWRmMzc1OGVjZDU4Y2Q0YTQ3NmQxMjEyYzYxOTdiNjQ5ZmU2Mzk1ZmJhMGU2ZDYwODciCiAgICB9CiAgfQp9",
                "c94e3741-6551-4acc-95ec-7630e2bd7fde",
                WHITE + "Infernal Tiefling: Abyssal"
        ));
    }

}
