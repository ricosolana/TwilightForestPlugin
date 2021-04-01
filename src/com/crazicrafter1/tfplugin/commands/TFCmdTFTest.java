package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.portal.TFPortal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdTFTest extends TFCommand {

    public TFCmdTFTest() {
        super("tftest");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        //new TFPortal().findValidPortal(((Player)commandSender).getLocation(), 32);

        TFPortal.createValidLitPortal(((Player)commandSender).getLocation());

        return true;
        //return issueSender(commandSender, "this command currently does nothing");
    }
}
