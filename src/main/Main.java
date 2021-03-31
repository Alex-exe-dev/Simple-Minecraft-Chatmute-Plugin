package main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import commands.MuteCommand;
import listener.ChatAsync;
import listener.CommandListener;


public class Main extends JavaPlugin{

	private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		getCommand("mute").setExecutor(new MuteCommand());
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new ChatAsync(), this);
		pluginManager.registerEvents(new CommandListener(), this);
	}
	
	public static Main getPlugin(){
		return plugin;
	}
	
}
