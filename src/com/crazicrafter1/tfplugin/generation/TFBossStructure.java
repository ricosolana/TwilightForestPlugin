package com.crazicrafter1.tfplugin.generation;

import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.World;

// was interface
public class TFBossStructure {
    //TFBoss.Type getType();

    protected int x, z;

    public TFBossStructure(TFBoss.Type type, int x, int z) {
        this.x = x;
        this.z = z;
    }

    public void generate() {

    }
}
