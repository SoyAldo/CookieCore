package me.soyaldo.cookiecore.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;

public class AdventureUtil {

    public static Duration parseDuration(int ticks) {
        return Duration.ofMillis((ticks * 1000L) / 20);
    }

    public static Component getComponent(String text) {
        return getComponent(text, new String[][]{}, null);
    }

    public static Component getComponent(String text, Player player) {
        return getComponent(text, new String[][]{}, player);
    }

    public static Component getComponent(String text, String[][] replacements, Player player) {
        return getComponent(text, replacements, (CommandSender) player);
    }

    public static Component getComponent(String text, String[][] replacements, CommandSender commandSender) {
        if (text.isEmpty()) return Component.empty();
        // Replacing the replacements on te text
        text = TextUtil.replace(text, replacements);
        // Applying the placeholders
        if (commandSender instanceof Player) text = PlaceholderUtil.setPlaceholder((Player) commandSender, text);
        // Returning the minimessage component deserialized.
        return MiniMessage.miniMessage().deserialize(text);
    }

}