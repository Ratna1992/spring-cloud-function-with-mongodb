package com.ratna.cloud.function.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;

import com.ratna.cloud.function.model.Book;
import com.ratna.cloud.function.repository.BookRepository;

@FunctionalSpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookServiceImplTest {

	@Mock
	BookRepository mockBookRepository;

	@InjectMocks
	BookServiceImpl mockBookServiceImpl;
	Book book = null;
	List<Book> bookList = null;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		book = new Book();
		book.setId(1);
		book.setName("Spring");

		bookList = Arrays.asList(book);
	}

	@Test
	void testSaveBook() throws Exception {
		when(mockBookRepository.save(book)).thenReturn(book);
		String apply = mockBookServiceImpl.saveBook().apply(book);
		assertEquals(book.getName() + " save to book_store", apply);

	}

	@Test
	void testGetAllBooks() throws Exception {
		when(mockBookRepository.findAll()).thenReturn(bookList);
		List<Book> list = mockBookServiceImpl.getAllBooks().get();
		assertEquals(bookList.size(), list.size());

	}

}
