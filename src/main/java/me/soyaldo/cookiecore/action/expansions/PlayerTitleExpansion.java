package me.soyaldo.cookiecore.action.expansions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionExpansion;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.action.actions.PlayerTitleAction;

import java.util.HashMap;

public class PlayerTitleExpansion extends ActionExpansion {

    public PlayerTitleExpansion(ActionManager actionManager) {
        super(actionManager, "player-title");
    }

    @Override
    public Action getAction() {
        return new PlayerTitleAction(getActionManager(), getName());
    }

    @Override
    public Action processProperties(Action action, HashMap<String, Object> deserialized) {
        PlayerTitleAction playerTitleAction = (PlayerTitleAction) action;
        // Setting the fade in duration
        if (deserialized.containsKey("fade-in")) {
            try {
                int fadeIn = Integer.parseInt(String.valueOf(deserialized.get("fade-in")));
                playerTitleAction.setFadeIn(fadeIn);
            } catch (NumberFormatException ignored) {
            }
        }
        // Setting the stay duration
        if (deserialized.containsKey("stay")) {
            try {
                int stay = Integer.parseInt(String.valueOf(deserialized.get("stay")));
                playerTitleAction.setStay(stay);
            } catch (NumberFormatException ignored) {
            }
        }
        // Setting the fade out duration
        if (deserialized.containsKey("fade-out")) {
            try {
                int fadeOut = Integer.parseInt(String.valueOf(deserialized.get("fade-out")));
                playerTitleAction.setFadeOut(fadeOut);
            } catch (NumberFormatException ignored) {
            }
        }
        // Setting the title text
        if (deserialized.containsKey("title")) {
            playerTitleAction.setTitle(String.valueOf(deserialized.get("title")));
        }
        // Setting the subtitle text
        if (deserialized.containsKey("subtitle")) {
            playerTitleAction.setSubTitle(String.valueOf(deserialized.get("subtitle")));
        }
        // Returning the action
        return playerTitleAction;
    }

}