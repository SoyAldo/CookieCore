package com.soyaldo.cookiecore.action;

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

    }

    public void reload() {

    }

    public boolean existExpansion(String name) {
        return expansions.containsKey(name);
    }

    public void addExpansion(ActionExpansion actionExpansion) {
        expansions.put(actionExpansion.getName(), actionExpansion);
    }

    public void removeExpansion(String name) {
        expansions.remove(name);
    }

    public ActionExpansion getExpansion(String name) {
        return expansions.get(name);
    }

    /**
     * Generate an Action from action syntax
     * @param syntax The action syntax
     * @return The Action generated or null if an error ocurres
     */
    public Action generateAction(String syntax) {
        HashMap<String, Object> properties = deserialize(syntax);
        return null;
    }

    public HashMap<String, Object> deserialize(String format) {
        HashMap<String, Object> deserialized = new HashMap<>();
        deserialized.put("global", false);
        deserialized.put("permission", "");
        deserialized.put("async", false);
        deserialized.put("delay", 0);

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