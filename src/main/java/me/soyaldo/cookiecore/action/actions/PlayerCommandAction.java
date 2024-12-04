package me.soyaldo.cookiecore.action.actions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.utils.PlaceholderUtil;
import me.soyaldo.cookiecore.utils.TextUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class PlayerCommandAction extends Action {

    public PlayerCommandAction(ActionManager actionManager, String type) {
        super(actionManager, type);
    }

    @Override
    public void onExecute(Player player, String[][] replacements) {
        // Get the message
        String message = getValue();
        // Replacements
        message = TextUtil.replace(message, replacements);
        // PlaceholderAPI
        message = PlaceholderUtil.setPlaceholder(player, message);
        // Generating the audience
        Audience audience = BukkitAudiences.create(getActionManager().getJavaPlugin()).console();
        // Generating the component
        Component componenteLegacy = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        String legacySerialized = MiniMessage.miniMessage().serialize(componenteLegacy);
        Component component = MiniMessage.miniMessage().deserialize(legacySerialized);
        String command = MiniMessage.miniMessage().serialize(component);
        // Sending the command
        getActionManager().getJavaPlugin().getServer().dispatchCommand(player, command);
    }

}