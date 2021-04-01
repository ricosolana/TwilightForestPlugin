package com.crazicrafter1.tfplugin.world.generator2;

import net.minecraft.server.v1_14_R1.GeneratorSettingsDefault;

public class GeneratorSettingsTF extends GeneratorSettingsDefault {

    // either biome size or river size
    public int v() {
        return 4;
    }

    // either biome size or river size
    public int w() {
        return 4;
    }

    // biome id
    public int x() {
        return -1;
    }

    // bedrock floor height
    public int u() {
        return 0;
    }

}
