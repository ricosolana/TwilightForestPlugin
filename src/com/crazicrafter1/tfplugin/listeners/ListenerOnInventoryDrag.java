package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.crafting.TFCrafting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class ListenerOnInventoryDrag implements Listener {

    public ListenerOnInventoryDrag(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {
            TFCrafting.crafting.get(p.getUniqueId()).onInventoryDrag(e);
        }
    }

}
