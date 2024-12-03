package com.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Action {

    private final ActionManager actionManager;
    private final String type;
    private String value = "";
    private boolean global = false;
    private boolean async = false;
    private int delay = 0;
    private String permission = "";

    public abstract void onExecute(Player player, String[][] replacements);

    public String onSerializeProperties() {
        return "";
    }

    public void execute(Player player, String[][] replacements) {
        // Getting the JavaPlugin and the BukkitScheduler.
        JavaPlugin javaPlugin = actionManager.getJavaPlugin();
        BukkitScheduler bukkitScheduler = javaPlugin.getServer().getScheduler();
        // If the action is global.
        if (global) {
            for (Player globalPlayer : javaPlugin.getServer().getOnlinePlayers()) {
                // If the permission not is empty and the player and the player not have the permission.
                if (!permission.isEmpty() && !globalPlayer.hasPermission(permission)) {
                    continue;
                }
                // If the action is asynchronously.
                if (async) {
                    bukkitScheduler.runTaskLaterAsynchronously(javaPlugin, () -> onExecute(globalPlayer, replacements), delay);
                } else {
                    bukkitScheduler.runTaskLater(javaPlugin, () -> onExecute(globalPlayer, replacements), delay);
                }
            }
        } else {
            // If the permission not is empty and the player and the player not have the permission.
            if (!permission.isEmpty() && !player.hasPermission(permission)) {
                return;
            }
            // If the action is asynchronously.
            if (async) {
                bukkitScheduler.runTaskLaterAsynchronously(javaPlugin, () -> onExecute(player, replacements), delay);
            } else {
                bukkitScheduler.runTaskLater(javaPlugin, () -> onExecute(player, replacements), delay);
            }
        }
    }

    public String serialize() {
        String result = "[" + type + "]";

        String extraProperties = onSerializeProperties();

        String properties = "";
        if (global) {
            properties = properties + " [global]";
        }

        if (async) {
            properties = properties + " [async]";
        }

        if (delay > 0) {
            properties = properties + " [delay:" + delay + "]";
        }

        if (!permission.isEmpty()) {
            properties = properties + " [permission:" + permission + "]";
        }

        if (!properties.isEmpty()) {
            result = result + properties;
        }

        if (!extraProperties.isEmpty()) {
            result = result + " " + extraProperties;
        }

        if (!value.isEmpty()) {
            result = result + " " + value;
        }

        return result;
    }

}