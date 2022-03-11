package org.mycompany;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.ShutdownStrategy;


public class Bs1Process implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Properties from Main Camel Context : "+exchange.getProperty("mainProp"));
	}

}
