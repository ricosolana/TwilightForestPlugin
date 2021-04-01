package com.crazicrafter1.tfplugin.generation.structure.lich2;

import com.crazicrafter1.tfplugin.generation.structure.TFComponent;

public class TFLichComponentMainTower extends TFLichTowerWing {

    private static final int size = 11;

    public TFLichComponentMainTower(int x, int y, int z, ComponentFace face) {
        super(x, y, z, face);
    }

    @Override
    public void generate() {
        // gen
        //this.addChild(new TFLichTowerWing());
    }

    /*
        accessor decorator?
     */

    @Override
    public void decorate() {

    }

    /*
    public TFLichComponent generateNext(ComponentFace direction)
    {

        int x = getX();
        int y = getY();
        int z = getZ();

        // generate a new component in the direction with correct new size

        switch (direction) {

            case EAST: x += size/2;

        }
        return null;
    }
     */

    public void decorateLichChandelier() {
        // place fences accordingly, with torches ...
    }

    public void decoratePaintings() {
        // place paintings along walls, with correct sizes and rotation
    }

    // should not be a decorator
    public void decorateMainSpiralStairs1() {}

    // should not be a decorator
    public void decorateMainSpiralStairs2() {}



}
