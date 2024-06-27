package com.soyaldo.cookiecore.inventory.v2;

import com.soyaldo.cookiecore.inventory.v2.action.Action;
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

    private final int slot;
    private final int priority;
    private final boolean update;
    private final String displayName;
    private final List<String> lore;
    private ItemStack itemStack;

    private final List<Action> leftActions = new ArrayList<>();
    private final List<Action> leftShiftActions = new ArrayList<>();
    private final List<Action> middleActions = new ArrayList<>();
    private final List<Action> rightActions = new ArrayList<>();
    private final List<Action> rightShiftActions = new ArrayList<>();

    public void updateItem(Player player) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        // display_name
        String newDisplayName = displayName;
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            newDisplayName = PlaceholderAPI.setPlaceholders(player, newDisplayName);
        newDisplayName = ChatColor.translateAlternateColorCodes('&', newDisplayName);
        itemMeta.setDisplayName(newDisplayName);
        // lore
        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            if (player != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                line = PlaceholderAPI.setPlaceholders(player, line);
            }
            line = ChatColor.translateAlternateColorCodes('&', line);
            newLore.add(line);
        }
        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);
    }

}