package com.crazicrafter1.tfplugin.schematic;

import com.crazicrafter1.tfplugin.Util;
import org.bukkit.ChatColor;
import org.bukkit.World;

import java.io.*;
import java.util.ArrayList;

public class Schematic {

    private int xSize;
    private int ySize;
    private int zSize;
    int[][][] blocks;
    String name;

    public enum Alignment {
        PPP, //also default
        PPN, PNP, NPP,
        PNN, NNP, NPN,
        NNN,
        CENTER,
        HCENTER
    }

    public Schematic(String name, InputStream in) {
        this.name = name;



        ArrayList<ArrayList<ArrayList<Integer>>> temp = new ArrayList<>();


        int x=0; int y=0; int z=0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String size = reader.readLine();

            Integer xSize = Util.safeToInt(size.split(",")[0]);
            Integer ySize = Util.safeToInt(size.split(",")[1]);
            Integer zSize = Util.safeToInt(size.split(",")[2]);

            if (xSize == null || ySize == null || zSize == null || xSize == 0 || ySize == 0 || zSize == 0) {

                System.out.println(ChatColor.RED + "Schematic " + name + " has a malformed size or a size of 0");
                return;
            }

            this.xSize = xSize;
            this.ySize = ySize;
            this.zSize = zSize;

            System.out.println("Loading " + name + "(" + xSize + " " + ySize + " " + zSize + ")");

            this.blocks = new int[ySize][xSize][zSize];

            String line;
            while ((line = reader.readLine()) != null) {
                int[][] horizontal = new int[xSize][zSize];

                for (String strID : line.split(",")) {
                    //System.out.println(strID);
                    Integer id = Util.safeToInt(strID);

                    if (id == null) {
                        System.out.println(ChatColor.RED + "Block ID " + strID + " could not be passed to int");
                        return;
                    }

                    horizontal[x][z] = id;

                    if (z == zSize-1) {
                        z = 0;
                        x++;
                    } else z++;
                }
                this.blocks[y] = horizontal;
                //z = 0;
                x=0;
                y++;
            }

        } catch (Exception e) {e.printStackTrace();}



    }

    public Schematic(String name, int xSize, int ySize, int zSize, String[] blocks) {
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;

        this.blocks = new int[ySize][xSize][zSize];

        int x=0; int y=0; int z=0;

        for (String h : blocks) {
            int[][] horizontal = new int[xSize][zSize];

            for (String strID : h.split(",")) {
                //System.out.println(strID);
                int id = Util.safeToInt(strID);

                horizontal[x][z] = id;

                if (z == zSize-1) {
                    z = 0;
                    x++;
                } else z++;
            }
            this.blocks[y] = horizontal;
            //z = 0;
            x=0;
            y++;
        }
    }

    public Schematic(String name, int[][][] blocks) {
        this.name = name;
        this.xSize = blocks[0].length;
        this.ySize = blocks.length;
        this.zSize = blocks[0][0].length;
        this.blocks = blocks;
    }

    public String getName() {
        return name;
    }

    public int[] getSize() {
        return new int[] {xSize, blocks.length, zSize};
    }

    public int[][][] getBlocks() {
        return blocks;
    }

    public void paste(World world, int x, int y, int z, boolean setAir) {
        paste(world, x, y, z, setAir, Alignment.PPP);
        /*
        for (int cy=0; cy<ySize; cy++) {
            for (int cx=0; cx<xSize; cx++) {
                for (int cz=0; cz<zSize; cz++) {
                    int id = blocks[cy][cx][cz];
                    if (!NMSHandler.setBlock(id, p.getWorld(), x+cx, y+cy, z+cz)) {
                        p.sendMessage(ChatColor.RED + "ERROR : " + ChatColor.GRAY + "There was an error setting block with id " + id + ".");
                    }
                }
            }
        }

         */
    }

    public void paste(World world, int x, int y, int z, boolean setAir, Alignment a) {

        int nx=0;
        int ny=0;
        int nz=0;

        switch (a)
        {
            //case PPP:
            //    nx=0; ny=0; nz=0;
            //    break;
            case NNN:
                nx=-xSize; ny=-ySize; nz=-zSize;
                break;
            case NNP:
                nx=-xSize; ny=-ySize; nz=0;
                break;
            case NPN:
                nx=-xSize; ny=0; nz=-zSize;
                break;
            case NPP:
                nx=-xSize; ny=0; nz=0;
                break;
            case PNN:
                nx=0; ny=-ySize; nz=-zSize;
                break;
            case PNP:
                nx=0; ny=-ySize; nz=0;
                break;
            case PPN:
                nx=0; ny=0; nz=-zSize;
                break;
            case CENTER:
                nx=-xSize/2; ny=-ySize/2; nz=-zSize/2;
                break;
            case HCENTER:
                nx=-xSize/2; nz=-zSize/2;
                break;
        }

        for (int cy=0; cy<ySize; cy++) {
            System.out.println("Setting : " + cy);
            for (int cx=0; cx<xSize; cx++) {
                for (int cz=0; cz<zSize; cz++) {
                    int id = blocks[cy][cx][cz];
                    if (!setAir || id == 0) {
                        //if (!NMSHandler.setBlock(id, world, x + cx + nx, y + cy + ny, z + cz + nz)) {
                        //    System.out.println(ChatColor.RED + "ERROR : " + ChatColor.GRAY + "There was an error setting block with id " + id +
                        //            " at (" + cx + ", " + cy + ", " + cz + ")");
                        //}
                    }
                }
            }
        }

    }

    /*
    public void exportAsArray(String exportName) {

        try {

            File file = new File(Main.getInstance().getDataFolder() + "\\exported\\" + exportName + ".tfx");

            file.delete();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.append("Schematic " + exportName + " = new Schematic(\"" + exportName + "\", " + "\n\tnew int[][][] {{{");

            for (int y=0; y<ySize; y++) {

                for (int x=0; x<xSize; x++) {

                    for (int z=0; z<zSize; z++) {

                        // add
                        writer.append("" + blocks[y][x][z]);

                        if (z < zSize-1) writer.append(", ");
                        else writer.append("}");

                    }

                    if (x < xSize-1) writer.append(", {");
                    else writer.append("}");

                }
                if (y < ySize-1) writer.append(", \n\t{{");
                else writer.append("}");

            }

            writer.append(");");

            writer.close();

        } catch (Exception e) {e.printStackTrace();}

    }


     */
}
