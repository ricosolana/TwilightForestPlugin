package com.crazicrafter1.tfplugin.crafting;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.managers.TFItemManager;
import com.crazicrafter1.tfplugin.item.items.TFItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class TFCrafting {

    public static HashMap<UUID, TFCrafting> crafting = new HashMap<>();

    private UUID uuid;
    Player player;
    private Inventory inventory;
    private BukkitTask detecter;

    private static ItemStack filler;

    static {
        filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        Util.setName(filler, " ");
    }

    public TFCrafting(Player p) {
        //crafting.put(p.getUniqueId(), new )
        uuid = p.getUniqueId();
        player = p;
        inventory = Bukkit.createInventory(p, 27, "Twilight Crafting");
        fill();
        open();
    }

    public void fill() {
        List<Integer> z = getGrid();
        for (int i=0; i<27; i++) {
            if (!z.contains(i) && i != 15) inventory.setItem(i, filler);
        }
    }

    public List<Integer> getGrid() {

        Integer[] i = new Integer[]
                {2,3,4,
                11,12,13,
                20,21,22};

        return Arrays.asList(i);
    }

    public void returnItems() {
        for (ItemStack item : getGridItems()) {
            if (item != null) player.getInventory().addItem(item);
        }
    }

    void open() {
        player.openInventory(inventory);
        detecter = runThreadRecipes();
    }

    BukkitTask runThreadRecipes() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                parseItemGrid();
            }
        }.runTaskTimer(Main.getInstance(), 1, 5);
    }

    public ArrayList<ItemStack> getGridItems() {
        ArrayList<ItemStack> gridItems = new ArrayList<>();

        for (Integer i : getGrid()) {
            //Main.getInstance().getServer().broadcastMessage("size: " + gridItems.size());
            gridItems.add(inventory.getItem(i));
        }

        return gridItems;
    }

    void setResult(ItemStack item) {
        inventory.setItem(15, item);
    }

    void decrementItems() {

        List<Integer> g = getGrid();

        for (int i=0; i<inventory.getSize(); i++) {
            // inventory.getItem(i).setAmount(inventory.getItem(i).getAmount()-1);
            //ItemStack item = inventory.getItem(i);
            //item.setAmount(item.getAmount()-1);
            if (g.contains(i) && inventory.getItem(i) != null) {
                //inventory.setItem(i, item);
                inventory.getItem(i).setAmount(inventory.getItem(i).getAmount()-1);
            }
        }
    }

    public void onInventoryDrag(InventoryDragEvent e) {

        for (int i : e.getRawSlots()) {
            //player.sendMessage("dslot : " + i);
            if (!getGrid().contains(i)) {
                // cancel
                e.setCancelled(true);
            }
        }

        //parseItemGrid();

        //player.sendMessage("");
    }

    public void print() {
        List<ItemStack> gridItems = getGridItems();
        for (int i=0; i<9; i++) {
            if ((i)%3 == 0) {
                System.out.println();
            }

            if (gridItems.get(i) != null)
                System.out.printf("%s | \t", gridItems.get(i).getType());
            else System.out.print("null | ");
        }

        System.out.println();
    }

    void parseItemGrid() {
        List<ItemStack> gridItems = getGridItems();

        for (TFItem tfitem : TFItems.item_registry.values()) {
            for (CraftableItem craftable : tfitem.getRecipes()) {
                //player.sendMessage("here");

                if (craftable.compare(gridItems)) {
                    if (inventory.getItem(15) != null && (inventory.getItem(15).getType() == craftable.getItem().getType() && inventory.getItem(15).getItemMeta().getDisplayName().equals(craftable.getItem().getItemMeta().getDisplayName()))) {
                        // then cancel, as it is the same item
                        return;
                    }
                    //player.sendMessage("SUCCESS!");

                    setResult(craftable.getItem());

                    new BukkitRunnable() {

                        @Override
                        public void run() {

                            //player.openInventory(inventory);
                            //player.updateInventory();
                        }
                    }.runTaskLater(Main.getInstance(), 1);

                    //player.sendMessage("should give result...");
                    return;
                } else inventory.setItem(15, null);


            }
        }

    }

    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT)) {
            e.setCancelled(true);
            return;
        }

        //player.sendMessage(e.getClick().toString());

        List<Integer> grid = getGrid();
        List<ItemStack> gridItems = getGridItems();

        int slot = e.getRawSlot();

        e.getWhoClicked().sendMessage("" + slot);

        //washn - rit univ

        if (slot == 15) {
            if (inventory.getItem(15) == null) {
                e.setCancelled(true);
                return;
            }
            // then deduct grid
            decrementItems();
            //player.updateInventory();
            return;
        }

        if (!grid.contains(slot) && (slot>=0 && slot<27)) {
            // then test grid
            //parseItemGrid();
            e.setCancelled(true);
        }

        //player.sendMessage("slot : " + e.getRawSlot());

        /*


        if (grid.contains(e.getRawSlot())) {
            player.sendMessage("ran");
            // then test grid
            parseItemGrid();
        } else if (e.getRawSlot() == 15) {
            if (inventory.getItem(15) != null) {
                // then player crafted the item.
                // remove 1 item from each slot of grid
                decrementItems();
            } else e.setCancelled(true);

        } else if (e.getRawSlot()>=0 && e.getRawSlot()<27){
            e.setCancelled(true);
            // then cancel event if is in this inventory
        }

         */


    }

    public void onInventoryClose(InventoryCloseEvent e) {
        returnItems();
        detecter.cancel();
        TFCrafting.crafting.remove(player.getUniqueId());
    }

}
