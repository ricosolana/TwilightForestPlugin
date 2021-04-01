package com.crazicrafter1.tfplugin;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.google.common.collect.Multimap;
import org.bukkit.World;

import java.util.HashMap;
import java.util.UUID;

public class TFGlobal {

    // does need volatile???
    //public static volatile Multimap<TFBoss.Type, Integer[]> bossesByType;

    public static HashMap<UUID, TFBoss.Type> playerProgress;

    public static final String NAME = "twilight_forest";
    public static World TFWORLD;

    //volatile public static Multimap<TFBoss, Integer[]> bossesByType = ArrayListMultimap.create();

}
