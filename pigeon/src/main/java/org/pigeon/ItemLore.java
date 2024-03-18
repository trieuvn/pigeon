package org.pigeon;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemLore {
    //Lấy ra lore
    public static String getLore(ItemStack item, int index){
        List<Component> list = item.getItemMeta().lore();
        Component component = list.get(index);
        if (component == null)
            return null;
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
}
