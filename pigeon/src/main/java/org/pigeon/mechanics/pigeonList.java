package org.pigeon.mechanics;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

//Danh sách entity có ID của Whistle là key
public class pigeonList {
    public static Map<String, entity> pigeonList = new HashMap<>();

    public static void add(String id, entity e){
        pigeonList.put(id,e);
    }
    public static entity getpigeon(String id){
        if (!pigeonList.containsKey(id)){
            entity e = new entity();
            pigeonList.put(id,e);
            return e;
        }
        return pigeonList.get(id);
    }

    public static entity getPigeon(Entity ent){
        for (entity e : pigeonList.values())
            if (e.getEntity() == ent)
                return e;
        return null;
    }
}
