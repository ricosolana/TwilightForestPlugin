package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.boss.TFBoss.Type;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class TFProgressionManager implements TFManager {

    private TFBoss.Type[] intHierarchy;
    private HashMap<TFBoss.Type, Integer> bossHierarchy;


    @Override
    public void onEnable(Main plugin) {
        TFGlobal.playerProgress = new HashMap<>();

        intHierarchy = new TFBoss.Type[] {TFBoss.Type.NAGA, TFBoss.Type.LICH, TFBoss.Type.MINOSHROOM, TFBoss.Type.HYDRA, TFBoss.Type.KNIGHTPHANTOM, TFBoss.Type.URGHAST, TFBoss.Type.ALPHAYETI, TFBoss.Type.SNOWQUEEN, TFBoss.Type.GIANT, TFBoss.Type.HIGHLANDBOSS};

        bossHierarchy = new HashMap<>();

        for (int i=0; i<intHierarchy.length; i++) bossHierarchy.put(intHierarchy[i], i);

        loadPlayers(plugin);
    }

    @Override
    public void onDisable(Main plugin) {
        saveProgress(plugin);
    }

    @Override
    public void update(Main plugin) {
        for (Player p : plugin.getServer().getOnlinePlayers()) {

            if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return;

            if (!TFProgression.canAccess(p, p.getLocation())) {



            }

        }
    }

    private void loadPlayers(Main plugin) {


        new BukkitRunnable() {
            @Override
            public void run() {

                String path = plugin.getDataFolder() + "\\progress.tf";

                File file = new File(path);

                if (file.exists()) {

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));

                        String line;

                        while ((line = reader.readLine()) != null) {


                            String[] split = line.split(":");

                            //playerProgress.put();

                            // FORMAT:
                            // uuid:TFBoss.Type
                            UUID uuid = null;
                            TFBoss.Type boss = null;

                            try {
                                uuid = UUID.fromString(split[0]);
                                boss = TFBoss.Type.valueOf(split[1]);
                            } catch (Exception e) {e.printStackTrace(); continue;}

                            TFGlobal.playerProgress.put(uuid, boss);

                            //bossLine = !bossLine;

                        }

                        //String s = reader.readLine();

                        reader.close();

                    } catch (Exception e) { }
                }
            }
        }.run();

    }

    public void saveProgress(Main plugin) {

        String path = plugin.getDataFolder() + "\\progress.tf";

        File file = new File(path);

        //noinspection ResultOfMethodCallIgnored
        file.delete();

        if (!TFGlobal.playerProgress.isEmpty()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                for (UUID uuid : TFGlobal.playerProgress.keySet()) {

                    //noinspection StringConcatenationInsideStringBufferAppend
                    writer.append(uuid + ":" + TFGlobal.playerProgress.get(uuid).name());

                    writer.newLine();

                }

                writer.close();

            } catch (Exception e) {
            }
        }

    }


}
