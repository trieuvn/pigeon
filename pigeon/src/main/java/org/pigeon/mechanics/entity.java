package org.pigeon.mechanics;

import com.destroystokyo.paper.entity.Pathfinder;
import com.destroystokyo.paper.entity.ai.VanillaGoal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.pigeon.Pigeon;


import static org.bukkit.Bukkit.getServer;

//class entity của pigeon
public class entity {
    //entity vật lý (thực)
    private Parrot entity;

    //Chủ
    private Player owner;

    //Inventory của entity
    private Inventory inventory;

    /*Trang thái
    * 0: Đang ở gần Owner (Rãnh)
    * 1: Đang bay (Bận)
    * 2: Đang ở gần Target (Bận)*/
    private int state;


    public entity(){
        pigeonInventory inventory = new pigeonInventory();
        this.inventory = inventory.getInventory();
        this.state = 0;
    }

    //Gọi entity vật lý đến nguoười chơi ngay lập tức
    public void come(Player player){
        if (entity != null)  return;
        Location location = player.getLocation();
        Parrot pigeon = (Parrot) location.getWorld().spawnEntity(location, EntityType.PARROT);
        pigeon.setVariant(Parrot.Variant.GRAY);
        pigeon.setInvulnerable(true);
        Bukkit.getMobGoals().removeGoal(pigeon,VanillaGoal.LAND_ON_OWNERS_SHOULDER);
        pigeon.setOwner(player);
        entity = pigeon;
        owner = player;
    }

    //Mở inventory của entity
    public void openInventory(Player player){
        player.openInventory(inventory);
    }

    /*Làm cho entity bay đi trong vòng 3 giây
    * Nếu không có entity console sẽ báo warning. Hiện vẫn chưa gặp bất kỳ bug nào dưới trường hợp trên*/
    public void flyAway(){
        if (entity == null)
            return;
        Pathfinder pathfinder = entity.getPathfinder();
        pathfinder.moveTo(getLocationInFrontOfPlayer(owner));

        entity.setTamed(false);
        int id = getServer().getScheduler().scheduleSyncDelayedTask(Pigeon.getPlugin(Pigeon.class), new Runnable() {
            @Override
            public void run() {
                entity.remove();
                entity = null;
                owner = null;
            }
        },60);

    }


    /*Gửi entity vật lý đến người chơi khác
    * Method này khác với method come(Player) ở chỗ sẽ tính toán thời gian bay từ Owner đến Target.
    * Sau 30 giây entity sẽ tự động bay khỏi Owner và chạy đến Target NGAY LẬP TỨC*/
    public void send(Player owner, Player target){
        Location loc = owner.getLocation();
        Double distance = target.getLocation().distance(loc);
        flyAway();
        this.state = 1;
        int id = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Pigeon.getPlugin(Pigeon.class), new Runnable() {
            @Override
            public void run() {
                state = 2;
                come(target);
                int id = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Pigeon.getPlugin(Pigeon.class), new Runnable() {
                    @Override
                    public void run() {
                        flyAway();
                        state = 0;
                    }
                },60+20*30);
            }
        },60+20*distance.longValue());
    }


    //Tính Location cách người chơi 10 block
    public static Location getLocationInFrontOfPlayer(Player player) {
        Location playerLocation = player.getLocation();
        // Tính toán vị trí mới
        Location newLocation = playerLocation.add(10,10,10);
        return newLocation;
    }

    //Lấy thuộc tính entity vật lý
    public Entity getEntity(){
        return this.entity;
    }

    //Lấy thuộc tính state
    public int getState(){
        return this.state;
    }

}
