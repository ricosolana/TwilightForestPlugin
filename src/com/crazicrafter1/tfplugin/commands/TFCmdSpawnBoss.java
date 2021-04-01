package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBossNaga;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TFCmdSpawnBoss extends TFCommand {

    public TFCmdSpawnBoss() {
        super("spawnboss");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        Entity entity = p.getWorld().spawnEntity(p.getLocation(), EntityType.SHEEP);

        Main.boss = new TFBossNaga(entity);

        return true;
    }
}
