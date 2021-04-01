package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdUnAdvance extends TFCommand {

    public TFCmdUnAdvance() {
        super("unadvance");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        TFBoss.Type type = TFProgression.decProgress(p.getUniqueId());

        if (type == null)
            p.sendMessage("You are already at the most minimum progression.");
        else p.sendMessage("You were unadvanced to " + type.name());



        return true;
    }
}
