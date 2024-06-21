package com.soyaldo.cookiecore;

import com.soyaldo.cookiecore.command.Command;
import com.soyaldo.cookiecore.messenger.Messenger;
import com.soyaldo.cookiecore.utils.CopyrightUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CookieCoreCommand extends Command {

    private final CookieCore cookieCore;

    public CookieCoreCommand(CookieCore cookieCore) {
        super("cookiecore");
        this.cookieCore = cookieCore;
    }

    private void onExecute(CommandSender sender, String[] args) {
        // Getting the messenger.
        Messenger messenger = cookieCore.getMessenger();
        // If the sender no has the admin permission.
        if (!sender.hasPermission("cookiecore.admin")) {
            // Send message.
            messenger.send(sender, "noPermission");
            return;
        }
        // If the arguments is empty.
        if (args.length == 0) {
            // Send message.
            messenger.send(sender, "emptySubcommand");
            return;
        }
        // Getting the subcommand.
        String subCommand = args[0];
        // Executing depend on the subcommand.
        if (subCommand.equalsIgnoreCase("help")) { // If the subcommand is 'help'
            messenger.send(sender, "help");
        } else if (subCommand.equalsIgnoreCase("reload")) { // If the subcommand is 'reload'
            cookieCore.reload();
            messenger.send(sender, "reloaded");
        } else if (subCommand.equalsIgnoreCase("version")) { // If the subcommand is 'version'
            CopyrightUtil.sendVersion(cookieCore, sender);
        } else { // If the subcommand is any other value.
            messenger.send(sender, "invalidSubcommand", new String[][]{{"%subcommand%", subCommand}});
        }
    }

    public String onTabComplete(CommandSender sender, int position) {
        if (sender.hasPermission("cookiecore.admin")) {
            return "";
        }
        if (position == 1) {
            return "help,reload,version";
        }
        return "";
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        onExecute(sender, args);
    }

    @Override
    public void onConsoleExecute(ConsoleCommandSender sender, String[] args) {
        onExecute(sender, args);
    }

    @Override
    public String onPlayerTabComplete(Player requester, int position, String[] previousArguments) {
        return onTabComplete(requester, position);
    }

    @Override
    public String onConsoleTabComplete(ConsoleCommandSender requester, int position, String[] previousArguments) {
        return onTabComplete(requester, position);
    }

}