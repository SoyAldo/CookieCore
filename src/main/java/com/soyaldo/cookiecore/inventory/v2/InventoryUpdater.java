package com.soyaldo.cookiecore.inventory.v2;

import com.soyaldo.cookiecore.scheduler.ScheduleAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

@RequiredArgsConstructor
@Getter
public class InventoryUpdater implements ScheduleAction {

    private final InventoryManager inventoryManager;
    private final Player player;
    private final InventoryView simpleInventoryView;

    @Override
    public boolean action(BukkitTask bukkitTask) {
        if (inventoryManager.containInventoryView(player)) {
            simpleInventoryView.update();
            return false;
        } else {
            bukkitTask.cancel();
            return true;
        }
    }

}