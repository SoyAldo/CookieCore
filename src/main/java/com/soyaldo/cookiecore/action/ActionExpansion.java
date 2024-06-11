package com.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class ActionExpansion {

    private final ActionManager actionManager;
    public final String name;

    public abstract Action buildAction(String format);

}