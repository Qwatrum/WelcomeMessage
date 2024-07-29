package de.qwatrum.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class WelcomeMessage extends JavaPlugin implements Listener, CommandExecutor {

    // For title on join
    int fadeIn = 30;
    int stay = 120;
    int fadeOut = 20;

    // Server name
    String serverName = "My server";

    //Sever IP
    String serverIP = "0.0.0.0";

    //Title
    String title = "WELCOME";

    //Greeting
    String greeting = "Hello";


    @Override
    public void onEnable() {
        getCommand("welcomemessage").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            sender.sendMessage(ChatColor.RED + "Missing argument");
            return true;
        } else if (args.length == 2) {

            if (Objects.equals(args[0], "setTitle")) {
                title = args[1];
                sender.sendMessage(ChatColor.GREEN + "Title set!");
                return true;
            } else if (Objects.equals(args[0], "setIp")) {
                serverIP = args[1];
                sender.sendMessage(ChatColor.GREEN + "Server IP set!");
                return true;
            } else if (Objects.equals(args[0], "setName")) {
                serverName = args[1];
                sender.sendMessage(ChatColor.GREEN + "Server name set!");
                return true;
            } else if (Objects.equals(args[0], "setGreeting")) {
                greeting = args[1];
                sender.sendMessage(ChatColor.GREEN + "Greeting set!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Unknown command.");
                return true;
            }

        } else {
            sender.sendMessage(ChatColor.GOLD + "This is a Welcome Message plugin! Made by Qwatrum");
            return true;
        }


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        player.sendTitle(ChatColor.WHITE + title, "playerName", fadeIn, stay, fadeOut);

        player.sendMessage(ChatColor.BLUE + greeting+" "+"playerName"+"! Welcome on "+serverName);
        player.sendMessage(ChatColor.BLUE + "IP: "+ChatColor.YELLOW+ serverIP);

        int playerCount = Bukkit.getOnlinePlayers().size();
        if (playerCount == 1) {
            player.sendMessage(ChatColor.BLUE + "You are alone right now on the server.");
        } else if (playerCount == 2) {
            player.sendMessage(ChatColor.BLUE + "There is 1 other player online.");
        } else {
            playerCount--;
            player.sendMessage(ChatColor.BLUE + "There are "+playerCount+" other players online");
        }


        World world = Bukkit.getWorld("world");
        long totalTicks = world.getFullTime();
        long totalDay = totalTicks / 2400;
        player.sendMessage(ChatColor.BLUE + "Day: "+totalDay+1+"\n");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
