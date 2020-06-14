package net.nyrheim.penandpaper.player;

import net.nyrheim.penandpaper.PenAndPaper;
import org.bukkit.OfflinePlayer;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static java.util.logging.Level.SEVERE;

public final class PenPlayer {

    private final PenAndPaper plugin;
    private PlayerId playerId;
    private final PlayerUUID playerUUID;
    private byte[] passwordHash;
    private byte[] passwordSalt;

    public PenPlayer(PenAndPaper plugin,
                     PlayerId playerId,
                     PlayerUUID playerUUID,
                     byte[] passwordHash,
                     byte[] passwordSalt) {
        this.plugin = plugin;
        this.playerId = playerId;
        this.playerUUID = playerUUID;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public PenPlayer(PenAndPaper plugin, PlayerId playerId, OfflinePlayer player, byte[] passwordHash, byte[] passwordSalt) {
        this(plugin, playerId, new PlayerUUID(player.getUniqueId()), passwordHash, passwordSalt);
    }

    public PenPlayer(PenAndPaper plugin, OfflinePlayer player) {
        this(plugin, new PlayerId(0), player, null, null);
    }

    public PenPlayer(PenAndPaper plugin, PlayerUUID playerUUID) {
        this(plugin, new PlayerId(0), playerUUID, null, null);
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public PlayerUUID getPlayerUUID() {
        return playerUUID;
    }

    public OfflinePlayer getPlayer() {
        return plugin.getServer().getOfflinePlayer(getPlayerUUID().getValue());
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPassword(String password) {
        if (password == null) {
            passwordHash = null;
            passwordSalt = null;
            return;
        }
        SecureRandom random = new SecureRandom();
        passwordSalt = new byte[16];
        random.nextBytes(passwordSalt);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordSalt, 65536, 128);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        } catch (NoSuchAlgorithmException exception) {
            plugin.getLogger().log(SEVERE, "Failed to find password hashing algorithm", exception);
            return;
        }
        try {
            passwordHash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException exception) {
            plugin.getLogger().log(SEVERE, "Invalid key spec", exception);
        }
    }

    public boolean checkPassword(String password) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordSalt, 65536, 128);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        } catch (NoSuchAlgorithmException exception) {
            plugin.getLogger().log(SEVERE, "Failed to find password hashing algorithm", exception);
            return false;
        }
        try {
            return Arrays.equals(passwordHash, factory.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException exception) {
            plugin.getLogger().log(SEVERE, "Invalid key spec", exception);
            return false;
        }
    }

}
