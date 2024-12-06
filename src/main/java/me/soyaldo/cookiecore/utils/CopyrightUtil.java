package me.soyaldo.cookiecore.utils;

import me.soyaldo.cookiecore.messenger.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CopyrightUtil {

    public static void sendVersion(JavaPlugin javaPlugin, CommandSender commandSender) {
        sendVersionStatus(javaPlugin, commandSender, "");
    }

    public static void sendVersionStatusFromConsole(JavaPlugin javaPlugin, String status) {
        sendVersionStatus(javaPlugin, javaPlugin.getServer().getConsoleSender(), status);
    }

    public static void sendVersionStatus(JavaPlugin plugin, CommandSender commandSender, String status) {
        List<String> versionStatus = new ArrayList<>();
        versionStatus.add("<gold>»</gold>");
        versionStatus.add("<gold>»</gold> <white>" + plugin.getName() + "</white> " + status);
        versionStatus.add("<gold>»</gold>");
        versionStatus.add("<gold>»</gold> <white>Version:</white> <yellow>" + plugin.getDescription().getVersion() + "</yellow>");
        versionStatus.add("<gold>»</gold> <white>Author:</white> <yellow>SoyAldo</yellow>");
        versionStatus.add("<gold>»</gold> <white>Website:</white> <yellow>https://soyaldo.me/project/</yellow>" + plugin.getName().toLowerCase());
        versionStatus.add("<gold>»</gold>");
        versionStatus.add("<gold>»</gold> <white>Coded with love <3</white>");
        versionStatus.add("<gold>»</gold>");
        Messenger.sendRaw(plugin, commandSender, versionStatus);
    }

}