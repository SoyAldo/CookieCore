package me.soyaldo.cookiecore.action;

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
            // If the action is asynchronously.
            if (async) {
                bukkitScheduler.runTaskLaterAsynchronously(javaPlugin, () -> {
                    for (Player globalPlayer : javaPlugin.getServer().getOnlinePlayers()) {
                        // If the permission not is empty and the player and the player not have the permission.
                        if (!permission.isEmpty() && !globalPlayer.hasPermission(permission)) {
                            continue;
                        }
                        onExecute(globalPlayer, replacements);
                    }
                }, delay);
            } else {
                bukkitScheduler.runTaskLater(javaPlugin, () -> {
                    for (Player globalPlayer : javaPlugin.getServer().getOnlinePlayers()) {
                        // If the permission not is empty and the player and the player not have the permission.
                        if (!permission.isEmpty() && !globalPlayer.hasPermission(permission)) {
                            continue;
                        }
                        onExecute(globalPlayer, replacements);
                    }
                }, delay);
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

}