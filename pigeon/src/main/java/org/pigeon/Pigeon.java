package org.pigeon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.pigeon.mechanics.entity;
import org.pigeon.mechanics.pigeonList;

public final class Pigeon extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("pigeon").setExecutor(new CommandExecutor());
        Bukkit.getServer().getPluginManager().registerEvents(new EventListeners(),this);
        new entity();
    }

    @Override
    public void onDisable() {
        for (entity e : pigeonList.pigeonList.values()){
            e.getEntity().remove();
        }
    }

}
