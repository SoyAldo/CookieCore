package me.soyaldo.cookiecore.action.actions;

import lombok.Getter;
import lombok.Setter;
import me.soyaldo.cookiecore.action.Action;
import me.soyaldo.cookiecore.action.ActionManager;
import me.soyaldo.cookiecore.utils.AdventureUtil;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

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
        Title.Times titleTimes = Title.Times.times(AdventureUtil.parseDuration(fadeIn), AdventureUtil.parseDuration(stay), AdventureUtil.parseDuration(fadeOut));
        // Generating the components.
        Component titleComponent = AdventureUtil.getComponent(this.title, replacements, player);
        Component subTitleComponent = AdventureUtil.getComponent(this.subTitle, replacements, player);
        // Generating the title
        Title title = Title.title(titleComponent, subTitleComponent, titleTimes);
        // Generating the audience.
        Audience audience = BukkitAudiences.create(getActionManager().getJavaPlugin()).sender(player);
        audience.showTitle(title);
    }

}