package org.mycompany.util;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.ShutdownStrategy;


public class KillRoute implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		ShutdownStrategy shutdownStrategy = exchange.getContext().getShutdownStrategy();
		shutdownStrategy.setTimeout(1000);
		shutdownStrategy.setTimeUnit(TimeUnit.MILLISECONDS);
		shutdownStrategy.setShutdownNowOnTimeout(true);
	}

}
