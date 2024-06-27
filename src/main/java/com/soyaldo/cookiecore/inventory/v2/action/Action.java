package com.soyaldo.cookiecore.inventory.v2.action;

import com.soyaldo.cookiecore.inventory.v2.InventoryView;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public abstract class Action {

    private final String name;
    private final ActionType actionType;

    public abstract void execute(Player player, InventoryView simpleInventoryView);

}