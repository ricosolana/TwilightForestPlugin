package com.crazicrafter1.tfplugin.world.trees;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.Util;
import net.minecraft.server.v1_14_R1.Blocks;
import net.minecraft.server.v1_14_R1.WorldGenFeatureEmptyConfiguration;
import net.minecraft.server.v1_14_R1.WorldGenTrees;
import net.minecraft.server.v1_14_R1.WorldGenerator;
import org.bukkit.Material;

public class TFTreeSickly extends TFTree {

    public TFTreeSickly() {
        super(5, 7, Blocks.SPRUCE_LOG, Blocks.OAK_LEAVES);
    }

    @Override
    public void generate(int x, int y, int z) {

        WorldGenerator<WorldGenFeatureEmptyConfiguration> SICKLY_TREE = WorldGenerator.NORMAL_TREE; //WorldGenerator.a("normal_tree", new WorldGenTrees(WorldGenFeatureEmptyConfiguration::a, false));

        //SICKLY_TREE.
//SICKLY_TREE.

        //generate at location a small tree
        int h = Util.randomRange(getMinHeight(), getMaxHeight());

        for (int a=-2; a<=2; a++) {
            for (int b=-2; b<=1; b++) {
                for (int c=-2; c<=2; c++) {
                    //blocks.add(new BlockPair(getLeafblock(), x+a, y+b+h-1, z+c))
                    //NMSHandler.setBlock(getLeafblock(), TFGlobal.TFWORLD, x+a, y+b+h-1, z+c);
                    //Main.queuePlugin.setBlock(Material.SPRUCE_LEAVES, TFGlobal.TFWORLD, x+a, y+b+h-1, z+c);
                }
            }
        }

        for (int i=0; i<h; i++) {
            //Main.queuePlugin.setBlock(Material.SPRUCE_LOG, TFGlobal.TFWORLD, x, y+i, z);
        }

    }
}
