package org.mycompany.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.ShutdownStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetApiId {

	public List<String> process(String input, Exchange exchange) throws Exception {
		List<String> ids = new ArrayList<String>();
//		System.out.println("input get API ID: " + input);
		JSONObject json = new JSONObject(input.toString());
		JSONArray array = json.getJSONArray("list");
		for (int i = 0; i < array.length(); i++) {
			JSONObject id = array.getJSONObject(i);
//			System.out.println(id.get("id").toString());
			ids.add(id.get("id").toString());
		}

		return ids;

	}
}