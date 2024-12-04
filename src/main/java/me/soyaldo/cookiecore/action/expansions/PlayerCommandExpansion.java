package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.PlayerCommandAction;

import java.util.HashMap;

public class PlayerCommandExpansion extends ActionExpansion {

    public PlayerCommandExpansion(ActionManager actionManager) {
        super(actionManager, "player-command");
    }

    @Override
    public Action getAction() {
        return new PlayerCommandAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        return action;
    }

}