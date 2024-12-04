package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.PlayerActionBarAction;

import java.util.HashMap;

public class PlayerActionBarExpansion extends ActionExpansion {

    public PlayerActionBarExpansion(ActionManager actionManager) {
        super(actionManager, "player-action-bar");
    }

    @Override
    public Action getAction() {
        return new PlayerActionBarAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        return action;
    }

}