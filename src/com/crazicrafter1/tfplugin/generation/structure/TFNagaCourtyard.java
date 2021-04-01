package com.crazicrafter1.tfplugin.generation.structure;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFStructureManager;
import com.crazicrafter1.tfplugin.generation.TFBossStructure;
import com.crazicrafter1.tfplugin.schematic.Schematic;
import org.bukkit.scheduler.BukkitRunnable;

public class TFNagaCourtyard extends TFBossStructure {

    public TFNagaCourtyard(int x, int z) {
        super(TFBoss.Type.NAGA, x, z);
    }

    //private static Schematic naga_courtyard = SchematicManager.loadSchematic("naga_courtyard");

    @Override
    public void generate() {
        // generate without the schematic because the courtyard is entirely random

        // randomly placed perimeter walls
        // randomly placed bushes
        // randomly placed substructures
        // all random

        // also, for boss spawning/AI, need a class to manage the spawner block, and naga

        //SchematicManager.schematics.get().paste(getWorld(), x, getWorld().getHighestBlockYAt(x, z), z, null);

        Schematic nagaCourtyard = TFStructureManager.structures.get(TFStructureManager.Type.NAGA_COURTYARD);

        int y = TFGlobal.TFWORLD.getHighestBlockYAt(x, z)-5;

        int m = 3;

        //for (int h=0; h<nagaCourtyard.getSize()[1]+10; h++) {

        //   // int y=
        //    // flatten area
        //    NMSHandler.setBox(0,
        //            TFGlobal.TFWORLD,
        //            x-nagaCourtyard.getSize()[0]/2 - ((h+1)*m),
        //            y+h,
        //            z-nagaCourtyard.getSize()[2]/2 - ((h+1)*m),

        //            x+nagaCourtyard.getSize()[0]/2 + ((h+1)*m),
        //            y+h,
        //            z+nagaCourtyard.getSize()[2]/2 + ((h+1)*m),
        //            false, false);
        //}

        //org.bukkit.Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
        //    @Override
        //    public void run() {
        //        NMSHandler.setBox(9,
        //                TFGlobal.TFWORLD,
        //                x-nagaCourtyard.getSize()[0]/2 - 30,
        //                y-1,
        //                z-nagaCourtyard.getSize()[2]/2 - 30,

        //                x+nagaCourtyard.getSize()[0]/2 + 30,
        //                y+20,
        //                z+nagaCourtyard.getSize()[2]/2 + 30,
        //                false, 1);
        //    }
        //}, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println("NagaCourtyard size " + nagaCourtyard.getSize()[0] + " " + nagaCourtyard.getSize()[1] + " " + nagaCourtyard.getSize()[2]);
                nagaCourtyard.paste(TFGlobal.TFWORLD, x, y, z, false, Schematic.Alignment.HCENTER);
            }
        }.runTaskLater(Main.getInstance(), 40);




        //NMSHandler.setBlock(Block.getCombinedId(BlockIDs.GREEN_CONCRETE.getBlockData()), TFGlobal.TFWORLD, x, TFGlobal.TFWORLD.getHighestBlockYAt(x, z), z);

    }

}
