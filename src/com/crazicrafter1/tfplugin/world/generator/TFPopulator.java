package com.crazicrafter1.tfplugin.world.generator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class TFPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random rand, Chunk chunk) {
        /*
        // Code below crashes server and spams console
        int x = chunk.getX()*16+7+rand.nextInt(20), z = chunk.getZ()*16+7+rand.nextInt(20);
        int y = world.getHighestBlockYAt(x, z);

        new BukkitRunnable() {
            @Override
            public void run() {

                new TFTreeSickly().generate(world, x, y, z);
            }
        }.run();
         */

        /*
        int x = chunk.getX()*16+5+rand.nextInt(14), z = chunk.getZ()*16+5+rand.nextInt(14);
        int y = world.getHighestBlockYAt(x, z);
        TreeType type = rand.nextInt(10) < 3 ? TreeType.BIG_TREE : TreeType.TREE;
        world.generateTree(world.getBlockAt(x, y, z).getLocation(), type);

         */

        //return;

        //.addData(result); // Calls bukkit methods etc
/*
        int x = chunk.getX()*16+7+rand.nextInt(13);
        int z = chunk.getZ()*16+7+rand.nextInt(13);
        int y = (((CraftWorld)world).getHandle()).getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, new BlockPosition(x, 0, z)).getY();
        //int y = world.getHighestBlockYAt(x, z);
        //TreeType type = rand.nextInt(10) < 3 ? TreeType.BIG_TREE : TreeType.TREE;
        //new TFTreeSickly().generate(x, y, z); //world.generateTree(world.getBlockAt(x, y, z).getLocation(), type);
        TFTreeManager.generateTree(TFTreeManager.Type.SICKLY, x, y, z);
 */
        int x = chunk.getX()*16+7+rand.nextInt(13);
        int z = chunk.getZ()*16+7+rand.nextInt(13);
        //int y = world.getHighestBlockYAt(x, z);
        //TreeType type = rand.nextInt(10) < 3 ? TreeType.BIG_TREE : TreeType.TREE;
        //new TFTreeSickly().generate(x, y, z); //world.generateTree(world.getBlockAt(x, y, z).getLocation(), type);
        //TFTreeManager.generateTree(TFTreeManager.Type.SICKLY, x, y, z);
        int y = getHighestBlockYAt(world, x, z, new HashSet<>(Collections.singleton(Material.GRASS_BLOCK)));
        world.generateTree(new Location(world, x, y, z), TreeType.TALL_REDWOOD);
        //world.get

    }

    private int getHighestBlockYAt(World world, int x, int z, HashSet<Material> filter) {

        int height = world.getHighestBlockYAt(x, z);
        while(!filter.contains(world.getBlockAt(x, height, z).getType()) && height > 0)
            height--;

        return height;

    }

}