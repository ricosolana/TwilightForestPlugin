package com.crazicrafter1.tfplugin.portal;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Random;

public class TFPortal {

    public enum Result {
        SUCCESS, INVALID_PORTAL, INVALID_DIM, SAFE_NOT_FOUND
    }

    //private static ArrayList<>

    private Location location;
    private Location otherLocation;
    private static final Material portalBlock = Material.END_PORTAL;

    /*
        Creates a TFPortal preparer
     */
    @SuppressWarnings("ConstantConditions")
    public TFPortal(Location location) {
        this.location = location;

        this.otherLocation = location.clone();
        this.otherLocation.setWorld(Main.getOther(location.getWorld()));
    }

    @SuppressWarnings("ConstantConditions")
    private boolean orient() {

        // This uses minimal resource relative block detection
        // to locate the corner block (will be at portal/water height)
        int bx = location.getBlockX(),
                by = location.getBlockY(),
                bz = location.getBlockZ();

        World w = location.getWorld();
        if (isDirt(w, bx - 1, by, bz) &&
                isDirt(w, bx, by, bz - 1))
            this.location.subtract(1, 0, 1);
        else if (isDirt(w, bx + 1, by, bz) &&
                isDirt(w, bx, by, bz - 1))
            this.location.subtract(2, 0, 1);
        else if (isDirt(w, bx + 1, by, bz) &&
                isDirt(w, bx, by, bz + 1))
            this.location.subtract(2, 0, 2);
        else if (isDirt(w, bx, by, bz + 1) &&
                isDirt(w, bx - 1, by, bz))
            this.location.subtract(1, 0, 2);
        else
            return false;

        //System.out.println("Corner location " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());

        return true;
    }

    private static void setPortalBlocks(Location location) {
        location.add(1, 0, 1).getBlock().setType(portalBlock);
        location.add(0, 0, 1).getBlock().setType(portalBlock);
        location.add(1, 0, 0).getBlock().setType(portalBlock);
        location.add(0, 0, -1).getBlock().setType(portalBlock);
        location.subtract(2, 0, 1);
    }

    private static void setDirtBlocks(Location location) {
        World w = location.getWorld();

        int bx = location.getBlockX(),
                by = location.getBlockY(),
                bz = location.getBlockZ();

        for (int h = 0; h < 2; h++) {
            for (int x = 0; x < 4; x++) {
                for (int z = 0; z < 4; z++) {
                    w.getBlockAt(bx + x, by - h, bz + z).setType(Material.GRASS_BLOCK);
                }
            }
        }

        //location.getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, 1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, 1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, 1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, 0).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, 0).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, 0).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, -1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, -1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(0, 0, -1).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, -0).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, -0).getBlock().setType(Material.GRASS_BLOCK);
        //location.add(1, 0, -0).getBlock().setType(Material.GRASS_BLOCK);
    }

    private static void setFlowerBlocks(Location location) {
        World w = location.getWorld();

        int bx = location.getBlockX(),
                h = location.getBlockY() + 1,
                bz = location.getBlockZ();

        Random random = new Random(location.getWorld().getSeed() + bx * h - bz);

        // set random flowers in area
        for (int x = 0; x < 4; x++) {
            for (int z = 0; z < 4; z++) {
                Block block = w.getBlockAt(x + bx, h, z + bz);
                switch (random.nextInt(6)) {
                    case 0: block.setType(Material.LILAC); break;
                    case 1: block.setType(Material.RED_TULIP); break;
                    case 2: block.setType(Material.BLUE_ORCHID); break;
                    case 3: block.setType(Material.AZURE_BLUET); break;
                    case 4: block.setType(Material.OXEYE_DAISY); break;
                    case 5: block.setType(Material.DANDELION); break;
                }
            }
        }

        for (int x = 1; x < 3; x++) {
            for (int z = 1; z < 3; z++) {
                w.getBlockAt(x + bx, h, z + bz).setType(Material.AIR);
            }
        }
    }

    private static boolean isPortalBlock(Location location) {
        return location.getBlock().getType() == portalBlock;
    }

    private static boolean isDirt(World world, int x, int y, int z) {
        Material mat = world.getBlockAt(x, y, z).getType();
        //System.out.println(mat.name() + " " + x + " " + y + " " + z + " " + world.getName());
        switch (mat) {
            case DIRT:
            case GRASS_BLOCK:
            case PODZOL:
            case MYCELIUM:
            case COARSE_DIRT:
            case FARMLAND:
                return true;
            default:
                return false;
        }
    }

    private static boolean isDirt(Location location) {
        Material mat = location.getBlock().getType();
        switch (mat) {
            case DIRT:
            case GRASS_BLOCK:
            case PODZOL:
            case MYCELIUM:
            case COARSE_DIRT:
            case FARMLAND:
                return true;
            default:
                return false;
        }
    }

    private static boolean isFlower(Location location) {
        Material mat = location.getBlock().getType();

        switch (mat) {
            case ORANGE_TULIP:
            case PINK_TULIP:
            case RED_TULIP:
            case WHITE_TULIP:
            case CORNFLOWER:
            case SUNFLOWER:
            case AZURE_BLUET:
            case POPPY:
            case OAK_SAPLING:
            case BIRCH_SAPLING:
            case SPRUCE_SAPLING:
            case DARK_OAK_SAPLING:
            case ACACIA_SAPLING:
            case JUNGLE_SAPLING:
            case DEAD_BUSH:
            case BLUE_ORCHID:
            case ALLIUM:
            case OXEYE_DAISY:
            case LILY_OF_THE_VALLEY:
            case WITHER_ROSE:
            case DANDELION:
            case PEONY:
            case ROSE_BUSH:
            case LILAC:
                return true;
            default:
                return false;

        }
    }

    //protected boolean isWater(World world, int x, int y, int z) {
    //    Block block = world.getBlockAt(x, y, z);
    //    return block.getType() == Material.WATER && block.getData() == 0;
    //    //return world.getBlockAt(x, y, z).getType() == Material.WATER;
    //}

    private static boolean isWater(Location location) {
        Block block = location.getBlock();
        return block.getType() == Material.WATER && block.getData() == 0;
        //return world.getBlockAt(x, y, z).getType() == Material.WATER;
    }

    /*
        will "whisk" the player to or from the Twilight Forest (teleport)
     */
    public static TFPortal.Result teleport(Player p) {

        TFPortal portal = new TFPortal(p.getLocation());

        if (!portal.orient())
            return Result.INVALID_PORTAL;

        if (portal.otherLocation.getWorld() == null) return Result.INVALID_DIM;

        // if player jumps into lit portal
        if (TFPortal.isValidLitPortal(portal.location, true)) {

            Location foundPortal = TFPortal.findValidPortal(portal.otherLocation, 32);

            if (foundPortal == null) {
                Location safeLocation = portal.findSafeSpot(32);

                if (safeLocation == null) return TFPortal.Result.SAFE_NOT_FOUND;

                TFPortal.createValidLitPortal(safeLocation);
                p.teleport(safeLocation.add(-1, 1, -1), PlayerTeleportEvent.TeleportCause.PLUGIN);

                p.sendMessage("created a portal!");

                return TFPortal.Result.SUCCESS;

            } else {
                p.teleport(foundPortal, PlayerTeleportEvent.TeleportCause.PLUGIN);

                p.sendMessage("found a portal!");

                return TFPortal.Result.SUCCESS;
            }

        }

        return TFPortal.Result.INVALID_PORTAL;
    }



    private static boolean isValidPortalFrame(Location location, boolean skipDirt) {
        if (skipDirt) {
            boolean b = isFlower(location.add(0, 1, 1)) && isFlower(location.add(0, 0, 1)) &&
                    isFlower(location.add(1, 0, 1)) && isFlower(location.add(1, 0, 0))  &&
                    isFlower(location.add(1, 0, -1)) && isFlower(location.add(0, 0, -1))  &&
                    isFlower(location.add(-1, 0, -1)) && isFlower(location.add(-1, 0, 0));
            location.add(-1, -1, 0);
            return b;
        }
        boolean b = isDirt(location.add(0, 0, 1)) && isDirt(location.add(0, 0, 1)) &&
                isDirt(location.add(1, 0, 1)) && isDirt(location.add(1, 0, 0)) &&
                isDirt(location.add(1, 0, -1)) && isDirt(location.add(0, 0, -1)) &&
                isDirt(location.add(-1, 0, -1)) && isDirt(location.add(-1, 0, 0)) &&
                isFlower(location.add(-1, 1, 1)) && isFlower(location.add(0, 0, 1)) &&
                isFlower(location.add(1, 0, 1)) && isFlower(location.add(1, 0, 0))  &&
                isFlower(location.add(1, 0, -1)) && isFlower(location.add(0, 0, -1))  &&
                isFlower(location.add(-1, 0, -1)) && isFlower(location.add(-1, 0, 0));
        location.add(-1, -1, 0);
        return b;
    }

    private static boolean isValidLitPortal(Location location, boolean skipDirt) {
        return isPortalBlock(location.add(1, 0, 1)) &&
                TFPortal.isValidPortalFrame(location.subtract(1, 0, 1), skipDirt);
    }

    private static boolean isValidUnlitPortal(Location location, boolean skipDirt) {
        return isWater(location.add(1, 0, 1)) && isWater(location.add(0, 0, 1)) &&
                isWater(location.add(1, 0, 0)) && isWater(location.add(0, 0, -1)) &&
                TFPortal.isValidPortalFrame(location.subtract(2, 0, 1), skipDirt);
    }

    public boolean ignitePortal(Item triggerItem) {
        if (!orient()) {
            //System.out.println("failed orient");
            return false;
        }

        if (isValidUnlitPortal(this.location, true)) {
            //System.out.println("Portal igniting");
            triggerItem.remove();

            setPortalBlocks(location);

            location.getWorld().strikeLightning(location.add(1.5, 0, 1.5));
            return true;
        }
        return false;
    }



    /*
        Returns a "safe" spot in the other dimension for portal placement (not that safe)
            notice the 4 for loops, it iterates in a circular pattern
     */
    @SuppressWarnings("ConstantConditions")
    private Location findSafeSpot(int size) {


        World w = otherLocation.getWorld();

        //System.out.println(w.getName());

        int bx = otherLocation.getBlockX(),
                bz = otherLocation.getBlockZ();



        Location loc = new Location(w, bx - 2,
                TFPortal.getHighestSolidBlockY(w, bx - 2, bz - 2, false),
                bz - 2,
                otherLocation.getYaw(), otherLocation.getPitch());

        System.out.println("" + loc);

        if (true)
            return loc;



        /*
            TODO
            fix the below
         */

        int x = 0, z = 0;
        int v;
        for (int s = 1; s < size; s++) {

            // up
            for (v=0; v < s; v++) {
                int h = TFPortal.getHighestSolidBlockY(w, x+bx, z+bz, false);

                z++;
                if (h == -1)
                    continue;

                if (w.getBlockAt(x+bx, h, z+bz).getType() != Material.GRASS)
                    continue;

                return new Location(w, x+bx, h, z+bz);
            }

            // right
            for (v=0; v < s; v++) {
                int h = TFPortal.getHighestSolidBlockY(w, x+bx, z+bz, false);

                x++;
                if (h == -1)
                    continue;

                if (w.getBlockAt(x+bx, h, z+bz).getType() != Material.GRASS)
                    continue;

                return new Location(w, x+bx, h, z+bz);
            }

            // required for both down and left ground scans
            s++;

            // down
            for (v=0; v < s; v++) {
                int h = TFPortal.getHighestSolidBlockY(w, x+bx, z+bz, false);

                z--;
                if (h == -1)
                    continue;

                if (w.getBlockAt(x+bx, h, z+bz).getType() != Material.GRASS)
                    continue;

                return new Location(w, x+bx, h, z+bz);
            }

            // left
            for (v=0; v < s; v++) {
                int h = TFPortal.getHighestSolidBlockY(w, x+bx, z+bz, false);

                x--;
                if (h == -1)
                    continue;

                if (w.getBlockAt(x+bx, h, z+bz).getType() != Material.GRASS)
                    continue;

                return new Location(w, x+bx, h, z+bz);
            }

        }

        return null;
    }



    //private void createValidLitPortal(Location location) {
    public static void createValidLitPortal(Location location) {

        /*
            DEBUG PURPOSES
         */
        //TFPortal portal = new TFPortal(location.subtract(0, 8, 0));
        TFPortal.setDirtBlocks(location);
        TFPortal.setFlowerBlocks(location);
        TFPortal.setPortalBlocks(location);




        //System.out.println("Creating portal at " + location);
        //this.setDirtBlocks();
        //this.setFlowerBlocks();
        //TFPortal.setPortalBlocks(location);
    }



    @SuppressWarnings("ConstantConditions")
    public static Location findValidPortal(Location location, int size) {
        size /= 2;

        World w = location.getWorld();

        int startX = location.getBlockX();
        int startZ = location.getBlockZ();

        for (int x=startX-size; x<startX+size; x++) {
            for (int z=startZ-size; z<startZ+size; z++) {

                for (int h=TFPortal.getHighestBlockY(w, x, z); h > 0; h--) {

                    // search world for nearby portal
                    if (TFPortal.isValidLitPortal(new Location(w, x, h, z), false))
                        return new Location(w, x - 1, h, z - 1, location.getYaw(), location.getPitch());
                }
            }
        }
        return null;
    }



    // returns the highest block y at the height of the block (not 1 above)
    private static int getHighestBlockY(World world, int x, int z) {
        world.loadChunk(x/16, z/16);
        int y=255;
        while (y > 0 && world.getBlockAt(x, y, z).isEmpty()) {
            y--;
        }
        return y;
    }


    /*
        Returns the highest solid block
        if allowFluids, will pass through fluids
        else, will return -1 on a fluid
     */
    private static int getHighestSolidBlockY(World world, int x, int z, boolean allowFluids) {
        world.loadChunk(x/16, z/16);
        int y=255;
        if (allowFluids) {
            while (y > 0 && (world.getBlockAt(x, y, z).isEmpty() || !world.getBlockAt(x, y, z).getType().isSolid())) {
                y--;
            }
        } else {
            while (y > 0 && (world.getBlockAt(x, y, z).isEmpty() || !world.getBlockAt(x, y, z).getType().isSolid()) && (
                    world.getBlockAt(x, y, z).getType() != Material.WATER && world.getBlockAt(x, y, z).getType() != Material.LAVA)
            ) {
                y--;
            }
            if (world.getBlockAt(x, y, z).getType() == Material.WATER || world.getBlockAt(x, y, z).getType() == Material.LAVA)
                return -1;
        }
        return y;
    }

}
