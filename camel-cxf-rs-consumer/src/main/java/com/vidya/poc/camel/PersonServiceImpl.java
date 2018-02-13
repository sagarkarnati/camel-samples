package com.vidya.poc.camel;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.vidya.poc.pojo.Person;

@Service
@Path("/person")
public class PersonServiceImpl {
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Person getPersonById(@PathParam(value = "id") int id) {
		return new Person();
	}
	
	@POST	
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Person createPerson(Person person) {
		return new Person();
	}
	
	@PUT
	@Path("/{id}")	
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Person updatePerson(Person person, @PathParam(value = "id") int id) {
		return new Person();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void deletePerson(@PathParam(value = "id") int id) {		
	}

}
