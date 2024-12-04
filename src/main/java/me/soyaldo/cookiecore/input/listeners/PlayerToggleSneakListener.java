package me.soyaldo.cookiecore.input.listeners;

import me.soyaldo.cookiecore.input.InputManager;
import me.soyaldo.cookiecore.input.inputs.BlockBreakInput;
import me.soyaldo.cookiecore.input.inputs.ChatInput;
import me.soyaldo.cookiecore.input.inputs.DropInput;
import me.soyaldo.cookiecore.input.inputs.SneakInput;
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
        if (inputManager.isBlockBreakInput(player)) {
            BlockBreakInput blockBreakInput = inputManager.getBlockBreakInput(player);
            blockBreakInput.onPlayerSneak(player);
            inputManager.removeBlockBreakInput(player);
        }
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