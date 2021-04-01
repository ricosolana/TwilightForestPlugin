package com.crazicrafter1.tfplugin.generation.structure.lich2;

import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.generation.TFBossStructure;
import com.crazicrafter1.tfplugin.generation.TFStructure;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;

import java.util.ArrayList;

public class TFLichTower extends TFStructure implements TFBossStructure {

    ArrayList<TFLichComponent> components = new ArrayList<>();

    public TFLichTower(int x, int z) {
        super(x, z);
    }

    @Override
    public TFBoss.Type getType() {
        return TFBoss.Type.LICH;
    }

    @Override
    public void generate() {
        components.add(new TFLichComponentMainTower(
                this.getX(), TFGlobal.TFWORLD.getHighestBlockYAt(getX(), getZ()), getZ(), TFComponent.ComponentFace.NONE));
    }
}
