package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.world.schematic.SchematicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TFCmdSCExport extends TFCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return issueSender(sender, "Must input a schematic and an export name");

        if (SchematicManager.schematics.containsKey(args[0])) {

            /*
            // export
            SchematicManager.schematics.get(args[0]).exportAsArray(args[1]);

            sender.sendMessage("Exported as " + args[1] + ".tfx");

            return true;

             */

        }

        return false;
    }
}
