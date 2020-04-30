package me.specifies.core.Exceptions;

public class ExceptionHandler {
	
	public void IllegialPortException(int port) {
		System.out.println(String.format("[PlayerStatistics] Configuration Error: \n The port %i is not permitted to be ran for the proxy server.", port));
	}
	
	public void IllegialRangeException() {
		System.out.println("[PlayerStatistics] An illegial range was inputted into the JSON factory.");
	}

}
