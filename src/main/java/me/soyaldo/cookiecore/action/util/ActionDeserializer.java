package me.soyaldo.cookiecore.action.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionDeserializer {

    public static HashMap<String, Object> process(String actionSyntax) {
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

    public static List<HashMap<String, Object>> process(List<String> actionSyntaxes) {
        List<HashMap<String, Object>> actionsDeserialized = new ArrayList<>();
        for (String actionSyntax : actionSyntaxes) {
            actionsDeserialized.add(process(actionSyntax));
        }
        return actionsDeserialized;
    }

}