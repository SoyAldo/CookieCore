package com.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
public abstract class ActionExpansion {

    private final ActionManager actionManager;
    public final String name;

    public abstract Action getAction();

    public abstract Action processProperties(Action action, HashMap<String, Object> deserialized);

    public Action buildAction(HashMap<String, Object> deserialized) {
        // Generate action
        Action action = getAction();
        // Process default properties
        if (deserialized.containsKey("value")) {
            action.setValue(String.valueOf(deserialized.get("value")));
        }
        if (deserialized.containsKey("global")) {
            action.setGlobal(true);
        }
        if (deserialized.containsKey("async")) {
            action.setAsync(true);
        }
        if (deserialized.containsKey("delay")) {
            try {
                int delay = Integer.parseInt(String.valueOf(deserialized.get("delay")));
                action.setDelay(delay);
            } catch (NumberFormatException ignored) {
            }
        }
        if (deserialized.containsKey("permission")) {
            action.setPermission(String.valueOf(deserialized.get("permission")));
        }
        // Process expansion properties
        action = processProperties(action, deserialized);
        // Return the action
        return action;
    }

}