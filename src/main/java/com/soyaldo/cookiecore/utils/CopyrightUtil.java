package com.soyaldo.cookiecore.utils;

import com.soyaldo.cookiecore.messenger.Messenger;
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
        versionStatus.add("&6»");
        versionStatus.add("&6» &f" + plugin.getName() + " " + status);
        versionStatus.add("&6»");
        versionStatus.add("&6» &fVersion:&e " + plugin.getDescription().getVersion());
        versionStatus.add("&6» &fAuthor:&e SoyAldo");
        versionStatus.add("&6» &fWebsite:&e https://soyaldo.com/plugins/" + plugin.getName().toLowerCase());
        versionStatus.add("&6»");
        versionStatus.add("&6» &fCoded with love <3");
        versionStatus.add("&6»");
        Messenger.sendRaw(commandSender, versionStatus);
    }

}