package me.specifies.PlayerStats.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.specifies.PlayerStats.PlayerData.GamePlayer;

public class BlockHandler implements Listener {
	
	@EventHandler
	public void breakEvent(BlockBreakEvent e) {
		
		GamePlayer p = new GamePlayer(e.getPlayer().getName());
		
		Block b = e.getBlock();
		
		if(b.getType() == Material.COAL_ORE) try { p.setBySpecificity("Coal Broken", p.getByIntegerSpecificity("Coal Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.IRON_ORE) try { p.setBySpecificity("Iron Broken", p.getByIntegerSpecificity("Iron Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.LAPIS_ORE) try { p.setBySpecificity("Lapis Broken", p.getByIntegerSpecificity("Lapis Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.REDSTONE_ORE) try { p.setBySpecificity("Redstone Broken", p.getByIntegerSpecificity("Redstone Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.DIAMOND_ORE) try { p.setBySpecificity("Diamond Broken", p.getByIntegerSpecificity("Diamond Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.GOLD_ORE) try { p.setBySpecificity("Gold Broken", p.getByIntegerSpecificity("Gold Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		else if(b.getType() == Material.EMERALD_ORE) try { p.setBySpecificity("Emerald Broken", p.getByIntegerSpecificity("Emerald Broken") + 1); } catch(Exception err) { err.printStackTrace(); }
		
		try { p.setBySpecificity("Broken Blocks", p.getByIntegerSpecificity("Broken Blocks") + 1); } catch(Exception err) { err.printStackTrace(); }
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e) {
		
		GamePlayer p = new GamePlayer(e.getPlayer().getName());
		
		try { p.setBySpecificity("Placed Blocks", p.getByIntegerSpecificity("Placed Blocks") + 1); } catch(Exception err) { err.printStackTrace(); }
	}

}
