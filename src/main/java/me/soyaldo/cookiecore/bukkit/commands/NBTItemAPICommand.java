package me.soyaldo.cookiecore.bukkit.commands;

import me.soyaldo.cookiecore.CookieCore;
import me.soyaldo.cookiecore.command.Command;
import me.soyaldo.cookiecore.messenger.Messenger;
import me.soyaldo.cookiecore.nbt.NBTItem;
import me.soyaldo.cookiecore.utils.ItemStackUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class NBTItemAPICommand extends Command {

    private final CookieCore PLUGIN;
    private final String permission = "cookiecore.nbtitemapi";

    public NBTItemAPICommand(CookieCore PLUGIN) {
        super("nbtitemapi");
        this.PLUGIN = PLUGIN;
    }

    @Override
    public void onPlayerExecute(Player sender, String[] args) {
        // Getting the messenger
        Messenger messenger = PLUGIN.getMessenger();
        // Check the permission
        if (!sender.hasPermission(permission)) {
            // Sending the no permission message
            messenger.send(sender, "nbtitemapi.noPermission");
            return;
        }
        // Check the arguments length
        if (args.length == 0) {
            // Sending the empty sub command message
            messenger.send(sender, "nbtitemapi.emptySubCommand");
            return;
        }
        // Getting the sub command
        String subCommand = args[0];
        // Check the sub command
        if (subCommand.equalsIgnoreCase("help")) {
            // Sending the help message
            messenger.send(sender, "nbtitemapi.help");
        } else if (subCommand.equalsIgnoreCase("setboolean")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2].toLowerCase();
                        if (value.equals("true") || value.equals("false")) {
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, value.equals("true"));
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.booleanSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } else {
                            messenger.send(sender, "nbtitemapi.invalidBoolean");
                        }
                    } else {
                        messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.emptyValue");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("getboolean")) {
            // nbtitemapi setstring key value
            if (args.length < 2) {
                messenger.send(sender, "nbtitemapi.emptyKey");
                return;
            }
            ItemStack itemStack = sender.getInventory().getItemInMainHand();
            if (!ItemStackUtil.isValidItemStack(itemStack)) {
                messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                return;
            }
            String key = args[1];

            NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
            boolean value = nbtItem.getBoolean(key);

            messenger.send(sender, "nbtitemapi.booleanView", new String[][]{
                    {"%key%", key},
                    {"%value%", value ? "true" : "false"}
            });
        } else if (subCommand.equalsIgnoreCase("setstring")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                        nbtItem.set(key, value);
                        sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                        messenger.send(sender, "nbtitemapi.stringSet", new String[][]{
                                {"%key%", key},
                                {"%value%", value}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.emptyValue");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("getstring")) {
            // nbtitemapi setstring key value
            if (args.length < 2) {
                messenger.send(sender, "nbtitemapi.emptyKey");
                return;
            }
            ItemStack itemStack = sender.getInventory().getItemInMainHand();
            if (!ItemStackUtil.isValidItemStack(itemStack)) {
                messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                return;
            }
            String key = args[1];

            NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
            String value = nbtItem.getString(key);

            messenger.send(sender, "nbtitemapi.stringView", new String[][]{
                    {"%key%", key},
                    {"%value%", value}
            });
        } else if (subCommand.equalsIgnoreCase("getall")) {
            ItemStack itemStack = sender.getInventory().getItemInMainHand();
            if (!ItemStackUtil.isValidItemStack(itemStack)) {
                messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                return;
            }

            NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
            List<String> keys = nbtItem.getKeys();
            for (String key : keys) {
                messenger.sendRaw(sender, key);
            }
        } else {
            // Sending the invalid sub command message
            messenger.send(sender, "nbtitemapi.invalidSubCommand");
        }
    }

    @Override
    public String onPlayerTabComplete(Player requester, int position, String[] previousArguments) {
        if (!requester.hasPermission(permission)) return "";
        if (position == 1) {
            return "help,setboolean,getboolean,setstring,getstring,getall";
        }
        if (position == 2) {
            if (previousArguments[0].equals("getboolean") || previousArguments[0].equals("getstring")) {
                NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                return String.join(",", nbtItem.getKeys());
            }
        }
        if (position == 3) {
            if (previousArguments[0].equalsIgnoreCase("setboolean")) {
                return "true,false";
            }
        }
        return "";
    }

}