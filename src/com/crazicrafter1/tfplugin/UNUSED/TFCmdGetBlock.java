package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.world.NMSHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdGetBlock extends TFCommand implements CommandExecutor {

    public TFCmdGetBlock(Main plugin) {
        plugin.getCommand("getblock").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Must be a player");

        Player p = (Player)sender;

        int id = NMSHandler.getBlockID(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY()-1, p.getLocation().getBlockZ());

        p.sendMessage("The block under you has ID " + id);


        return false;
    }
}
