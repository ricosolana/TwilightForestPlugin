package com.crazicrafter1.tfplugin.generation;

/*
    holds any type of generated structure in twilight forest
    - hollow hills
    - tf houses/cabins
    - lapis structures
    - boss structures
 */

public abstract class TFStructure {

/*
    public enum Type {
        HEDGE_MAZE, HOLLOW_HILL_SMALL, HOLLOW_HILL_MEDIUM, HOLLOW_HILL_LARGE, QUEST_GROVE, NAGA_COURTYARD,
        LICH_TOWER, LABYRINTH, DRUID_HOUSE, RUINS, WELL, MONOLITH, // aka obsidian pillar
        STONE_CIRCLE, STALAGMITE, LEAF_DUNGEON, QUEST_GROVE_RUINS, STUMP, HOLLOW_LOG
    }

 */

    public enum Type {
        HEDGE_MAZE, HOLLOW_HILL_SMALL, HOLLOW_HILL_MEDIUM, HOLLOW_HILL_LARGE, QUEST_GROVE,
        DRUID_HOUSE, RUINS, WELL, MONOLITH, // aka obsidian pillar
        STONE_CIRCLE, STALAGMITE, LEAF_DUNGEON, QUEST_GROVE_RUINS, STUMP, HOLLOW_LOG,
    }

    private int x;
    private int z;

    public TFStructure(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public abstract void generate();

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    /*
    public static TFBossStructure getStructureFromString(int x, int z, final String s) {

        TFStructure.Type type = null;

        try {
            Type.valueOf(s.toUpperCase());
        } catch (Exception e) {e.printStackTrace(); return null;}

        switch (type) {

            case:
                return new TFNagaCourtyard(x, z);

        }

    }

     */

}
