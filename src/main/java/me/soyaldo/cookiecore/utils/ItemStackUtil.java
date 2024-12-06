package me.soyaldo.cookiecore.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtil {

    /**
     * Check if the ItemStack is valid and contain a valid ItemStack.
     *
     * @return Return true if is valid, or false if not.
     */
    public static boolean isValidItemStack(ItemStack itemStack) {
        // Checking if the ItemStack not is null
        if (itemStack == null) return false;
        // Checking the material
        if (itemStack.getType().equals(Material.AIR)) return false;
        // Return if the ItemMeta not is null
        return itemStack.getItemMeta() != null;
    }

}