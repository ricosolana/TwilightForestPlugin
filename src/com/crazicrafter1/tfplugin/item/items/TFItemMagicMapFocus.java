package com.crazicrafter1.tfplugin.item.items;

import com.crazicrafter1.tfplugin.crafting.CraftableItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TFItemMagicMapFocus extends TFItem {

    public TFItemMagicMapFocus() {
        //super(Material.FEATHER, "&eMagic Map Focus", null);
        super("&eMagic Map Focus", Material.FEATHER, null);

        addRecipe(new CraftableItem(getItem(1),
                new ItemStack[]{
                        new TFItemRavenFeather().getItem(), new ItemStack(Material.GLOWSTONE_DUST), null,
                        null, new TFItemTorchberries().getItem(), null,
                        null, null, null
                }
        ));

    }

}
