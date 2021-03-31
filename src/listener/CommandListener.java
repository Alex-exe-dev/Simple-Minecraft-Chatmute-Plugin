package listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener{
	
	@EventHandler
	public static void onCommand(PlayerCommandPreprocessEvent event) {
		
		FileConfiguration mutedList = new YamlConfiguration();
		File file = new File("plugins/ChatMute/mutes/muteList.yml");
		Player p = event.getPlayer();
		
		try {
			mutedList.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (mutedList.contains(p.getUniqueId().toString())) {
			if (mutedList.getLong(p.getUniqueId().toString() + ".muteTime") >= System.currentTimeMillis()) {
				System.out.println(System.currentTimeMillis());
				System.out.println(mutedList.getLong(p.getUniqueId().toString() + ".muteTime"));
				event.setCancelled(true);
				p.sendMessage(ChatColor.RED + "Du BiSt ChAtGeMuTeD!");
				
				
			} else {
				mutedList.set(p.getUniqueId().toString(), null);
				mutedList.set(p.getUniqueId().toString(), null);
				try {
					mutedList.save(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
