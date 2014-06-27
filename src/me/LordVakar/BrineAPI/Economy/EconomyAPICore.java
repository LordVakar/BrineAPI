package me.LordVakar.BrineAPI.Economy;

import java.io.File;
import java.io.IOException;

import me.LordVakar.BrineAPI.BrineAPI;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class EconomyAPICore
{
	BrineAPI main;
	
	public EconomyAPICore() {
		
	}
	
	public EconomyAPICore(BrineAPI plugin) {
		this.main = (BrineAPI) BrineAPI.pl;
	}
	
	/**
	 * Set the economy up for a {@link Player}.
	 * Call this on PlayerJoinEvent on join.
	 * For more effectiveness call this only if the player 
	 * has not played before on the server and if they have !isEconomySetup
	 * returning false.
	 * @param player The player to set the economy up for.
	 */
	public void setUpEconomy(Player player) {
		File folder = new File("plugins/BrineAPI/");
		File folder2 = new File("plugins/BrineAPI/PlayerData");
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		if (!folder.exists()) {
			folder.mkdir();
		}
		else if(!folder2.exists()) {
			folder2.mkdir();
			try {
				playerFile.createNewFile();
				playerFileConfig.set(configPath + "playerName", player.getName());
				playerFileConfig.set(configPath + "playerIP", player.getAddress().getHostString());
				playerFileConfig.set(configPath + "playerUUID", player.getUniqueId().toString());
				playerFileConfig.set(configPath + "Guld", 0);
				playerFileConfig.set(configPath + "Gore", 0);
				playerFileConfig.save(playerFile);
				playerFileConfig.load(playerFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		else if(!playerFile.exists()) {
			try {
				playerFile.createNewFile();
				playerFileConfig.set(configPath + "playerName", player.getName());
				playerFileConfig.set(configPath + "playerIP", player.getAddress().toString());
				playerFileConfig.set(configPath + "playerUUID", player.getUniqueId().toString());
				playerFileConfig.set(configPath + "Guld", 0);
				playerFileConfig.set(configPath + "Gore", 0);
				playerFileConfig.save(playerFile);
				playerFileConfig.load(playerFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		else if(playerFile.exists()) {
			//Do Nothing
		}
	}
	
	/**
	 * Returns a boolean if a {@link Player}'s economy has
	 * been setup before.
	 * @param player The player to check if the economy
	 * is setup for.
	 * @return True or False.
	 */
	public boolean isEconomySetup(Player player) {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		if (playerFile.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adds Guld to a {@link Player}'s balance.
	 * @param amount The amount of Guld to add.
	 * @param player The player to add Guld to.
	 * @throws IOException 
	 */
	public void addGuld(int amount, Player player) throws IOException 
	{
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGuld = getGuld(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", currentGuld + amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Adds Guld to a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Guld to add.
	 * @param player The player to add Guld to.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void addGuld(int amount, Player player, String message) throws IOException 
	{
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGuld = getGuld(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", currentGuld + amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
	
	/**
	 * Removes Guld from a {@link Player}'s balance.
	 * @param amount The amount of Guld to remove.
	 * @param player The player to remove Guld from.
	 * @throws IOException 
	 */
	public void removeGuld(int amount, Player player) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGuld = getGuld(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", currentGuld - amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Removes Guld from a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Guld to remove.
	 * @param player The player to remove Guld from.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void removeGuld(int amount, Player player, String message) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGuld = getGuld(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", currentGuld - amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
	
	/**
	 * Adds Gore to a {@link Player}'s balance.
	 * @param amount The amount of Gore to add.
	 * @param player The player to add Gore to.
	 * @throws IOException 
	 */
	public void addGore(int amount, Player player) throws IOException 
	{
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGore = getGore(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", currentGore + amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Adds Gore to a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Gore to add.
	 * @param player The player to add Gore to.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void addGore(int amount, Player player, String message) throws IOException 
	{
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGore = getGore(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", currentGore + amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
	
	/**
	 * Removes Gore from a {@link Player}'s balance.
	 * @param amount The amount of Gore to remove.
	 * @param player The player to remove Gore from.
	 * @throws IOException 
	 */
	public void removeGore(int amount, Player player) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGore = getGore(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", currentGore - amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Removes Gore from a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Gore to remove.
	 * @param player The player to remove Gore from.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void removeGore(int amount, Player player, String message) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		int currentGore = getGore(player);
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", currentGore - amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
	
	/**
	 * Returns an int of how much Guld a
	 * {@link Player} has.
	 * @param player The player to get the Guld of.
	 */
	public int getGuld(Player player) {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		return playerFileConfig.getInt(configPath + "Guld");
	}
	
	/**
	 * Returns an int of how much Gore a
	 * {@link Player} has.
	 * @param player The player to get the Gore of.
	 */
	public int getGore(Player player) {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		return playerFileConfig.getInt(configPath + "Gore");
	}
	
	/**
	 * Sets the Guld of a {@link Player}'s balance.
	 * @param amount The amount of Guld to set.
	 * @param player The player to set the Guld for.
	 * @throws IOException 
	 */
	public void setGuld(int amount, Player player) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Sets the Guld of a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Guld to set.
	 * @param player The player to set the Guld for.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void setGuld(int amount, Player player, String message) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		if(amount > 0) {
			playerFileConfig.set(configPath + "Guld", amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
	
	/**
	 * Sets the Gore of a {@link Player}'s balance.
	 * @param amount The amount of Gore to set.
	 * @param player The player to set the Gore for.
	 * @throws IOException 
	 */
	public void setGore(int amount, Player player) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", amount);
			playerFileConfig.save(playerFile);
		}
	}
	
	/**
	 * Sets the Gore of a {@link Player}'s balance
	 * and displays a message.
	 * @param amount The amount of Gore to set.
	 * @param player The player to set the Gore for.
	 * @param message The message to send to the player.
	 * @throws IOException 
	 */
	public void setGore(int amount, Player player, String message) throws IOException {
		File playerFile = new File("plugins/BrineAPI/PlayerData" + "/" + player.getUniqueId().toString() + ".yml");
		YamlConfiguration playerFileConfig = YamlConfiguration.loadConfiguration(playerFile);
		String configPath = "PlayerData.";
		if(amount > 0) {
			playerFileConfig.set(configPath + "Gore", amount);
			playerFileConfig.save(playerFile);
			player.sendMessage(message);
		}
	}
}
