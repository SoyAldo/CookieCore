package com.soyaldo.cookiecore.action.expansions;

import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.action.ActionExpansion;
import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.action.actions.MessageAction;

public class MessageExpansion extends ActionExpansion {

    public MessageExpansion(ActionManager actionManager, String name) {
        super(actionManager, name);
    }

    @Override
    public Action buildAction(String format) {
        return new MessageAction(getActionManager());
    }

}