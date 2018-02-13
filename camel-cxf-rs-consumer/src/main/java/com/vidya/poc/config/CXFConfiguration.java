package com.vidya.poc.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vidya.poc.camel.HelloServiceImpl;
import com.vidya.poc.camel.PersonServiceImpl;

@Configuration
public class CXFConfiguration {

	@Autowired
	private Bus cxf;

	@Bean
	public Bus cxf() {
		return new SpringBus();
	}

	@Bean
	public JAXRSServerFactoryBean rsServer() {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(cxf);
		endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl(),new PersonServiceImpl()));
		endpoint.setProviders(Arrays.<Object>asList(new JAXBElementProvider(),new JacksonJsonProvider()));
		endpoint.setAddress("/");
		return endpoint;
	}

	@Bean
	public ServletRegistrationBean exampleServletBean() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new CXFServlet(), "/services/*");
		bean.setLoadOnStartup(1);
		return bean;
	}
}
