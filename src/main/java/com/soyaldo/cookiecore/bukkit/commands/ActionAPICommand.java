package com.soyaldo.cookiecore.bukkit.commands;

import com.soyaldo.cookiecore.CookieCore;
import com.soyaldo.cookiecore.action.Action;
import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.command.Command;
import com.soyaldo.cookiecore.messenger.Messenger;
import org.bukkit.entity.Player;

public class ActionAPICommand extends Command {


    /*
    /actionapi help
    /actionapi verify [action-syntax]
     */

    private final CookieCore PLUGIN;

    public ActionAPICommand(CookieCore PLUGIN) {
        super("actionapi");
        this.PLUGIN = PLUGIN;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        // Getting the messenger
        Messenger messenger = PLUGIN.getMessenger();
        // Check the permission
        if (!sender.hasPermission("cookiecore.actionapi")) {
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
            String actionSyntax = String.join(" ", args).substring(17);
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

}