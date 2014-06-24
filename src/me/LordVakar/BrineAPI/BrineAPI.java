package me.LordVakar.BrineAPI;

import me.LordVakar.BrineAPI.Economy.EconomyAPICore;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BrineAPI extends JavaPlugin
{

	public static Plugin pl;
	public static FileConfiguration config;
	private EconomyAPICore economyapi;
	
	
	public void onEnable() {
		pl = this;
		config = pl.getConfig();
		economyapi = new EconomyAPICore();
		registerEvents(this//, new
				);
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
	
}
