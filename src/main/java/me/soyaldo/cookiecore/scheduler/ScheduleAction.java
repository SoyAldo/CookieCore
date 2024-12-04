package me.soyaldo.cookiecore.scheduler;

import org.bukkit.scheduler.BukkitTask;

public interface ScheduleAction {

    /**
     * This action can be cancel task if this return true
     *
     * @return Cancel task if true
     */
    boolean action(BukkitTask bukkitTask);

}