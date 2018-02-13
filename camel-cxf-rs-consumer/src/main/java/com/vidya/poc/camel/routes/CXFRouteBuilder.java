package com.vidya.poc.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vidya.poc.pojo.Address;
import com.vidya.poc.pojo.Person;

@Component
public class CXFRouteBuilder extends RouteBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(CXFRouteBuilder.class);
	
	@Override
	public void configure() throws Exception {
		
		from("cxfrs:bean:rsServer?bindingStyle=SimpleConsumer")
			.log("Request: operationName=${header.operationName} , type=${header.type}, active=${header.active}, customerData=${body}")
			.recipientList(simple("direct:${header.operationName}"));
		
		from("direct:sayHello")
			.routeId("sayHello")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setBody("Setting reponse in the camel process");
				}
			})
			.log("Request: type=${header.type}, active=${header.active}, customerData=${body}");
		
		from("direct:getPersonById")
			.routeId("getPersonById")
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					
					LOGGER.debug(exchange.getIn().getHeader("id")+"");
					int inputPathParam = exchange.getIn().getHeader("id", Integer.class);
					
					Address address = new Address();
					address.setZipCode(27560);
					
					Person person = new Person();
					person.setId(inputPathParam);
					person.setName("Person Name");
					person.setAge(20);
					person.setAddress(address);
					
					exchange.getIn().setBody(person);
				}
			});
			
	}
}
