package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdLichStructure extends TFCommand {

    public TFCmdLichStructure() {
        super("lichstructure");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //TFStructure structure = TFStructure.args[0];

        if (!(sender instanceof Player)) return issueSender(sender, "Must be a player");

        Player p = (Player)sender;

        int x = p.getLocation().getBlockX();
        int z = p.getLocation().getBlockZ();

        //new TFLichTower(x, z).generate();

        return true;
    }
}
