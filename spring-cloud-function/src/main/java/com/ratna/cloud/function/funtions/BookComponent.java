package com.ratna.cloud.function.funtions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ratna.cloud.function.exceptions.BookNotFoundException;
import com.ratna.cloud.function.model.Book;
import com.ratna.cloud.function.service.BookService;

@Component
public class BookComponent {

	@Autowired
	BookService bookService;

	@Bean
	public Function<Book, String> saveBook() {
		try {
			return bookService.saveBook();
		} catch (Throwable e) {
			throw new BookNotFoundException("Unable to save Book");
		}
	}

	@Bean
	public Supplier<List<Book>> getAllBooks() {
		try {
			return bookService.getAllBooks();
		} catch (Throwable e) {
			throw new BookNotFoundException("Unable to Fetch Book List");
		}

	}
}
