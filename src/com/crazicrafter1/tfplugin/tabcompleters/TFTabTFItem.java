package com.crazicrafter1.tfplugin.tabcompleters;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.item.TFItemEnum;
import com.crazicrafter1.tfplugin.item.TFItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TFTabTFItem implements TabCompleter {

    public TFTabTFItem(Main plugin) {
        plugin.getCommand("tfitem").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabs = new ArrayList<>();

        // for correct autocomplete suggest items which names are in args[end]

        if (args.length == 1) {
            // should not need the below:
            //if (args[args.length-1].length() > 0)

            String arg = args[0];

            for (TFItemEnum itemEnum : TFItems.item_registry.keySet()) {
                String item = itemEnum.name().toLowerCase();
                String current = arg.toLowerCase();
                if (item.length() >= current.length() && item.substring(0, current.length()).equals(current))
                    tabs.add(item);
            }
        } else if (args.length == 0){
            /*
                basically, if player hasnt inputted anything (based on the assumption that the autocompleter functions as it should)
                all items are up for suggestion, until an arg is inputted
             */

            for (TFItemEnum itemEnum : TFItems.item_registry.keySet()) {
                tabs.add(itemEnum.name().toLowerCase());
            }
        }
        return tabs;
    }
}
