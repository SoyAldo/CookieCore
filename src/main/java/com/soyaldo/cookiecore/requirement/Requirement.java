package com.soyaldo.cookiecore.requirement;

import com.sun.org.apache.xpath.internal.operations.String;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class Requirement {

    private final String name;
    @Setter
    private boolean optional = false;
    private final List<String> denyActions = new ArrayList<>();
    private final List<String> successActions = new ArrayList<>();

    public boolean verify(Player player) {
        return onVerify(player, new String[][]{});
    }

    public boolean verify(Player player, String[][] replacements) {
        return onVerify(player, replacements);
    }

    protected abstract boolean onVerify(Player player, String[][] replacements);

}