package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.generation.TFBossStructure;

public class TFLichTower extends TFBossStructure {

    //private HashMap<TFComponent

    public TFLichTower(int x, int z) {
        super(TFBoss.Type.LICH, x, z);
    }

    @Override
    public void generate() {
        // generate without the schematic because the courtyard is entirely random

        // randomly placed perimeter walls
        // randomly placed bushes
        // randomly placed substructures
        // all random

        // also, for boss spawning/AI, need a class to manage the spawner block, and naga

        int y = TFGlobal.TFWORLD.getHighestBlockYAt(x, z);
        //SchematicManager.schematics.get().paste(getWorld(), x, getWorld().getHighestBlockYAt(x, z), z, null);

        //NMSHandler.setBlock(Block.getCombinedId(BlockIDs.YELLOW_CONCRETE.getBlockData()), TFDimension.world, x, TFDimension.world.getHighestBlockYAt(x, z), z);
        //NMSHandler.setBox(Block.getCombinedId(BlockIDs.BIRCH_PLANKS.getBlockData()), TFGlobal.TFWORLD, x-5, y-5, z-5, x+10, y+10, z+10, false, true);
        //NMSHandler.setBox(Block.getCombinedId(BlockIDs.STONE_BRICKS.getBlockData()), TFGlobal.TFWORLD, x-7, y, z-7, x+7, y+70, z+7, true, true);


        //new TFLichComponentMainTower(x, y, z).generate();
        new TFLichComponentMainTower(x, y, z);
    }

}
