package co.th.ford.tms.test;

import java.util.HashMap;
import java.util.Map;

public class testMap {
	
	public static void main(String[] args) {
		
		Map<String, String> mapData = new HashMap<>();
		mapData.put("no", "No101");
		mapData.put("name", "Bunyong");
		mapData.put("userName", "AuiAye");
		
		System.out.println(mapData.toString());
		
		System.out.println(mapData.get("userName"));
		
				
	}
}
