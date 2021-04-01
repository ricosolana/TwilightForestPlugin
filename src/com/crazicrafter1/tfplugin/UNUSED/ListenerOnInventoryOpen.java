package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ListenerOnInventoryOpen implements Listener {

    public ListenerOnInventoryOpen(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        Player p = (Player)e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) return;

        Biome biome = e.getBlock().getBiome();

        if (TFBiomeManager.biomeBosses.containsKey(biome)) {

            //p.sendMessage(ChatColor.GRAY + "Destroyed block in boss biome " + biome.name());

            TFBoss.Type required = TFProgression.biomeToBoss(biome);

            TFBoss.Type current = TFGlobal.playerProgress.get(e.getPlayer().getUniqueId());

            if (required == current || TFProgression.isGreater(current, required)) {
                return;
            } else e.setCancelled(true);
        }

    }

}
