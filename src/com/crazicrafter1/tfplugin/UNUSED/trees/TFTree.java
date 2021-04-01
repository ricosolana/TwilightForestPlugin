package com.crazicrafter1.tfplugin.world.trees;

import com.crazicrafter1.tfplugin.world.NMSHandler;
import net.minecraft.server.v1_14_R1.Block;
import net.minecraft.server.v1_14_R1.Blocks;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

public abstract class TFTree {

    private int minHeight;
    private int maxHeight;
    private int mainBlock;
    private int leafblock;
    private int[][][] structure;

    public TFTree(int minHeight, int maxHeight, Block mainBlock, Block leafBlock) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.mainBlock = Block.getCombinedId(mainBlock.getBlockData());
        this.leafblock = Block.getCombinedId(leafBlock.getBlockData());
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMainBlock() {
        return mainBlock;
    }

    public int getLeafblock() {
        return leafblock;
    }

    public int[][][] getStructure() {
        return structure;
    }

    //public abstract void generate(World world, int x, int y, int z);
    public abstract void generate(int x, int y, int z);


    /*
    protected void setBlocks(World world, int ax, int ay, int az) {
        for (int y=0; y<structure.length; y++) {
            for (int x=0; x<structure[0].length; x++) {
                for (int z=0; z<structure[0][0].length; z++) {
                    NMSHandler.setBlock(structure[y][x][z], world, ax, ay, az);
                }
            }
        }
    }

     */

}
