package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.maprenderers.TFMagicMapRenderer;
import com.crazicrafter1.tfplugin.maprenderers.TFMapRenderer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

public class TFMapManager implements TFManager {


    public static HashMap<TFStructureManager.Type, BufferedImage> mapImages = new HashMap<>();


    @Override
    public void onEnable(Main plugin) {

        new BukkitRunnable() {
            @Override
            public void run() {
                String path = "/resources/map_images" + "/";

                final String[] icon_names = new String[]{
                        "aurora_palace",
                        "castle",
                        "cloud_cottage",
                        "dark_tower",
                        "goblin_knight_stronghold",
                        "hedge_maze",
                        "hollow_hill_large",
                        "hollow_hill_medium",
                        "hollow_hill_small",
                        "hydra_lair",
                        "labyrinth",
                        "lich_tower",
                        "naga_courtyard",
                        "quest_grove",
                        "yeti_lair"};


                try {
                    TFMapRenderer.TEMP_GREEN = ImageIO.read(Main.class.getResourceAsStream(path + "temp_green.png"));
                } catch (Exception e) {}


                System.out.println(ChatColor.GRAY + "Loading icons...");

                for (String icon : icon_names) {
                    try {
                        String resourcePath = path + icon + ".png";

                        InputStream in = Main.class.getResourceAsStream(resourcePath);

                        if (in == null) {
                            System.out.println(ChatColor.RED + icon + " cannot be found at " + resourcePath);
                            continue;
                        }

                        BufferedImage img = ImageIO.read(in);

                        if (img == null) {
                            System.out.println(ChatColor.RED + icon + " cannot be found at " + resourcePath);
                            continue;
                        }

                        mapImages.put(TFStructureManager.Type.valueOf(icon.replaceAll("", "").toUpperCase()), img);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.run();


    }

    @Override
    public void onDisable(Main plugin) {

    }

    public static boolean itemToMap(Player p, Main plugin) {

        //TFMapRenderer renderer = null;

        ItemStack itemStack = p.getInventory().getItemInMainHand();

        /*
        if (TFItemManager.tfitems.get(TFItemEnum.MAGIC_MAP).isSameItem(itemStack))
            renderer = new TFMagicMapRenderer();
        else return false;

         */

        //itemStack.setAmount(itemStack.getAmount()-1);

        //ItemStack map = TFItemManager.tfitems.get(TFItemEnum.FILLED_MAGIC_MAP).getItem();

        itemStack.setType(TFItems.filledMagicMap.getMaterial());
        Util.setName(itemStack, TFItems.filledMagicMap.getName());

        //else if (TFItemManager.tfitems.get(TFItemEnum.FILLED_MAZE_MAP).isSameItem(item))
        //  renderer = new TFMazeMapRenderer();



        new BukkitRunnable() {
            @Override
            public void run() {

                MapMeta meta = (MapMeta)(itemStack.getItemMeta()); //int id = ((Damageable)item).getDamage();

                MapView view = meta.getMapView();

                //System.out.println("Meta is null : " + (meta == null));
                int centerX = Util.getCenter(p.getLocation().getBlockX());
                int centerZ = Util.getCenter(p.getLocation().getBlockZ());
                view.setCenterX(centerX);
                view.setCenterZ(centerZ);
                MapView mapView = Bukkit.getServer().getMap(meta.getMapId());

                //noinspection ConstantConditions
                mapView.getRenderers().clear();

                //noinspection
                //mapView.addRenderer(renderer);
                validateMap(itemStack);


            }
        }.runTaskLater(plugin, 5);
        return true;
    }



    public static boolean validateMap(ItemStack item) {

        if (item.getItemMeta() instanceof MapMeta) {

            TFMapRenderer renderer = null;

            if (TFItems.filledMagicMap.isSameItem(item))
                renderer = new TFMagicMapRenderer();

            //System.out.println(item.getType() + " " + item.getItemMeta().getDisplayName());

            if (renderer == null)
                return false;

            MapMeta mapMeta = ((MapMeta)item.getItemMeta());

            if (mapMeta.hasMapView()) {
                if (mapMeta.getMapView().getRenderers().size() > 0 && !(mapMeta.getMapView().getRenderers().get(0) instanceof TFMapRenderer)) {
                    for (MapRenderer mapRenderer : mapMeta.getMapView().getRenderers()) mapMeta.getMapView().removeRenderer(mapRenderer);
                    mapMeta.getMapView().addRenderer(renderer);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void update(Main plugin) {

        for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {

            // then make sure item is valid
            TFMapManager.validateMap(player.getInventory().getItemInMainHand());
        }
    }
}
