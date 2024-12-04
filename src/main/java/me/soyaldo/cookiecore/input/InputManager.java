package me.soyaldo.cookiecore.input;

import me.soyaldo.cookiecore.input.inputs.BlockBreakInput;
import me.soyaldo.cookiecore.input.inputs.ChatInput;
import me.soyaldo.cookiecore.input.inputs.DropInput;
import me.soyaldo.cookiecore.input.inputs.SneakInput;
import lombok.RequiredArgsConstructor;
import me.soyaldo.cookiecore.input.listeners.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

@RequiredArgsConstructor
public class InputManager implements Listener {

    private final JavaPlugin javaPlugin;

    private final HashMap<String, BlockBreakInput> blockBreakInputs = new HashMap<>();
    private final HashMap<String, ChatInput> chatInputs = new HashMap<>();
    private final HashMap<String, DropInput> dropInputs = new HashMap<>();
    private final HashMap<String, SneakInput> sneakInputs = new HashMap<>();

    public void register() {
        javaPlugin.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(this), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), javaPlugin);
        javaPlugin.getServer().getPluginManager().registerEvents(new PlayerToggleSneakListener(this), javaPlugin);
    }

    public void reload() {
        blockBreakInputs.clear();
        chatInputs.clear();
        dropInputs.clear();
        sneakInputs.clear();
    }

    public boolean isBlockBreakInput(Player player) {
        return blockBreakInputs.containsKey(player.getUniqueId().toString());
    }

    public boolean isChatInput(Player player) {
        return chatInputs.containsKey(player.getUniqueId().toString());
    }

    public boolean isDropInput(Player player) {
        return dropInputs.containsKey(player.getUniqueId().toString());
    }

    public boolean isSneakInput(Player player) {
        return sneakInputs.containsKey(player.getUniqueId().toString());
    }

    public void addInput(Player player, BlockBreakInput input) {
        blockBreakInputs.put(player.getUniqueId().toString(), input);
    }

    public void addInput(Player player, ChatInput input) {
        chatInputs.put(player.getUniqueId().toString(), input);
    }

    public void addInput(Player player, DropInput input) {
        dropInputs.put(player.getUniqueId().toString(), input);
    }

    public void addInput(Player player, SneakInput input) {
        sneakInputs.put(player.getUniqueId().toString(), input);
    }

    public BlockBreakInput getBlockBreakInput(Player player) {
        return blockBreakInputs.get(player.getUniqueId().toString());
    }

    public ChatInput getChatInput(Player player) {
        return chatInputs.get(player.getUniqueId().toString());
    }

    public DropInput getDropInput(Player player) {
        return dropInputs.get(player.getUniqueId().toString());
    }

    public SneakInput getSneakInput(Player player) {
        return sneakInputs.get(player.getUniqueId().toString());
    }

    public void removeBlockBreakInput(Player player) {
        blockBreakInputs.remove(player.getUniqueId().toString());
    }

    public void removeChatInput(Player player) {
        chatInputs.remove(player.getUniqueId().toString());
    }

    public void removeDropInput(Player player) {
        dropInputs.remove(player.getUniqueId().toString());
    }

    public void removeSneakInput(Player player) {
        sneakInputs.remove(player.getUniqueId().toString());
    }

}