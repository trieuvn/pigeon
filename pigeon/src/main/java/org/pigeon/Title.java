package org.pigeon;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.time.Duration;

public class Title {
    //Gửi title
    public static void send(Player player, String title, String subtitle, Duration fadeIn, Duration stay, Duration fadeOut){
        Component titleComponent = Component.text(title);
        Component subtitleComponent = Component.text(subtitle);

        net.kyori.adventure.title.Title titleMessage = net.kyori.adventure.title.Title.title(titleComponent, subtitleComponent, net.kyori.adventure.title.Title.Times.times(fadeIn,stay,fadeOut));

        player.showTitle(titleMessage);
    }
}
