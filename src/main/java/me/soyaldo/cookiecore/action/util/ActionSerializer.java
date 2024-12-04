package me.soyaldo.cookiecore.action.util;

import me.soyaldo.cookiecore.action.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionSerializer {

    public static String process(Action action) {
        List<String> serializedElements = new ArrayList<>();
        // Add action type
        serializedElements.add("[" + action.getType() + "]");
        // Add global properties
        if (action.isGlobal()) serializedElements.add("[global]");
        if (action.isAsync()) serializedElements.add("[async]");
        if (action.getDelay() > 0) serializedElements.add("[delay:" + action.getDelay() + "]");
        if (!action.getPermission().isEmpty()) serializedElements.add("[permission:" + action.getPermission() + "]");
        // Add extra properties
        String extraProperties = action.onSerializeProperties();
        if (!extraProperties.isEmpty()) serializedElements.add(extraProperties);
        // Add value
        if (!action.getValue().isEmpty()) serializedElements.add(action.getValue());
        // Return the serialized elements with space
        return String.join(" ", serializedElements);
    }

    public static List<String> process(List<Action> actions) {
        List<String> serializedActions = new ArrayList<>();
        for (Action action : actions) {
            serializedActions.add(process(action));
        }
        return serializedActions;
    }

}