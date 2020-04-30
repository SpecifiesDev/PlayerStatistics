package me.specifies.core.Proxy.Handlers.Players;

import java.io.IOException;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import me.specifies.core.PlayerData.GamePlayer;
import me.specifies.core.Proxy.JSONFactory;
import me.specifies.core.Proxy.ParameterFactory;

public class GetMobKills implements HttpHandler {
	
	public void handle(HttpExchange exc) throws IOException {
		
		
		ParameterFactory paramHolder = new ParameterFactory(exc.getRequestURI().getQuery());
		
		String user = paramHolder.query("user");
		
		byte[] response = null;
		
		JSONFactory factory = new JSONFactory();
		
		GamePlayer player = new GamePlayer(user);
		

			
		if(!player.exists()) {
			factory.putMultiple(new String[] {"success", "false", "message", "User not found"});
		} else {
				
			
			int kills = player.getMobKills();
			factory.putMultiple(new String[] {"success", "true", "mob-kills", Integer.toString(kills), "user", user});
				
		}
			
		
		
		response = factory.stringify().getBytes();
		
		exc.sendResponseHeaders(200, response.length);
		
		OutputStream stream = exc.getResponseBody();
		stream.write(response);
		stream.close();
		
		
	}

}
