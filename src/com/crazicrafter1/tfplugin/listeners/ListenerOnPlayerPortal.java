package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.portal.TFPortal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class ListenerOnPlayerPortal implements Listener {

    public ListenerOnPlayerPortal(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {

        e.setCancelled(true);

        Player p = e.getPlayer();

        TFPortal.Result result = TFPortal.teleport(p);

        //if (result == TFPortal.Result.INVALID_PORTAL) e.setCancelled(false);

        p.sendMessage(result.name());
        /*

        if (TFPortalManager.isValidPortal(p.getLocation())) {

            e.setCancelled(true);

            if (!TFPortalManager.tpIfExists(p)) {

                TFPortalManager.createAndTP(p);

            }
        }

         */

    }
}
