package me.LordVakar.BrineAPI.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Party {
	
	public static List<String> partyMembers = new ArrayList<String>();
	public static List<Party> parties = new ArrayList<Party>();
	private String partyName;
	private String creator;
	
	public Party(String partyName, String creator) 
	{
		this.partyName = partyName;
		this.creator = creator;
		
		parties.add(this);
	}
	
	public void addPlayer(Player p) {
		getPartyMembers().add(p.getName());
	}
	
	public void removePlayer(Player p) {
		getPartyMembers().remove(p.getName());
	}
	
	public boolean checkIfPlayerInParty(Player p) {
		if (getPartyMembers().contains(p.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getSizeOfParty() {
		return this.getPartyMembers().size();
	}
	
	public void messageAllInParty(String message) {
		for (String allP : getPartyMembers()) {
			Bukkit.getPlayer(allP).sendMessage(message);
		}
	}
	
	public String getPartyName() {
		return this.partyName;
	}

	public static List<String> getPartyMembers() {
		return partyMembers;
	}
}
