package me.specifies.core.Proxy;

import java.util.HashMap;
import java.util.Map;

public class ParameterFactory {
	
	private String build = "";
	private Map<String, String> querySet = null;
	
	public ParameterFactory(String build) {
		this.build = build;
		this.querySet = new HashMap<>(this.toMap());
	}
	
	public String query(String parameter) {
		return this.querySet.get(parameter);
	}
	
	private Map<String, String> toMap() {
		Map<String, String> res = new HashMap<>();
		
		for(String parameter : this.build.split("&")) {
			String[] entry = parameter.split("=");
			if(entry.length > 1) res.put(entry[0], entry[1]);
			else res.put(entry[0], "");
		}
		
		return res;
	}

}
