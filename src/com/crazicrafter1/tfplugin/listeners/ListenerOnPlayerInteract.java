package com.crazicrafter1.tfplugin.listeners;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.crafting.TFCrafting;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.managers.TFBiomeManager;
import com.crazicrafter1.tfplugin.managers.TFMapManager;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ListenerOnPlayerInteract implements Listener {

    public ListenerOnPlayerInteract(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
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

                TFMapManager.itemToMap(p, Main.getInstance());
            }

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if (e.getClickedBlock().getType().isInteractable()) {

                    if (p.getGameMode() == GameMode.CREATIVE) return;

                    Biome biome = e.getClickedBlock().getBiome();

                    if (TFBiomeManager.biomeBosses.containsKey(biome)) {

                        //p.sendMessage(ChatColor.GRAY + "Destroyed block in boss biome " + biome.name());

                        TFBoss.Type required = TFProgression.biomeToBoss(biome);

                        TFBoss.Type current = TFGlobal.playerProgress.get(e.getPlayer().getUniqueId());

                        if (required == current || TFProgression.isGreater(current, required)) {
                            return;
                        } else e.setCancelled(true);
                    }

                }

            }

        }
    }

}
