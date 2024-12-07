package me.soyaldo.cookiecore.item;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ItemInfo {

    private String type;
    private int amount = 1;
    private String displayName = "";
    private List<String> lore = new ArrayList<>();
    private int customModelData = 0;
    private boolean unbreakable = false;
    private List<String> itemFlags = new ArrayList<>();
    private HashMap<String, Integer> enchantments = new HashMap<>();
    private HashMap<String, Object> nbt = new HashMap<>();

}