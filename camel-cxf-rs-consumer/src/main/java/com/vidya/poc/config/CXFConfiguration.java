package com.vidya.poc.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vidya.poc.camel.HelloServiceImpl;

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
		endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl()));
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
