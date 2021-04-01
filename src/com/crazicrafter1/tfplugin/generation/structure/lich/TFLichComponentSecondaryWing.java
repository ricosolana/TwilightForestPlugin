package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;
import net.minecraft.server.v1_14_R1.Blocks;

import java.util.ArrayList;

public class TFLichComponentSecondaryWing extends TFLichComponent {

    //private int signX, signZ;

    TFLichComponentSecondaryWing(int x, int y, int z, int originDir) {
        super(ComponentType.SECONDARY, x, y, z, originDir);
        //this.signX = Util.sign(wingX - x);
        //this.signZ = Util.sign(wingZ - z);
    }

    @Override
    public void generate() {

        int floorCount = Util.randomRange(2, 3);

        //this.hollowMultiBlockCube(wallBlockVariants, x-size/2, y, z-size/2, x+size/2, y+floorCount*5, z+size/2, false);
        hollowMultiBlockCube(wallBlockVariants, x-getSize()/2, y, z-getSize()/2, x+getSize()/2, y+floorCount*5, z+getSize()/2, false);

        for (int i=1; i<floorCount; i++) {
            this.singleBlockFloor(Blocks.BIRCH_PLANKS, x, y+i*5, z, 2, 2);
        }

        genParentOpening();


        genSideOpenings();

        for (TFComponent childComponent : getChildren()) {
            //((TFLichComponent)childComponent).getComponentType() == T
            childComponent.generate();
        }




        //outer
        //NMSHandler.setBlock(BlockIDs.SMOOTH_STONE_SLAB, TFGlobal.TFWORLD, x+(size*signX)/2, y + 3, z+(size*signZ)/2);


        //int signX = Util.sign(signX-x);
        //int signZ = Util.sign(signZ-z);
        //generateWings();

    }

    @Override
    public ArrayList<TFComponent> generateChildren() {        // the generator of all (children locations only)
        boolean[] wings = this.generateWings(getOriginDir());

        for (int i=0; i<wings.length; i++) {
            if (wings[i]) {
                int[] center = super.centerForChild(ComponentType.TERTIARY, i*90);

                TFLichComponent component = new TFLichComponentTertiaryWing(center[0], y, center[1], i*90 + 180);

                addChild(component);
            }
        }

        ArrayList<TFComponent> subChildren = new ArrayList<>();

        for (TFComponent childComponent : getChildren())
            subChildren.addAll(childComponent.generateChildren());

        return subChildren;

    }


    /*
    @Override
    protected void generateWings() {

        boolean[] wings = getWings();

        for (int i=0; i<wings.length; i++) {

            int rx = Util.fastCos(i*90)*7;
            int rz = Util.fastSin(i*90)*7;

            int signX = Util.sign(rx);
            int signZ = Util.sign(rz);

            int wingCX = x + rx + signX*4;
            int wingCZ = z + rz + signZ*4;

            if (wings[i]) {
                this.singleBlockVertColumn(BlockIDs.AIR, x + rx, y + 2, z + rz, -2);

                NMSHandler.setBlock(doubleStoneSlab, TFGlobal.TFWORLD, x + rx, y + 3, z + rz);

                new TFLichComponentTertiaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();

                //if (Util.randomRange(0,2)==0)
                //    new TFLichComponentPrimaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();
                //else new TFLichComponentSecondaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();

            }

        }

    }


    private boolean[] getWings() {

        // set wing-origin extension to false, and dont modify
        // need to use signX and signZ

        // basically get atan of both
        int index = Util.fastAtan(signX, signZ)/45;


        boolean[] floor = new boolean[] {false, false, false, false};


        final int minCount = 0;
        final int maxCount = 3;
        int c = 0;

        final int count = Util.randomRange(minCount, maxCount);

        if (count>0)
            for (int i = 0; i < 8; i++) {
                if (Util.randomRange(0, 1) == 0 && c < count) {
                    floor[(i%3 == index) ? i%3 : ((i+1)%3)] = true;
                    c++;
                }
            }

        return floor;
    }

     */
}
