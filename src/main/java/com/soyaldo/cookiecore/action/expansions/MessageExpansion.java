package com.soyaldo.cookiecore.action.expansions;

import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.action.ActionExpansion;
import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.action.actions.MessageAction;

import java.util.HashMap;

public class MessageExpansion extends ActionExpansion {

    public MessageExpansion(ActionManager actionManager, String name) {
        super(actionManager, name);
    }

    @Override
    public Action processProperties(Action action, HashMap<String, String> properties) {
        return action;
    }

    @Override
    public Action getAction() {
        return new MessageAction(getActionManager());
    }

}