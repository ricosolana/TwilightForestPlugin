package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.portal.TFPortal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ListenerEntityDropItem implements Listener {

    public ListenerEntityDropItem(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {

        //((Player)e.getEntity()).sendMessage("Dropped an item");
        //e.getEntity().getItemStack()
        e.getPlayer().sendMessage("Dropped item");

        Item item = e.getItemDrop();

        if (item.getItemStack().getType() == Material.DIAMOND) {

            e.getPlayer().sendMessage("Dropped diamond");

            new BukkitRunnable() {
                @Override
                public void run() {
                    new TFPortal(item.getLocation()).ignitePortal(item);
                }
            }.runTaskLater(Main.getInstance(), 40);

        }

    }

}
