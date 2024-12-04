package me.soyaldo.cookiecore.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class Scheduler {

    private final JavaPlugin plugin;
    private final long ticks;
    public final boolean async;
    public final boolean repeat;
    private BukkitTask task;

    public Scheduler(JavaPlugin plugin, long ticks, boolean async, boolean repeat) {
        this.plugin = plugin;
        this.ticks = ticks;
        this.async = async;
        this.repeat = repeat;
    }

    public Scheduler(JavaPlugin plugin, long ticks) {
        this.plugin = plugin;
        this.ticks = ticks;
        this.async = false;
        this.repeat = false;
    }

    public void startScheduler() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        if (repeat) {
            if (async) {
                task = scheduler.runTaskTimerAsynchronously(plugin, this::onExecute, 0L, ticks);
            } else {
                task = scheduler.runTaskTimer(plugin, this::onExecute, 0L, ticks);
            }
        } else {
            if (async) {
                task = scheduler.runTaskLaterAsynchronously(plugin, this::onExecute, ticks);
            } else {
                task = scheduler.runTaskLater(plugin, this::onExecute, ticks);
            }
        }
    }

    public void stopScheduler() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.cancelTask(task.getTaskId());
    }

    public abstract void onExecute();

}