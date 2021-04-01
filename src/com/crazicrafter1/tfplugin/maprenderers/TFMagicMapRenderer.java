package com.crazicrafter1.tfplugin.maprenderers;

import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.managers.TFMapManager;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFStructureManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;

public class TFMagicMapRenderer extends TFMapRenderer {

    public TFMagicMapRenderer() {}

    //MapCanvas fastCanvas = null;
    private int time = 0;

    @Override
    public void render(MapView view, MapCanvas canvas, Player player) {

        ItemStack item = player.getInventory().getItemInMainHand();

        int mapCenterX = view.getCenterX();
        int mapCenterZ = view.getCenterZ();

        if (item.getType() == Material.AIR) return;

        if (time > 4 && TFItems.filledMagicMap.isSameItem(item)
        && ((MapMeta)item.getItemMeta()).getMapId() == view.getId() && player.getLocation().getWorld() == TFGlobal.TFWORLD) {

            time = 0;

            //org.bukkit.Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
            //    @Override
            //    public void run() {
                    //if (player.getLocation().getWorld() == TFGlobal.TFWORLD) {
                        MapCursorCollection cursors = new MapCursorCollection();

                        MapCursor cursor = getCursor(player, view.getCenterX(), view.getCenterZ());
                        cursors.addCursor(cursor);

                        canvas.setCursors(cursors);
                    //}

                    // loaded = true;
                    //player.sendMessage("Center " + view.getCenterX() + " " + view.getCenterZ());
                    canvas.drawImage(0, 0, TEMP_GREEN);

                    for (TFBoss.Type boss : TFBoss.bossesByType.keySet()) {

                        for (Integer[] loc : TFBoss.bossesByType.get(boss)) {
                            // truncate to be a proportion of 128x128

                            // get a map position
                            // and mapnode position?


                            int locCenterX = (loc[0]/2048)*2048+(Util.sign(loc[0])*1024);
                            int locCenterZ = (loc[1]/2048)*2048+(Util.sign(loc[1])*1024);

                            /*
                            switch (boss) {
                                case HYDRA: canvas.drawImage(x, y, tficons.get(Icon.HYDRA_LAIR)); break;
                                case URGHAST: canvas.drawImage(x, y, tficons.get(Icon.DARK_TOWER)); break;
                                case SNOWQUEEN: canvas.drawImage(x, y, tficons.get(Icon.AURORA_PALACE)); break;
                                case HIGHLANDBOSS: canvas.drawImage(x, y, tficons.get(Icon.CASTLE)); break;
                            }
                             */

                            //player.sendMessage(ChatColor.GREEN + "" + locCenterX + " " + locCenterZ);

                            if (!(locCenterX == mapCenterX && locCenterZ == mapCenterZ)) continue;

                            //int mapX = Math.abs(loc[0]*128/2048)%127;
                            //int mapZ = Math.abs(loc[1]*128/2048)%127;

                            int mapX = mapCoord(loc[0], locCenterX);
                            int mapZ = mapCoord(loc[1], locCenterZ);

                            switch (boss) {
                                case HYDRA: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.HYDRA_LAIR), true); break;
                                case URGHAST: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.DARK_TOWER), true); break;
                                case SNOWQUEEN: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.AURORA_PALACE), true); break;
                                case HIGHLANDBOSS: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.CASTLE), true); break;

                                case MINOSHROOM: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.LABYRINTH), true); break;
                                case KNIGHTPHANTOM: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.GOBLIN_KNIGHT_STRONGHOLD), true); break;
                                case ALPHAYETI: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.YETI_LAIR), true); break;
                                case GIANT: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.CLOUD_COTTAGE), true); break;

                                case NAGA: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.NAGA_COURTYARD), true); break;
                                case LICH: drawImage(canvas, mapX, mapZ, TFMapManager.mapImages.get(TFStructureManager.Type.LICH_TOWER), true); break;
                            }
                        }
                    }
            //    }
            //});

            //new BukkitRunnable() {
            //    @Override
            //    public void run() {



                    //canvas.setPixel(player.getLocation().getBlockX(), player.getLocation().getBlockZ(), (byte)0);

        }
            //}.run();

        //}

        time++;
    }

    private static int mapCoord(int a, int center) {

        int diff = a-center;

        int b = Util.clamp(diff, -1024, 1023) + 1024;
        b = b*128/2048;

        return b;

    }

    private static MapCursor getCursor(Player player, int mapCenterX, int mapCenterZ) {

        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();

        // needs a map reference so this wont be constant
        //int playerMapX = Util.clamp((playerX*128/2048)+(mapCenterX*128/1024), 0, 127);
        //int playerMapZ = (playerZ*128/2048);

        int playerMapX = mapCoord(playerX, mapCenterX);
        int playerMapZ = mapCoord(playerZ, mapCenterZ);

        //player.sendMessage(playerMapX + " " + playerMapZ);

        int xDistance = Math.abs(mapCenterX-playerX);
        int zDistance = Math.abs(mapCenterZ-playerZ);

        MapCursor.Type type = (xDistance > 1536 || zDistance > 1536) ? MapCursor.Type.SMALL_WHITE_CIRCLE :
                ((xDistance >= 1024 || zDistance >= 1024) ? MapCursor.Type.WHITE_CIRCLE : MapCursor.Type.WHITE_POINTER);

        //player.sendMessage("Distance from center : " + xDistance + " " + zDistance);

        byte dir = (type == MapCursor.Type.WHITE_POINTER) ? (byte)Util.clamp(15 - (int) (16*(-player.getLocation().getYaw())/(360.0D)), 0, 15) : 0;

        //player.sendMessage("byte : " + (byte)(playerMapX) + " " + (byte)(playerMapZ));

        return new MapCursor(
                (byte)(Util.clamp((playerMapX)*2-128, -127, 128)),
                (byte)(Util.clamp((playerMapZ)*2-128, -127, 128)),
                dir,
                type,
                true);

         /*

        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();

        player.sendMessage("byte : " + (byte)(playerX) + " " + (byte)(playerZ));

        return new MapCursor(
                (byte)playerX,
                (byte)playerZ,
                (byte)0,
                MapCursor.Type.WHITE_POINTER,
                true);

          */
    }

    private void drawImage(MapCanvas canvas, int x0, int y0, Image img, boolean centered) {
        if (centered && img instanceof BufferedImage)
            drawImage(canvas, x0-((BufferedImage)img).getWidth()/2, y0-((BufferedImage)img).getHeight()/2, img);
        else drawImage(canvas, x0, y0, img);
    }

    private static void drawImage(MapCanvas canvas, int x0, int y0, Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        //int x0 = (128 / 2) - (width / 2);
        //int y0 = (128 / 2) - (height / 2);

        int[] pixels = new int[width * height];
        PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0, width);

        try {
            pg.grabPixels();
            int tel = 0;
            byte color;
            if (pixels.length > 0) {
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int c = pixels[tel];
                        int red = (c & 0x00ff0000) >> 16;
                        int green = (c & 0x0000ff00) >> 8;
                        int blue = c & 0x000000ff;

                        // Don't know if this is the right way to check if the pixel is
                        // transparent, but it worked for me. You can uncomment
                        // the below if part to print the first pixels color to the console.
                        //if(x == 0 && y == 0) {
                        //    System.out.print("  c: " + c);
                        //}
                        if (c>>24 != 0x00) {
                        //if (c != -16777216) {
                            color = getColor(red, green, blue);
                            canvas.setPixel(x0 + x, y0 + y, color);
                        }
                        tel++;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static byte getColor(int r, int g, int b) {
        //noinspection deprecation
        return MapPalette.matchColor(r, g, b);
    }

}
