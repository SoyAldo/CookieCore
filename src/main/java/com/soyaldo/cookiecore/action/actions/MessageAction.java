package com.soyaldo.cookiecore.action.actions;

import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.utils.PlaceholderUtil;
import com.soyaldo.cookiecore.utils.TextUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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

        // Generating the audience.
        Audience audience = BukkitAudiences.create(getActionManager().getJavaPlugin()).sender(player);

        // Generating the component.
        Component component = MiniMessage.miniMessage().deserialize(message);

        // Send the message.
        audience.sendMessage(component);
    }

    @Override
    public String onSerializeProperties() {
        return "";
    }

}