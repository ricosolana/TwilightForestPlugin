package com.crazicrafter1.tfplugin;

import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.crafting.TFCrafting;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.managers.TFMapManager;
import com.crazicrafter1.tfplugin.managers.TFStructureManager;
import com.crazicrafter1.tfplugin.portal.TFPortalTeleporter;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TFListener implements Listener {

    public TFListener(Main plugin) {org.bukkit.Bukkit.getPluginManager().registerEvents(this, plugin);}

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {

        if (e.getWorld() == TFGlobal.TFWORLD && e.isNewChunk()) {

            int chunkX = e.getChunk().getX(); int chunkZ = e.getChunk().getZ();

            TFBoss.generator.generate(chunkX, chunkZ);

            //Generator.generate(chunkX, chunkZ);
            TFStructureManager.generate(chunkX, chunkZ);

            //e.getChunk().b
            //TFBiomeManager.setBossBiome(e.getChunk().getX(), e.getChunk().getZ());

            //TFStructure.genBasicPlatforms(e.getChunk());

        }


    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (!TFGlobal.playerProgress.containsKey(e.getPlayer().getUniqueId())) {

            TFGlobal.playerProgress.put(e.getPlayer().getUniqueId(), TFBoss.Type.NAGA);
        }

    }

    /*
    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent e) {
        Player p = ((Player)(e.getInitiator().getHolder()));

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {
            //TFCrafting.crafting.get(p.getUniqueId()).onInventoryDrag(e);
            TFCrafting.crafting.get(p.getUniqueId()).print();
        }

    }

     */

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {
            TFCrafting.crafting.get(p.getUniqueId()).onInventoryDrag(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getView().getPlayer();

        //p.sendMessage((e.getCursor() == null) ? "null" : e.getCursor().getType().toString());

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {
            TFCrafting.crafting.get(p.getUniqueId()).onInventoryClick(e);
        }

    }

    /*
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (((Player)e.getPlayer()).isSneaking() && (e.getInventory() instanceof CraftingInventory)) {
            e.setCancelled(true);
        }
    }

     */

    /*
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (event.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING && TFPortalManager.isActivePortal(event.getIgnitingBlock().getLocation())) {
            event.setCancelled(true);
        }
    }

     */

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        if (e.getCause() == BlockIgniteEvent.IgniteCause.LIGHTNING) {
            if (new TFPortalTeleporter().isActivePortal(e.getIgnitingBlock().getLocation())) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        //p.sendMessage("PlayerInteractEvent called");

        ItemStack item = p.getInventory().getItemInMainHand();

        //p.sendMessage(item.getType().toString());
        //p.sendMessage("interact");

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                if (p.isSneaking()) {
                    e.setCancelled(true);
                    //p.sendMessage("Would open tf crafting!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            // only adds, not updates
                            TFCrafting.crafting.put(p.getUniqueId(), new TFCrafting(p));
                        }
                    }.runTaskLater(Main.getInstance(), 1);
                    return;
                }
            }

            if (TFItems.magicMap.isSameItem(item)) {
                e.setCancelled(true);

                TFMapManager.itemToMap(p);
            }
        }
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e) {

        //((Player)e.getEntity()).sendMessage("Dropped an item");
        //e.getEntity().getItemStack()

        if (e.getEntity().getItemStack().getType() == Material.DIAMOND) {

            //e.getEntity().sendMessage("Dropped a diamond!");

            new BukkitRunnable() {

                int time = 0;

                @Override
                public void run() {

                    TFPortalTeleporter teleporter = new TFPortalTeleporter();

                    if (teleporter.ignitePortal(e.getEntity().getLocation())) {
                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());

                        //e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());

                        this.cancel();
                        return;
                    }

                    if (time > 3) {
                        this.cancel();
                        return;
                    }


                    time++;



                }
            }.runTaskTimer(Main.getInstance(), 5, 20);

        }

    }

    @EventHandler
    public void onPortalEnter(PlayerPortalEvent e) {

        //e.setCancelled(true);

        e.setCancelled(true);

        Player p = e.getPlayer();

        TFPortalTeleporter.Result result = new TFPortalTeleporter().teleport(p);

        if (result == TFPortalTeleporter.Result.INVALID_PORTAL) e.setCancelled(false);

        p.sendMessage(result.name());
        /*

        if (TFPortalManager.isActivePortal(p.getLocation())) {

            e.setCancelled(true);

            if (!TFPortalManager.tpIfExists(p)) {

                TFPortalManager.createAndTP(p);

            }
        }

         */

    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent e) {

        // see if player incProgress is enough
        Biome biome = e.getBlock().getBiome();

        Player p = e.getPlayer();

        if (TFBiomeManager.biomeBosses.containsKey(biome)) {

            p.sendMessage(ChatColor.GRAY + "Destroyed block in boss biome " + biome.name());

            TFBoss.Type required = TFProgression.biomeToBoss(biome);

            TFBoss.Type current = TFGlobal.playerProgress.get(e.getPlayer().getUniqueId());

            if (required == current || TFProgression.isGreater(current, required)) {

                return;
            } else e.setCancelled(true);
        }

        //BlockDamageEvent q;

        /*
        for (int i=0; i<360; i+=90) {
            int x = e.getBlock().getX()+Util.fastCos(i);
            int z = e.getBlock().getX()+Util.fastSin(i);
            if (TFPortalManager.isActivePortal(new Location(e.getPlayer().getWorld(), x, e.getBlock().getY(), z))) {

                // then break portal
                // aka set to water
                NMSHandler.setBlock()

            }
        }

         */

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        Player p = (Player)e.getPlayer();

        if (TFCrafting.crafting.containsKey(p.getUniqueId())) {

            TFCrafting.crafting.get(p.getUniqueId()).onInventoryClose(e); //returnItems();
        }
    }

/*
    @EventHandler
    public void onMapCreation(MapInitializeEvent e) {
        Main.getInstance().getServer().broadcastMessage("MapInitializeEvent called");
        //if (e.)
    }

 */


/*
    @EventHandler
    public void onWorldLoad(final WorldLoadEvent event) {
        Bukkit.getLogger().info("try to hook in to world " + event.getWorld().getName());

        // checking if the Bukkit world is an instance of CraftWorld, if not return
        if (!(event.getWorld() instanceof CraftWorld)) {
            Bukkit.getLogger().info("can't hook into world: " + event.getWorld().getName() + ", because World is not an instance of CraftWorld");
            return;
        }

        final CraftWorld world = (CraftWorld) event.getWorld();

        try {

            // get the playerChunkMap where the ChunkGenerator is store, that we need to override
            final PlayerChunkMap playerChunkMap = world.getHandle().getChunkProvider().playerChunkMap;

            // get the ChunkGenerator from the PlayerChunkMap
            final Field ChunkGeneratorField = PlayerChunkMap.class.getDeclaredField("chunkGenerator");
            ChunkGeneratorField.setAccessible(true);
            final Object chunkGeneratorObject = ChunkGeneratorField.get(playerChunkMap);

            // return, if the chunkGeneratorObject is not an instance of ChunkGenerator
            if(!(chunkGeneratorObject instanceof ChunkGenerator)) {
                Bukkit.getLogger().info("can't hook into world: " + world.getName() + ", because object is not an instance of ChunkTaskScheduler");
                return;
            }

            final ChunkGenerator<?> chunkGenerator = (ChunkGenerator<?>) chunkGeneratorObject;

            // create a new ChunkOverrider
            final TFChunkGenerator<?> overrider = new TFChunkGenerator<>(chunkGenerator);

            // set the ChunkOverrider to the PlayerChunkMap
            ChunkGeneratorField.set(playerChunkMap, overrider);

        } catch (Exception e) {
            Bukkit.getLogger().warning("Unexpected error while hook into world: " + world.getName() + ", send the stacktrace below to the developer");
            e.printStackTrace();
        }
    }

 */


}
