package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.crafting.TFCrafting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerOnInventoryClick implements Listener {

    public ListenerOnInventoryClick(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getView().getPlayer();

        //p.sendMessage((e.getCursor() == null) ? "null" : e.getCursor().getType().toString());

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {
            TFCrafting.crafting.get(p.getUniqueId()).onInventoryClick(e);
        }

    }

}
