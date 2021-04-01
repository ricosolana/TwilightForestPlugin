package com.crazicrafter1.tfplugin.generation.structure;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.generation.TFBossStructure;

public class TFAuroraPalace extends TFBossStructure {

    public TFAuroraPalace(int x, int z) {
        super(TFBoss.Type.SNOWQUEEN, x, z);
    }

    @Override
    public void generate() {
        // generate without the schematic because the courtyard is entirely random

        // randomly placed perimeter walls
        // randomly placed bushes
        // randomly placed substructures
        // all random

        // also, for boss spawning/AI, need a class to manage the spawner block, and naga

        //SchematicManager.schematics.get().paste(getWorld(), x, getWorld().getHighestBlockYAt(x, z), z, null);

        //NMSHandler.setBlock(Block.getCombinedId(Blocks.ICE.getBlockData()), TFGlobal.TFWORLD, x, TFGlobal.TFWORLD.getHighestBlockYAt(x, z), z);



    }

}
