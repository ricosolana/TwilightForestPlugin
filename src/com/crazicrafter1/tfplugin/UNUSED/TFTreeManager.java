package com.crazicrafter1.tfplugin.managers;

import com.crazicrafter1.tfplugin.world.trees.TFTree;
import com.crazicrafter1.tfplugin.world.trees.TFTreeSickly;
import org.bukkit.World;

import java.util.HashMap;

public class TFTreeManager { // extends TFManager {

    public enum Type {
        SICKLY,ROBUST,CANOPY,MANGROVE,DARKWOOD,RAINBOW
    }

    static HashMap<Type, TFTree> trees;

    public static void init() {

        trees = new HashMap<>();

        trees.put(Type.CANOPY, null);
        trees.put(Type.DARKWOOD, null);
        trees.put(Type.MANGROVE, null);
        trees.put(Type.RAINBOW, null);
        trees.put(Type.ROBUST, null);
        trees.put(Type.SICKLY, new TFTreeSickly());

    }

    public static void generateTree(Type type, int x, int y, int z) {

        if (trees.get(type) != null) trees.get(type).generate(x, y, z);
        else System.out.println("NULL!");

    }

}
