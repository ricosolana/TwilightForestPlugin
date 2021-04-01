package com.crazicrafter1.tfplugin.generation.structure.lich2;

import com.crazicrafter1.tfplugin.generation.structure.TFComponent;
import net.minecraft.server.v1_14_R1.Block;
import net.minecraft.server.v1_14_R1.Blocks;

public class TFLichTowerWing extends TFComponent {

    private int size;

    public TFLichTowerWing(int x, int y, int z, TFComponent.ComponentFace face, int size) {
        super(x, y, z, face);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void decorate() {

    }

    @Override
    public void generate() {

    }

    @Override
    public TFComponent generateNext(ComponentFace direction) {
        return null;
    }

    /*
        Now, all decorator methods (GENERAL, NO SPECIALTY / combinations)
     */

    public void decorateOutlineBookshelves() {

        this.outlineSingleBlockCube(Blocks.BOOKSHELF,
                -size/2 + getX(), getY(), -size/2 + getZ(),
                +size/2 + getX(), getY()+2, +size/2 + getZ(),
                false);

    }

    public void decorateBookshelves() {
        this.solidMultiBlockCube(new Block[]{Blocks.AIR, Blocks.BOOKSHELF},
                -size/2 + getX(), getY(), -size/2 + getZ(),
                +size/2 + getX(), getY()+2, +size/2 + getZ(),
                false);




    }

    public void decorateLootBookshelves() {

        this.decorateBookshelves();
        // choose random chest location, then place at location

    }

    public void decorateWebs() {
        this.solidMultiBlockCube(new Block[]{Blocks.AIR, Blocks.AIR, Blocks.AIR, Blocks.COBWEB},
                -size/2 + getX() + 1, getY(), -size/2 + getZ() + 1,
                +size/2 + getX() - 1, getY()+2, +size/2 + getZ() - 1,
                true);
    }

    public void decorateFurniture() {
        //Player p = null;/
        //this.singleBlockVertColumn(Blocks.OAK_FENCE, getX(), getY()+1, getZ(), 1);
        this.set(Blocks.OAK_FENCE, getX(), getY()+1, getZ(), false);
        this.set(Blocks.OAK_PRESSURE_PLATE, getX(), getY()+2, getZ(), false);
        this.set(BlockIDs.birchStairNX, getX()+2, getY()+1, getZ(), false);
        this.set(BlockIDs.birchStairNZ, getX(), getY()+1, getZ()+2, false);

        this.decorateWebs();
    }

    public void decorateSpiralStairs1() {

        // general 1 wide stairs, varient: birch slab

        // need to get edges of each wall (use trig) with rotating for
        // set each stair->to stone-slab, like so:
        /*
            _=
          _=
        _=
         */
    }

    public void decorateSpiralStairs2() {

        // general 1 wide stairs, varient: stone slab

    }

    public void decorateGlowstoneEaves() {

        /*
            decorate the hanging glowstone eaves,
            lamps of slowstone (might have trapdoors, hanging from iron bars)
         */
        // Eave count will be equivalent to size/2 - 1

        // ex SIZE = 7
        // count = 7/2 - 2 = 3

        // generate random locations and height for each Eave, dependant on height of room

    }

    /* * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~


                MAJOR TODO


        -

     * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ */

    //public void decorate


}
