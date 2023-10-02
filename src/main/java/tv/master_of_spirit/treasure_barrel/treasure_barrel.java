package tv.master_of_spirit.treasure_barrel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class treasure_barrel extends JavaPlugin implements Listener {

    public NamespacedKey key = NamespacedKey.minecraft(this.getName());
    public int spawnpercentage = 75;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new replace_treasuremap(), this);
        Bukkit.getPluginManager().registerEvents(new place_treasurebarrel(), this);
        if (!new File("treasure_barrel.yml").exists()) {
            try {
                FileWriter myWriter = new FileWriter("treasure_barrel.yml");
                myWriter.write("treasure_barrel: " + spawnpercentage);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                File myObj = new File("treasure_barrel.yml");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String nextLine = myReader.nextLine();
                    if (nextLine.contains("treasure_barrel:")) {
                        spawnpercentage = Integer.parseInt(nextLine.split(" ")[1]);
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "can't read the file!" + ChatColor.RESET);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
