package org.pigeon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemHead {
    //Lấy item head của player
    public static ItemStack getItem(Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta)item.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.displayName(Component.text("§f§l" + player.getName()));
        item.setItemMeta(skullMeta);
        return item;
    }

    //Từ itemHead lấy Player
    public static Player getPlayer(ItemStack head){
        if (head == null || head.getType() != Material.PLAYER_HEAD)
            return null;
        for (Player p : Bukkit.getServer().getOnlinePlayers()){
            if (p.getName().equals(LegacyComponentSerializer.legacySection().serialize(head.getItemMeta().displayName()).replace("§f§l",""))){
                return p;
            }
        }
        return null;
    }
}
