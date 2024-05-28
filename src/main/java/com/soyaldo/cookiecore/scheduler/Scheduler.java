package com.soyaldo.cookiecore.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class Scheduler {

    private final JavaPlugin plugin;
    private final long ticks;
    public final boolean async;
    private BukkitTask task;

    public Scheduler(JavaPlugin plugin, long ticks, boolean async) {
        this.plugin = plugin;
        this.ticks = ticks;
        this.async = async;
    }

    public Scheduler(JavaPlugin plugin, long ticks) {
        this.plugin = plugin;
        this.ticks = ticks;
        this.async = false;
    }

    public void startScheduler() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        task = scheduler.runTaskTimer(plugin, this::onExecute, 0L, ticks);
    }

    public void stopScheduler() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.cancelTask(task.getTaskId());
    }

    public abstract void onExecute();

}