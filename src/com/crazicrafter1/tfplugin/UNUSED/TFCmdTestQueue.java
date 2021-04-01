package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdTestQueue extends TFCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player)sender;

        //Main.queuePlugin.setBlock(Material.BIRCH_PLANKS, p.getWorld(), p.getLocation().getBlockX(),p.getLocation().getBlockY(),
        //p.getLocation().getBlockZ());

        return false;
    }
}
