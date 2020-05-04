package me.specifies.PlayerStats.Proxy;

import java.util.HashMap;
import java.util.Map;

import me.specifies.PlayerStats.Exceptions.ExceptionHandler;

/*
 * I chose to do it this way as I see no pratical reason to use the nested style of GSON to build strings.
 * 
 */

public class JSONFactory {
	

	private Map<String, String> values = new HashMap<>();
	
	
	public void put(String key, String value) {
		this.values.put(key, value);
	}
	
	public void putMultiple(String[] arr) {
		
		if(arr.length % 2 == 0) {
			for(int i = 0; i < arr.length; i+=2) {
				this.values.put(arr[i], arr[i+1]);
			}
		} else new ExceptionHandler().IllegialRangeException();
		
		
	}
	
	public void remove(String key) {
		values.remove(key);
	}
	
	public String stringify() {
		
		String returnString = "{";
		int position = 0;
		
		for(Map.Entry<String, String> entry : this.values.entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue();
			
			if(position == this.values.size() - 1) {
				returnString += "\"" + key + "\": \"" + value + "\" }";
			} else {
				returnString += "\"" + key + "\": \"" + value + "\",";
			}
			
			position++;
		}
		
		return returnString;
		
	}
	

}

