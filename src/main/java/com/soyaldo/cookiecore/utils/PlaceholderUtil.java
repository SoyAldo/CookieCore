package com.soyaldo.cookiecore.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderUtil {

    public static String setPlaceholder(Player player, String text) {
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return text;
        }
        return PlaceholderAPI.setPlaceholders(player, text);
    }

}