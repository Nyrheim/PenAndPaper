package com.github.liamvii.penandpaper;

import com.github.liamvii.penandpaper.character.PlayerCharacter;
import com.github.liamvii.penandpaper.character.jobs.Job;
import com.github.liamvii.penandpaper.characterholder.CharacterHolder;
import com.github.liamvii.penandpaper.commands.CharacterCommandHandler;
import com.github.liamvii.penandpaper.commands.EXPCommandHandler;
import com.github.liamvii.penandpaper.listener.InventoryListener;
import com.github.liamvii.penandpaper.listener.PlayerListener;
import com.github.liamvii.penandpaper.utils.ConnectionManager;
import com.github.liamvii.penandpaper.utils.Insert;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.util.*;
import java.util.logging.Logger;

import static com.github.liamvii.penandpaper.utils.CreateTables.*;

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

    // Fetch login information for database from config.yml
    final String username = config.getString("Database Username");
    final String password = config.getString("Database Password");
    final String url = config.getString("Database Path"); //db url through jdbc
    public static Connection connection;

    public static int[] levelEXP = {0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000};

    public static HashMap<Player, CharacterHolder> holders = new HashMap<>();
    public static Map<Integer, PlayerCharacter> characters = new HashMap<>();
    public static Map<Player, LinkedList<String>> answers = new HashMap<>();
    public static Map<Integer, Job> jobs = new HashMap<>();

    private Pen plugin;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        connection = ConnectionManager.connectDB(url, username, password); // connects to db
        createTables();
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getCommand("soul").setExecutor(new CharacterCommandHandler());
        this.getCommand("exp").setExecutor(new EXPCommandHandler());
    }

    @Override
    public void onDisable() {
        ConnectionManager.disconnectDB(connection); // closes the connection
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdfFile.getName() + "has been disabled! Version: " + pdfFile.getVersion());
    }

    private void createTables() {
        if (connection == null) {
            System.out.print("[ERROR]: Database connection is null!");
        } else {
            createCharTable();
            createJobsTable();
            createCharacterHolderTable();
        }
    }

    public static void addHolder(Player player, CharacterHolder holder) {
        if (!holders.containsKey(player)) {
            holders.put(player, holder);
        }
        else {
            holders.remove(player);
            holders.put(player, holder);
        }
    }

    public static CharacterHolder getHolder(Player player) {
        return holders.getOrDefault(player, null);
    }

    public static void addCharacter(int id, PlayerCharacter character) {
        if (!characters.containsKey(id)) {
            characters.put(id, character);
        }
        else {
            characters.remove(id);
            characters.put(id, character);
        }
    }

    public static PlayerCharacter getCharacter(int id) {
        return characters.get(id);
    }

    public static void delCharacter(int id) {
        characters.remove(id);
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

    public static void addJob(int id, Job job) {
        if (!jobs.containsKey(id)) {
            jobs.put(id, job);
        }
        else {
            jobs.remove(id);
            jobs.put(id, job);
        }
    }

    public static Job getJob(int id) {
        return jobs.get(id);
    }

    public static int getLevel(int index) {
        return levelEXP[index];
    }

}
