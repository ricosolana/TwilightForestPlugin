package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;

public interface TFManager {

    //protected static Main plugin = Main.getInstance();
    /*
        init a single instance of Main without constantly
        inputting as arg
     */

    //public TFManager() {}

    void onEnable(Main plugin);

    void onDisable(Main plugin);

    void update(Main plugin);

}
