package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.generation.structure.TFComponent;

import java.util.ArrayList;

public class TFLichComponentQuatertiaryWing extends TFLichComponent{

    public TFLichComponentQuatertiaryWing(int x, int y, int z, int originDir) {
        super(ComponentType.QUATERTIARY, x, y, z, originDir);
    }

    @Override
    public void generate() {

    }

    @Override
    public ArrayList<TFComponent> generateChildren() { return null;}
}
