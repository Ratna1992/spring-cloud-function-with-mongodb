package com.ratna.cloud.function.funtions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.ratna.cloud.function.SpringCloudFunctionApplication;
import com.ratna.cloud.function.model.Book;

@SpringBootTest(classes = { SpringCloudFunctionApplication.class,
		StartUpFunctions.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class StartUpFunctionsTest {

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void testUppercase() throws Exception {
		ResponseEntity<String> result = this.rest.exchange(RequestEntity.post(new URI("/uppercase")).body("hello"),
				String.class);
		assertEquals("HELLO", result.getBody());
	}

	@Test
	public void testgetBook() throws Exception {
		Book body = this.rest.getForEntity(new URI("/getBook"), Book.class).getBody();
		assertEquals(1, body.getId());
		assertEquals("Spring Cloud Function", body.getName());

	}

	@Test
	public void testgetBookNameLength() throws Exception {
		Book b1 = new Book();
		b1.setId(1);
		b1.setName("Spring");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Book> request = new HttpEntity<>(b1, headers);

		ResponseEntity<Integer> postForEntity = this.rest.postForEntity("/length", request, Integer.class);

		assertEquals(6, postForEntity.getBody());

	}

}
