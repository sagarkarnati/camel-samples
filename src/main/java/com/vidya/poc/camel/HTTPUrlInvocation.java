package com.vidya.poc.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

/**
 * This camel sample explain how to invoke HTTP Rest url from camel route.
 * 
 * This reads a string from console and searches in the google.
 * 
 * @author vidya
 *
 */
public class HTTPUrlInvocation extends RouteBuilder {

	// using main since this is a sample,don't use this in the real project.
	public static void main(String[] args) throws Exception {

		new Main().run(args);
	}

	@Override
	public void configure() throws Exception {

		from("stream:in?promptMessage=What do you want to search : ").log("Your search string is :: ${body}")
				.to("https://www.google.com/search?q=${body}").log("${body}");
	}

}
