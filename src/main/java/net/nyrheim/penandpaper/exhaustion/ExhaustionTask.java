package net.nyrheim.penandpaper.exhaustion;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.database.table.CharacterTable;
import org.bukkit.scheduler.BukkitRunnable;

public final class ExhaustionTask extends BukkitRunnable {

    private final PenAndPaper plugin;

    public ExhaustionTask(PenAndPaper plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getDatabase().getTable(CharacterTable.class).updateExhaustion();
    }
}
