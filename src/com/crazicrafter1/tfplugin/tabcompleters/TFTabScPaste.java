package com.crazicrafter1.tfplugin.tabcompleters;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.schematic.SchematicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TFTabScPaste implements TabCompleter {

    public TFTabScPaste(Main plugin) {
        plugin.getCommand("scpaste").setTabCompleter(this);
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();

        if (!(sender instanceof Player)) return tabs;

        Player p = (Player)sender;

        switch (args.length) {

            case 1:
                tabs.addAll(SchematicManager.schematics.keySet());
                break;
            case 2:
                tabs.add("" + p.getLocation().getBlockX());
                break;
            case 3:
                tabs.add("" + p.getLocation().getBlockY());
                break;
            case 4:
                tabs.add("" + p.getLocation().getBlockZ());
                break;

        }

        return tabs;
    }

}
