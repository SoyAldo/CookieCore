package me.soyaldo.cookiecore.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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

    public static Component getComponent(String text, String[][] replacements) {
        return getComponent(text, replacements, null);
    }

    public static Component getComponent(String text, CommandSender commandSender) {
        return getComponent(text, new String[][]{}, commandSender);
    }

    public static Component getComponent(String text, String[][] replacements, CommandSender commandSender) {
        if (text.isEmpty()) return Component.empty();
        // Replacing the replacements on te text
        text = TextUtil.replace(text, replacements);
        // Applying the placeholders
        if (commandSender != null) {
            if (commandSender instanceof Player) text = PlaceholderUtil.setPlaceholder((Player) commandSender, text);
        }
        // Returning the minimessage component deserialized.
        return MiniMessage.miniMessage().deserialize(text);
    }

    public static String legacyProcess(String text) {
        return legacyProcess(getComponent(text));
    }

    public static String legacyProcess(String text, String[][] replacements) {
        return legacyProcess(getComponent(text, replacements));
    }

    public static String legacyProcess(String text, CommandSender commandSender) {
        return legacyProcess(getComponent(text, new String[][]{}, commandSender));
    }

    public static String legacyProcess(String text, String[][] replacements, CommandSender commandSender) {
        return legacyProcess(getComponent(text, replacements, commandSender));
    }

    public static String legacyProcess(Component component) {
        return LegacyComponentSerializer.builder()
                .character('§')
                .hexColors()
                .hexCharacter('§')
                .build()
                .serialize(component);
    }

}