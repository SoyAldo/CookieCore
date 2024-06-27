package com.soyaldo.cookiecore.inventory.v2;

import org.bukkit.inventory.ItemStack;

public interface GetItem {

    ItemStack getItem();

    boolean isAsync();

}