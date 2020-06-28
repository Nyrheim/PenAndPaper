package net.nyrheim.penandpaper.test.character;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.character.PenCharacter;
import net.nyrheim.penandpaper.clazz.PenClass;
import net.nyrheim.penandpaper.player.PlayerId;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static net.nyrheim.penandpaper.experience.ExperienceLookupTable.getExperienceRequiredForLevel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PenCharacterTests {

    @Test
    public void testGetMaxHPOneClass() {
        PenCharacter character = createCharacter();
        character.addClass(PenClass.BARBARIAN);
        assertEquals(12, character.getMaxHP());
    }

    @Test
    public void testGetMaxHPTwoClasses() {
        PenCharacter character = createCharacter();
        character.setExperience(getExperienceRequiredForLevel(2));
        character.addClass(PenClass.BARBARIAN);
        character.addClass(PenClass.BARD);
        assertEquals(17, character.getMaxHP());
    }

    @NotNull
    private PenCharacter createCharacter() {
        PenAndPaper plugin = mock(PenAndPaper.class);
        World world = mock(World.class);
        when(world.getSpawnLocation()).thenReturn(new Location(world, 0, 0, 0));
        Server server = mock(Server.class);
        when(server.getWorlds()).thenReturn(Collections.singletonList(world));
        when(plugin.getServer()).thenReturn(server);
        return new PenCharacter(
                plugin,
                new PlayerId(0)
        );
    }

}
