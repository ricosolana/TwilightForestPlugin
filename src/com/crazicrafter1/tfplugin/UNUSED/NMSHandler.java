package com.crazicrafter1.tfplugin.world;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.v1_14_R1.Block;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.Blocks;
import net.minecraft.server.v1_14_R1.IBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NMSHandler {

    public static int getBlockID(World world, int x, int y, int z) {
        IBlockData ibd = ((CraftBlock)world.getBlockAt(x, y, z)).getNMS();
        return Block.getCombinedId(ibd);
    }

    public static boolean setBlock(Block block, World world, int x, int y, int z) {
        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = block.getBlockData(); //net.minecraft.server.v1_14_R1.Block.getByCombinedId(combinedID);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setBlock(int combinedID, World world, int x, int y, int z) {
        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combinedID);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setBlock(int combinedID, Location location) {
        return setBlock(combinedID, location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static void setBox(int combinedID, World world, int x1, int y1, int z1, int x2, int y2, int z2, boolean outline, boolean replaceAirOnly) {

        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (!replaceAirOnly || getBlockID(world, x, y, z) == 0) {
                        if (outline) {
                            if (y > startY && y < endY) {
                                if (x > startX && x < endX && z > startZ && z < endZ)
                                    NMSHandler.setBlock(combinedID, world, x, y, z);
                            } else NMSHandler.setBlock(combinedID, world, x, y, z);
                        } else NMSHandler.setBlock(combinedID, world, x, y, z);
                    }

                }
            }
        }
    }

    public static void setBox(int combinedID, World world, int x1, int y1, int z1, int x2, int y2, int z2, boolean outline, int replaceBlock) {

        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (getBlockID(world, x, y, z) == replaceBlock) {
                        if (outline) {
                            if (y > startY && y < endY) {
                                if (x > startX && x < endX && z > startZ && z < endZ)
                                    NMSHandler.setBlock(combinedID, world, x, y, z);
                            } else NMSHandler.setBlock(combinedID, world, x, y, z);
                        } else NMSHandler.setBlock(combinedID, world, x, y, z);
                    }

                }
            }
        }
    }

    /*
    @Deprecated
    public static void setBlock(String block, World world, int x, int y, int z) {
        if (!blocks.containsKey(block.toLowerCase())) return;

        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        int combined = blocks.get(block.toLowerCase()) + ((byte)0 << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setBlock(String block, Location location) {
        if (!blocks.containsKey(block.toLowerCase())) return;

        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) location.getWorld()).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        int combined = blocks.get(block.toLowerCase()) + ((byte)0 << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setBlock(Block block, World world, int x, int y, int z) {
        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        //int combined = Bukkit.blblocks.get(material.getData().getName().toLowerCase()) + ((byte)0 << 12);
        IBlockData ibd = block.getBlockData();
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBlock(Material material, World world, int x, int y, int z) {
        if (!material.isBlock()) return;

        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        int combined = blocks.get(material.getData().getName().toLowerCase()) + ((byte)0 << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Deprecated
    public static int getID(Block block) {
        //Block block1 = BlockIDs.TNT;
        return net.minecraft.server.v1_14_R1.Block.getCombinedId(block.getBlockData());
    }

    @Deprecated
    public static void setBlock(Material material, Location location) {
        if (!material.isBlock()) return;

        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) location.getWorld()).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        int combined = blocks.get(material.getData().getName().toLowerCase()) + ((byte)0 << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setBlock(int blockId, byte data, World world, int x, int y, int z) {
        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        int combined = blockId + (data << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        //ibd.
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static void setBlock(int blockId, byte data, Location location) {
        net.minecraft.server.v1_14_R1.World w = ((CraftWorld) location.getWorld()).getHandle();
        net.minecraft.server.v1_14_R1.Chunk chunk = w.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        int combined = blockId + (data << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        w.setTypeAndData(bp, ibd, 2);

        try {
            chunk.setType(bp, ibd, false); // FLAG???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static String getBlockName(int blockId, byte data) {
        int combined = blockId + (data << 12);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(combined);
        //ibd.getBlock().get
        //return blockId + "/" + data + " : " + ibd.getBlock().getItem().getName().replaceAll("block.minecraft.", ""); //.getMaterial().toString();
        return ibd.getBlock().getItem().getName().replaceAll("block.minecraft.", ""); //.getMaterial().toString();
    }
     */
}
