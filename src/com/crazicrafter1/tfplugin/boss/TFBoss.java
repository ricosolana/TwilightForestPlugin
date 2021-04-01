package com.crazicrafter1.tfplugin.boss;

import com.crazicrafter1.tfplugin.Util;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class TFBoss {

    private Type type;
    private Integer[] __loc;

    public TFBoss(Type type, Integer[] loc) {
        this.type = type;
        this.__loc = loc;
    }

    public Integer[] getLoc() {
        return __loc;
    }

    public Type getType() {
        return type;
    }

    public static TFBoss.Generator generator = new TFBoss.Generator();

    public enum Type {
        NAGA, LICH, MINOSHROOM, KNIGHTPHANTOM, ALPHAYETI,
        HYDRA, URGHAST, SNOWQUEEN, HIGHLANDBOSS, GIANT
    }

    public static Multimap<Type, Integer[]> bossesByType = ArrayListMultimap.create();
    public static Map<Point, TFBoss> bossesByCoordinate = new HashMap<>();


    public static final List<TFBoss.Type> primary = Arrays.asList(Type.HYDRA, Type.URGHAST, Type.SNOWQUEEN, Type.HIGHLANDBOSS);

    public static final List<TFBoss.Type> subPrimary = Arrays.asList(Type.MINOSHROOM, Type.KNIGHTPHANTOM, Type.ALPHAYETI, Type.GIANT);

    public static final List<TFBoss.Type> secondary = Arrays.asList(Type.NAGA, Type.LICH);

    public static boolean nearSecondary(int x, int z, int sqRad) {

        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (secondary.contains(boss)) {
                for (Integer[] loc : bossesByType.get(boss)) {

                    int sqDist = Util.sqDist(x, z, loc[0], loc[1]);

                    if (sqDist < sqRad) return true;
                }
            }
        }

        return false;
    }

    public static boolean nearSubPrimary(int x, int z, int sqRad) {

        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (subPrimary.contains(boss)) {
                for (Integer[] loc : bossesByType.get(boss)) {

                    int sqDist = Util.sqDist(x, z, loc[0], loc[1]);

                    if (sqDist < sqRad) return true;
                }
            }
        }

        return false;
    }

    public static boolean nearPrimary(int x, int z, int sqRad) {

        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (primary.contains(boss)) {
                for (Integer[] loc : bossesByType.get(boss)) {

                    int sqDist = Util.sqDist(x, z, loc[0], loc[1]);

                    if (sqDist < sqRad) return true;
                }
            }
        }

        return false;
    }

    public static boolean primaryInMap(int mapCenterX, int mapCenterZ) {
        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (primary.contains(boss)) {
                for (Integer[] loc : bossesByType.get(boss)) {

                    int x = Util.getCenter(loc[0]);
                    int z = Util.getCenter(loc[1]);

                    if (x == mapCenterX && z == mapCenterZ && primary.contains(boss)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    /*
        get nearest boss with filter
     */

    public static TFBoss getNearestBossWithinSqRad(int x, int z, int maxSqRad) {
        return getNearestBossWithinSqRad(x, z, maxSqRad, 0);
    }

    public static TFBoss getNearestBossWithinSqRad(int x, int z, int maxSqRad, int filter) {

        TFBoss boss = null;
        int minSqDist = maxSqRad; // RADIUS*RADIUS

        for (TFBoss.Type type : bossesByType.keySet()) {
            if (filter == 0 || (filter == 1 && primary.contains(type)) || (filter == 2 && subPrimary.contains(type))) {
                for (Integer[] loc : bossesByType.get(type)) {
                    // loc
                    int sqDist = Util.sqDist(x, z, loc[0], loc[1]);
                    if (sqDist < minSqDist) {
                        boss = new TFBoss(type, loc);
                        minSqDist = sqDist;
                    }

                    //if (minSqDist < 50*50) return boss;
                }
            }
        }

        return boss;
    }


    /*

     */


    public static TFBoss getBossOverlappingPointWithinSqRad(int x, int z, int sqDistance) {

        return getBossOverlappingPointWithinSqRad(x, z, sqDistance, 0);
    }

    //    0 = any
    //    1 = primary only
    //    2 = subPrimary only

    public static TFBoss getBossOverlappingPointWithinSqRad(int x, int z, int sqDistance, int filter) {


        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (filter == 0 || (filter == 1 && primary.contains(boss)) || (filter == 2 && subPrimary.contains(boss))) {

                for (Integer[] loc : bossesByType.get(boss)) {

                    double dist = Util.sqDist(x, z, loc[0], loc[1]);

                    if (dist < sqDistance) {

                        return new TFBoss(boss, loc);

                    }

                }
            }

        }

        return null;
    }



    /*
        return all the bosses in the radius
     */

    public static ArrayList<TFBoss> getBossesOverlappingPointWithinSqRad(int x, int z, int sqDistance) {

        return getBossesOverlappingPointWithinSqRad(x, z, sqDistance, 0);
    }

    public static ArrayList<TFBoss> getBossesOverlappingPointWithinSqRad(int x, int z, int sqDistance, int filter) {

        ArrayList<TFBoss> rangeBosses = new ArrayList<>();

        for (TFBoss.Type boss : bossesByType.keySet()) {
            if (filter == 0 || (filter == 1 && primary.contains(boss)) || (filter == 2 && subPrimary.contains(boss))) {

                for (Integer[] loc : bossesByType.get(boss)) {

                    double dist = Util.sqDist(x, z, loc[0], loc[1]);

                    if (dist < sqDistance) {

                        //return boss;
                        rangeBosses.add(new TFBoss(boss, loc));

                    }

                }
            }

        }

        return rangeBosses;
    }

    public static Type getSubBoss(Type primary) {
        switch (primary) {
            case HYDRA:        return TFBoss.Type.MINOSHROOM;
            case URGHAST:      return TFBoss.Type.KNIGHTPHANTOM;
            case SNOWQUEEN:    return TFBoss.Type.ALPHAYETI;
            case HIGHLANDBOSS: return TFBoss.Type.GIANT;
        }
        return null;
    }

    public static final int RADIUS = 128; // 128
    public static final int JAGGED_CHANCE = 50; // 1-100
    public static final int JAGGED_MAX_MULTIPLIER = 3; // how many max blocks to offset from center

    public static class Generator {

        static Random random = new Random();

        public Generator() {

        }

        // naga --> lich --> minoshroom --> hydra -->
        // knight phantoms --> ur-ghast --> alpha-yeti --> snow queen
        // troll caves-pre --> giants --> lamp of cinders/thorns --> highlands

        //HashMap<TFBoss, ArrayList<TFBossStructure>> bossesByType = new HashMap<>();

        //public static HashMap<String, TFBossEntity> locations = new HashMap<>();




        public void generate(int chunkX, int chunkZ) {

            //if (random == null) random = new Random(TFGlobal.TFWORLD.getSeed());

        /*
            new method:
             - split the 2048x2048 map into a total of 16 parts,
               generate a random boss in one of those square nodes of 512x512 blocks.

         */

            List<TFBoss.Type> mPrimary = new ArrayList<>(primary);

            // (chunkX * 16) / 2048 = MAP NODE location
            //                          (MAP NODE loc is different from NODE loc,
            //                           MAP NODE is what 2048x2048 node you are located in
            //                           NODE is what 512x512 location you are located in



            //System.out.println("Mapnodes : " + mapNodeX + " " + mapNodeZ);

            // Now, make greater emphasis on "MapNodes", which are just 4x4 nodes to be 2048x2048

            // Generate here if there are no primary bosses in this fat map node
            //new BukkitRunnable() {
            //    @Override
            //    public void run() {

            int mapCenterX = Util.getCenter(chunkX*16);
            int mapCenterZ = Util.getCenter(chunkZ*16);

            if (!primaryInMap(mapCenterX, mapCenterZ)) {

                System.out.println("Generating at center of " + mapCenterX + " " + mapCenterZ);

                //System.out.println("Generating new structures in map center " + mapCenterX + " " + mapCenterZ);



                for (int x = 0; x <= 2; x += 2) {
                    for (int z = 0; z <= 2; z += 2) {

                        // choose the random boss for each node, at a random location within each node
                        //Integer[] loc = new Integer[] {Util.randomRange(x*512, x*512+512)+(mapNodeX*chunkX*16), Util.randomRange(z*512, z*512+512)+mapNodeZ};
                        Integer[] loc = new Integer[]{
                                Util.randomRange(x * 512, x * 512 + 512, random) + mapCenterX - 1024,
                                Util.randomRange(z * 512, z * 512 + 512, random) + mapCenterZ - 1024};

                        // generate structure
                        int rand = Util.randomRange(0, mPrimary.size() - 1, random);

                        TFBoss.Type boss = mPrimary.get(rand);
                        mPrimary.remove(rand);

                        // Add the p_BOSS here
                        bossesByType.put(boss, loc);
                        bossesByCoordinate.put(new Point(loc), new TFBoss(boss, loc));

                        //TFStructure structure
                        TFBoss.Type subBoss = getSubBoss(boss);

                        for (int r = 0; r < 360; r += 90) {

                            int ix = Util.fastCos(r) * RADIUS * 2;
                            int iz = Util.fastSin(r) * RADIUS * 2;

                            Integer[] subLoc = new Integer[]{loc[0] + ix, loc[1] + iz};

                            // Add the s_BOSS here
                            bossesByType.put(subBoss, subLoc);
                            bossesByCoordinate.put(new Point(subLoc), new TFBoss(boss, subLoc));

                        }

                    /*
                    for (int ix=-1; ix<=1; ix+=2) {

                        for (int iz=-1; iz<=1; iz+=2) {

                            // gen
                            bossesByType.put(subBoss, new Integer[] {loc[0]+ix*250, loc[1]+iz*250});
                        }

                    }

                     */

                    }
                }

                int nagaCount = Util.randomRange(3, 6, random);
                int lichCount = Util.randomRange(3, 6, random);

                    /*
                        naga generation
                     */

                final int maxTrys = 10;

                for (int i = 0; i < nagaCount; i++) {

                    // generate at random seeded locations away from other structures
                    // very far from primaries
                    Integer[] loc = new Integer[]{
                            Util.randomRange(mapCenterX - 1024, mapCenterX + 1024),
                            Util.randomRange(mapCenterZ - 1024, mapCenterZ + 1024)
                    };

                    int trys = 0;
                    while ((nearPrimary(loc[0], loc[1], (RADIUS*4)*(RADIUS*4)) || nearSecondary(loc[0], loc[1], 128*128)) && trys<maxTrys) {
                        loc = new Integer[]{
                                Util.randomRange(mapCenterX - 1024, mapCenterX + 1024),
                                Util.randomRange(mapCenterZ - 1024, mapCenterZ + 1024)
                        };
                        trys++;
                    }

                    if (trys<maxTrys) bossesByType.put(TFBoss.Type.NAGA, loc);

                }

            /*
                lich generation
             */

                for (int i = 0; i < lichCount; i++) {

                    // generate at random seeded locations away from other structures
                    // very far from primaries
                    Integer[] loc = new Integer[]{
                            Util.randomRange(mapCenterX - 1024, mapCenterX + 1024),
                            Util.randomRange(mapCenterZ - 1024, mapCenterZ + 1024)
                    };

                    int trys = 0;
                    while ((nearPrimary(loc[0], loc[1], (RADIUS*4)*(RADIUS*4)) || nearSecondary(loc[0], loc[1], 128*128)) && trys<maxTrys) {
                        loc = new Integer[]{
                                Util.randomRange(mapCenterX - 1024, mapCenterX + 1024),
                                Util.randomRange(mapCenterZ - 1024, mapCenterZ + 1024)
                        };
                        trys++;
                    }

                    if (trys<maxTrys) bossesByType.put(TFBoss.Type.LICH, loc);

                }

            }
            //}
            //}.run();
        }


    /*
    private static int getCenter(int i) {
        return (i/2048)*2048+(Util.strictSign(i)*1024);
    }

     */

    }



}
