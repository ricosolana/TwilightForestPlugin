package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class TFCmdMapData extends TFCommand {

    public TFCmdMapData() {
        super("mapdata");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return issueSender(sender, "Only a player can execute this command");

        Player p = (Player)sender;

        if (!(p.getInventory().getItemInMainHand().getItemMeta() instanceof MapMeta)) return issueSender(sender, "You are not holding a map");

        MapView view = ((MapMeta)p.getInventory().getItemInMainHand().getItemMeta()).getMapView();

        int count = 0;

        String renderers = "";

        try {
            count = view.getRenderers().size();

            for (MapRenderer renderer : view.getRenderers()) {

                renderers += renderer.getClass().getName() + ", ";

            }

        } catch (Exception e) { return issueSender(sender, "An unexpected error has occurred"); }

        p.sendMessage(ChatColor.GREEN + "Center : " + ChatColor.GRAY + view.getCenterX() + " " + view.getCenterZ());

        if (renderers.length() > 0)
            p.sendMessage(ChatColor.GREEN + "Renderers (" + ChatColor.GRAY + count + ChatColor.GREEN + ") : " + ChatColor.GRAY + renderers);

        return true;
    }
}
