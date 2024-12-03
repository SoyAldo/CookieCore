package com.soyaldo.cookiecore;

import com.soyaldo.cookiecore.action.ActionManager;
import com.soyaldo.cookiecore.bukkit.commands.ActionAPICommand;
import com.soyaldo.cookiecore.bukkit.commands.CookieCoreCommand;
import com.soyaldo.cookiecore.file.Yaml;
import com.soyaldo.cookiecore.messenger.Messenger;
import com.soyaldo.cookiecore.utils.CopyrightUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CookieCore extends JavaPlugin {

    // Files
    private final Yaml settings = new Yaml(this, "settings", getResource("settings.yml"));
    // Messenger
    private final Messenger messenger = new Messenger(this);

    // Managers
    private final ActionManager actionManager = new ActionManager(this);

    @Override
    public void onEnable() {
        // Settings
        settings.register();
        // Messenger
        messenger.setLangType(settings.getString("lang", "en_us"));
        messenger.register();
        // Commands
        new CookieCoreCommand(this).registerCommand(this);
        new ActionAPICommand(this).registerCommand(this);
        // Managers
        actionManager.register();
        // Copyright
        CopyrightUtil.sendVersionStatusFromConsole(this, "&aEnabled");
    }

    @Override
    public void onDisable() {
        // Copyright
        CopyrightUtil.sendVersionStatusFromConsole(this, "&cDisabled");
    }

    public void reload() {
        // Settings
        settings.reload();
        // Messenger
        messenger.setLangType(settings.getString("lang", "en_us"));
        messenger.reload();
        // Managers
        actionManager.reload();
    }

}