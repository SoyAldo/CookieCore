package me.soyaldo.cookiecore.input.inputs;

import org.bukkit.entity.Player;

public interface ChatInput {

    boolean onPlayerChat(Player player, String input);

    void onPlayerSneak(Player player);

}