package com.vidya.poc.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CXFRouteBuilder extends RouteBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(CXFRouteBuilder.class);
	
	@Override
	public void configure() throws Exception {
		
		from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					
					LOGGER.info("Hitting the processor");
					exchange.getOut().setBody("Sending Response from the camel processor");
				}
			})
			.to("log:TEST?showAll=true");
	}
}
