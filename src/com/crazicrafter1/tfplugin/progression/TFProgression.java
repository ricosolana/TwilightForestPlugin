package com.crazicrafter1.tfplugin.progression;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class TFProgression {

    private static TFBoss.Type[] hierarchy = new TFBoss.Type[] {TFBoss.Type.NAGA, TFBoss.Type.LICH, TFBoss.Type.MINOSHROOM, TFBoss.Type.HYDRA, TFBoss.Type.KNIGHTPHANTOM, TFBoss.Type.URGHAST, TFBoss.Type.ALPHAYETI, TFBoss.Type.SNOWQUEEN, TFBoss.Type.GIANT, TFBoss.Type.HIGHLANDBOSS};
    private static Map<TFBoss.Type, Integer> iHierarchy;// = new HashMap<>(Map.EntryArrays.asList(TFBoss.NAGA, TFBoss.LICH, TFBoss.MINOSHROOM, TFBoss.HYDRA, TFBoss.KNIGHTPHANTOM, TFBoss.URGHAST, TFBoss.ALPHAYETI, TFBoss.SNOWQUEEN, TFBoss.GIANT, TFBoss.HIGHLANDBOSS));

    private static HashMap<TFBoss.Type, ProtectedArea> bossAreas = new HashMap<>();

    static {

        //bossAreas.put(TFB)


        Hashtable<TFBoss.Type, Integer> tmp = new Hashtable<>();
        for (int i=0; i<hierarchy.length; i++) tmp.put(hierarchy[i], i);
        iHierarchy = Collections.unmodifiableMap(tmp);
    }

    public static TFBoss.Type biomeToBoss(Biome biome) {

        if (TFBiomeManager.biomeBosses.containsKey(biome))
            return TFBiomeManager.biomeBosses.get(biome);
        return null;
    }

    public static TFBoss.Type getPrevious(TFBoss.Type boss) {
        if (boss != TFBoss.Type.NAGA)
            return hierarchy[iHierarchy.get(boss)-1];
        return null;
    }

    public static TFBoss.Type getNext(TFBoss.Type boss) {
        if (boss != TFBoss.Type.HIGHLANDBOSS)
            return hierarchy[iHierarchy.get(boss)+1];
        return null;
    }

    public static TFBoss.Type incProgress(UUID uuid) {

        if (!TFGlobal.playerProgress.containsKey(uuid)) return null;

        TFBoss.Type next = getNext(TFGlobal.playerProgress.get(uuid));

        if (next != null)
            TFGlobal.playerProgress.put(uuid, next);

        return next;
    }

    public static TFBoss.Type decProgress(UUID uuid) {

        if (!TFGlobal.playerProgress.containsKey(uuid)) return null;

        TFBoss.Type next = getPrevious(TFGlobal.playerProgress.get(uuid));

        if (next != null)
            TFGlobal.playerProgress.put(uuid, next);

        return next;
    }

    public static boolean isGreater(TFBoss.Type boss, TFBoss.Type other) {

        return iHierarchy.get(boss) > iHierarchy.get(other);

    }

    public static boolean isLess(TFBoss.Type boss, TFBoss.Type other) {

        return iHierarchy.get(boss) < iHierarchy.get(other);

    }

    public static boolean canAccess(Player p, Location loc) {

        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) return true;

        Biome biome = loc.getBlock().getBiome();

        if (TFBiomeManager.biomeBosses.containsKey(biome)) {

            //p.sendMessage(ChatColor.GRAY + "Destroyed block in boss biome " + biome.name());

            TFBoss.Type required = TFProgression.biomeToBoss(biome);

            TFBoss.Type current = TFGlobal.playerProgress.get(p.getUniqueId());

            return required == current || TFProgression.isGreater(current, required);
        }

        return false;
    }

    //private static HashMap<Biome, PotionEffect> effects = new HashMap<>();

    public static PotionEffect effect(Player p, Location loc) {

        return null;

    }

    private class ProtectedArea {

        private int hierarchy;
        private Biome biome;
        private TFBoss.Type boss;
        private PotionEffect effect;
        private Particle particle;

        public ProtectedArea(int hierarchy, Biome biome, TFBoss.Type boss, PotionEffect effect, Particle particle) {
            this.hierarchy = hierarchy;
            this.biome = biome;
            this.boss = boss;
            this.effect = effect;
            this.particle = particle;
        }

        public int getHierarchy() {
            return hierarchy;
        }

        public Biome getBiome() {
            return biome;
        }

        public TFBoss.Type getBoss() {
            return boss;
        }

        public PotionEffect getEffect() {
            return effect;
        }

        public Particle getParticle() {
            return particle;
        }
    }


}
