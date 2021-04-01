package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.commands.*;

public class TFCommandManager implements TFManager {

    @Override
    public void onEnable(Main plugin) {
        //plugin.getCommand("").setExecutor();
        new TFCmdAdvance();
        new TFCmdLichStructure();
        new TFCmdMapData();
        new TFCmdNagaStructure();
        new TFCmdProgress();
        new TFCmdSpawnBoss();
        new TFCmdTFBossList();
        new TFCmdTFItem();
        new TFCmdTFLocate();
        new TFCmdToggleDim();
        new TFCmdUnAdvance();
        new TFCmdScPaste();
        new TFCmdTFTest();
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }
}
