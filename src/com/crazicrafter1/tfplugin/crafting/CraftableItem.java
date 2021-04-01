package com.crazicrafter1.tfplugin.crafting;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CraftableItem {

    private List<ItemStack> gridItems;
    private ItemStack item;

    public CraftableItem(ItemStack item, List<ItemStack> items) {
        gridItems = items;
        this.item = item;
    }

    public CraftableItem(ItemStack item, ItemStack[] items) {
        gridItems = Arrays.asList(items);
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<ItemStack> getGridItems() {
        return gridItems;
    }

    public boolean compare(List<ItemStack> items) {
        li:
        {
            for (int i = 0; i < 9; i++) {
                ItemStack item1 = items.get(i);

                //e.getViewers().get(0).sendMessage("" + i + " : " + item.getType());

                //if (item1 == null) return ;

                ItemStack item2 = gridItems.get(i);

                if (((item2 == null && item1 != null) ||
                        (item2 != null && item1 == null))) break li;

                //noinspection ConstantConditions
                if (item2 == null && item1 == null) continue;

                if (item2.getType() != item1.getType() ||
                        !ChatColor.stripColor(item2.getItemMeta().getDisplayName()).equals(ChatColor.stripColor(item1.getItemMeta().getDisplayName()))) {

                    break li;
                }
            }

            return true;
        }
        return false;
    }
}
