package me.LordVakar.BrineAPI;

import java.util.UUID;

import me.LordVakar.BrineAPI.Economy.EconomyAPICore;
/*import me.bigteddy98.packetapi.PacketAPI;
import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketSendEvent;
import me.bigteddy98.packetapi.api.PacketType;
import me.bigteddy98.packetapi.api.wrapper.PacketWrapperStatusOutServerInfo;
import net.minecraft.util.com.mojang.authlib.GameProfile;*/

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BrineAPI extends JavaPlugin //implements PacketListener
{

	public static Plugin pl;
	public static FileConfiguration config;
	private EconomyAPICore economyapi;
	//private String[] lines = new String[] { ChatColor.GOLD + "Epic DEV Test Server", ChatColor.AQUA + "Owned by LordVakar." };
	
	
	public void onEnable() {
		pl = this;
		config = pl.getConfig();
		economyapi = new EconomyAPICore();
		registerEvents(this//, new
				);
		//PacketAPI.getInstance().addListener(this);
	}
	
	public void onDisable() {
		
	}
	
	//Main Utils
	public void registerCommands() 
	{	
	    //registerCommand("sg", new CmdSurvivalGames(this));
	}
	
	/**
	 * Register Commands Method
	 * @param command The command name.
	 * @param commandexecutor The commandexecutor class.
	 */
	public void registerCommand(String command, CommandExecutor commandexecutor) {
	    Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	  }
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
		}
	
	/**
	 * Gets the EconomyAPI.
	 * @return Returns EconomyAPI.
	 */
	public EconomyAPICore getEconomyAPI() {
		return economyapi;
	}
	
/*	 @PacketHandler(listenType = PacketType.PacketStatusOutServerInfo)
	 private void onSend(PacketSendEvent event) throws Exception {
	  
	 // loop through list of lines
	 GameProfile[] list = new GameProfile[lines.length];
	 for (int i = 0; i < lines.length; i++) {
		 list[i] = new GameProfile(UUID.randomUUID(), lines[i]);
	 }
	  
	 //create wrapper
	 PacketWrapperStatusOutServerInfo packet = new PacketWrapperStatusOutServerInfo(event.getPacket().getNMSPacket());
	 packet.setOnlinePlayers(list);
	 }
	 
	 public void addServerLine(String serverline) {
	 }*/
	
}
