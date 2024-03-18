package org.pigeon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.pigeon.mechanics.entity;
import org.pigeon.mechanics.whistle;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args[0].equals("give") && !args[1].equals("")){
                new whistle((Player) sender,args[1]);
            }
        }



        return true;
    }
}
