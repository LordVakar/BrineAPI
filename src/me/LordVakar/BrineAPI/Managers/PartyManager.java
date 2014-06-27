package me.LordVakar.BrineAPI.Managers;

import me.LordVakar.BrineAPI.Objects.Party;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PartyManager {
	
	private static PartyManager pm = new PartyManager();
	private int maxPartySize = 9; //Modify as necessary.
	public String prefix = "§9Party>§7";
	
	public static PartyManager getManager() {
		return pm;
	}

	public void createParty(String partyName, String creator) {
		partyName = partyName.toLowerCase();
		creator = creator.toLowerCase();
		Player player = getPlayer(creator);
		if (player == null)
			return;
		if (isPlayerInParty(creator)) {
			player.sendMessage(prefix + ChatColor.RED + "You are already in a party!");
			return;
		}
		if (partyExists(partyName)) {
			player.sendMessage(prefix + ChatColor.RED
					+ "Error, this party name already exists!");
			return;
		}
		Party party = new Party(partyName, creator);
		joinParty(player, partyName);
	}

	public void removeParty(String partyName) {
		partyName = partyName.toLowerCase();
		Party.parties.remove(partyName);
		Party.getPartyMembers().clear();
	}
	
	public void joinParty(Player player, String partyName) 
	{
		Party party = getParty(partyName);
		if (party == null) {
			player.sendMessage(prefix + ChatColor.RED + "Party doesn't exist!");
			return;
		}
		if (partyExists(partyName)) {
			if (!(isPlayerInParty(player.getName()))) {
				if(!(isFull(partyName))) {
					party.getPartyMembers().add(player.getName());
					party.messageAllInParty(prefix + ChatColor.GOLD + player.getName() + "has joined the party!");
					player.sendMessage(prefix + ChatColor.GOLD + "You have joined the party!");
				} else {
					player.sendMessage(prefix + ChatColor.RED + "Party is full!");
				}
			} else {
				player.sendMessage(prefix + ChatColor.RED + "You're already in a party!");
			}
		}
	}
	
	public void leaveParty(Player player) 
	{
		Party.getPartyMembers().remove(player.getName());
	}
	
	public void invitePlayer(Player player, String partyName) 
	{
		Party party = getParty(partyName);
		if (party == null) {
			player.sendMessage(prefix + ChatColor.RED + "Party doesn't exist!");
			return;
		}
		if (partyExists(partyName)) {
			if (!(isPlayerInParty(player.getName()))) {
				if(!(isFull(partyName))) {
					party.messageAllInParty(prefix + ChatColor.GOLD + player.getName() + "was invited to the party!");
				} else {
					player.sendMessage(prefix + ChatColor.RED + "You were invited to a party that was full.");
				}
			} else {
				player.sendMessage(prefix + ChatColor.RED + "You were invited to a party, but you're already in one!");
			}
		}
	}
	
	public void acceptInvite(Player player, String partyName)
	{
		Party party = getParty(partyName);
		if (party == null) {
			player.sendMessage(prefix + ChatColor.RED + "Party doesn't exist!");
			return;
		}
		if (partyExists(partyName)) {
			if (!(isPlayerInParty(player.getName()))) {
				if(!(isFull(partyName))) {
					party.messageAllInParty(prefix + ChatColor.GOLD + player.getName() + "accepted their invitation to the party!");
					joinParty(player, partyName);
				} else {
					player.sendMessage(prefix + ChatColor.RED + "You were invited to a party that was full.");
				}
			} else {
				player.sendMessage(prefix + ChatColor.RED + "You were invited to a party, but you're already in one!");
			}
		}
	}

	public Player getPlayer(String player) {
		return Bukkit.getPlayer(player);
	}

	public boolean isFull(String partyName) {
		Party party = getParty(partyName);
		if (party.getSizeOfParty() >= maxPartySize) {
			return true;
		}
		return false;
	}
	public boolean isPlayerInParty(String playerName) {
		if (Party.getPartyMembers().contains(playerName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
    public boolean partyExists(String partyName)
    {
    	partyName = partyName.toLowerCase();
    	if (getParty(partyName) != null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

	public static Party getParty(String partyName) {
		partyName = partyName.toLowerCase();
		for (Party party : Party.parties) {
			if (party.getPartyName().equals(partyName)) {
				return party;
			}
		}
		return null;
	}
}