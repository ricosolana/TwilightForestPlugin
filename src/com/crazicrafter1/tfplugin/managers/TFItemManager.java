package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.item.TFItemEnum;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.item.items.*;
import com.crazicrafter1.tfplugin.managers.TFManager;

import java.util.HashMap;

public class TFItemManager implements TFManager {

    //public static HashMap<TFItemEnum, TFItem> tfitems = new HashMap<>();
    //public static final TFItem magic_map_focus = new TFItemMagicMapFocus();
    //public static final TFItem magic_map = new TFItemMagicMap();
    //public static final TFItem filled_magic_map = new TFItemFilledMagicMap();

    @Override
    public void onEnable(Main plugin) {
        TFItems.registerItems();
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }
}
