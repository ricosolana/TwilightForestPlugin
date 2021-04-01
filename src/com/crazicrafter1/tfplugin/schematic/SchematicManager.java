package com.crazicrafter1.tfplugin.schematic;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.Util;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.*;

public class SchematicManager {

    //public static void saveAsSchematic(Location location1, Location location2, String name) {
    //    saveAsSchematic(location1.getWorld(), location1.getBlockX(), location1);
    //}

    public static HashMap<String, Schematic> schematics = new HashMap<>();

    public static void saveSchematic(Schematic schematic) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    //saveAsSchematic(player.getWorld(), x1, y1, z1, x2, y2, z2, name);

                    String f = Main.getInstance().getDataFolder() + "\\schematics\\" + schematic.getName() + ".tfs";

                    File file = new File(f);

                    file.delete();

                    //p.sendMessage(ChatColor.GOLD + "Logging blocks to file : '" + file + "'");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                    // write the dimensions of the schematic as a header
                    //String header = (Math.abs(x2-x1)+1) + "," + (Math.abs(y2-y1)+1) + "," + (Math.abs(z2-z1)+1);

                    //String header = (Math.abs(x2-x1)+1) + "," + (Math.abs(z2-z1)+1);

                    int sx = schematic.getSize()[0];
                    int sy = schematic.getSize()[1];
                    int sz = schematic.getSize()[2];

                    String header = sx + "," + sy + "," + sz;
                    writer.append(header);

                    writer.newLine();

                    int total = sx*sy*sz;


                    for (int y=0; y<sy; y++) {
                        for (int x = 0; x < sx; x++) {
                            for (int z=0; z<sz; z++) {
                                writer.append(" " + schematic.getBlocks()[y][x][z]);
                            }
                        }
                        writer.newLine();
                    }

                    writer.close();

                    //System.out.println("" + total + " = " + i + "?");
                } catch (Exception e) {
                    //p.sendMessage(ChatColor.RED + "An error occurred while attempting to log.");
                }
            }
        }.run();
    }

    /*
    public static void saveAsSchematic(World world, int x1, int y1, int z1, int x2, int y2, int z2, String name) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    String file = Main.getInstance().getDataFolder() + "\\schematics\\" + name + ".tfs";

                    //p.sendMessage(ChatColor.GOLD + "Logging blocks to file : '" + file + "'");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                    // write the dimensions of the schematic as a header
                    String header = (Math.abs(x2-x1)+1) + "," + (Math.abs(y2-y1)+1) + "," + (Math.abs(z2-z1)+1);
                    writer.append(header);
                    writer.newLine();


                    int xa = Math.min(x1, x2);
                    int ya = Math.min(y1, y2);
                    int za = Math.min(z1, z2);

                    int xb = Math.max(x1, x2);
                    int yb = Math.max(y1, y2);
                    int zb = Math.max(z1, z2);

                    int total = ((xb-xa+1)*(yb-ya+1)*(zb-za+1));

                    System.out.println("Writing from " + ChatColor.GOLD + xa + " " + ya + " " + za + ChatColor.RESET + " TO " + ChatColor.GOLD + xb + " " + yb + " " + zb);
                    System.out.println("Total of " + total + " blocks in area");

                    int i=0;
                    for (int y=ya; y<yb+1; y++) {
                        for (int x=xa; x<xb+1; x++) {
                            for (int z=za; z<zb+1; z++) {
                                // crypt it
                                // then write blocks to file
                                String b = world.getBlockAt(x, y, z).getBlockData().getMaterial().toString().toLowerCase();
                                if (NMSHandler.blocks.containsKey(b)) {
                                    int block = NMSHandler.blocks.get(b);
                                    writer.append(block + ",");
                                    //System.out.println("WRITING!"+block);
                                } else System.out.println(ChatColor.RED + "ERROR : Block " + b + " cannot be identified");
                                i++;
                            }
                            writer.newLine();
                        }
                        writer.newLine();
                    }

                    writer.close();

                    //System.out.println("" + total + " = " + i + "?");
                } catch (Exception e) {
                    //p.sendMessage(ChatColor.RED + "An error occurred while attempting to log.");
                }
            }
        }.run();
    }

     */

    public static void saveArea(String name, World world, int x1, int y1, int z1, int x2, int y2, int z2, Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    //saveAsSchematic(player.getWorld(), x1, y1, z1, x2, y2, z2, name);

                    String f = Main.getInstance().getDataFolder() + "\\schematics\\" + name + ".tfs";

                    File file = new File(f);

                    file.delete();

                    //p.sendMessage(ChatColor.GOLD + "Logging blocks to file : '" + file + "'");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                    // write the dimensions of the schematic as a header
                    //String header = (Math.abs(x2-x1)+1) + "," + (Math.abs(y2-y1)+1) + "," + (Math.abs(z2-z1)+1);

                    int xSize = (Math.abs(x2-x1)+1);
                    int ySize = (Math.abs(y2-y1)+1);
                    int zSize = (Math.abs(z2-z1)+1);

                    String header = xSize + "," + ySize + "," + zSize;
                    writer.append(header);

                    writer.newLine();

                    int xStart = Math.min(x1, x2);
                    int yStart = Math.min(y1, y2);
                    int zStart = Math.min(z1, z2);

                    int xEnd = xStart + xSize;
                    int yEnd = yStart + ySize;
                    int zEnd = zStart + zSize;

                    int total = xSize*ySize*zSize;

                    //System.out.println("Writing from " + ChatColor.GOLD + xa + " " + ya + " " + za + ChatColor.RESET + " TO " + ChatColor.GOLD + xb + " " + yb + " " + zb);
                    //System.out.println("Total of " + total + " blocks in area");

                    player.sendMessage(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Saving area " + ChatColor.GRAY + xStart + " " + yStart + " " + zStart +
                            ChatColor.DARK_GRAY + " to " + ChatColor.GRAY + xEnd + " " + yEnd + " " + zEnd + ChatColor.DARK_GRAY + " as schematic " + ChatColor.GRAY + "(" + total + " blocks).");

                    //World cworld = ((CraftWorld)player.getWorld()).getHandle();

                    int[][][] blocks = new int[ySize][xSize][zSize];

                    for (int y=0; y<ySize; y++) {
                        for (int x=0; x<xSize; x++) {
                            for (int z=0; z<zSize; z++) {
                                // write id to file
                                int block = 0; //NMSHandler.getBlockID(world, x+xStart, y+yStart, z+zStart);
                                //player.sendMessage("" + block);
                                writer.append(block + ",");

                                blocks[y][x][z] = block;
                            }
                            //writer.newLine();
                        }
                        writer.newLine();
                    }

                    writer.close();

                    player.sendMessage(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Saved area as " + name + ".tfs");

                    SchematicManager.schematics.put(name, new Schematic(name, blocks));



                    //System.out.println("" + total + " = " + i + "?");
                } catch (Exception e) {
                    //p.sendMessage(ChatColor.RED + "An error occurred while attempting to log.");
                    e.printStackTrace();
                }
            }
        }.run();
    }

    public static void loadAll() {
        File folder = new File(Main.getInstance().getDataFolder() + "\\schematics");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].getName().contains(".tfs")) {
                    System.out.println("Loaded schematic : " + loadSchematic(listOfFiles[i].getName()).getName());
                    //System.out.println("Loaded schematic '" + listOfFiles[i].getName() + "'");
                }
            }
        }
    }

    public static Schematic loadSchematic(String file) {
        try {
            String name = file.replaceAll(".tfs", "").toLowerCase();

            BufferedReader reader = new BufferedReader(new FileReader(Main.getInstance().getDataFolder() + "\\schematics\\" + name + ".tfs"));

            String size = reader.readLine();

            int xSize = Util.safeToInt(size.split(",")[0]);
            int ySize = Util.safeToInt(size.split(",")[1]);
            int zSize = Util.safeToInt(size.split(",")[2]);

            String[] blocks = new String[ySize];

            int end = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                blocks[end] = line;
                end++;
            }

            reader.close();

            Schematic schematic = new Schematic(name, xSize, ySize, zSize, blocks);

            schematics.put(name, schematic);
            return schematic;

        } catch (
                Exception e) {
            //player.sendMessage(ChatColor.RED + "ERROR : " + ChatColor.GRAY + e.getMessage());
            e.printStackTrace();
        }
        return null;

    }

}
