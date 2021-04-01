package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerOnPlayerJoin implements Listener {

    public ListenerOnPlayerJoin(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (!TFGlobal.playerProgress.containsKey(e.getPlayer().getUniqueId())) {

            TFGlobal.playerProgress.put(e.getPlayer().getUniqueId(), TFBoss.Type.NAGA);
        }

    }

}
