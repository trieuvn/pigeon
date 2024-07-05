package org.pigeon.mechanics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.pigeon.Holders.pigeonInventoryHolder;

import java.util.ArrayList;
import java.util.List;

public class pigeonInventory implements InventoryHolder{

    private Inventory inventory;

    //Khởi tạo 1 inventory mặc định cho entity
    public pigeonInventory(){
        Component guiTitle = MiniMessage.miniMessage().deserialize("<black><bold>Gửi thư");
        Inventory inventory = Bukkit.createInventory(new pigeonInventoryHolder(), 9, guiTitle);
        //invisible
        ItemStack iv = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta ivmeta = iv.getItemMeta();
        ivmeta.displayName(Component.text(""));
        ivmeta.setCustomModelData(1);
        iv.setItemMeta(ivmeta);
        inventory.setItem(0,iv);
        inventory.setItem(1,iv);
        inventory.setItem(2,iv);
        inventory.setItem(3,iv);
        inventory.setItem(5,iv);
        inventory.setItem(6,iv);
        inventory.setItem(7,iv);
        inventory.setItem(8,iv);
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        // Write the content on page 1
        Component newContent = MiniMessage.miniMessage().deserialize("<blue>Ghi nội dung vào đây");
        bookMeta.addPages(newContent);


        Component bookDisplayName = MiniMessage.miniMessage().deserialize("<#e6cf73><bold>Thư");
        List<Component> bookLores = new ArrayList<>();
        bookLores.add(MiniMessage.miniMessage().deserialize("<reset>").asComponent());
        bookLores.add(MiniMessage.miniMessage().deserialize("<white>Lấy ghi nội dung và bỏ lại vào đây.").asComponent());

        bookMeta.displayName(bookDisplayName.asComponent());
        bookMeta.lore(bookLores);

        book.setItemMeta(bookMeta);

        inventory.setItem(4,book);

        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

}
