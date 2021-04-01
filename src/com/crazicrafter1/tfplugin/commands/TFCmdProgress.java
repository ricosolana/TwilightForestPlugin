package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdProgress extends TFCommand {

    public TFCmdProgress() {
        super("progress");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        p.sendMessage(ChatColor.GREEN + "Current progress : " + TFGlobal.playerProgress.get(p.getUniqueId()).name());
        return true;
    }
}
