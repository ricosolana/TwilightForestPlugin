package com.crazicrafter1.tfplugin;

import com.crazicrafter1.tfplugin.boss.TFBossEntity;
import com.crazicrafter1.tfplugin.managers.*;
import com.crazicrafter1.tfplugin.managers.TFProgressionManager;
import com.crazicrafter1.tfplugin.schematic.SchematicManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static Main instance;
    public static TFBossEntity boss = null;

    public static World OVERWORLD;

    private ArrayList<TFManager> managers = new ArrayList<>();

    //public static BlockQueue queuePlugin = null;

    @Override
    public void onEnable() {

        //queuePlugin = (BlockQueue) (Bukkit.getPluginManager().getPlugin("BlockQueue"));

        instance = this;

        saveDefaultConfig();
        managers.add(new TFBiomeManager());
        managers.add(new TFBossManager());
        managers.add(new TFCommandManager());
        managers.add(new TFCraftingManager());
        managers.add(new TFDimensionManager());
        managers.add(new TFItemManager());
        managers.add(new TFListenerManager());
        managers.add(new TFMapManager());
        managers.add(new TFProgressionManager());
        managers.add(new TFStructureManager());
        managers.add(new TFTabCompleteManager());

        for (TFManager manager : managers) manager.onEnable(this);

        SchematicManager.loadAll();

        OVERWORLD = Bukkit.getWorld("world");

        org.bukkit.Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (TFManager manager : managers) manager.update(Main.getInstance());

            }
        }, 1, 20);

    }

    @Override
    public void onDisable() {
        //Generator.saveBosses();

        for (TFManager manager : managers) manager.onDisable(this);
        //TFProgressionManager.saveProgress();
    }

    public static World getOther(World w) {
        // most common case first
        if (w == OVERWORLD) {
            return TFGlobal.TFWORLD;
        } else if (w == TFGlobal.TFWORLD) return OVERWORLD;
        return null;
    }

    public static Main getInstance() {
        return instance;
    }

    //world.setBiome(chunkX*16+X, chunkZ*16+X, Biome.FOREST);

}
