package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.Util;
import com.crazicrafter1.tfplugin.schematic.SchematicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdScPaste extends TFCommand {

    public TFCmdScPaste() {
        super("scpaste");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Must be a player");

        if (args.length != 4) return issueSender(sender, "Input arguments");

        if (SchematicManager.schematics.containsKey(args[0])) {
            try {

                SchematicManager.schematics.get(args[0]).paste(
                        ((Player) sender).getWorld(), Util.safeToInt(args[1]), Util.safeToInt(args[2]), Util.safeToInt(args[3]), true);

                return true;

            } catch (Exception e) { return issueSender(sender, "Input integers"); }
        }

        return issueSender(sender, "That schematic doesn't exist");
    }
}
