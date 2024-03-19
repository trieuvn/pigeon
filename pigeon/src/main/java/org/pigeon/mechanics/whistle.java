package org.pigeon.mechanics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class whistle{

    entity pigeon;

    public whistle(){}

    //Tạo và add Item vào inventory người chơi
    public whistle(Player player, String id){
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        //Set id
        meta.setCustomModelData(11070);
        //Set name
        meta.displayName(Component.text("§3§lCòi"));
        TextComponent.Builder loreBuilder = Component.text();
        //Set lore
        loreBuilder.append(Component.text(id));

        meta.lore(loreBuilder.children());
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }

    //Khi whisle được sử dụng
    public static void use(Player player, String id){
        entity pigeon = pigeonList.getpigeon(id);
        if (pigeon.getEntity() == null)
            pigeon.come(player);
        else {
            pigeon.flyAway();
        }
        playSound(player);
    }


    //Kiểm tra itemStack có phải Whistle hay không
    public static boolean isWhistle(ItemStack itemStack){
        if (itemStack.getType() == Material.FEATHER && itemStack.getItemMeta().getCustomModelData() == 11070)
            return true;
        return false;
    }

    //Tạo âm thanh
    public static void playSound(Player player){
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0,100,50);
    }

}
