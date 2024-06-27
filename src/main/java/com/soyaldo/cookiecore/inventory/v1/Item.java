package com.soyaldo.cookiecore.inventory.v1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Item {

    private ItemStack itemStack;
    private final boolean update;
    private final String displayName;
    private final List<String> lore;

    public void updateItem(Player player) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        boolean papi = (player != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);

        String newName = displayName;
        if (papi) newName = PlaceholderAPI.setPlaceholders(player, newName);
        newName = ChatColor.translateAlternateColorCodes('&', newName);

        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            if (papi) line = PlaceholderAPI.setPlaceholders(player, line);
            line = ChatColor.translateAlternateColorCodes('&', line);
            newLore.add(line);
        }

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(newLore);

        itemStack.setItemMeta(itemMeta);
    }

}