package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import main.Main;

public class MuteCommand implements CommandExecutor{
	
	public static String defaultError = "Es ist ein Fehler aufgetreten!";
	public static String onSuccess = " wurde erfolgreich gemutet";

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 1 || args.length == 2) {
			if (args.length == 1) {
				//Unlimited Mute
				
				try {
					Player muted = Bukkit.getPlayer(args[0]);
					mutePlayer(sender, args, true, muted);
				} catch (NullPointerException e) {
					OfflinePlayer muted = Bukkit.getOfflinePlayer(args[0]);
					muteOfflinePlayer(sender, args, true, muted);
				}

			} else if (args.length == 2) {
				//TimeBorderdMute in hours...
				
				try {
					Player muted = Bukkit.getPlayer(args[0]);
					mutePlayer(sender, args, false, muted);
				} catch (NullPointerException e) {
					OfflinePlayer muted = Bukkit.getOfflinePlayer(args[0]);
					muteOfflinePlayer(sender, args, false, muted);
				}
				
			}
			//TimeBorderdMute
		}
		
		
		return true;
	}
	
	
	public static void mutePlayer(CommandSender sender, String[] args, Boolean unlimit, Player muted) {
		
		FileConfiguration mutedList = new YamlConfiguration();
		FileConfiguration config = Main.getPlugin().getConfig();
		File file = new File("plugins/ChatMute/mutes/muteList.yml");
		
		try {
			defaultError = config.getString("Default.ErrorMessage");
			onSuccess = config.getString("Default.OnSuccessMsg");
			
		} catch (Exception e) {
			
		}
		
		if (unlimit == true) {
			//Unlimited Mute

			try {
				mutedList.load(file);
			} catch (FileNotFoundException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (IOException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			}
			mutedList.set(muted.getUniqueId().toString(), "1");
			mutedList.set(muted.getUniqueId().toString() + ".muteTime", (System.currentTimeMillis() + System.currentTimeMillis()));
			
			
			
		} else if (unlimit == false) {
			//Unlimited Mute

			try {
				mutedList.load(file);
			} catch (FileNotFoundException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (IOException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			}
			
			
			//calculate mute time in hours
			long muteTime = Integer.parseInt(args[1]);

			
			long mT = muteTime * 1000 * 60 * 60;
			
			mutedList.set(muted.getUniqueId().toString(), "1");
			mutedList.set(muted.getUniqueId().toString() + ".muteTime", (System.currentTimeMillis() + mT));
			
		}
		try {
			mutedList.save(file);
		} catch (IOException e) {
			sender.sendMessage(defaultError);
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.GREEN + muted.getDisplayName() + onSuccess);

	}
	
	public static void muteOfflinePlayer(CommandSender sender, String[] args, Boolean unlimit, OfflinePlayer muted) {
		
		FileConfiguration mutedList = new YamlConfiguration();
		File file = new File("plugins/ChatMute/mutes/muteList.yml");
		
		if (unlimit == true) {
			//Unlimited Mute

			try {
				mutedList.load(file);
			} catch (FileNotFoundException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (IOException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			}
			mutedList.set(muted.getUniqueId().toString(), "1");
			mutedList.set(muted.getUniqueId().toString() + ".muteTime", (System.currentTimeMillis() + System.currentTimeMillis()));
			
			
			
		} else if (unlimit == false) {
			//limited Mute

			try {
				mutedList.load(file);
			} catch (FileNotFoundException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (IOException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				sender.sendMessage(defaultError);
				e.printStackTrace();
			}
			
			

			long muteTime = Integer.parseInt(args[1]);

			
			long mT = muteTime * 1000 * 60 * 60;
			
			mutedList.set(muted.getUniqueId().toString(), "1");
			mutedList.set(muted.getUniqueId().toString() + ".muteTime", (System.currentTimeMillis() + mT));
			
		}
		try {
			mutedList.save(file);
		} catch (IOException e) {
			sender.sendMessage(defaultError);
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.GREEN + muted.getName() + onSuccess);

	}
	

}
