package me.soyaldo.cookiecore.action.actions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.utils.AdventureUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ConsoleMessageAction extends Action {

    public ConsoleMessageAction(ActionManager actionManager, String type) {
        super(actionManager, type);
    }

    @Override
    public void onExecute(Player player, String[][] replacements) {
        // Getting the component
        Component component = AdventureUtil.getComponent(getValue(), replacements, player);
        // Generating the audience
        Audience audience = BukkitAudiences.create(getActionManager().getJavaPlugin()).console();
        // Send the message
        audience.sendMessage(component);
    }

}