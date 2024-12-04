package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.PlayerMessageAction;

import java.util.HashMap;

public class PlayerMessageExpansion extends ActionExpansion {

    public PlayerMessageExpansion(ActionManager actionManager) {
        super(actionManager, "player-message");
    }

    @Override
    public Action getAction() {
        return new PlayerMessageAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        return action;
    }

}