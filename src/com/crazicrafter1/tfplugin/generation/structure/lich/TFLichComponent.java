package com.crazicrafter1.tfplugin.generation.structure.lich;

import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.generation.structure.TFComponent;
import com.crazicrafter1.tfplugin.world.NMSHandler;
import net.minecraft.server.v1_14_R1.Block;
import net.minecraft.server.v1_14_R1.Blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TFLichComponent extends TFComponent {

    protected enum FloorType {
        BOOKSHELF, CENTER_CHEST, COBWEB, BOOKSHELF_CHEST
    }

    protected enum ComponentType {
        MAIN, PRIMARY, SECONDARY, TERTIARY, QUATERTIARY
    }

    Block[] wallBlockVariants = new Block[] {Blocks.STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICKS,
            Blocks.CRACKED_STONE_BRICKS};

    private ComponentType componentType;
    private int height;

    TFLichComponent(ComponentType componentType, int x, int y, int z, int originDir) {
        //super(x, y, z);
        super(x, y, z, originDir);
        this.componentType = componentType;
    }

    protected ComponentType getComponentType() {
        return componentType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {

        switch (componentType) {

            case MAIN: return 15;
            case PRIMARY: return 9;
            case SECONDARY: return 7;
            case TERTIARY: return 5;
            case QUATERTIARY: return 3;
        }

        return 0;
        //return getSize(this.componentType);
    }

    //abstract protected void generateWings();

    //abstract protected

    /*
    protected int floorHeightFromComponent(FloorType type) {
        switch (type) {
            case COBWEB: return
        }
    }
     */


    protected boolean overlapsOther(TFLichComponent other) {

        int s1 = getSize()/2;
        int s2 = other.getSize()/2;

        if (            Util.inRange(x-s1, x+s1, other.x-s2) || Util.inRange(x-s1, x+s1, other.x+s2)) {
            if (        Util.inRange(y-s1, y+s1, other.y-s2) || Util.inRange(y-s1, y+s1, other.y+s2)) {
                return  Util.inRange(z-s1, z+s1, other.z-s2) || Util.inRange(z-s1, z+s1, other.z+s2);
            }
        }
        return false;
    }

    protected boolean overlapsAny(ArrayList<TFLichComponent> others) {
        for (TFLichComponent other : others) {
            if (this.overlapsOther(other)) return true;
        }
        return false;
    }


    // get the center of a structure from:
    // - the original / parent (THIS)
    // - the direction to extrude towards (in degrees)
    // - the desired wingtype (primary/secondary...)
    protected int[] centerForChild(ComponentType childType, int extrusionDir) {

        int signX = Util.fastCos(extrusionDir);
        int signZ = Util.fastSin(extrusionDir);

        int relEdgeX = signX * (getSize() / 2 + 1);
        int relEdgeZ = signZ * (getSize() / 2 + 1);

        int wingCenterX = this.x + relEdgeX + signX * (getSize(childType)/2);
        int wingCenterZ = this.z + relEdgeZ + signZ * (getSize(childType)/2);

        return new int[] {wingCenterX, wingCenterZ};
    }


    protected static int getSize(ComponentType type) {

        switch (type) {

            case MAIN: return 15;
            case PRIMARY: return 9;
            case SECONDARY: return 7;
            case TERTIARY: return 5;
            case QUATERTIARY: return 3;
        }

        return 0;
    }

    protected final boolean[] generateWings() {
        boolean[] wings = new boolean[] {false, false, false, false};

        final int count = Util.randomRange(2, 3);

        ArrayList<Integer> selected = new ArrayList<>(Arrays.asList(0,1,2,3)); // ;

        for (int i=0; i<count; i++) {
            int index = Util.randomRange(0, selected.size()-1);
            int randWing = selected.get(index);

            selected.remove(index);
            wings[randWing] = true;
        }

        return wings;

    }

    protected final boolean[] generateWings(int originDir) {

        //Integer[] wings = new Integer[] {null, null, null, null};

        // xSignOrigin and zSignOrigin are both relative signs of where the wing split was (the direction of its parent)
        // modify all values except for the one at that index as a degree (use atan with the signs)

        boolean[] wings = new boolean[] {false, false, false, false};

        final int count = Util.randomRange(2, 3);

        List<Integer> selected = new ArrayList<>(); //Arrays.asList(0,1,2,3);
        for (int a=0; a<4; a++) {
            if (a != originDir/90)
                selected.add(a);
        }

        for (int i=0; i<count; i++) {
            int index = Util.randomRange(0, selected.size()-1);
            int randWing = selected.get(index);

            selected.remove(index);
            wings[randWing] = true;
        }

        return wings;
    }

    // generate the openings for children as a parent
    public final void genSideOpenings() {

        //System.out.println(ChatColor.DARK_GREEN + "" + getChildren().size() + " children!");
        // generate openings
        for (TFComponent childComponent : getChildren()) {

            //System.out.println(ChatColor.DARK_GREEN + "child : " + childComponent.x + " " + childComponent.y + " " + childComponent.z + " origin: " + childComponent.getOriginDir());

            TFLichComponent child = (TFLichComponent)childComponent;

            int signX = Util.fastCos(child.getOriginDir()+180);
            int signZ = Util.fastSin(child.getOriginDir()+180);

            int parentEdgeX = x + signX*(getSize()/2);
            int parentEdgeZ = z + signZ*(getSize()/2);

            this.singleBlockVertColumn(net.minecraft.server.v1_14_R1.Blocks.AIR, parentEdgeX, this.y+1, parentEdgeZ, 2);
            NMSHandler.setBlock(BlockIDs.doubleStoneSlab, TFGlobal.TFWORLD, parentEdgeX, this.y+3, parentEdgeZ);
        }

    }

    // generate as a child
    public final void genParentOpening() {

        // open the part towards originDir
        int signX = Util.fastCos(getOriginDir());
        int signZ = Util.fastSin(getOriginDir());

        int parentEdgeX = x + signX*(getSize()/2);
        int parentEdgeZ = z + signZ*(getSize()/2);

        this.singleBlockVertColumn(net.minecraft.server.v1_14_R1.Blocks.AIR, parentEdgeX, this.y+1, parentEdgeZ, 2);
        NMSHandler.setBlock(BlockIDs.doubleStoneSlab, TFGlobal.TFWORLD, parentEdgeX, this.y+3, parentEdgeZ);

    }

}
