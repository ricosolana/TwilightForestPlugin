package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.world.schematic.SchematicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdSCSave extends TFCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Must be a player");

        if (args.length != 7) return issueSender(sender, "Must input x1,y1,z1,x2,y2,z2 and name");

        Player p = (Player)sender;

        SchematicManager.saveArea(args[6],
                p.getWorld(),
                Util.safeToInt(args[0]),
                Util.safeToInt(args[1]),
                Util.safeToInt(args[2]),
                Util.safeToInt(args[3]),
                Util.safeToInt(args[4]),
                Util.safeToInt(args[5]),
                p);

        return true;
    }
}
