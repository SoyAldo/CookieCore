package com.soyaldo.cookiecore;

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

    @Override
    public void onEnable() {
        // Settings
        settings.register();
        // Messenger
        messenger.setLangType(settings.getString("lang", "en_us"));
        messenger.register();
        // Commands
        new CookieCoreCommand(this).registerCommand(this);
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
    }

}