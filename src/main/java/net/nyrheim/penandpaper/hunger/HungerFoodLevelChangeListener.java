package net.nyrheim.penandpaper.hunger;

import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.FISH;
import static net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType.MEAT;
import static org.bukkit.ChatColor.GRAY;

public class HungerFoodLevelChangeListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        HumanEntity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        if (event.getFoodLevel() > player.getFoodLevel()) {
            ItemStack item = event.getItem();
            if (item != null) {
                PenItemStack penItemStack = PenItemStack.fromItemStack(event.getItem());
                if (penItemStack == null) {
                    event.setFoodLevel(player.getFoodLevel());
                    return;
                }
                ItemType type = penItemStack.getType();
                if (type instanceof AdventuringGearType) {
                    AdventuringGearType adventuringGearType = (AdventuringGearType) type;
                    event.setFoodLevel(player.getFoodLevel() + adventuringGearType.getHunger());
                    if (type == MEAT || type == FISH) {
                        player.sendMessage(GRAY + "You feel like someone is glaring at you, judging you for what you " +
                                "just did, but you look around and there's nobody there.");
                    }
                } else {
                    event.setFoodLevel(player.getFoodLevel());
                }
            }
        } else {
            if (!player.isSprinting()) event.setCancelled(true);
        }
    }

}
