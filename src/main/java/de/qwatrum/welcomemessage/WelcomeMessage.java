package de.qwatrum.welcomemessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class WelcomeMessage extends JavaPlugin implements Listener {

    // For title on join
    int fadeIn = 30;
    int stay = 120;
    int fadeOut = 20;

    // Server name
    String serverName = "My server";

    //Sever IP
    String serverIP = "0.0.0.0";


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        player.sendTitle(ChatColor.WHITE + "WELCOME", "playerName", fadeIn, stay, fadeOut);

        player.sendMessage(ChatColor.BLUE + "Hello "+"playerName"+"! Welcome on "+serverName);
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
