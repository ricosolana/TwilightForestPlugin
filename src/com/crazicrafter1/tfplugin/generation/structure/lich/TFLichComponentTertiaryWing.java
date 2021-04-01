package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;

import java.util.ArrayList;

public class TFLichComponentTertiaryWing extends TFLichComponent {

    //private int signX, signZ;

    TFLichComponentTertiaryWing(int x, int y, int z, int originDir) {
        super(ComponentType.TERTIARY, x, y, z, originDir);
        //this.signX = Util.sign(wingX - x);
        //this.signZ = Util.sign(wingZ - z);
    }

    @Override
    public void generate() {
        int floorCount = Util.randomRange(1, 2);

        //this.hollowMultiBlockCube(wallBlockVariants, x-size/2, y, z-size/2, x+size/2, y+floorCount*5, z+size/2, false);
        hollowMultiBlockCube(wallBlockVariants, x-getSize()/2, y, z-getSize()/2, x+getSize()/2, y+floorCount*5, z+getSize()/2, false);

        //for (int i=1; i<floorCount; i++) {
        //    this.singleBlockFloor(Blocks.BIRCH_PLANKS, x, y+i*5, z, 2, 2);
        //}



        genParentOpening();


        genSideOpenings();

        for (TFComponent childComponent : getChildren()) {
            //((TFLichComponent)childComponent).getComponentType() == T
            childComponent.generate();
        }


    }

    @Override
    public ArrayList<TFComponent> generateChildren() {
        boolean[] wings = this.generateWings(getOriginDir());

        for (int i=0; i<wings.length; i++) {
            if (wings[i]) {
                int[] center = super.centerForChild(ComponentType.QUATERTIARY, i*90);

                TFLichComponent component = new TFLichComponentQuatertiaryWing(center[0], y, center[1], i*90 + 180);

                //if (super.overlapsAny())

                addChild(component);
            }
        }

        /*
        ArrayList<TFComponent> subChildren = new ArrayList<>();
*/
        for (TFComponent childComponent : getChildren())
            childComponent.generateChildren();



        return this.getChildren();

    }
}
