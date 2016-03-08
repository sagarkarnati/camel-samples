package com.vidya.poc.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints
@UseAdviceWith
@ContextConfiguration({ "classpath:META-INF/spring/camel-context-test.xml" })
public class HTTPUrlInvocationTest {

	@Produce(uri = "direct:start")
	private ProducerTemplate template;

	@EndpointInject(uri = "mock:https://www.google.com/search?q=${body}")
	private MockEndpoint resultEndPoint;
	
	@EndpointInject(uri = "mock:advised")
	private MockEndpoint mockResultEndPoint;
	
	@Autowired
	private ModelCamelContext modelCamelContext;

	// This method invokes the URL when ever tests are run.
	@Test
	public void test_happy_path() throws Exception {
		template.sendBody("Himalayas");

		resultEndPoint.expectedMessageCount(1);
		resultEndPoint.assertIsSatisfied();
	}

	// This method invokes the Mock end point URL when ever tests are run.
	@Test
	public void test_happy_path_mock_endpoint_url() throws Exception {
		modelCamelContext.getRouteDefinition("urlInvocation").adviceWith(modelCamelContext, new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				// intercept sending to mock:foo and do something else
				interceptSendToEndpoint("mock:https://www.google.com/search?q=${body}").skipSendToOriginalEndpoint().to("mock:advised");
			}
		});

		template.sendBody("Himalayas");

		resultEndPoint.expectedMessageCount(0);
		mockResultEndPoint.expectedMessageCount(1);
		resultEndPoint.assertIsSatisfied();
		mockResultEndPoint.assertIsSatisfied();
	}
}
