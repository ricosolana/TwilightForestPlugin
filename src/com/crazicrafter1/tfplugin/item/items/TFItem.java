package com.crazicrafter1.tfplugin.item.items;

import com.crazicrafter1.tfplugin.crafting.CraftableItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TFItem {

    private Material material;
    //private String id;
    private String name;
    private List<String> lore;
    //private List<St>
    //private ItemStack recipeItem;
    //private String[] recipe;
    ArrayList<CraftableItem> recipes;

    public TFItem(String name, Material material, List<String> lore) {
        recipes = new ArrayList<>();
        //recipe = new String[3];
        this.material = material;
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.lore = lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return this.name;
    }

    public  List<String> getLore() {
        return this.lore;
    }

    public ItemStack getItem() {
        return getItem(1);
    }

    public ItemStack getItem(int count) {

        ItemStack item = new ItemStack(this.material, count);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public boolean isSameItem(ItemStack item) {

        //System.out.println("Comparing materials : " + item.getType() + " and " + this.material);
        //System.out.println("Comparing names : " + item.getItemMeta().getDisplayName() + ChatColor.RESET + " and " + this.getName());


        if (item == null) return false;

        return item.getType() == this.material &&
                ChatColor.stripColor(item.getItemMeta().getDisplayName()).equals(ChatColor.stripColor(this.getName()));

        //System.out.println("are not the same item...");
    }

    public void addRecipe(CraftableItem recipe) {
        recipes.add(recipe);
    }

    public ArrayList<CraftableItem> getRecipes() {
        return recipes;
    }

    /*
    public void setRecipeItem(int count, String a, String b, String c) {
        recipeItem = getItem(count);

        recipe[0] = a;
        recipe[1] = b;
        recipe[2] = c;
    }

    public ItemStack getRecipeItem() {
        return recipeItem;
    }

    public String[] getRecipe() {
        return recipe;
    }

     */



    /*
    public void setRecipe(String r1, String r2, String r3) {

    }

     */

    public void rightClickEvent(Player p) {}

}
