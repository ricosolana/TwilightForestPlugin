package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ListenerOnBlockBreak implements Listener {

    public ListenerOnBlockBreak(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        // see if player incProgress is enough


        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) return;

        Biome biome = e.getBlock().getBiome();

        if (TFBiomeManager.biomeBosses.containsKey(biome)) {

            //p.sendMessage(ChatColor.GRAY + "Destroyed block in boss biome " + biome.name());

            TFBoss.Type required = TFProgression.biomeToBoss(biome);

            TFBoss.Type current = TFGlobal.playerProgress.get(p.getUniqueId());

            if (required == current || TFProgression.isGreater(current, required)) {

                return;
            } else e.setCancelled(true);
        }

        //BlockDamageEvent q;

        /*
        for (int i=0; i<360; i+=90) {
            int x = e.getBlock().getX()+Util.fastCos(i);
            int z = e.getBlock().getX()+Util.fastSin(i);
            if (TFPortalManager.isValidPortal(new Location(e.getPlayer().getWorld(), x, e.getBlock().getY(), z))) {

                // then break portal
                // aka set to water
                NMSHandler.setBlock()

            }
        }

         */

    }

}
