package me.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.soyaldo.cookiecore.action.expansions.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Getter
public class ActionManager {

    private final JavaPlugin javaPlugin;
    private final HashMap<String, ActionExpansion> expansions = new HashMap<>();

    public void register() {
        // Add default expansions
        addExpansion(new ConsoleCommandExpansion(this));
        addExpansion(new ConsoleMessageExpansion(this));
        addExpansion(new PlayerActionBarExpansion(this));
        addExpansion(new PlayerCommandExpansion(this));
        addExpansion(new PlayerMessageExpansion(this));
        addExpansion(new PlayerTitleExpansion(this));
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
     * @param actionSyntax The action syntax
     * @return The Action generated or null if an error ocurres
     */
    public Action generateAction(String actionSyntax) {
        // Deserialize the action syntax
        HashMap<String, Object> deserialized = deserialize(actionSyntax);
        // If the deserialized has the type
        if (deserialized.containsKey("type")) {
            // Getting the type from deserialized
            String type = String.valueOf(deserialized.get("type"));
            // Getting the action expansion
            ActionExpansion actionExpansion = expansions.get(type);
            // If the action expansion is not null
            if (actionExpansion != null) {
                // Return the action built from the action expansion
                return actionExpansion.buildAction(deserialized);
            }
        }
        // Return null
        return null;
    }

    public HashMap<String, Object> deserialize(String actionSyntax) {
        HashMap<String, Object> result = new HashMap<>();
        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(actionSyntax);
        String type = null;
        String value = actionSyntax;
        while (matcher.find()) {
            String attribute = matcher.group(1);
            if (type == null) {
                type = attribute;
                result.put("type", type);
            } else {
                String[] parts = attribute.split(":", 2);
                if (parts.length == 2) {
                    result.put(parts[0], parts[1]);
                } else {
                    result.put(attribute, true);
                }
            }
            value = value.replace("[" + attribute + "]", "").trim();
        }
        result.put("value", value);
        return result;
    }

}