package me.soyaldo.cookiecore.bukkit.commands;

import me.soyaldo.cookiecore.CookieCore;
import me.soyaldo.cookiecore.command.Command;
import me.soyaldo.cookiecore.messenger.Messenger;
import me.soyaldo.cookiecore.nbt.DataType;
import me.soyaldo.cookiecore.nbt.NBTItem;
import me.soyaldo.cookiecore.utils.ItemStackUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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
            messenger.send(sender, "nbtitemapi.help");
        } else if (subCommand.equalsIgnoreCase("setbyte")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            byte parsedValue = Byte.parseByte(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.byteSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidByte");
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
        } else if (subCommand.equalsIgnoreCase("getbyte")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isByte(keyName)) {
                        byte value = nbtItem.getByte(keyName);
                        messenger.send(sender, "nbtitemapi.byteView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoByte");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("setdouble")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            double parsedValue = Double.parseDouble(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.doubleSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidDouble");
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
        } else if (subCommand.equalsIgnoreCase("getdouble")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isDouble(keyName)) {
                        double value = nbtItem.getDouble(keyName);
                        messenger.send(sender, "nbtitemapi.doubleView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoDouble");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("setfloat")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            float parsedValue = Float.parseFloat(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.floatSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidFloat");
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
        } else if (subCommand.equalsIgnoreCase("getfloat")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isFloat(keyName)) {
                        float value = nbtItem.getFloat(keyName);
                        messenger.send(sender, "nbtitemapi.floatView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoFloat");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("setlong")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            long parsedValue = Long.parseLong(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.longSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidLong");
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
        } else if (subCommand.equalsIgnoreCase("getlong")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isLong(keyName)) {
                        long value = nbtItem.getLong(keyName);
                        messenger.send(sender, "nbtitemapi.longView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoLong");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("setinteger")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            int parsedValue = Integer.parseInt(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.integerSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidInteger");
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
        } else if (subCommand.equalsIgnoreCase("getinteger")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isInteger(keyName)) {
                        int value = nbtItem.getInteger(keyName);
                        messenger.send(sender, "nbtitemapi.integerView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoInteger");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("setshort")) {
            if (args.length >= 2) {
                if (args.length >= 3) {
                    if (ItemStackUtil.isValidItemStack(sender.getInventory().getItemInMainHand())) {
                        String key = args[1];
                        String value = args[2];
                        try {
                            short parsedValue = Short.parseShort(value);
                            NBTItem nbtItem = new NBTItem(PLUGIN, sender.getInventory().getItemInMainHand());
                            nbtItem.set(key, parsedValue);
                            sender.getInventory().setItemInMainHand(nbtItem.getItemStack());
                            messenger.send(sender, "nbtitemapi.integerSet", new String[][]{
                                    {"%key%", key},
                                    {"%value%", value}
                            });
                        } catch (NumberFormatException e) {
                            messenger.send(sender, "nbtitemapi.invalidInteger");
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
        } else if (subCommand.equalsIgnoreCase("getshort")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isShort(keyName)) {
                        short value = nbtItem.getShort(keyName);
                        messenger.send(sender, "nbtitemapi.shortView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoShort");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
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
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String key = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isString(key)) {
                        String value = nbtItem.getString(key);
                        messenger.send(sender, "nbtitemapi.stringView", new String[][]{
                                {"%key%", key},
                                {"%value%", value}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoString");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
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
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.isBoolean(keyName)) {
                        boolean value = nbtItem.getBoolean(keyName);
                        messenger.send(sender, "nbtitemapi.booleanView", new String[][]{
                                {"%key%", keyName},
                                {"%value%", String.valueOf(value)}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.keyNoBoolean");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
            }
        } else if (subCommand.equalsIgnoreCase("getall")) {
            ItemStack itemStack = sender.getInventory().getItemInMainHand();
            if (ItemStackUtil.isValidItemStack(itemStack)) {
                NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                if (!nbtItem.getKeys().isEmpty()) {
                    List<String> keys = nbtItem.getKeys();
                    messenger.send(sender, "nbtitemapi.viewAllHeader");
                    for (String keyName : keys) {
                        DataType dataType = nbtItem.getDataType(keyName);
                        Object value = nbtItem.get(keyName);
                        if (value != null) {
                            messenger.send(sender, "nbtitemapi.viewAllFormat", new String[][]{
                                    {"%key%", keyName},
                                    {"%value%", String.valueOf(value)},
                                    {"%type%", dataType.toString()}
                            });
                        } else {
                            messenger.send(sender, "nbtitemapi.viewAllFormat", new String[][]{
                                    {"%key%", keyName},
                                    {"%value%", "?"},
                                    {"%type%", dataType.toString()}
                            });
                        }
                    }
                    messenger.send(sender, "nbtitemapi.viewAllFooter");
                } else {
                    messenger.send(sender, "nbtitemapi.viewAllEmpty");
                }
            } else {
                messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
            }
        } else if (subCommand.equalsIgnoreCase("remove")) {
            if (args.length > 1) {
                ItemStack itemStack = sender.getInventory().getItemInMainHand();
                if (ItemStackUtil.isValidItemStack(itemStack)) {
                    String keyName = args[1];
                    NBTItem nbtItem = new NBTItem(PLUGIN, itemStack);
                    if (nbtItem.contains(keyName)) {
                        messenger.send(sender, "nbtitemapi.keyRemoved", new String[][]{
                                {"%key%", keyName}
                        });
                    } else {
                        messenger.send(sender, "nbtitemapi.itemNoContainsKey");
                    }
                } else {
                    messenger.send(sender, "nbtitemapi.invalidItemStackInHand");
                }
            } else {
                messenger.send(sender, "nbtitemapi.emptyKey");
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
            return "help,setbyte,getbyte,setdouble,getdouble,setfloat,getfloat,setlong,getlong,setinteger,getinteger,setshort,getshort,setstring,getstring,setboolean,getboolean,getall,remove";
        }
        if (position == 2) {
            switch (previousArguments[0]) {
                case "remove": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    return String.join(",", nbtItem.getKeys());
                }
                case "getbyte": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isByte(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getdouble": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isDouble(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getfloat": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isFloat(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getlong": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isLong(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getinteger": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isInteger(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getshort": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isShort(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getstring": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isString(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
                case "getboolean": {
                    NBTItem nbtItem = new NBTItem(PLUGIN, requester.getInventory().getItemInMainHand());
                    List<String> keys = new ArrayList<>();
                    for (String key : nbtItem.getKeys()) {
                        if (nbtItem.isBoolean(key)) keys.add(key);
                    }
                    return String.join(",", keys);
                }
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