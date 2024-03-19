package org.pigeon;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pigeon.Holders.pigeonInventoryHolder;
import org.pigeon.Holders.sendableListHolder;
import org.pigeon.mechanics.*;

import javax.swing.*;
import java.time.Duration;

public class EventListeners implements Listener {
    //Khi player sử dụng Whistle
    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getHand() != null && event.getHand() == EquipmentSlot.HAND){
                if (event.getItem() == null)    return;
                if(whistle.isWhistle(event.getItem())) {
                    if (pigeonList.getpigeon(ItemLore.getLore(event.getItem(), 0)).getState() != 0){
                        Title.send(event.getPlayer(),"","§aĐang bận",Duration.ofSeconds(0),Duration.ofSeconds(1),Duration.ofSeconds(1));
                        event.setCancelled(true);
                        return;
                    }
                    whistle.use(event.getPlayer(),ItemLore.getLore(event.getItem(), 0));
                    event.setCancelled(true);
                }
            }
        }
    }

    //Khi player chuột phải vào entity vật lý
    @EventHandler
    public void onPlayerInteractPigeon(PlayerInteractEntityEvent event){
        if (event.getRightClicked() != null && event.getRightClicked().getType() == EntityType.PARROT)
            if (pigeonList.getPigeon(event.getRightClicked()) != null){
                Player player = event.getPlayer();
                pigeonList.getPigeon(event.getRightClicked()).openInventory(player);
                event.setCancelled(true);
            }
    }

    //Khi player chuột trái vào Whitsle để mở danh sách gửi entity
    @EventHandler
    public void setPigeonLand(PlayerInteractEvent event){
        if (event.getItem() != null && whistle.isWhistle(event.getItem())){
            if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {
                if (pigeonList.getpigeon(ItemLore.getLore(event.getItem(), 0)).getState() != 0){
                    Title.send(event.getPlayer(),"","§aĐang bận",Duration.ofSeconds(0),Duration.ofSeconds(1),Duration.ofSeconds(1));
                    event.setCancelled(true);
                    return;
                }
                sendableList s = new sendableList(event.getPlayer(), ItemLore.getLore(event.getItem(),0));
                s.open();
                event.setCancelled(true);
            }
        }
    }

    //Khi player chọn Target để gửi
    @EventHandler
    public void selectTarget(InventoryClickEvent event){
        if (event.getInventory().getHolder() instanceof sendableListHolder){
            ItemStack item = event.getCurrentItem();
            Player target = ItemHead.getPlayer(item);
            if (target == null){
                event.setCancelled(true);
                return;
            }
            Player owner = (Player) event.getWhoClicked();
            if (target != null){
                pigeonList.getpigeon(sendableList.getID(owner)).send(owner,target);
                owner.closeInventory();
                Title.send(owner,"","§aGửi thành công",Duration.ofSeconds(0),Duration.ofSeconds(1),Duration.ofSeconds(1));
            }
            event.setCancelled(true);
        }
    }

    //Khi player thay đổi item trong inventory của entity
    @EventHandler
    public void pigeonInventorySelect(InventoryClickEvent event){
        if (event.getInventory().getHolder() instanceof pigeonInventoryHolder){
            if (event.isShiftClick())   event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE){
                event.setCancelled(true);
                return;
            }
            if (event.getRawSlot() == 4) {
                if (event.getCursor().getType() == Material.AIR || event.getCursor().getType() == Material.WRITABLE_BOOK || event.getCursor().getType() == Material.WRITTEN_BOOK)
                    return;
                event.setCancelled(true);
                return;
            }
        }
    }
}
