package com.github.liamvii.penandpaper;

import com.github.liamvii.penandpaper.commands.CharacterCommandHandler;
import com.github.liamvii.penandpaper.commands.EXPCommandHandler;
import com.github.liamvii.penandpaper.listener.InventoryListener;
import com.github.liamvii.penandpaper.listener.PlayerListener;
import com.github.liamvii.penandpaper.utils.ConnectionManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* Pen and Paper's main class.
Please avoid declaring unnecessary instances of this class, and follow best practice wherever possible throughout.
API: Spigot 1.14.4.
30/04/2020
 */

// Empty slots (charSlot, jobID, etc.) are denoted by a value of -1 to make querying the database easier.
// The default 'Active' character for a player is -2, denoting no active character. This largely applies to new players, or those
// who have deleted their only character.

public class Pen extends JavaPlugin {

    public FileConfiguration config = this.getConfig();

    public static Map<Player, LinkedList<String>> answers = new HashMap<>();

    private Pen plugin;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getCommand("soul").setExecutor(new CharacterCommandHandler());
        this.getCommand("exp").setExecutor(new EXPCommandHandler());
    }

    @Override
    public void onDisable() {
    }

    public static void addAnswer(Player player, String answer) {
        if (answers.containsKey(player)) {
            answers.get(player).add(answer);
        }
        else {
            answers.put(player, new LinkedList<>(Collections.singleton(answer)));
        }
    }

    public static LinkedList<String> getAnswers(Player player) {
        return answers.get(player);
    }


}
