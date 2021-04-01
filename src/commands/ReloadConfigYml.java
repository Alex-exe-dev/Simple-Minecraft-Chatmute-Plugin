package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import main.Main;

public class ReloadConfigYml implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		try {
			Main.getPlugin().reloadConfig();
			FileConfiguration config = Main.getPlugin().getConfig();
			sender.sendMessage(config.getString("config.SuccessReload"));
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "There was an error reloading the config file!");
		}
		
		return true;
	}

}
