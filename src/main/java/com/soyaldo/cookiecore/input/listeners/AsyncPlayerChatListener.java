package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import com.soyaldo.cookiecore.input.inputs.ChatInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class AsyncPlayerChatListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        // Getting the player and the unique id.
        Player player = event.getPlayer();
        // If the player unique id do not exist on chat input.
        if (!inputManager.isChatInput(player)) return;
        // Cancel the event.
        event.setCancelled(true);
        // Getting the player chat input.
        ChatInput chatInput = inputManager.getChatInput(player);
        // If the chat input return false.
        if (!chatInput.onPlayerChat(player, event.getMessage())) return;
        // Remove the chat input from input manager.
        inputManager.removeChatInput(player);
    }

}