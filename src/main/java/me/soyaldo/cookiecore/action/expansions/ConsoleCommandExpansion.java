package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.ConsoleCommandAction;

import java.util.HashMap;

public class ConsoleCommandExpansion extends ActionExpansion {

    public ConsoleCommandExpansion(ActionManager actionManager) {
        super(actionManager, "console-command");
    }

    @Override
    public Action getAction() {
        return new ConsoleCommandAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        return action;
    }

}