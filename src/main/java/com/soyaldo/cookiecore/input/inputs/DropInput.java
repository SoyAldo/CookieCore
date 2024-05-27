package com.soyaldo.cookiecore.input.inputs;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface DropInput {

    void onPlayerDrop(Player player, ItemStack itemStack);

    void onPlayerSneak(Player player);

}