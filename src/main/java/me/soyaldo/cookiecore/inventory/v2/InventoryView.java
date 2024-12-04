package me.soyaldo.cookiecore.inventory.v2;

import me.soyaldo.cookiecore.inventory.v2.action.Action;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class InventoryView {

    private final Player player;
    private final Inventory inventory;
    private final String title;
    private int updateInterval;
    private final LinkedHashMap<Integer, Item> items = new LinkedHashMap<>();
    private final List<Action> closeActions = new ArrayList<>();

    public void update() {
        for (Item item : items.values()) {
            if (item.isUpdate()) {
                item.updateItem(player);
                inventory.setItem(item.getSlot(), item.getItemStack());
            }
        }
    }

}