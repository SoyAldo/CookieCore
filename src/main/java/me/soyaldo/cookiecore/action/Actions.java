package me.soyaldo.cookiecore.action;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Actions {

    private final List<Action> actions = new ArrayList<>();

    public void execute(Player player, String[][] replacements) {
        actions.forEach(action -> action.execute(player, replacements));
    }

}