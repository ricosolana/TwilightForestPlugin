package com.crazicrafter1.tfplugin;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.util.TaskManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;

public class BlockUtils {

    public static void setStairs(Material material, BlockFace facing, Stairs.Shape shape, World w, int x, int y, int z) {

        TaskManager.IMP.async(new Runnable() {
            @Override
            public void run() {
                // Create or load a world async with the provided WorldCreator settings
                //AsyncWorld world = AsyncWorld.create(new WorldCreator("MyWorld"));
                AsyncWorld world = AsyncWorld.wrap(w); // Or wrap existing world

                Block block = world.getBlockAt(x, y, z);
                block.setType(material);
                ((Stairs) block).setFacing(facing);
                ((Stairs)block).setShape(shape); //.setBlockData(data);

                // When you are done
                world.commit();
            }
        });
    }

    /*
    public static void setBlock(Material material, BlockData data, Location location) {

        TaskManager.IMP.async(new Runnable() {
            @Override
            public void run() {
                // Create or load a world async with the provided WorldCreator settings
                //AsyncWorld world = AsyncWorld.create(new WorldCreator("MyWorld"));
                AsyncWorld world = AsyncWorld.wrap(location.getWorld()); // Or wrap existing world
                Block block = world.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
                block.setType(material);
                //block.setBlockData(data);
                // When you are done
                world.commit();
            }
        });

    }
     */

    public static void setBlock(Material material, World w, int x, int y, int z) {

        TaskManager.IMP.async(new Runnable() {
            @Override
            public void run() {
                // Create or load a world async with the provided WorldCreator settings
                //AsyncWorld world = AsyncWorld.create(new WorldCreator("MyWorld"));
                AsyncWorld world = AsyncWorld.wrap(w); // Or wrap existing world

                Block block = world.getBlockAt(x, y, z);
                block.setType(material);
                //block.setBlockData(data);
                // When you are done
                world.commit();
            }
        });

    }

}
