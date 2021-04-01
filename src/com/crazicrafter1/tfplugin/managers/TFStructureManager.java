package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.generation.structure.*;
//import com.crazicrafter1.tfplugin.generation.structure.lich.TFLichTower;
import com.crazicrafter1.tfplugin.schematic.Schematic;
import org.bukkit.ChatColor;

import java.io.InputStream;
import java.util.HashMap;

public class TFStructureManager implements TFManager {

    public enum Type {

        NAGA_COURTYARD, LICH_TOWER, LABYRINTH, HYDRA_LAIR,
        GOBLIN_KNIGHT_STRONGHOLD, DARK_TOWER, YETI_LAIR, AURORA_PALACE,
        CLOUD_COTTAGE, CASTLE,
        QUEST_GROVE, HEDGE_MAZE, HOLLOW_HILL_SMALL, HOLLOW_HILL_MEDIUM, HOLLOW_HILL_LARGE
        //TEMP_GREEN

        // add
        // color-map for biomes/ore/
        // instead byte[] of colors and correspondence

    }

    public static HashMap<Type, Schematic> structures = new HashMap<>();

    public TFStructureManager() {
    }

    @Override
    public void onEnable(Main plugin) {
        loadStructureSchematics();
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }

    private void loadStructureSchematics() {

        //structures.put(TFBoss.Type.NAGA, )


        String path = "/resources/structures" + "/";

        final String[] structure_names = new String[]{
                "naga_courtyard"};

        System.out.println(ChatColor.GRAY + "Loading schematics");

        for (String structure : structure_names) {
            try {
                String resourcePath = path + structure + ".tfs";

                InputStream in = Main.class.getResourceAsStream(resourcePath);

                if (in == null) {
                    System.out.println(ChatColor.RED + structure + " cannot be found at " + resourcePath);
                    continue;
                }

                Schematic schematic = new Schematic(structure, in);

                structures.put(Type.valueOf(structure.toUpperCase()), schematic);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /*
    public static TFBossStructure getBossStructure(final String s) {

        TFBossStructure structure = null;

        TFBoss.Type type = null;

        try {
            type = TFBoss.Type.valueOf(s.toUpperCase());
        } catch (Exception e) {e.printStackTrace(); return null;}

        switch (type) {

            case NAGA:
                return new TFNagaCourtyard();
            case LICH:
                return new TFLichTower();
            case MINOSHROOM:
                return new TFLabyrinth();
            case HYDRA:
                return new TFHydraLair();
            case KNIGHTPHANTOM:
                return new TFGoblinKnightStronghold();
            case URGHAST:
                return new TFDarkTower();
            case ALPHAYETI:
                return new TFYetiLair();
            case SNOWQUEEN:
                return new TFAuroraPalace();
            //case GIANT:
            //    return null;
            //case HIGHLANDBOSS:
            //    return null;


        }

        return null;

    }

     */


    public static void generate(int chunkX, int chunkZ) {

        //System.out.println("");

        for (TFBoss.Type boss : TFBoss.bossesByType.keySet()) {

            for (Integer[] loc : TFBoss.bossesByType.get(boss)) {
                if (loc[0]/16 == chunkX && loc[1]/16 == chunkZ) {

                    int x = loc[0];
                    int z = loc[1];

                    //Block block = TFDimension.world.getBlockAt(loc[0], TFDimension.world.getHighestBlockYAt(loc[0], loc[1])+10, loc[1]);

                    //block.setType(Material.OBSIDIAN);

                    org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {

                            switch (boss) {

                                case HYDRA:
                                    new TFHydraLair(x, z).generate();
                                    break;
                                case URGHAST:
                                    new TFDarkTower(x, z).generate();
                                    break;
                                case SNOWQUEEN:
                                    new TFAuroraPalace(x, z).generate();
                                    break;
                                case HIGHLANDBOSS:
                                    //new TFHighlandBoss(x, z).generate();
                                    break;


                                case MINOSHROOM:
                                    new TFLabyrinth(x, z).generate();
                                    break;
                                case KNIGHTPHANTOM:
                                    new TFGoblinKnightStronghold(x, z).generate();
                                    break;
                                case ALPHAYETI:
                                    new TFYetiLair(x, z).generate();
                                    break;


                                case NAGA:
                                    new TFNagaCourtyard(x, z).generate();
                                    break;
                                case LICH:
                                    //new TFLichTower(x, z).generate();
                                    //new TFLichTower(x, z);
                                    break;

                            }
                        }
                    }, 60);

                }
            }
        }
    }

}
