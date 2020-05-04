package me.specifies.PlayerStats.Proxy.Handlers.Players;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import me.specifies.PlayerStats.PlayerData.GamePlayer;
import me.specifies.PlayerStats.Proxy.JSONFactory;
import me.specifies.PlayerStats.Proxy.ParameterFactory;

public class GetPlayerData implements HttpHandler {

	public void handle(HttpExchange exc) throws IOException {
		
		
		ParameterFactory paramHolder = new ParameterFactory(exc.getRequestURI().getQuery());
		
		String user = paramHolder.query("user");
		
		byte[] response = null;
		
		JSONFactory factory = new JSONFactory();
		
		GamePlayer player = new GamePlayer(user);
		

			
		if(!player.exists()) {
			factory.putMultiple(new String[] {"success", "false", "message", "User not found"});
		} else {
				
			
			int kills = player.getPlayerKills();
			int mobKills = player.getMobKills();
			int brokenBlocks = player.getByIntegerSpecificity("Broken Blocks");
			int placedBlocks = player.getByIntegerSpecificity("Placed Blocks");
			int walkedBlocks = player.getMetersWalked();
			int jumps = player.getTimesJumped();
			int blocksFlown = player.getMetersFlown();
			int deaths = player.getDeaths();
			int boatTraveled = player.getMetersBoated();
			
			
			factory.putMultiple(new String[] {"success", "true", "mob-kills", Integer.toString(mobKills), "kills", Integer.toString(kills), "user", user});
			factory.putMultiple(new String[] {"broken-blocks", Integer.toString(brokenBlocks), "placed-blocks", Integer.toString(placedBlocks)});
			factory.putMultiple(new String[] {"meters-walked", Integer.toString(walkedBlocks), "jumps", Integer.toString(jumps), "meters-flown", Integer.toString(blocksFlown)});
			factory.putMultiple(new String[] {"deaths", Integer.toString(deaths), "meters-traveled-by-boat", Integer.toString(boatTraveled)});
			factory.putMultiple(new String[] {"xp-earned", Integer.toString((int) player.getXP()), "level", Integer.toString(player.getLevel())}); 
		}
			
		
		
		response = factory.stringify().getBytes();
		
		exc.sendResponseHeaders(200, response.length);
		
		OutputStream stream = exc.getResponseBody();
		stream.write(response);
		stream.close();
		
		
	}
	
}
