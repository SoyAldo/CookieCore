package com.soyaldo.cookiecore.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ActionManager {

    private final JavaPlugin javaPlugin;
    private final HashMap<String, ActionExpansion> expansions = new HashMap<>();

    public void register() {

    }

    public void reload() {

    }

    public boolean existExpansion(String name) {
        return expansions.containsKey(name);
    }

    public void addExpansion(ActionExpansion actionExpansion) {
        expansions.put(actionExpansion.getName(), actionExpansion);
    }

    public void removeExpansion(String name) {
        expansions.remove(name);
    }

    public ActionExpansion getExpansion(String name) {
        return expansions.get(name);
    }


    public List<String> deserialize(String format) {
        /*
        type:name
        global:yes
        async:yes
        delay:10
        permission:your.permission.node
        value:Your value!
         */
        List<String> deserialized = new ArrayList<>();



        return deserialized;
    }

}