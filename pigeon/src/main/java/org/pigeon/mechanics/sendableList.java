package org.pigeon.mechanics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.pigeon.Holders.sendableListHolder;
import org.pigeon.ItemHead;
import org.pigeon.config;

import java.util.HashMap;
import java.util.Map;

//Danh sách target có thể gửi với yêu cầu cách xa 50 block theo config
public class sendableList extends config implements InventoryHolder{

    private static Map<Player, String> List = new HashMap<>();
    private Inventory inventory;
    private Player player;
    public sendableList(Player player, String id){
        Location loc = player.getLocation();
        Component sendableListGuiTitle = MiniMessage.miniMessage().deserialize("<black><bold>Danh sách người chơi");
        Inventory inventory = Bukkit.createInventory(new sendableListHolder(), 18, sendableListGuiTitle.asComponent());
        for (Player sendablePlayers : Bukkit.getServer().getOnlinePlayers()){
            // Check player khác world
            if (player.getWorld() == sendablePlayers.getWorld() && !sendablePlayers.isOp() && sendablePlayers != player && sendablePlayers.getLocation().distance(loc) >= MAX_DISTANCE){
                ItemStack a = ItemHead.getItem(sendablePlayers);
                inventory.addItem(a);
            }
        }
        this.inventory = inventory;
        this.player = player;
        List.put(player,id);
    }

    public void open(){
        player.openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public static String getID(Player player){
        return List.get(player);
    }

}
