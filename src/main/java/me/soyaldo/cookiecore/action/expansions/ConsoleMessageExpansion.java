package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.ConsoleMessageAction;

import java.util.HashMap;

public class ConsoleMessageExpansion extends ActionExpansion {

    public ConsoleMessageExpansion(ActionManager actionManager) {
        super(actionManager, "console-message");
    }

    @Override
    public Action getAction() {
        return new ConsoleMessageAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        return action;
    }

}