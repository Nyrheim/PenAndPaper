package net.nyrheim.penandpaper.test.player;

import net.nyrheim.penandpaper.PenAndPaper;
import net.nyrheim.penandpaper.player.PenPlayer;
import net.nyrheim.penandpaper.player.PlayerUUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

public final class PlayerTests {

    @Mock private PenAndPaper plugin;

    @Test
    public void testCheckPasswordNoPasswordSet() {
        PenPlayer player = new PenPlayer(
                plugin,
                new PlayerUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        );
        Assertions.assertFalse(player.checkPassword(""));
    }

    @Test
    public void testCheckPasswordHappyPath() {
        PenPlayer player = new PenPlayer(
                plugin,
                new PlayerUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        );
        player.setPassword("Password1!");
        Assertions.assertTrue(player.checkPassword("Password1!"));
    }

    @Test
    public void testCheckPasswordIncorrectPassword() {
        PenPlayer player = new PenPlayer(
                plugin,
                new PlayerUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
        );
        player.setPassword("Password1!");
        Assertions.assertFalse(player.checkPassword("Password2!"));
    }

}
