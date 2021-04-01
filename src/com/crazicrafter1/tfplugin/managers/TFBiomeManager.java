package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.block.Biome;

import java.util.HashMap;

public class TFBiomeManager implements TFManager {

    public static HashMap<TFBoss.Type, Biome> bossBiomes;
    public static HashMap<Biome, TFBoss.Type> biomeBosses;

    @Override
    public void onEnable(Main plugin) {
        bossBiomes = new HashMap<>();

        //bossBiomes.put(TFBoss.LICH, Biome.PLAINS);

        /*
        bossBiomes.put(TFBoss.Type.MINOSHROOM, Biome.BADLANDS_PLATEAU);
        bossBiomes.put(TFBoss.Type.HYDRA, Biome.MODIFIED_BADLANDS_PLATEAU);

        bossBiomes.put(TFBoss.Type.KNIGHTPHANTOM, Biome.WOODED_BADLANDS_PLATEAU);
        bossBiomes.put(TFBoss.Type.URGHAST, Biome.MODIFIED_WOODED_BADLANDS_PLATEAU);

        bossBiomes.put(TFBoss.Type.ALPHAYETI, Biome.GRAVELLY_MOUNTAINS);
        bossBiomes.put(TFBoss.Type.SNOWQUEEN, Biome.MODIFIED_GRAVELLY_MOUNTAINS);

        bossBiomes.put(TFBoss.Type.GIANT, Biome.JUNGLE_EDGE);
        bossBiomes.put(TFBoss.Type.HIGHLANDBOSS, Biome.MODIFIED_JUNGLE_EDGE);
         */

        bossBiomes.put(TFBoss.Type.MINOSHROOM, Biome.SWAMP);
        bossBiomes.put(TFBoss.Type.HYDRA, Biome.GRAVELLY_MOUNTAINS);

        bossBiomes.put(TFBoss.Type.KNIGHTPHANTOM, Biome.DARK_FOREST);
        bossBiomes.put(TFBoss.Type.URGHAST, Biome.END_BARRENS);

        bossBiomes.put(TFBoss.Type.ALPHAYETI, Biome.SNOWY_TAIGA);
        bossBiomes.put(TFBoss.Type.SNOWQUEEN, Biome.SNOWY_TUNDRA);

        bossBiomes.put(TFBoss.Type.GIANT, Biome.BADLANDS);
        bossBiomes.put(TFBoss.Type.HIGHLANDBOSS, Biome.MODIFIED_JUNGLE_EDGE);

        biomeBosses = new HashMap<>(); for (TFBoss.Type boss : bossBiomes.keySet()) biomeBosses.put(bossBiomes.get(boss), boss);
    }

    @Override
    public void onDisable(Main plugin) {

    }

    @Override
    public void update(Main plugin) {

    }
}
