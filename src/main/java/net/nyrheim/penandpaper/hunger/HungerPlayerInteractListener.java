package net.nyrheim.penandpaper.hunger;

import net.nyrheim.penandpaper.item.ItemType;
import net.nyrheim.penandpaper.item.PenItemStack;
import net.nyrheim.penandpaper.item.adventuringgear.AdventuringGearType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.GRAY;

public class HungerPlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType().isEdible()) return;
        PenItemStack penItem = PenItemStack.fromItemStack(item);
        if (penItem == null) return;
        ItemType type = penItem.getType();
        if (!(type instanceof AdventuringGearType)) return;
        AdventuringGearType adventuringGearType = (AdventuringGearType) type;
        if (adventuringGearType.getHunger() <= 0) return;
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            event.getPlayer().getInventory().removeItem(item);
        }
        event.getPlayer().setFoodLevel(event.getPlayer().getFoodLevel() + adventuringGearType.getHunger());
        if (type == AdventuringGearType.BUTTER || type == AdventuringGearType.SPRINGBERRY_JAM) {
            event.getPlayer().sendMessage(GRAY + "You feel like someone is glaring at you, judging you for what you " +
                    "just did, but you look around and there's nobody there.");
        }
    }

}
