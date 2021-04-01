package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TFCmdTFBossList extends TFCommand {

    public TFCmdTFBossList() {
        super("tfbosslist");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!TFBoss.bossesByType.isEmpty()) {

            String bosses = "";

            for (TFBoss.Type tfboss : TFBoss.bossesByType.keySet()) {
                bosses += tfboss.name().toLowerCase() + ", ";

            }

            sender.sendMessage(ChatColor.GREEN + bosses);

        } else issueSender(sender, "There are no generated boss structures (might be an error)");

        return false;
    }
}
