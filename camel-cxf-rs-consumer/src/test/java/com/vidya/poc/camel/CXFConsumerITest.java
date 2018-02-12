package com.vidya.poc.camel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vidya.poc.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class CXFConsumerITest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test_hapy_path() throws Exception {

		String body = restTemplate.getForObject("/services/sayHello/a", String.class);
		assertThat(body).isEqualTo("Sending Response from the camel processor");
	}
}