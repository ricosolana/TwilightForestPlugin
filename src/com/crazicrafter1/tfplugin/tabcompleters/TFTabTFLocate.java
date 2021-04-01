package com.crazicrafter1.tfplugin.tabcompleters;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TFTabTFLocate implements TabCompleter {

    public TFTabTFLocate(Main plugin) {
        plugin.getCommand("tflocate").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabs = new ArrayList<>();

        // for correct autocomplete suggest items which names are in args[end]

        if (args.length == 1) {
            // should not need the below:
            //if (args[args.length-1].length() > 0)

            String arg = args[0];

            for (TFBoss.Type tfboss : TFBoss.bossesByType.keySet()) {
                String boss = tfboss.name().toLowerCase();
                String current = arg.toLowerCase();
                if (boss.length() >= current.length() && boss.substring(0, current.length()).equals(current))
                    tabs.add(boss);
            }
        } else if (args.length == 0){
            /*
                basically, if player hasnt inputted anything (based on the assumption that the autocompleter functions as it should)
                all items are up for suggestion, until an arg is inputted
             */

            for (TFBoss.Type tfboss : TFBoss.bossesByType.keySet()) {
                System.out.println("added boss auto");
                tabs.add(tfboss.name().toLowerCase());
            }
        }
        return tabs;
    }
}
