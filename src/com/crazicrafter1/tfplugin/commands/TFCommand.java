package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public abstract class TFCommand implements CommandExecutor {

    //public static HashMap<String, TFCommand> commands = new HashMap<>();

    //private String name;

    //public TFCommand(String cmd) { name = cmd; } // commands.put(cmd, this); }

    //abstract public boolean onCommand(CommandSender sender, String[] args);

    protected static Main plugin = Main.getInstance();

    public TFCommand(String name) {
        plugin.getCommand(name).setExecutor(this);
    }

    boolean issueSender(CommandSender sender, String message) {
        sender.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "[ERROR] : " + ChatColor.RESET + ChatColor.GRAY + message + ".");
        return true;
    }

    abstract public boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    //public String getName() { return name; }

    //public static void registerCommand(TFCommand command) { commands.put(command.getName(), command); }
}
