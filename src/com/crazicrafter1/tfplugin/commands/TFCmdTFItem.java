package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.item.TFItemEnum;
import com.crazicrafter1.tfplugin.item.TFItems;
import com.crazicrafter1.tfplugin.managers.TFItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TFCmdTFItem extends TFCommand {

    public TFCmdTFItem() {
        super("tfitem");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        if (args.length == 0) return issueSender(sender, "Input an item id");

        TFItemEnum itemEnum;

        try {
            itemEnum = TFItemEnum.valueOf(args[0].toUpperCase());
        } catch (Exception e) { return issueSender(sender, "That item does not exist"); }

        if (TFItems.item_registry.containsKey(itemEnum)) {
            p.sendMessage(ChatColor.GREEN + "Received 1 " + TFItems.item_registry.get(itemEnum).getName());
            p.getInventory().addItem(TFItems.item_registry.get(itemEnum).getItem(1));
            return true;
        }
        return false;
    }
}
