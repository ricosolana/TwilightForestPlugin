package com.crazicrafter1.tfplugin.generation.structure;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.generation.TFBossStructure;

public class TFHydraLair extends TFBossStructure {

    public TFHydraLair(int x, int z) {
        super(TFBoss.Type.HYDRA, x, z);
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

        //NMSHandler.setBlock(Block.getCombinedId(Blocks.MAGMA_BLOCK.getBlockData()), TFGlobal.TFWORLD, x, TFGlobal.TFWORLD.getHighestBlockYAt(x, z), z);

    }

}
