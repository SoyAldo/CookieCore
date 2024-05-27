package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import com.soyaldo.cookiecore.input.inputs.DropInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerDropItemListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        // Getting the player and the unique id.
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        // If the player unique id do not exist on drop input.
        if (inputManager.getDrops().containsKey(uuid)) return;
        // Cancel the event.
        event.setCancelled(true);
        // Getting the player drop input.
        DropInput chatInput = inputManager.getDrops().get(uuid);
        // Call the player drop logic.
        chatInput.onPlayerDrop(player, event.getItemDrop().getItemStack());
        // Remove the drop input from input manager.
        inputManager.getDrops().remove(uuid);
    }

}