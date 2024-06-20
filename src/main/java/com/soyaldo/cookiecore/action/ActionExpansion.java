package com.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
public abstract class ActionExpansion {

    private final ActionManager actionManager;
    public final String name;

    public abstract Action processProperties(Action action, HashMap<String, String> properties);

    public abstract Action getAction();

    public Action buildAction(String format) {
        Action action = getAction();

        return action;
    }

}