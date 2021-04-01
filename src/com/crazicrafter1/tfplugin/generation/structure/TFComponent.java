package com.crazicrafter1.tfplugin.generation.structure;

import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.Util;
import org.bukkit.World;

import java.util.ArrayList;

public abstract class TFComponent {

    private static World world = TFGlobal.TFWORLD;
    private final int x, y, z;
    private ComponentFace face;
    private ArrayList<TFComponent> children = new ArrayList<>();

    public TFComponent(int x, int y, int z, ComponentFace face) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public ComponentFace getFace() {
        return face;
    }
    public ArrayList<TFComponent> getChildren() {
        return children;
    }
    public void addChild(TFComponent child) {children.add(child);}

    public abstract void generate();

    public abstract void decorate();

    public abstract TFComponent generateNext(ComponentFace direction);


    /* \ / \ / \ / \ / \ / \ / \ / \ /

           Utility fill methods

     \ / \ / \ / \ / \ / \ / \ / \ / */

    /*
        Fill area  solid with block; optional air mask
     */
    /*
    public final void solidSingleBlockCube(Block block, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        NMSHandler.setBlock(block, world, x, y, z);
                    }

                }
            }
        }
    }

     */

    /*
        Fill area hollow with block; optional air mask
     */
    /*
    public final void hollowSingleBlockCube(Block block, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        if (y > startY && y < endY) {
                            if (!(x > startX && x < endX && z > startZ && z < endZ))
                                NMSHandler.setBlock(block, world, x, y, z);
                        } else NMSHandler.setBlock(block, world, x, y, z);

                    }

                }
            }
        }
    }

     */

    /*
        Fill area hollow with random blocks; optional air mask
     */
    /*
    public final void hollowMultiBlockCube(Block[] blocks, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {
                    Block rBlock = blocks[Util.randomRange(0, blocks.length-1)];

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        if (y > startY && y < endY) {
                            if (!(x > startX && x < endX && z > startZ && z < endZ)) {
                                NMSHandler.setBlock(rBlock, world, x, y, z);
                            }
                        } else NMSHandler.setBlock(rBlock, world, x, y, z);

                    }

                }
            }
        }
    }

     */

    /*
        Fill area solid with random blocks; optional air mask
     */
    /*
    public final void solidMultiBlockCube(Block[] blocks, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {
                    Block rBlock = blocks[Util.randomRange(0, blocks.length-1)];

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        NMSHandler.setBlock(rBlock, world, x, y, z);
                    }

                }
            }
        }
    }

     */

    /*
        Fill area downwards solid until solid hit
     */
    /*
    public final void solidSingleBlockFloodCube(Block block, int x1, int y1, int z1, int x2, int y2, int z2) {

        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int x = startX; x <= endX; x++) {
            for (int z = startZ; z <= endZ; z++) {
                for (int y = endY; y >= startY; y--) {

                    if (NMSHandler.getBlockID(world, x, y, z) == 0) {
                        //int rID = Util.randomRange(0, combinedID.length-1);
                        NMSHandler.setBlock(block, world, x, y, z);
                    } else break;

                }
            }
        }
    }

     */

    /*
        fill a floor with x,y,z as center, and xSize,zSize as the half-sizes.
     */
    /*
    public final void singleBlockFloor(Block block, int x, int y, int z, int xSize, int zSize) {

        for (int sx = x-xSize; sx <= x+xSize; sx++) {
            for (int sz = z-zSize; sz <= z+zSize; sz++) {
                //if (NMSHandler.getBlockID(world, x, y, z) == 0) {
                //int rID = Util.randomRange(0, combinedID.length-1);
                NMSHandler.setBlock(block, world, sx, y, sz);
                //}
            }
        }
    }

     */

    /*
        Fill a single vertical column
     */
    /*
    public final void singleBlockVertColumn(Block block, int x, int y, int z, int yDown) {

        if (yDown<0) {
            for (int h = y; h > y + yDown; h--) {
                NMSHandler.setBlock(block, world, x, h, z);
            }
        } else {
            for (int h = y; h < y + yDown; h++) {
                NMSHandler.setBlock(block, world, x, h, z);
            }

        }
    }

     */

    /*
        Outline an area with solid blocks; optional air mask
     */
    /*
    public final void outlineSingleBlockCube(Block block, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        if (!(x > startX && x < endX && z > startZ && z < endZ)) NMSHandler.setBlock(block, world, x, y, z);
                    }

                }
            }
        }
    }

     */

    /*
        Outline an area with multiple random blocks; optional air mask
     */
    /*
    public final void outlineMultiBlockCube(Block[] blocks, int x1, int y1, int z1, int x2, int y2, int z2, boolean replaceAirOnly) {
        int startX = Math.min(x1, x2);
        int startY = Math.min(y1, y2);
        int startZ = Math.min(z1, z2);

        int endX = Math.max(x1, x2);
        int endY = Math.max(y1, y2);
        int endZ = Math.max(z1, z2);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {

                    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) {
                        if (!(x > startX && x < endX && z > startZ && z < endZ)) {
                            Block rBlock = blocks[Util.randomRange(0, blocks.length-1)];
                            NMSHandler.setBlock(rBlock, world, x, y, z);
                        }
                    }

                }
            }
        }
    }
     */

    /*
        just abstracted setBlock
     */
    //public final void set(Block block, int x, int y, int z, boolean replaceAirOnly) {
    //    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) NMSHandler.setBlock(block, world, x, y, z);
    //}

    /*
        just abstracted setBlock with int
    */
    //public final void set(int block, int x, int y, int z, boolean replaceAirOnly) {
    //    if (!replaceAirOnly || NMSHandler.getBlockID(world, x, y, z) == 0) NMSHandler.setBlock(block, world, x, y, z);
    //}

    @SuppressWarnings("unused")
    public static class BlockIDs {

        public static final int topStoneSlab = 7807;
        public static final int bottomStoneSlab = 7809;
        public static final int doubleStoneSlab = 7811;

        public static final int topBirchSlab = 7777;
        public static final int bottomBirchSlab = 7779;
        public static final int doubleBirchSlab = 7781;

        public static final int stoneBrickStairPX = 4967;
        public static final int stoneBrickStairPZ = 4927;
        public static final int stoneBrickStairNX = 4987;
        public static final int stoneBrickStairNZ = 4947;

        public static final int birchStairPX = 5519;
        public static final int birchStairPZ = 5479;
        public static final int birchStairNX = 5539;
        public static final int birchStairNZ = 5499;

        public static final int ladderPX = 3642;
        public static final int ladderPZ = 3638;
        public static final int ladderNX = 3640;
        public static final int ladderNZ = 3636;

        public static final int chestPX = 2051;
        public static final int chestPZ = 2039;
        public static final int chestNX = 2045;
        public static final int chestNZ = 2033;

    }

    public enum ComponentFace {

        EAST, WEST, NORTH, SOUTH, UP, DOWN, NONE

    }

}
