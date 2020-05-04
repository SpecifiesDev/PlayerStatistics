package me.specifies.PlayerStats.Proxy;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import me.specifies.PlayerStats.Exceptions.ExceptionHandler;
import me.specifies.PlayerStats.Proxy.Handlers.Players.GetPlayerData;

public class DataServer {
	
	private final int port;
	private final int parentPort;
	
	private HttpServer proxy;
	
	/*
	 * Ensure that the port isn't the same as the 
	 * parent server, or the default ports for HTTP/S,
	 * if it is, throw an error and disable the proxy
	 */
	public DataServer(int port, int parentPort) {
		this.port = port;
		this.parentPort = parentPort;
		
		if(this.port == 80 || this.port == 443 || this.port == this.parentPort) {
			new ExceptionHandler().IllegialPortException(this.port);
		} else {
			
			try { startProxy(); } catch(Exception e) { e.printStackTrace(); }

		}
	}
	
	private void startProxy() throws Exception {
		
		this.proxy = HttpServer.create(new InetSocketAddress(this.port), 0);
		this.proxy.createContext("/api/v1/players/player", new GetPlayerData());
		this.proxy.setExecutor(null);
		this.proxy.start();
		
	}
	
	public void stopProxy() throws Exception {
		
		this.proxy.stop(0);
		
	}

}

