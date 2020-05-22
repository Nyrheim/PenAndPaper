package com.github.liamvii.penandpaper.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ItemStackUtils {

    private ItemStackUtils() {}

    public static byte[] serializeItemStackArray(ItemStack[] inventoryContents) throws IOException {
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutput = new BukkitObjectOutputStream(byteArrayOutput);
        bukkitObjectOutput.writeObject(inventoryContents);
        return byteArrayOutput.toByteArray();
    }

    public static ItemStack[] deserializeItemStackArray(byte[] serialized) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(serialized);
        BukkitObjectInputStream bukkitObjectInput = new BukkitObjectInputStream(byteArrayInput);
        return ((ItemStack[]) bukkitObjectInput.readObject());
    }

    public static byte[] serializeItemStack(ItemStack inventoryContents) throws IOException {
        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
        BukkitObjectOutputStream bukkitObjectOutput = new BukkitObjectOutputStream(byteArrayOutput);
        bukkitObjectOutput.writeObject(inventoryContents);
        return byteArrayOutput.toByteArray();
    }

    public static ItemStack deserializeItemStack(byte[] serialized) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(serialized);
        BukkitObjectInputStream bukkitObjectInput = new BukkitObjectInputStream(byteArrayInput);
        return ((ItemStack) bukkitObjectInput.readObject());
    }

}
