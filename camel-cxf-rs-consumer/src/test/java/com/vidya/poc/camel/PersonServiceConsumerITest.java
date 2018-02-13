package com.vidya.poc.camel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vidya.poc.Application;
import com.vidya.poc.pojo.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class PersonServiceConsumerITest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test_hapy_path() throws Exception {

		Person body = restTemplate.getForObject("/services/person/1", Person.class);
		assertThat(body).isExactlyInstanceOf(Person.class);
		assertEquals(1, body.getId());
	}
}
