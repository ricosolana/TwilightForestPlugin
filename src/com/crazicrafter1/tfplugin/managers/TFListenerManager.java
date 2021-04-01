package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.listeners.*;

public class TFListenerManager implements TFManager {

    @Override
    public void onEnable(Main plugin) {
        new ListenerOnBlockBreak(plugin);
        //new ListenerOnBlockIgnite(plugin);
        new ListenerOnBlockPlace(plugin);
        new ListenerOnChunkLoad(plugin);
        new ListenerOnInventoryClick(plugin);
        new ListenerOnInventoryClose(plugin);
        new ListenerOnInventoryDrag(plugin);
        new ListenerEntityDropItem(plugin);
        new ListenerOnPlayerInteract(plugin);
        new ListenerOnPlayerJoin(plugin);
        new ListenerOnPlayerPortal(plugin);
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }
}
