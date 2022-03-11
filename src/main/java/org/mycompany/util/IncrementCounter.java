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

public class IncrementCounter {

	public void process(String input, Exchange exchange) throws Exception {
		int i = (int) exchange.getProperty("i") + 1;
		exchange.setProperty("i", i);
	}
}