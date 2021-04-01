package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.crazicrafter1.tfplugin.progression.TFProgression;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdAdvance extends TFCommand {

    public TFCmdAdvance() {
        super("advance");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        TFBoss.Type type = TFProgression.incProgress(p.getUniqueId());

        if (type == null)
            p.sendMessage("You are already at the greatest progression.");
        else p.sendMessage("You were advanced to " + type.name());


        return true;
    }

}
