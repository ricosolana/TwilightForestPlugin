package com.crazicrafter1.tfplugin.commands;

import com.crazicrafter1.tfplugin.Main;
import com.crazicrafter1.tfplugin.TFGlobal;
import com.crazicrafter1.tfplugin.boss.TFBoss;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TFCmdTFLocate extends TFCommand {

    public TFCmdTFLocate() {
        super("tflocate");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) return issueSender(sender, "Input a tfstructure id");

        try {
            TFBoss.Type boss = TFBoss.Type.valueOf(args[0].toUpperCase());

            for (Integer[] loc : TFBoss.bossesByType.get(boss)) {
                sender.sendMessage(ChatColor.DARK_PURPLE + "(" + loc[0] + ", " + loc[1] + ")");
                //sender.se
            }

            return true;

            /*
            if (sender instanceof Player) {

                Player p = (Player)sender;

                Multimap<TFBoss.Type, Integer[]> copy = ArrayListMultimap.create(TFBoss.bossesByType);

                ArrayList<Packet> packets = new ArrayList<>();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Integer[] loc : copy.get(boss)) {

                            IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(

                                    "[\"\",{\"text\":\"" +
                                            String.format("(%d, %d)", loc[0], loc[1]) +
                                            "\",\"color\":\"dark_purple\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" +
                                            String.format("/tp %s %d %d %d", p.getDisplayName(), loc[0], TFGlobal.TFWORLD.getHighestBlockYAt(loc[0], loc[1]), loc[1]) +
                                            "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to tp!\",\"color\":\"aqua\"}]}}}]"
                            );

                            PacketPlayOutChat packet = new PacketPlayOutChat(component, ChatMessageType.CHAT);
                            //((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                            packets.add(packet);
                        }

                        org.bukkit.Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                for (Packet packet : packets) {

                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);

                                }
                            }
                        });

                    }
                }.run();

                return true;
            } else {

                for (Integer[] loc : TFBoss.bossesByType.get(boss)) {
                    sender.sendMessage(ChatColor.DARK_PURPLE + "(" + loc[0] + ", " + loc[1] + ")");
                    //sender.se
                }


            }

             */

        } catch (Exception e) { return issueSender(sender, "Invalid boss or structure (currently only "); }

    }
}
