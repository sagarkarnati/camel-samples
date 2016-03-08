package com.vidya.poc.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints
@UseAdviceWith
@ContextConfiguration({"classpath:META-INF/spring/camel-context-test.xml"})
public class HTTPUrlInvocationTest {
	
	@Produce(uri = "mock:stream:in")
	private ProducerTemplate template;
	
	@EndpointInject(uri = "mock:https://www.google.com/search?q=${body}")
	private MockEndpoint resultEndPoint;
	
	
	@Test
	public void test_happy_path() throws Exception
	{
		template.sendBody("Himalayas");
		
		resultEndPoint.expectedMessageCount(1);
		resultEndPoint.assertIsSatisfied();
	}
}
