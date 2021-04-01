package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TFCmdToggleDim extends TFCommand {

    public TFCmdToggleDim() {
        super("toggledim");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.getWorld() == Main.OVERWORLD) {
                //Location loc = p.getLocation();
                //loc.setY();
                //loc.setWorld();
                //p.teleport(new Location(p.getWorld()), PlayerTeleportEvent.TeleportCause.PLUGIN);
                p.teleport(TFGlobal.TFWORLD.getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                //p.teleport(new Location(p.getWorld(), 0, ))
                return true;
            } else if (p.getWorld() == TFGlobal.TFWORLD) {
                //p.teleport(Main.OVERWORLD.getSpawnLocation());
                p.teleport(new Location(Main.OVERWORLD, 0, Main.OVERWORLD.getHighestBlockYAt(0, 0)+5, 0));
                return true;
            }
            else return issueSender(sender, "You are not in the overworld or twilight forest dimensions");
        }

        return issueSender(sender, "Command can only be issued by a player");
    }
}
