package com.soyaldo.cookiecore.inventory.v2;

import com.soyaldo.cookiecore.inventory.v2.action.Action;
import com.soyaldo.cookiecore.scheduler.SimpleScheduler;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class InventoryManager implements Listener {

    private final JavaPlugin javaPlugin;
    private final HashMap<String, InventoryBuilder> inventoryBuilders = new HashMap<>();
    private final HashMap<UUID, InventoryView> inventoryViews = new HashMap<>();

    public InventoryManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public void openInventory(Player player, String name) {
        try {
            player.closeInventory();
        } catch (IllegalStateException ignored) {
        } finally {
            UUID uuid = player.getUniqueId();
            if (inventoryBuilders.containsKey(name)) {
                InventoryBuilder inventoryBuilder = new InventoryBuilder(inventoryBuilders.get(name));
                InventoryView simpleInventoryView = inventoryBuilder.generateInventoryView(javaPlugin, player);
                inventoryViews.put(uuid, simpleInventoryView);
                // Open Inventory
                SimpleScheduler openInventoryScheduler = new SimpleScheduler(javaPlugin, 1);
                openInventoryScheduler.addScheduleAction(bukkitTask -> {
                    player.openInventory(simpleInventoryView.getInventory());
                    return true;
                });
                openInventoryScheduler.runSchedulerLater();
                // Inventory Updater
                if (inventoryBuilder.getUpdateInterval() > 0) {
                    SimpleScheduler inventoryUpdateScheduler = new SimpleScheduler(javaPlugin, 1);
                    inventoryUpdateScheduler.addScheduleAction(new InventoryUpdater(this, player, simpleInventoryView));
                    inventoryUpdateScheduler.runSchedulerTimer();
                }
            }
        }
    }

    public void openInventory(Player player, InventoryBuilder inventoryBuilder) {
        try {
            player.closeInventory();
        } catch (IllegalStateException ignored) {
        } finally {
            if (!inventoryBuilders.containsKey(inventoryBuilder.getName()))
                inventoryBuilders.put(inventoryBuilder.getName(), inventoryBuilder);
            openInventory(player, inventoryBuilder.getName());
        }
    }

    public boolean containInventoryBuilder(InventoryBuilder inventoryBuilder) {
        return inventoryViews.containsValue(inventoryBuilder);
    }

    public boolean containInventoryView(Player player) {
        return inventoryViews.containsKey(player.getUniqueId());
    }

    public InventoryBuilder getInventoryBuilder(String name) {
        return inventoryBuilders.get(name);
    }

    public InventoryView getInventoryView(Player player) {
        return inventoryViews.get(player.getUniqueId());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        if (inventoryViews.containsKey(uuid)) {
            InventoryView inventoryView = inventoryViews.get(uuid);
            if (event.getView().getTitle().equals(inventoryView.getTitle())) {
                event.setCancelled(true);
                int slot = event.getRawSlot();
                if (inventoryView.getItems().containsKey(slot)) {
                    switch (event.getClick()) {
                        case LEFT: {
                            for (Action action : inventoryView.getItems().get(slot).getLeftActions())
                                action.execute(player, inventoryView);
                            break;
                        }
                        case SHIFT_LEFT: {
                            for (Action action : inventoryView.getItems().get(slot).getLeftShiftActions())
                                action.execute(player, inventoryView);
                            break;
                        }
                        case MIDDLE: {
                            for (Action action : inventoryView.getItems().get(slot).getMiddleActions())
                                action.execute(player, inventoryView);
                            break;
                        }
                        case RIGHT: {
                            for (Action action : inventoryView.getItems().get(slot).getRightActions())
                                action.execute(player, inventoryView);
                            break;
                        }
                        case SHIFT_RIGHT: {
                            for (Action action : inventoryView.getItems().get(slot).getRightShiftActions())
                                action.execute(player, inventoryView);
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (inventoryViews.containsKey(uuid)) {
            InventoryView inventoryView = inventoryViews.get(uuid);
            if (event.getView().getTitle().equals(inventoryView.getTitle())) {
                inventoryViews.remove(uuid);
                for (Action action : inventoryView.getCloseActions()) action.execute(player, inventoryView);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        inventoryViews.remove(event.getPlayer().getUniqueId());
    }

}