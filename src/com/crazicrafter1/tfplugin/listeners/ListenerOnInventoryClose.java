package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.crafting.TFCrafting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ListenerOnInventoryClose implements Listener {

    public ListenerOnInventoryClose(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        Player p = (Player)e.getPlayer();

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {

            TFCrafting.crafting.get(p.getUniqueId()).onInventoryClose(e); //returnItems();
        }
    }

}
