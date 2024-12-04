package com.soyaldo.cookiecore.action;

import com.soyaldo.cookiecore.action.expansions.PlayerMessageExpansion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
public class ActionManager {

    private final JavaPlugin javaPlugin;
    private final HashMap<String, ActionExpansion> expansions = new HashMap<>();

    public void register() {
        // Add default expansions
        expansions.put("player-message", new PlayerMessageExpansion(this));
    }

    public void reload() {

    }

    /**
     * Add a new action expansion in the manager
     *
     * @param actionExpansion The action expansion
     */
    public void addExpansion(ActionExpansion actionExpansion) {
        expansions.put(actionExpansion.getName(), actionExpansion);
    }

    /**
     * Verify if exist a specific action expansion
     *
     * @param actionExpansionName The action expansion name
     * @return true if exist of false if not
     */
    public boolean existExpansion(String actionExpansionName) {
        return expansions.containsKey(actionExpansionName);
    }

    /**
     * Remove a specific action expansion
     *
     * @param actionExpansionName The action expansion name
     */
    public void removeExpansion(String actionExpansionName) {
        expansions.remove(actionExpansionName);
    }

    /**
     * Get a specific action expansion
     *
     * @param actionExpansionName The action expansion name
     * @return The action expansion if exist of null if not
     */
    public ActionExpansion getExpansion(String actionExpansionName) {
        return expansions.get(actionExpansionName);
    }

    /**
     * Generate an Action from action syntax
     *
     * @param syntax The action syntax
     * @return The Action generated or null if an error ocurres
     */
    public Action generateAction(String syntax) {
        HashMap<String, Object> properties = deserialize(syntax);
        return null;
    }

    public HashMap<String, Object> deserialize(String format) {
        HashMap<String, Object> deserialized = new HashMap<>();

        while (format.split(" ")[0].startsWith("[") && format.split(" ")[0].endsWith("]")) {
            String property = format.split(" ")[0];
            property = property.substring(1, property.length() - 1);
            if (property.contains(":")) {
                String key = property.split(":")[0];
                String value = property.substring(key.length() + 1);
                // Delay
                if (key.equals("delay")) {
                    try {
                        int delay = Integer.parseInt(value);
                        deserialized.put("delay", delay);
                    } catch (NumberFormatException e) {
                        deserialized.put("delay", 0);
                    }
                }
                // Permission
                else if (key.equals("permission")) {
                    deserialized.put(key, value);
                }
            } else {
                // Global
                if (property.equals("global")) deserialized.put("global", true);
                    // Async
                else if (property.equals("async")) deserialized.put("async", true);
            }
            format = format.substring(property.length() + 1);
        }

        if (!format.isEmpty()) {
            deserialized.put("value", format);
        }

        return deserialized;
    }

}