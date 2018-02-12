package com.vidya.poc.camel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Service
@Path("/sayHello")
public class HelloServiceImpl {

	@GET
	@Path("/{a}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(String a) {

		return "This is a dummy method only for configuration and this should not be called.";
	}
}