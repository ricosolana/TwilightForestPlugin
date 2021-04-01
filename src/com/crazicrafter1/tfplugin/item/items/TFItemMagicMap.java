package com.crazicrafter1.tfplugin.item.items;

import com.crazicrafter1.tfplugin.crafting.CraftableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TFItemMagicMap extends TFItem {

    public TFItemMagicMap() {
        //super(Material.MAP, "&fBlank Magic Map", null);
        super("&fBlank Magic Map", Material.MAP, null);

        addRecipe(new CraftableItem(getItem(1),
                new ItemStack[] {
                        new ItemStack(Material.PAPER), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER),
                        new ItemStack(Material.PAPER), new TFItemMagicMapFocus().getItem(),  new ItemStack(Material.PAPER),
                        new ItemStack(Material.PAPER), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER)
                }
                ));
    }

    @Override
    public void rightClickEvent(Player p) {
        ItemStack item = new ItemStack(Material.FILLED_MAP, 1);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(super.getName());
        meta.setLore(super.getLore());

        item.setItemMeta(meta);

        p.getInventory().setItemInMainHand(item); //getItemInMainHand() = new ItemStack(Material.)

        item = new ItemStack(Material.FILLED_MAP, 1);
    }
}
