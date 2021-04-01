package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFStructureManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ListenerOnChunkLoad implements Listener {

    public ListenerOnChunkLoad(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {

        if (e.getWorld() == TFGlobal.TFWORLD && e.isNewChunk()) {

            int chunkX = e.getChunk().getX(); int chunkZ = e.getChunk().getZ();

            TFBoss.generator.generate(chunkX, chunkZ);

            //Generator.generate(chunkX, chunkZ);
            TFStructureManager.generate(chunkX, chunkZ);

            //e.getChunk().b
            //TFBiomeManager.setBossBiome(e.getChunk().getX(), e.getChunk().getZ());

            //TFStructure.genBasicPlatforms(e.getChunk());

        }


    }




}
