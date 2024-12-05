package me.soyaldo.cookiecore.item;

import me.soyaldo.cookiecore.file.Yaml;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemLoader {

    public static ItemStack load(Yaml yaml) {
        return load(yaml.getFileConfiguration());
    }

    public static ItemStack load(Yaml yaml, String path) {
        return load(yaml.getFileConfiguration(), path);
    }

    public static ItemStack load(FileConfiguration fileConfiguration) {
        return load(fileConfiguration, "");
    }

    public static ItemStack load(FileConfiguration fileConfiguration, String path) {
        String realPath = path.isEmpty() ? "" : path + ".";
        ItemBuilder itemBuilder = new ItemBuilder();
        // Type
        if (fileConfiguration.contains(realPath + "material")) {
            if (fileConfiguration.isString(realPath + "material")) {
                itemBuilder.setType(fileConfiguration.getString(realPath + "material", "").toUpperCase());
            }
        } else if (fileConfiguration.contains(realPath + "type")) {
            if (fileConfiguration.isString(realPath + "type")) {
                itemBuilder.setType(fileConfiguration.getString(realPath + "type", "").toUpperCase());
            }
        }
        // Amount
        if (fileConfiguration.contains(realPath + "amount")) {
            if (fileConfiguration.isInt(realPath + "amount")) {
                itemBuilder.setAmount(fileConfiguration.getInt(realPath + "amount"));
            }
        }
        // CustomModelData
        if (fileConfiguration.contains(realPath + "customModelData")) {
            if (fileConfiguration.isInt(realPath + "customModelData")) {
                itemBuilder.setCustomModelData(fileConfiguration.getInt(realPath + "customModelData"));
            }
        } else if (fileConfiguration.contains(realPath + "cmd")) {
            if (fileConfiguration.isInt(realPath + "cmd")) {
                itemBuilder.setCustomModelData(fileConfiguration.getInt(realPath + "cmd"));
            }
        }
        // Unbreakable
        if (fileConfiguration.contains(realPath + "unbreakable")) {
            if (fileConfiguration.isBoolean(realPath + "unbreakable")) {
                itemBuilder.setUnbreakable(fileConfiguration.getBoolean(realPath + "unbreakable"));
            }
        }
        // DisplayName
        if (fileConfiguration.contains(realPath + "displayName")) {
            if (fileConfiguration.isString(realPath + "displayName")) {
                itemBuilder.setDisplayName(fileConfiguration.getString(realPath + "displayName"));
            }
        } else if (fileConfiguration.contains(realPath + "name")) {
            if (fileConfiguration.isString(realPath + "name")) {
                itemBuilder.setDisplayName(fileConfiguration.getString(realPath + "name"));
            }
        }
        // Lore
        if (fileConfiguration.contains(realPath + "lore")) {
            if (fileConfiguration.isList(realPath + "lore")) {
                itemBuilder.setLore(fileConfiguration.getStringList(realPath + "lore"));
            }
        }
        // ItemFlags
        if (fileConfiguration.contains(realPath + "itemFlags")) {
            if (fileConfiguration.isList(realPath + "itemFlags")) {
                itemBuilder.setItemFlags(fileConfiguration.getStringList(realPath + "itemFlags"));
            }
        } else if (fileConfiguration.contains(realPath + "flags")) {
            if (fileConfiguration.isList(realPath + "flags")) {
                itemBuilder.setItemFlags(fileConfiguration.getStringList(realPath + "flags"));
            }
        }
        // Enchantments
        String enchantmentsPath = "";
        if (fileConfiguration.contains(realPath + "enchantments")) {
            enchantmentsPath = "enchantments";
        } else if (fileConfiguration.contains(realPath + "enchants")) {
            enchantmentsPath = "enchants";
        }
        if (!enchantmentsPath.isEmpty()) {
            if (fileConfiguration.contains(realPath + enchantmentsPath)) {
                if (fileConfiguration.isConfigurationSection(realPath + enchantmentsPath)) {
                    ConfigurationSection enchantmentsSection = fileConfiguration.getConfigurationSection(realPath + enchantmentsPath);
                    if (enchantmentsSection != null) {
                        HashMap<String, Integer> enchantments = new HashMap<>();
                        for (String enchantment : enchantmentsSection.getKeys(false)) {
                            if (enchantmentsSection.isInt(enchantment)) {
                                enchantments.put(enchantment, enchantmentsSection.getInt(enchantment));
                            }
                        }
                        itemBuilder.setEnchantments(enchantments);
                    }
                }
            }
        }
        // Build the ItemStack and return the ItemStack
        return itemBuilder.build();
    }

}