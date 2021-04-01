package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.tabcompleters.TFTabScPaste;
import com.crazicrafter1.tfplugin.tabcompleters.TFTabTFItem;
import com.crazicrafter1.tfplugin.tabcompleters.TFTabTFLocate;

public class TFTabCompleteManager implements TFManager {

    @Override
    public void onEnable(Main plugin) {
        new TFTabTFItem(plugin);
        new TFTabTFLocate(plugin);
        new TFTabScPaste(plugin);
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }
}
