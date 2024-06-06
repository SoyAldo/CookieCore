package com.soyaldo.cookiecore.scheduler.schedulers;

import com.soyaldo.cookiecore.scheduler.Scheduler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenInventoryScheduler extends Scheduler {

    private final Player player;
    private final Inventory inventory;

    public OpenInventoryScheduler(JavaPlugin plugin, Player player, Inventory inventory, long ticks) {
        super(plugin, ticks);
        this.player = player;
        this.inventory = inventory;
    }

    @Override
    public void onExecute() {
        if (player.isOnline()) {
            player.openInventory(inventory);
        }
        stopScheduler();
    }

}