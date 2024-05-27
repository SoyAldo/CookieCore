package com.soyaldo.cookiecore.input.listeners;

import com.soyaldo.cookiecore.input.InputManager;
import com.soyaldo.cookiecore.input.inputs.ChatInput;
import com.soyaldo.cookiecore.input.inputs.DropInput;
import com.soyaldo.cookiecore.input.inputs.ShiftInput;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerToggleSneakListener implements Listener {

    private final InputManager inputManager;

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        // Getting the player and the unique id.
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (chats.containsKey(uuid)) {
            ChatInput chatInput = chats.get(uuid);
            chatInput.onPlayerSneak(player);
            chats.remove(uuid);
        }

        if (drops.containsKey(uuid)) {
            DropInput chatInput = drops.get(uuid);
            chatInput.onPlayerSneak(player);
            drops.remove(uuid);
        }

        if (shifts.containsKey(uuid)) {
            ShiftInput shiftInput = shifts.get(uuid);
            shiftInput.onShift(player);
            shifts.remove(uuid);
        }
    }

}