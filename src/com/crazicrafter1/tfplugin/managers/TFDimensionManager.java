package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.world.generator.TFGenerator;
import org.bukkit.WorldCreator;

public class TFDimensionManager implements TFManager {

    @Override
    public void onEnable(Main plugin) {
        TFGlobal.TFWORLD = new WorldCreator(TFGlobal.NAME).generator(new TFGenerator()).createWorld();
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {
        TFGlobal.TFWORLD.setFullTime(23200L);
    }
}
