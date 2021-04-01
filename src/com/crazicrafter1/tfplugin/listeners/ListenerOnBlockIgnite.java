package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.portal.TFPortal;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class ListenerOnBlockIgnite implements Listener {

    public ListenerOnBlockIgnite(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    //@EventHandler
    //public void onBlockIgnite(BlockIgniteEvent e) {
    //    if (e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
    //        try {
    //            if (new TFPortal().isValidPortal(e.getIgnitingBlock().getLocation())) {
    //                e.setCancelled(true);
    //            }
    //        } catch (Exception err) {}
    //    }
    //}

}
