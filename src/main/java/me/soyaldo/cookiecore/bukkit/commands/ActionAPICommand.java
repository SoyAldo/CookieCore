package me.soyaldo.cookiecore.bukkit.commands;

import me.soyaldo.cookiecore.CookieCore;
import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.command.Command;
import me.soyaldo.cookiecore.messenger.Messenger;
import org.bukkit.entity.Player;

public class ActionAPICommand extends Command {

    private final CookieCore PLUGIN;
    private final String permission = "cookiecore.actionapi";

    public ActionAPICommand(CookieCore PLUGIN) {
        super("actionapi");
        this.PLUGIN = PLUGIN;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        // Getting the messenger
        Messenger messenger = PLUGIN.getMessenger();
        // Check the permission
        if (!sender.hasPermission(permission)) {
            // Sending the no permission message
            messenger.send(sender, "actionapi.noPermission");
            return;
        }
        // Check the arguments length
        if (args.length == 0) {
            // Sending the empty sub command message
            messenger.send(sender, "actionapi.emptySubCommand");
            return;
        }
        // Getting the sub command
        String subCommand = args[0];
        // Check the sub command
        if (subCommand.equalsIgnoreCase("help")) {
            // Sending the help message
            messenger.send(sender, "actionapi.help");
        } else if (subCommand.equalsIgnoreCase("verify")) {
            // Checking if the arguments has more content than the sub command
            if (args.length < 2) {
                // Sending the empty action syntax message
                messenger.send(sender, "actionapi.emptyActionSyntax");
                return;
            }
            // Obtaining the action syntax
            String actionSyntax = String.join(" ", args).substring(7);
            // Building the action
            ActionManager actionManager = PLUGIN.getActionManager();
            Action action = actionManager.generateAction(actionSyntax);
            // Checking the Action if is null
            if (action == null) {
                // Sending the action syntax error message
                messenger.send(sender, "actionapi.actionSyntaxError");
                return;
            }
            // Executing
            action.execute(sender, new String[][]{});
            // Sending the action verified message
            messenger.send(sender, "actionapi.actionVerified");
        } else {
            // Sending the invalid sub command message
            messenger.send(sender, "actionapi.invalidSubCommand");
        }
    }

    @Override
    public String onPlayerTabComplete(Player requester, int position, String[] previousArguments) {
        if (!requester.hasPermission(permission)) return "";
        if (position == 1) return "help,verify";
        return "";
    }
}