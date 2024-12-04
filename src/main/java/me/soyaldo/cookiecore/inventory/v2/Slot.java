package me.soyaldo.cookiecore.inventory.v2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Slot {

    private final int slot;

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
        items.sort((o1, o2) -> {
            if (o1.getPriority() > o2.getPriority()) return 1;
            else if (o1.getPriority() == o2.getPriority()) return 0;
            return -1;
        });
    }

}