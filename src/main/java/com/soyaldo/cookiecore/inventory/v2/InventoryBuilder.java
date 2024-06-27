package com.soyaldo.cookiecore.inventory.v2;

import com.soyaldo.cookiecore.inventory.v2.action.Action;
import com.soyaldo.cookiecore.scheduler.SimpleScheduler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
public class InventoryBuilder {

    private final String name;
    private final String title;
    private final int rows;
    private final int updateInterval;
    private final LinkedHashMap<Integer, Slot> slots;
    private final List<Action> closeActions;

    public InventoryBuilder(String name) {
        this.name = name;
        title = "Default Title";
        rows = 3;
        this.updateInterval = 0;
        slots = new LinkedHashMap<>();
        closeActions = new ArrayList<>();
    }

    public InventoryBuilder(String name, String title) {
        this.name = name;
        this.title = title;
        rows = 3;
        this.updateInterval = 0;
        slots = new LinkedHashMap<>();
        closeActions = new ArrayList<>();
    }

    public InventoryBuilder(String name, String title, int rows) {
        this.name = name;
        this.title = title;
        this.rows = rows;
        this.updateInterval = 0;
        slots = new LinkedHashMap<>();
        closeActions = new ArrayList<>();
    }

    public InventoryBuilder(String name, String title, int rows, int updateInterval) {
        this.name = name;
        this.title = title;
        this.rows = rows;
        this.updateInterval = updateInterval;
        slots = new LinkedHashMap<>();
        closeActions = new ArrayList<>();
    }

    public InventoryBuilder(InventoryBuilder inventoryBuilder) {
        this.name = inventoryBuilder.getName();
        title = inventoryBuilder.getTitle();
        rows = inventoryBuilder.getRows();
        updateInterval = inventoryBuilder.getUpdateInterval();
        slots = inventoryBuilder.getSlots();
        closeActions = new ArrayList<>();
    }

    public InventoryView generateInventoryView(JavaPlugin javaPlugin, Player player) {
        // todo Generar el SimpleInventoryView
        // todo Colocar prioridades
        // todo y view requirements
        Inventory inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));
        // Starting Items load
        for (int slot = 0; slot < (rows * 9); slot++) {
            if (inventory.getSize() > slot) {
                SimpleScheduler addItem = new SimpleScheduler(javaPlugin, 1);
                int finalSlot = slot;
                addItem.addScheduleAction(bukkitTask -> {
                    //inventory.setItem(finalSlot, getSlots().get(finalSlot).getItemStack());
                    return true;
                });
                addItem.runSchedulerLater(true);
            } else break;
        }
        // Ending Items load
        InventoryView inventoryView = new InventoryView(player, inventory, title);
        // todo actions
        return inventoryView;
    }

}