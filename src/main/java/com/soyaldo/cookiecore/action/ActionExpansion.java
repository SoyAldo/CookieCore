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

    public abstract Action processProperties(Action action, HashMap<String, String> properties);

    public Action buildAction(HashMap<String, Object> deserialized) {
        Action action = getAction();
        action = processProperties(action, deserialized);
        return action;
    }

}