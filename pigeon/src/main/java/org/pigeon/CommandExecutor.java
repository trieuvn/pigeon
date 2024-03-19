package org.pigeon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pigeon.mechanics.entity;
import org.pigeon.mechanics.whistle;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Component a = MiniMessage.miniMessage().deserialize("<white><bold>Given Whistle to player " + p.getName());
            if (args[0].equals("give") && !args[1].equals("")){

                new whistle(p ,args[1]);
                p.sendMessage(a.asComponent());
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("give");

            return arguments;
        } else if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);

            for(int i = 0; i < players.length; i++) {
                playerNames.add(players[i].getName());
            }
        }
        return null;
    }
}
