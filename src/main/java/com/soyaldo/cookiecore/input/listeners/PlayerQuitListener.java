package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Getting the player.
        Player player = event.getPlayer();
        // Removing the inputs.
        inputManager.removeChatInput(player);
        inputManager.removeDropInput(player);
        inputManager.removeSneakInput(player);
    }

}