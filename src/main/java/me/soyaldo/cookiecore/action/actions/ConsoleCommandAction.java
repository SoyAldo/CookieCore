package me.soyaldo.cookiecore.action.actions;

import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.utils.AdventureUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleCommandAction extends Action {

    public ConsoleCommandAction(ActionManager actionManager, String type) {
        super(actionManager, type);
    }

    @Override
    public void onExecute(Player player, String[][] replacements) {
        // Getting the component
        Component component = AdventureUtil.getComponent(getValue(), replacements, player);
        // Serializing the component to string
        String command = MiniMessage.miniMessage().serialize(component);
        // Getting the command sender
        CommandSender commandSender = getActionManager().getJavaPlugin().getServer().getConsoleSender();
        // Sending the command in the main thread
        getActionManager().getJavaPlugin().getServer().getScheduler().runTaskLater(getActionManager().getJavaPlugin(), () -> {
            getActionManager().getJavaPlugin().getServer().dispatchCommand(commandSender, command);
        }, 0L);
    }

}