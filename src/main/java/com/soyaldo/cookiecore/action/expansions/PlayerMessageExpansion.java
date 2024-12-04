package com.soyaldo.cookiecore.action.expansions;

import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.action.ActionExpansion;
import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.action.actions.PlayerMessageAction;

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