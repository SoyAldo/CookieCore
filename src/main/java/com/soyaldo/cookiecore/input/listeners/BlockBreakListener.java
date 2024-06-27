package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import com.soyaldo.cookiecore.input.inputs.BlockBreakInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Getting the player and the unique id.
        Player player = event.getPlayer();
        // If the player unique id do not exist on chat input.
        if (inputManager.isBlockBreakInput(player)) {
            // Getting the player chat input.
            BlockBreakInput blockBreakInput = inputManager.getBlockBreakInput(player);
            // If the block break input return false.
            if (blockBreakInput.onBlockBreak(player, event)) {
                // Remove the block break input from input manager.
                inputManager.removeBlockBreakInput(player);
            }
        }
    }

}