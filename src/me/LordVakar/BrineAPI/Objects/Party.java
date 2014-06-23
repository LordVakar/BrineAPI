package me.LordVakar.BrineAPI.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Party {
	
	List<String> partyMembers = new ArrayList<String>();
/*	public Party() 
	{

	}*/
	
	public List<String> getMembers() {
		return this.partyMembers;
	}
	
	public void addPlayer(Player p) {
		partyMembers.add(p.getName());
	}
	
	public void removePlayer(Player p) {
		partyMembers.remove(p.getName());
	}
	
	public boolean checkIfPlayerInParty(Player p) {
		if (partyMembers.contains(p.getName())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getSizeOfParty() {
		return this.partyMembers.size();
	}
	
	public void messageAllInParty(String message) {
		for (String allP : partyMembers) {
			Bukkit.getPlayer(allP).sendMessage(message);
		}
	}
}
