package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;

public class TFBossManager implements TFManager {

    public TFBossManager() {
    }

    @Override
    public void onEnable(Main plugin) {
        //TFGlobal.generator = new TFBossGenerator();
        loadBosses();
        TFBoss.generator.generate(0, 0);
        TFBoss.generator.generate(-1, 0);
        TFBoss.generator.generate(0, -1);
        TFBoss.generator.generate(-1, -1);
    }

    @Override
    public void onDisable(Main plugin) {
        saveBosses();
    }

    @Override
    public void update(Main plugin) {

    }

    private void loadBosses() {

        new BukkitRunnable() {
            @Override
            public void run() {

                String path = Main.getInstance().getDataFolder() + "\\bosses.tf";

                File file = new File(path);

                if (file.exists()) {

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));

                        String line;

                        while ((line = reader.readLine()) != null) {

                            String[] split = line.split(",");

                            TFBoss.bossesByType.put(TFBoss.Type.valueOf(split[0]), new Integer[]{Util.safeToInt(split[1]), Util.safeToInt(split[2])});

                            //bossLine = !bossLine;

                        }

                        //String s = reader.readLine();

                        reader.close();

                    } catch (Exception e) { e.printStackTrace(); }
                } else {

                    //Generator.generate(0, 0);

                }
            }
        }.run();
    }

    private static void saveBosses() {

        String path = Main.getInstance().getDataFolder() + "\\bosses.tf";

        File file = new File(path);

        file.delete();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            for (TFBoss.Type boss : TFBoss.bossesByType.keySet()) {

                for (Integer[] loc : TFBoss.bossesByType.get(boss)) {

                    writer.append(boss.name() + "," + loc[0] + "," + loc[1]);

                    writer.newLine();
                }

            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
