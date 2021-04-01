package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;
import net.minecraft.server.v1_14_R1.Blocks;

import java.util.ArrayList;


public class TFLichComponentMainTower extends TFLichComponent {

    private ArrayList<TFComponent> components;

    @SuppressWarnings("WeakerAccess")
    protected TFLichComponentMainTower(int x, int y, int z) {
        super(ComponentType.MAIN, x, y, z, 0);
        //components = new ArrayList<>();
        components = generateChildren();

        this.generate();
    }

    @Override
    public ArrayList<TFComponent> generateChildren() {
        /*
            BOTTOM CHILD GENERATION
         */

        // get locations with getWing
        boolean[] wings = this.generateWings();

        for (int i=0; i<wings.length; i++) {
            if (wings[i]) {
                TFLichComponent component = null;

                ComponentType randChildComponent = (Util.randomChance(.5f)) ? ComponentType.PRIMARY : ComponentType.SECONDARY;

                int[] center = super.centerForChild(randChildComponent, i*90);
                if (randChildComponent == ComponentType.PRIMARY) {
                    // Util.randomRange(0, 1) == 0
                    component = new TFLichComponentPrimaryWing(center[0], y, center[1], i*90 + 180);

                } else {
                    component = new TFLichComponentSecondaryWing(center[0], y, center[1], i*90 + 180);


                }
                addChild(component);
            }
        }


        /*
            TOP-FLOORS CHILD GENERATION
         */

        /*
        wings = this.generateWings();

        for (int i=0; i<wings.length; i++) {
            if (wings[i]) {
                TFLichComponent component = null;

                ComponentType randChildComponent = (Util.randomChance(.5f)) ? ComponentType.PRIMARY : ComponentType.SECONDARY;

                int[] center = super.centerForChild(randChildComponent, i*90);
                if (randChildComponent == ComponentType.PRIMARY) {
                    // Util.randomRange(0, 1) == 0
                    component = new TFLichComponentPrimaryWing(center[0], y, center[1], i*90 + 180);

                } else {
                    component = new TFLichComponentSecondaryWing(center[0], y, center[1], i*90 + 180);


                }
                addChild(component);
            }
        }

         */

        ArrayList<TFComponent> subChildren = new ArrayList<>();

        for (TFComponent childComponent : getChildren())
            subChildren.addAll(childComponent.generateChildren());

        return subChildren;

    }

    @Override
    public void generate() {

        this.hollowMultiBlockCube(wallBlockVariants, x-getSize()/2, y, z-getSize()/2, x+getSize()/2, y+70, z+getSize()/2, false);

        //int[][] floorExtrusions; // = new int[][];

        this.solidSingleBlockFloodCube(Blocks.COBBLESTONE,
                x-getSize()/2, 1, z-getSize()/2, x+getSize()/2, y-1, z+getSize()/2);

        this.genCenterPool();
        this.genSpiralStair();
        genSideOpenings();

        for (TFComponent childComponent : getChildren()) {
            //((TFLichComponent)childComponent).getComponentType() == T
            childComponent.generate();
        }





       //generateWings();
        //addChild();

    }

    private void genSpiralStair() {



    }

    private void genCenterPool() {



    }

    /*
    @Override
    protected void generateWings() {

        boolean[] wings = getWings();

        for (int i=0; i<wings.length; i++) {

            if (Util.randomRange(0,2)==0) {

                // then generate PrimaryWing
                int rx = Util.fastCos(i * 90) * (size / 2);
                int rz = Util.fastSin(i * 90) * (size / 2);

                int signX = Util.sign(rx);
                int signZ = Util.sign(rz);

                int wingCenterX = x + rx + signX * 5;
                int wingCenterZ = z + rz + signZ * 5;

                if (wings[i]) {
                    this.singleBlockVertColumn(BlockIDs.AIR, x + rx, y + 2, z + rz, -2);

                    NMSHandler.setBlock(doubleStoneSlab, TFGlobal.TFWORLD, x + rx, y + 3, z + rz);

                    addChild(new TFLichComponentPrimaryWing(wingCenterX, y, wingCenterZ,
                            x + rx + signX, z + rz + signZ));

                //if (Util.randomRange(0,2)==0)
                //    new TFLichComponentPrimaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();
                //else new TFLichComponentSecondaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();


                }
            } else {

                // then generate SecondaryWing

                int rx = Util.fastCos(i * 90) * (size / 2);
                int rz = Util.fastSin(i * 90) * (size / 2);

                int signX = Util.sign(rx);
                int signZ = Util.sign(rz);

                int wingCenterX = x + rx + signX * 4;
                int wingCenterZ = z + rz + signZ * 4;

                if (wings[i]) {
                    this.singleBlockVertColumn(BlockIDs.AIR, x + rx, y + 2, z + rz, -2);

                    NMSHandler.setBlock(doubleStoneSlab, TFGlobal.TFWORLD, x + rx, y + 3, z + rz);

                    new TFLichComponentSecondaryWing(wingCenterX, y, wingCenterZ,
                            x + rx + signX, z + rz + signZ).generate();

                //if (Util.randomRange(0,2)==0)
                //    new TFLichComponentPrimaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();
                //else new TFLichComponentSecondaryWing(wingCX, y, wingCZ, x + rx + signX, z + rz + signZ).generate();


                }
            }

        }
    }

    private boolean[] getWings() {
        boolean[] floor = new boolean[] {false, false, false, false};

        final int minCount = 2;
        final int maxCount = 4;
        int c = 0;

        final int count = Util.randomRange(minCount, maxCount);

        if (count > 0)
            for (int i = 0; i < 8; i++) {
                if (Util.randomRange(0, 1) == 0 && c < count) {
                    floor[i%floor.length] = true;
                    c++;
                }
            }

        return floor;
    }
     */
}