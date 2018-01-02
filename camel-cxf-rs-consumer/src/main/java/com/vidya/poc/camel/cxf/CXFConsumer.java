package com.vidya.poc.camel.cxf;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

/**
 * This camel sample explain how to create a CXF Consumer with camel route.
 * 
 * @author vidya
 *
 */
public class CXFConsumer extends RouteBuilder {

	//Using main since this is a sample,don't use this in the real project.
	public static void main(String[] args) throws Exception {

		new Main().run(args);
	}

	@Override
	public void configure() throws Exception {

		from("direct:start").routeId("urlInvocation").log("Your search string is :: ${body}")
				.to("https://www.google.com/search?q=${body}").log("${body}");
	}	
}
