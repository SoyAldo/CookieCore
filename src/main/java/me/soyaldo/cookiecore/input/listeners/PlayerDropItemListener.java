package me.soyaldo.cookiecore.input.listeners;

import me.soyaldo.cookiecore.input.InputManager;
import me.soyaldo.cookiecore.input.inputs.DropInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

@RequiredArgsConstructor
public class PlayerDropItemListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        // Getting the player.
        Player player = event.getPlayer();
        // If the player unique id do not exist on drop input.
        if (!inputManager.isDropInput(player)) return;
        // Cancel the event.
        event.setCancelled(true);
        // Getting the player drop input.
        DropInput chatInput = inputManager.getDropInput(player);
        // Call the player drop logic.
        chatInput.onPlayerDrop(player, event.getItemDrop().getItemStack());
        // Remove the drop input from input manager.
        inputManager.removeDropInput(player);
    }

}