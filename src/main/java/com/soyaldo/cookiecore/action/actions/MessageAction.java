package com.soyaldo.cookiecore.action.actions;

import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.utils.MinedownUtil;
import com.soyaldo.cookiecore.utils.PlaceholderUtil;
import com.soyaldo.cookiecore.utils.TextUtil;
import org.bukkit.entity.Player;

public class MessageAction extends Action {

    public MessageAction(ActionManager actionManager) {
        super(actionManager, "message");
    }

    @Override
    public void onExecute(Player player, String[][] replacements) {
        // Get the message
        String message = getValue();

        // Replacements
        message = TextUtil.replace(message, replacements);

        // PlaceholderAPI
        message = PlaceholderUtil.setPlaceholder(player, message);

        // Send message
        player.spigot().sendMessage(MinedownUtil.translate(message));
    }

    @Override
    public String onSerializeProperties() {
        return "";
    }

}