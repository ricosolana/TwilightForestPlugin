package com.crazicrafter1.tfplugin.world.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
//import net.minecraft.server.v1_14_R1.ChunkGenerator;
//import net.m
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class TFGenerator extends ChunkGenerator {

    private int currentHeight = 30;

    private Random r = null;

    //ChunkGeneratorSettings s;

    public TFGenerator() {

    }



    @Override
    public List<BlockPopulator> getDefaultPopulators(World world)
    {
        return new ArrayList<>();

        //ArrayList<BlockPopulator> pop = new ArrayList<>();
        //pop.add(new TFPopulator());
        //return pop;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random rand)
    {
        //return new Location(world, 0, world.getHighestBlockYAt(0, 0)+2, 0);
        Random random = new Random(world.getSeed());

        int x = Util.randomRange(-200, 200, random);
        int z = Util.randomRange(-200, 200, random);

        int h = world.getHighestBlockYAt(x,z);

        return new Location(world, x, h+1, z);
    }

    public int coordsToIndex(int x, int y, int z)
    {
        return (x*16+z)*128+y;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biomeGrid) {
        if (random == null) r = new Random(world.getSeed());

        SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(new Random(world.getSeed()), 16);
        SimplexOctaveGenerator gen2 = new SimplexOctaveGenerator(new Random(world.getSeed()), 16);
        SimplexOctaveGenerator gen3 = new SimplexOctaveGenerator(new Random(world.getSeed()), 16);

        ChunkData chunk = super.createChunkData(world);
        gen1.setScale(0.010D);
        gen2.setScale(0.005D);
        gen3.setScale(0.001D);

        /*
        for (int rx=0; rx<16; rx++) {

            for (int rz=0; rz<16; rz++) {

                int x = chunkX*16+rx;
                int z = chunkZ*16+rz;



                //    fill the major ring (the one that will be mainly used for in-between biome fill)

                int totalSqRad = (TFBoss.RADIUS*3) * (TFBoss.RADIUS*3);

                int spacing = TFBoss.RADIUS*2;
                int sqRad = TFBoss.RADIUS * TFBoss.RADIUS;

                TFBoss temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, totalSqRad, 1);

                // then setbiome to boss biome
                if (temp_boss != null) {

                    // test to see that is not in range of a corner biome exclusion ring
                    //if (Util.sqDist(rx, rz, boss.getLoc()[0]-spacing, boss.getLoc()[1]-spacing) > sPow2 &&
                    //        Util.sqDist(rx, rz, boss.getLoc()[0]+spacing, boss.getLoc()[1]-spacing) > sPow2 &&
                    //        Util.sqDist(rx, rz, boss.getLoc()[0]-spacing, boss.getLoc()[1]+spacing) > sPow2 &&
                    //        Util.sqDist(rx, rz, boss.getLoc()[0]+spacing, boss.getLoc()[1]+spacing) > sPow2) {

                        biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(temp_boss.getType()));
                    //}
                    continue;


                } else biomeGrid.setBiome(rx, rz, Biome.FOREST);



                 //   fill the primary boss biome

                temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, sqRad, 1);

                // then setbiome to boss biome
                if (temp_boss != null) {

                    biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(temp_boss.getType()));
                    continue;

                } else biomeGrid.setBiome(rx, rz, Biome.FOREST);



                //    fill the subPrimary boss radius

                temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, sqRad, 2);

                // then setbiome to boss biome
                if (temp_boss != null) {

                    biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(temp_boss.getType()));

                } else biomeGrid.setBiome(rx, rz, Biome.FOREST);

            }

        }

         */

        for (int rx = 0; rx < 16; rx++)
            for (int rz = 0; rz < 16; rz++) {
                int x = chunkX*16+rx;
                int z = chunkZ*16+rz;

                int h1 = (int) (gen1.noise(x, z, 0.25D, 0.25D)*15D+50D);
                int h2 = (int) (gen2.noise(x, z, 0.25D, 0.5D)*15D+50D);
                int h3 = (int) (gen3.noise(x, z, 0.5D, 0.25D)*15D+50D);
                currentHeight = ((h1+h2+h3)/3);
                chunk.setBlock(rx, currentHeight, rz, Material.GRASS_BLOCK);
                chunk.setBlock(rx, currentHeight-1, rz, Material.DIRT);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(rx, i, rz, Material.STONE);
                chunk.setBlock(rx, 0, rz, Material.BEDROCK);

                /*
                switch (Util.randomRange(0, 2, r)) {

                    case 0: chunk.setBlock(rx, currentHeight-1, rz, Material.GRASS);
                    case 1: chunk.setBlock(rx, currentHeight-1, rz, Material.TALL_GRASS);
                    //case 2: chunk.setBlock(rx, currentHeight-1, rz, Material.AIR);
                }

                 */

                /*
                    Biome setting
                 */

                biomeGrid.setBiome(rx, rz, Biome.FOREST);

                //    fill the major ring (the one that will be mainly used for in-between biome fill)

                final int spacing = TFBoss.RADIUS*2;

                final int totalSqRad = (int)(TFBoss.RADIUS*2.2)*(int)(TFBoss.RADIUS*2.2);

                final int sqRad = TFBoss.RADIUS * TFBoss.RADIUS;

                {

                    TFBoss temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, totalSqRad, 1);

                    // then setbiome to boss biome
                    if (temp_boss != null) {
                        // test to see that is not in range of a corner biome exclusion ring

                        /*
                            ideas for random biome flicker:
                             - get the sqDist from the below and store as an int
                             - test to see if the sqDist is near radSq
                             - if so, then randomly decide whether to place biome at block location
                         */

                        if (Util.sqDist(x, z, temp_boss.getLoc()[0] - spacing, temp_boss.getLoc()[1] - spacing) > sqRad &&
                                Util.sqDist(x, z, temp_boss.getLoc()[0] + spacing, temp_boss.getLoc()[1] - spacing) > sqRad &&
                                Util.sqDist(x, z, temp_boss.getLoc()[0] - spacing, temp_boss.getLoc()[1] + spacing) > sqRad &&
                                Util.sqDist(x, z, temp_boss.getLoc()[0] + spacing, temp_boss.getLoc()[1] + spacing) > sqRad) {

                            biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(TFBoss.getSubBoss(temp_boss.getType())));
                            //break label1;
                        }

                    }


                    //   fill the primary boss biome

                    temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, sqRad, 1);

                    // then setbiome to boss biome
                    if (temp_boss != null) {
                        biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(temp_boss.getType()));
                        //break label1;
                    }


                    //    fill the subPrimary boss radius

                    temp_boss = TFBoss.getNearestBossWithinSqRad(x, z, sqRad, 2);

                    // then setbiome to boss biome
                    if (temp_boss != null) {
                        biomeGrid.setBiome(rx, rz, TFBiomeManager.bossBiomes.get(temp_boss.getType()));
                    }

                }

            }
        return chunk;
    }

    //@Override
    //public short[][] generateExtBlockSections(World world, Random random, int x, int z, BiomeGrid biomeGrid)




    //public void setBiome(

}