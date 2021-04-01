package com.crazicrafter1.tfplugin.item;

import com.crazicrafter1.tfplugin.item.items.*;

import java.util.HashMap;

public class TFItems {
    public static HashMap<TFItemEnum, TFItem> item_registry = new HashMap<>();

    public static TFItemMagicMapFocus magicMapFocus = new TFItemMagicMapFocus();
    public static TFItemMagicMap magicMap = new TFItemMagicMap();
    public static TFItemFilledMagicMap filledMagicMap = new TFItemFilledMagicMap();
    public static TFItemRavenFeather ravenFeather = new TFItemRavenFeather();
    public static TFItemTorchberries torchberries = new TFItemTorchberries();

    public static void registerItems() {
        item_registry.put(TFItemEnum.MAGIC_MAP_FOCUS, magicMapFocus);
        item_registry.put(TFItemEnum.MAGIC_MAP, magicMap);
        item_registry.put(TFItemEnum.FILLED_MAGIC_MAP, filledMagicMap);
        item_registry.put(TFItemEnum.RAVEN_FEATHER, ravenFeather);
        item_registry.put(TFItemEnum.TORCHBERRIES, torchberries);
    }



}
