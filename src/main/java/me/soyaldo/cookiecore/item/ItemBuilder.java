package me.soyaldo.cookiecore.item;

import lombok.Getter;
import me.soyaldo.cookiecore.utils.AdventureUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class ItemBuilder {

    private final ItemInfo itemInfo = new ItemInfo();

    /**
     * Sets the type of this item.
     *
     * @param material The new material
     * @return Current ItemBuilder
     */
    public ItemBuilder setType(Material material) {
        itemInfo.setType(material.name());
        return this;
    }

    /**
     * Sets the type of this item.
     *
     * @param materialName The new material.
     * @return Current ItemBuilder
     */
    public ItemBuilder setType(String materialName) {
        Material material = Material.getMaterial(materialName);
        if (material != null) {
            itemInfo.setType(material.name());
        }
        return this;
    }

    /**
     * Sets the amount of items in this stack.
     *
     * @param amount The new amount
     * @return Current ItemBuilder
     */
    public ItemBuilder setAmount(int amount) {
        itemInfo.setAmount(amount);
        return this;
    }

    /**
     * Sets the custom model data.
     *
     * @param customModelData The new custom model data
     * @return Current ItemBuilder
     */
    public ItemBuilder setCustomModelData(int customModelData) {
        itemInfo.setCustomModelData(customModelData);
        return this;
    }

    /**
     * Sets the unbreakable tag. An unbreakable item will not lose durability.
     *
     * @param unbreakable The new unbreakable value
     * @return Current ItemBuilder
     */
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemInfo.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the display name.
     *
     * @param displayName The new display name
     * @return Current ItemBuilder
     */
    public ItemBuilder setDisplayName(String displayName) {
        itemInfo.setDisplayName(displayName);
        return this;
    }

    /**
     * Sets the lore.
     *
     * @param lore The new lore
     * @return Current ItemBuilder
     */
    public ItemBuilder setLore(List<String> lore) {
        itemInfo.setLore(lore);
        return this;
    }

    /**
     * Add item flag which should be ignored when rendering a ItemStack in the Client.
     *
     * @param itemFlag The new item flag
     * @return Current ItemBuilder
     */
    public ItemBuilder addItemFlag(String itemFlag) {
        itemInfo.getItemFlags().add(itemFlag);
        return this;
    }

    /**
     * Sets item flag which should be ignored when rendering a ItemStack in the Client.
     *
     * @param itemFlags The new item flags
     * @return Current ItemBuilder
     */
    public ItemBuilder setItemFlags(List<String> itemFlags) {
        itemInfo.setItemFlags(itemFlags);
        return this;
    }

    /**
     * Adds the specified enchantment to this item stack.
     *
     * @param enchantment The new enchantment
     * @param level       The enchantment level
     * @return Current ItemBuilder
     */
    public ItemBuilder addEnchantment(String enchantment, int level) {
        itemInfo.getEnchantments().put(enchantment, level);
        return this;
    }

    /**
     * Sets the specified enchantments to this item stack.
     *
     * @param enchantments The news enchantments
     * @return Current ItemBuilder
     */
    public ItemBuilder setEnchantments(HashMap<String, Integer> enchantments) {
        itemInfo.setEnchantments(enchantments);
        return this;
    }

    /**
     * Build the ItemStack.
     *
     * @return The ItemStack if all data are correct or null if contains incorrect data.
     */
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.STONE);
        // Type
        if (itemInfo.getType() == null) return null;
        Material material = Material.getMaterial(itemInfo.getType().toUpperCase());
        if (material == null) return null;
        itemStack = new ItemStack(material);
        // Amount
        if (itemInfo.getAmount() < 1) itemInfo.setAmount(1);
        itemStack.setAmount(itemInfo.getAmount());
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            // CustomModelData
            if (itemInfo.getCustomModelData() != 0) {
                itemMeta.setCustomModelData(itemInfo.getCustomModelData());
            }
            // Unbreakable
            itemMeta.setUnbreakable(itemInfo.isUnbreakable());
            // DisplayName
            if (!itemInfo.getDisplayName().isEmpty()) {
                itemMeta.setDisplayName(AdventureUtil.legacyProcess(itemInfo.getDisplayName()));
            }
            // Lore
            if (!itemInfo.getLore().isEmpty()) {
                List<String> lore = new ArrayList<>();
                for (String line : itemInfo.getLore()) {
                    lore.add(AdventureUtil.legacyProcess(line));
                }
                itemMeta.setLore(lore);
            }
            // ItemFlags
            for (String itemFlagName : itemInfo.getItemFlags()) {
                List<ItemFlag> flags = new ArrayList<>();
                try {
                    ItemFlag itemFlag = ItemFlag.valueOf(itemFlagName);
                    flags.add(itemFlag);
                } catch (Exception ignored) {
                }
                for (ItemFlag itemFlag : flags) itemMeta.addItemFlags(itemFlag);
            }
            // Set the ItemMeta to the ItemStack
            itemStack.setItemMeta(itemMeta);
        }
        // Enchantments
        for (String enchantmentName : itemInfo.getEnchantments().keySet()) {
            Enchantment enchantment = Enchantment.getByName(enchantmentName);
            int level = itemInfo.getEnchantments().get(enchantmentName);
            if (enchantment != null) {
                if (level > 0) {
                    itemStack.addUnsafeEnchantment(enchantment, level);
                }
            }
        }
        // Return the ItemStack
        return itemStack;
    }


}