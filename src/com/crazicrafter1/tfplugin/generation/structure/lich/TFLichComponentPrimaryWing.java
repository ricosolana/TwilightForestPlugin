package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;

import java.util.ArrayList;

public class TFLichComponentPrimaryWing extends TFLichComponent {

    //private int signX, signZ;

    public TFLichComponentPrimaryWing(int x, int y, int z, int originDir) { //, int wingX, int wingZ) {
        super(ComponentType.PRIMARY, x, y, z, originDir);
        //this.signX = Util.sign(wingX - x);
        //this.signZ = Util.sign(wingZ - z);

        //generateChildren();
    }

    @Override
    public void generate() {

        //System.out.println(ChatColor.RED + "YOU SHOULD NOT SEE THIS MESSAGE (2)!");

        int floorCount = Util.randomRange(2, 3);

        hollowMultiBlockCube(wallBlockVariants,
                x-getSize()/2,  y,               z-getSize()/2,
                x+getSize()/2,  y+floorCount*5,  z+getSize()/2,
                false);

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

        //System.out.println(ChatColor.RED + "YOU SHOULD NOT SEE THIS MESSAGE (1)!");

        // the generator of all (children locations only)
        boolean[] wings = this.generateWings(getOriginDir());

        for (int i=0; i<wings.length; i++) {
            if (wings[i]) {
                int[] center = super.centerForChild(ComponentType.SECONDARY, i*90);

                TFLichComponent component = new TFLichComponentSecondaryWing(center[0], y, center[1], i*90 + 180);

                addChild(component);
            }
        }

        /*
        for (TFComponent childComponent : getChildren())
            childComponent.generateChildren();

         */

        ArrayList<TFComponent> subChildren = new ArrayList<>();

        for (TFComponent childComponent : getChildren())
            subChildren.addAll(childComponent.generateChildren());

        return subChildren;

    }

    /*
    @Override
    protected void generateWings() {

        ArrayList<TFLichComponent> generatedComponents = new ArrayList<>();

        boolean[] wings = getWings();

        for (int i=0; i<wings.length; i++) {

            int rx = Util.fastCos(i*90)*5;
            int rz = Util.fastSin(i*90)*5;

            int signX = Util.sign(rx);
            int signZ = Util.sign(rz);

            int wingCX = x + rx + signX*4;
            int wingCZ = z + rz + signZ*4;

            if (wings[i]) {
                this.singleBlockVertColumn(BlockIDs.AIR, x + rx, y + 2, z + rz, -2);

                NMSHandler.setBlock(doubleStoneSlab, TFGlobal.TFWORLD, x + rx, y + 3, z + rz);

                //new TFLichComponentTertiaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();
                generatedComponents.add(new TFLichComponentSecondaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ));

            }

        }

    }

    private boolean[] getWings() {

        boolean[] floor = new boolean[] {false, false, false, false};

        final int minCount = 0;
        final int maxCount = 3;
        int c = 0;

        final int count = Util.randomRange(minCount, maxCount);

        if (count>0)
            for (int i = 0; i < 8; i++) {
                if (Util.randomRange(0, 1) == 0 && c < count) {
                    floor[(i%3)+1] = true;
                    c++;
                }
            }

        return floor;
    }
     */
}
