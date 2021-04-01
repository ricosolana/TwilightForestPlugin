package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.generation.structure.TFNagaCourtyard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdNagaStructure extends TFCommand {

    public TFCmdNagaStructure() {
        super("nagastructure");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //TFStructure structure = TFStructure.args[0];

        if (!(sender instanceof Player)) return issueSender(sender, "Must be a player");

        Player p = (Player)sender;

        int x = p.getLocation().getBlockX();
        int z = p.getLocation().getBlockZ();

        new TFNagaCourtyard(x, z).generate();

        return true;
    }
}
