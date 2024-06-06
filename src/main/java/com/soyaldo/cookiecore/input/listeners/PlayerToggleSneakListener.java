package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import com.soyaldo.cookiecore.input.inputs.ChatInput;
import com.soyaldo.cookiecore.input.inputs.DropInput;
import com.soyaldo.cookiecore.input.inputs.SneakInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

@RequiredArgsConstructor
public class PlayerToggleSneakListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (inputManager.isChatInput(player)) {
            ChatInput chatInput = inputManager.getChatInput(player);
            chatInput.onPlayerSneak(player);
            inputManager.removeChatInput(player);
        }
        if (inputManager.isDropInput(player)) {
            DropInput chatInput = inputManager.getDropInput(player);
            chatInput.onPlayerSneak(player);
            inputManager.removeDropInput(player);
        }
        if (inputManager.isSneakInput(player)) {
            SneakInput sneakInput = inputManager.getSneakInput(player);
            sneakInput.onPlayerSneak(player);
            inputManager.removeSneakInput(player);
        }
    }

}