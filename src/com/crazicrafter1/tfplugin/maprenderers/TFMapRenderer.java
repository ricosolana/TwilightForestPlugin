package com.crazicrafter1.tfplugin.maprenderers;

import org.bukkit.map.MapRenderer;

import java.awt.image.BufferedImage;

public abstract class TFMapRenderer extends MapRenderer {

    protected TFMapRenderer() {

        super();


    }

    public static BufferedImage TEMP_GREEN;

    //abstract public boolean itemToMap(ItemStack item);

    //abstract public boolean forceConvertMap(ItemStack item);



    /*
    public static boolean forceConvertMap(ItemStack item) {


        MapMeta meta = (MapMeta)(item.getItemMeta()); //int id = ((Damageable)item).getDamage();

        //noinspection ConstantConditions,deprecation
        MapView map = Bukkit.getServer().getMap(meta.getMapId());

        //noinspection ConstantConditions
        map.getRenderers().clear();

        try {
            map.addRenderer(new TFMagicMapRenderer());
        } catch (Exception e) {}
        return false;

    }

     */
}
