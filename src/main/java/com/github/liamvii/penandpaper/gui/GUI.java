package com.github.liamvii.penandpaper.gui;

import org.bukkit.Material;

public class GUI {

    public static Material getMaterial(String jobName) {
        Material mat = Material.PAPER;
        switch (jobName) {
            case "Fighter":
                mat = Material.IRON_SWORD;
                break;
            case "Ranger":
                mat = Material.BOW;
                break;
            case "Rogue" :
                mat = Material.IRON_AXE;
                break;
            case "Barbarian":
                mat = Material.IRON_AXE;
                break;
            case "Monk":
                mat = Material.IRON_SWORD;
                break;
            case "Bard":
                mat = Material.MUSIC_DISC_MALL;
                break;
            case "Wizard" :
                mat = Material.BOOK;
                break;
            case "Sorcerer":
                mat = Material.BOOK;
                break;
            case "Warlock":
                mat = Material.ENDER_EYE;
                break;
            case "Druid":
                mat = Material.SUNFLOWER;
                break;
            case "Paladin" :
                mat = Material.CHAINMAIL_CHESTPLATE;
                break;
            case "Cleric":
                mat = Material.IRON_CHESTPLATE;
                break;
        }
        return mat;
    }

    public static String getJobItemName(String jobName) {
        String itemName = "default";
        switch (jobName) {
            case "Fighter":
                itemName = "Longsword";
                break;
            case "Ranger":
                itemName = "Short Bow";
                break;
            case "Rogue":
                itemName = "Hunting Knife";
                break;
            case "Barbarian":
                itemName = "Twoheaded Waraxe";
                break;
            case "Monk":
                itemName = "Handsaw";
                break;
            case "Bard":
                itemName = "Lute";
                break;
            case "Wizard":
                itemName = "Tome of Manaflow";
                break;
            case "Sorcerer":
                itemName = "Tome of Fire";
                break;
            case "Warlock":
                itemName = "Patron's Mark";
                break;
            case "Druid":
                itemName = "Nature's Blessing";
                break;
            case "Cleric":
                itemName = "Basic Chainmail";
                break;
            case "Paladin":
                itemName = "English Knight";
                break;
        }
        return itemName;
    }

}
