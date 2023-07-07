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

//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if ((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
//            switch (command.getName()) {
//                case "set_percentage":
//                    if (args.length == 1) {
//                        spawnpercentage = Integer.parseInt(args[0]);
//                        FileWriter myWriter = null;
//                        try {
//                            myWriter = new FileWriter("treasure_barrel.yml");
//                            myWriter.write("treasure_barrel: " + spawnpercentage);
//                            myWriter.close();
//                        } catch (Exception e) {
//                            sender.sendMessage(ChatColor.RED + "You can only use Intagers between 0 and 100!" + ChatColor.RESET);
//                        }
//                        sender.sendMessage(ChatColor.GRAY + "Spawn-percentage for the Treasure-barrel is now set to " + spawnpercentage + ChatColor.RESET);
//                    }
//                    else if (args.length == 0) {
//                        sender.sendMessage(ChatColor.GRAY + "Spawn-percentage for the Treasure-barrel is currently " + spawnpercentage + ChatColor.RESET);
//                    }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
//        if (sender instanceof Player) {
//            if (command.getName().equals("set_percentage")) {
//                if (args.length == 1) {
//                    List<String> intret = new ArrayList<>();
//                    for (int i = 0; i < 100; i++) {
//                        intret.add(Integer.toString(i + 1));
//                    }
//                    return intret;
//                } else {
//                    return new ArrayList<>();
//                }
//            }
//        }
//        return null;
//    }
}
