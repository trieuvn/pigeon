package org.pigeon.mechanics;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pigeon.Holders.pigeonInventoryHolder;

public class pigeonInventory implements InventoryHolder{

    private Inventory inventory;

    //Khởi tạo 1 inventory mặc định cho entity
    public pigeonInventory(){
        Inventory inventory = Bukkit.createInventory(new pigeonInventoryHolder(), 9);
        //gui
        ItemStack gui = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta guimeta = gui.getItemMeta();
        guimeta.displayName(Component.text(""));
        guimeta.setCustomModelData(11071);
        gui.setItemMeta(guimeta);
        inventory.setItem(0,gui);
        //invisible
        ItemStack iv = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta ivmeta = iv.getItemMeta();
        ivmeta.displayName(Component.text(""));
        ivmeta.setCustomModelData(11070);
        iv.setItemMeta(ivmeta);
        inventory.setItem(1,iv);
        inventory.setItem(2,iv);
        inventory.setItem(3,iv);
        inventory.setItem(5,iv);
        inventory.setItem(6,iv);
        inventory.setItem(7,iv);
        inventory.setItem(8,iv);
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        inventory.setItem(4,book);
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

}
