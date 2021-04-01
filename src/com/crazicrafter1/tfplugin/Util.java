package com.crazicrafter1.tfplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Util {

    public static Color matchColor(String c)
    {
        String color = c.toUpperCase();
        // BLUE, RED, WHITE, GRAY, GREEN, YELLOW, AQUA, BLACK, FUCHSIA, LIME, MAROON, NAVY, OLIVE
        // ORANGE, PURPLE, SILVER, TEAL
        if (color.equals("BLUE"))
            return Color.BLUE;
        if (color.equals("RED"))
            return Color.RED;
        if (color.equals("WHITE"))
            return Color.WHITE;
        if (color.equals("GRAY"))
            return Color.GRAY;
        if (color.equals("GREEN"))
            return Color.GREEN;
        if (color.equals("YELLOW"))
            return Color.YELLOW;
        if (color.equals("AQUA"))
            return Color.AQUA;
        if (color.equals("BLACK"))
            return Color.BLACK;
        if (color.equals("FUCHSIA"))
            return Color.FUCHSIA;
        if (color.equals("LIME"))
            return Color.LIME;
        if (color.equals("MAROON"))
            return Color.MAROON;
        if (color.equals("NAVY"))
            return Color.NAVY;
        if (color.equals("OLIVE"))
            return Color.OLIVE;
        if (color.equals("ORANGE"))
            return Color.ORANGE;
        if (color.equals("PURPLE"))
            return Color.PURPLE;
        if (color.equals("SILVER"))
            return Color.SILVER;
        if (color.equals("TEAL"))
            return Color.TEAL;
        return null;
    }

    public static int getCenter(int i) {

        int sign = Util.strictSign(i);

        return (i/2048)*2048+(1024*sign);
    }

    public static void setName(ItemStack item, String name)
    {
        /*
        name
         */
        ItemMeta meta = item.getItemMeta();

        //noinspection ConstantConditions
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r"+name));
        item.setItemMeta(meta);
    }

    public static boolean isOnline(String name)
    {
        //noinspection deprecation
        return Bukkit.getServer().getPlayer(name) == null;
    }

    @SuppressWarnings({"deprecation", "ConstantConditions"})
    public static UUID getUUID(String name)
    {
        if (Bukkit.getServer().getPlayer(name) != null)
            return Bukkit.getServer().getPlayer(name).getUniqueId();
        return null;
    }

    public static void setLore(ItemStack item, List<String> lore)
    {
        ItemMeta meta = item.getItemMeta();

        List<String> loreList = lore;

        for (int i=0; i<loreList.size(); i++)
        {
            loreList.set(i, ChatColor.translateAlternateColorCodes('&', "&r"+loreList.get(i)));
        }

        meta.setLore(lore);

        item.setItemMeta(meta);
    }

    public static boolean in2dPolygon(int x, int y, int[][] pts) {

        int[] polyX = new int[pts.length];
        int[] polyY = new int[pts.length];

        for (int i=0; i<pts.length; i++) {
            polyX[i] = pts[i][0];
            polyY[i] = pts[i][1];
        }

        int i, j = pts.length - 1;
        boolean oddNodes = false;

        for (i = 0; i < pts.length; i++) {
            if (polyY[i] < y && polyY[j] >= y
                    || polyY[j] < y && polyY[i] >= y) {
                if (polyX[i] + (y - polyY[i]) / (polyY[j] - polyY[i]) * (polyX[j] - polyX[i]) < x) {
                    oddNodes = !oddNodes;
                }
            }
            j = i;
        }

        return oddNodes;

    }

    public static int[] randomLoc(int a, int b, int min, int max) {
        // get a valid x location
        int r = randomRange(min, max);
        int angle = randomRange(0, 360);

        return new int[] {(int) Math.cos((double)angle*(double)r)+a, (int) Math.sin((double)angle*(double)r)+b};
    }

    public static int randomRange(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static int randomRange(int min, int max, int min1, int max1)
    {
        if ((int)(Math.random()*2) == 0)
            return min + (int)(Math.random() * ((max - min) + 1));
        return min1 + (int)(Math.random() * ((max1 - min1) + 1));
    }

    public static int randomRange(int min, int max, Random random)
    {
        return random.nextInt((max - min) + 1) + min;
    }

    // argument: float 0 -> 1
    public static boolean randomChance(float i)
    {
        return i <= Math.random() * 100;
    }

    public static int[] randomLoc(int a, int b, int min, int max, Random random) {
        // get a valid x location
        int r = randomRange(min, max, random);
        int angle = randomRange(0, 360, random);

        return new int[] {(int) Math.cos((double)angle*(double)r)+a, (int) Math.sin((double)angle*(double)r)+b};
    }

    public static boolean fineOverlap(int x1, int y1, int x2, int y2, int r) {

        int distance = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);

        if (distance - r >= 0) {

            return  (distance <= 2 * r); // return true;

        }
        return false;
    }

    public static int normalizeAngle(int angle) {

        int a = angle%360;

        return (a<0)?a+360:a;

    }

    public static int sqDist(int x1, int y1, int x2, int y2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(sqDist(x1, y1, x2, y2));
    }


    public static Integer safeToInt(String s)
    {
        //if ()
        // test if is numeric

        if (isNumeric(s))
        {
            return Integer.parseInt(s);
        }

        return null;
    }

    public static boolean isNumeric(String s)
    {
        for (int i=0;i<s.length();i++)
        {
            try {
                Integer.parseInt(s.substring(i));
            }
            catch(NumberFormatException e)
            {
                return false;
            }
        }
        return true;
    }

    public static int sign(int i) {

        if (i>0) return 1;
        if (i<0) return -1;
        return 0;
    }

    public static int strictSign(int i) {

        if (i>=0) return 1;
        return -1;
    }

    /*

            TRIGONOMETRY UTIL

     */

    private final static int[] sinTable = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    private final static int[][] tanTable = new int[][] {{1, 0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1, -1}, {0, -1}, {1, -1}};

    public static int fastSin(int degree) {
        return sinTable[normalizeAngle(degree)/45];
    }

    public static int fastCos(int degree) {
        return fastSin(degree+90);
    }

    public static int fastAtan(int x, int y) {
        for (int d=0; d<tanTable.length; d++) {
            if (tanTable[d][0] == x && tanTable[d][1] == y) return d*45;
        }
        return -1;
    }



    public static boolean inRange(int i, int min, int max) {
        return (i <= max && min >= i);
    }

    public static int clamp(int i, int min, int max) {

        return Math.max(min, Math.min(i, max));

    }

    public static Enchantment matchEnchant(String enchant)
    {
        String e = enchant.toUpperCase();

        if (e.equals("DURABILITY") || e.equals("UNBREAKING"))
            return Enchantment.DURABILITY;
        if (e.equals("ARROW_DAMAGE") || e.equals("POWER"))
            return Enchantment.ARROW_DAMAGE;
        if (e.equals("ARROW_FIRE") || e.equals("FLAME"))
            return Enchantment.ARROW_FIRE;
        if (e.equals("ARROW_INFINITE") || e.equals("INFINITY"))
            return Enchantment.ARROW_INFINITE;
        if (e.equals("ARROW_KNOCKBACK") || e.equals("PUNCH"))
            return Enchantment.ARROW_KNOCKBACK;
        if (e.equals("BINDING_CURSE") || e.equals("CURSE_OF_BINDING"))
            return Enchantment.BINDING_CURSE;
        if (e.equals("CHANELLING"))
            return Enchantment.CHANNELING;
        if (e.equals("DAMAGE_ALL") || e.equals("SHARPNESS"))
            return Enchantment.DAMAGE_ALL;
        if (e.equals("DAMAGE_ANTHROPODS") || e.equals("BANE_OF_ANTHROPODS"))
            return Enchantment.DAMAGE_ARTHROPODS;
        if (e.equals("DAMAGE_UNDEAD") || e.equals("SMITE"))
            return Enchantment.DAMAGE_UNDEAD;
        if (e.equals("DEPTH_STRIDER"))
            return Enchantment.DEPTH_STRIDER;
        if (e.equals("DIG_SPEED") || e.equals("EFFICIENCY"))
            return Enchantment.DIG_SPEED;
        if (e.equals("FIRE_ASPECT"))
            return Enchantment.FIRE_ASPECT;
        if (e.equals("FROST_WALKER"))
            return Enchantment.FROST_WALKER;
        if (e.equals("IMPALING"))
            return Enchantment.IMPALING;
        if (e.equals("KNOCKBACK"))
            return Enchantment.KNOCKBACK;
        if (e.equals("LOOT_BONUS_BLOCKS") || e.equals("FORTUNE"))
            return Enchantment.LOOT_BONUS_BLOCKS;
        if (e.equals("LOOT_BONUS_MOBS") || e.equals("LOOTING"))
            return Enchantment.LOOT_BONUS_MOBS;
        if (e.equals("LOYALTY"))
            return Enchantment.LOYALTY;
        if (e.equals("LUCK") || e.equals("LUCK_OF_THE_SEA"))
            return Enchantment.LUCK;
        if (e.equals("LURE"))
            return Enchantment.LURE;
        if (e.equals("MENDING"))
            return Enchantment.MENDING;
        if (e.equals("MULTISHOT"))
            return Enchantment.MULTISHOT;
        if (e.equals("OXYGEN") || e.equals("RESPIRATION"))
            return Enchantment.OXYGEN;
        if (e.equals("PIERCING"))
            return Enchantment.PIERCING;
        if (e.equals("PROTECTION_ENVIRONMENTAL") || e.equals("PROTECTION"))
            return Enchantment.PROTECTION_ENVIRONMENTAL;
        if (e.equals("PROTECTION_FIRE") || e.equals("FIRE_PROTECTION"))
            return Enchantment.PROTECTION_FIRE;
        if (e.equals("PROTECTION_FALL") || e.equals("FEATHER_FALLING"))
            return  Enchantment.PROTECTION_FALL;
        if (e.equals("PROTECTION_EXPLOSIONS") || e.equals("BLAST_PROTECTION"))
            return Enchantment.PROTECTION_EXPLOSIONS;
        if (e.equals("PROTECTION_PROJECTILE") || e.equals("PROJECTILE_PROTECTION"))
            return Enchantment.PROTECTION_PROJECTILE;
        if (e.equals("QUICK_CHARGE"))
            return Enchantment.QUICK_CHARGE;
        if (e.equals("RIPTIDE"))
            return Enchantment.RIPTIDE;
        if (e.equals("SILK_TOUCH"))
            return Enchantment.SILK_TOUCH;
        if (e.equals("SWEEPING_EDGE"))
            return Enchantment.SWEEPING_EDGE;
        if (e.equals("THORNS"))
            return Enchantment.THORNS;
        if (e.equals("VANISHING_CURSE") || e.equals("CURSE_OF_VANISHING"))
            return Enchantment.VANISHING_CURSE;
        if (e.equals("WATER_WORKER") || e.equals("AQUA_AFFINITY"))
            return Enchantment.WATER_WORKER;
        return null;
    }


}
