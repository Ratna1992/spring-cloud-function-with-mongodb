package com.ratna.cloud.function.funtions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ratna.cloud.function.SpringCloudFunctionApplication;
import com.ratna.cloud.function.model.Book;
import com.ratna.cloud.function.service.BookService;

@SpringBootTest(classes = { SpringCloudFunctionApplication.class,
		BookComponent.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
class BookComponentTest {

	@Mock
	private TestRestTemplate rest;

	@Mock
	BookService mockBookService;

	Book book = null;
	List<Book> bookList = null;

	@BeforeEach
	void setUp() {
		book = new Book();
		book.setId(1);
		book.setName("Spring");
		bookList = Arrays.asList(book);

	}

	@Test
	void testSaveBook() throws Exception {
		Function<Book, String> bookFunction = (input) -> input.getName();
		when(mockBookService.saveBook()).thenReturn(bookFunction);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Book> request = new HttpEntity<>(book, headers);
		ResponseEntity<String> postForEntity = new ResponseEntity<String>(book.getName(), HttpStatus.OK);
		when(rest.postForEntity("/saveBook", request, String.class)).thenReturn(postForEntity);
		String body = postForEntity.getBody();
		assertEquals(book.getName(), body);
	}

	@Test
	void testGetAllBooks() throws Exception {
		Supplier<List<Book>> bookListSupplier = () -> bookList;
		when(mockBookService.getAllBooks()).thenReturn(bookListSupplier).getMock();
		Book[] array = (Book[]) bookList.toArray();
		ResponseEntity<Book[]> bookResponseEntity = new ResponseEntity<Book[]>(array, HttpStatus.OK);
		when(rest.getForEntity(new URI("/getAllBooks"), Book[].class)).thenReturn(bookResponseEntity);
		Book[] body = bookResponseEntity.getBody();
		assertEquals(bookList.size(), body.length);

	}

}
