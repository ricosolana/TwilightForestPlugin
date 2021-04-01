package com.crazicrafter1.tfplugin.boss;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TFBossNaga extends TFBossEntity {

    public TFBossNaga(Entity entity) {
        super(entity);
        getHolder().setCustomName(ChatColor.GREEN + "" + ChatColor.BOLD + "NAGA");
        getHolder().setInvulnerable(true);
        //((CraftSheep) getHolder()).setColor(DyeColor.GREEN);
        getHolder().setCustomNameVisible(true);
        //getHolder().eff
    }

    @Override
    public void update() {
        try {
            getHolder().setVelocity(getTargetVect(getPlayer()).normalize().multiply(.2f));
        } catch (Exception e) {}
    }

    private Player getPlayer() {
        return Main.getInstance().getServer().getPlayer("crazicrafter1");
    }

}
