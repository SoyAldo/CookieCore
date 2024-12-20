package me.soyaldo.cookiecore.input.listeners;

import me.soyaldo.cookiecore.input.InputManager;
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
        inputManager.removeBlockBreakInput(player);
        inputManager.removeChatInput(player);
        inputManager.removeDropInput(player);
        inputManager.removeSneakInput(player);
    }

}