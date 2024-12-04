package me.soyaldo.cookiecore.action.actions;

import lombok.Getter;
import lombok.Setter;
import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.utils.PlaceholderUtil;
import me.soyaldo.cookiecore.utils.TextUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;

@Getter
@Setter
public class PlayerTitleAction extends Action {

    private String title = "";
    private String subTitle = "";
    private int fadeIn = 10;
    private int stay = 70;
    private int fadeOut = 20;

    public PlayerTitleAction(ActionManager actionManager, String type) {
        super(actionManager, type);
    }

    @Override
    public void onExecute(Player player, String[][] replacements) {
        // Generating the title times
        Title.Times titleTimes = Title.Times.times(parseDuration(fadeIn), parseDuration(stay), parseDuration(fadeOut));
        // Generating the components.
        Component titleComponent = getComponent(player, this.title, replacements);
        Component subTitleComponent = getComponent(player, this.subTitle, replacements);
        // Generating the title
        Title title = Title.title(titleComponent, subTitleComponent, titleTimes);
        // Generating the audience.
        Audience audience = BukkitAudiences.create(getActionManager().getJavaPlugin()).sender(player);
        audience.showTitle(title);
    }

    private Duration parseDuration(int ticks) {
        return Duration.ofMillis((ticks * 1000L) / 20);
    }

    private Component getComponent(Player player, String text, String[][] replacements) {
        if (text.isEmpty()) return Component.empty();
        // Replacing the replacements on te text
        text = TextUtil.replace(text, replacements);
        // Applying the placeholders
        text = PlaceholderUtil.setPlaceholder(player, text);
        // Generating the component.
        Component legacy = LegacyComponentSerializer.legacyAmpersand().deserialize(text);
        String legacySerialized = MiniMessage.miniMessage().serialize(legacy);
        // Returning the minimessage component deserialized.
        return MiniMessage.miniMessage().deserialize(legacySerialized);
    }

}