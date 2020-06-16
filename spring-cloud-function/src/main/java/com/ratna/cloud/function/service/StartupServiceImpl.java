package com.ratna.cloud.function.service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.ratna.cloud.function.model.Book;

@Service
public class StartupServiceImpl implements StartUpService {

	// it takes an argument (object of type T) and returns an object (object of type
	// R). The argument and output can be a different type.

	@Override
	public Function<Book, Integer> length() {
		Function<Book, Integer> function = (input) -> input.getName().length();
		return function;
	}

	// it takes no arguments and returns a result
	@Override
	public Supplier<Book> getBook() {
		Book book = new Book();
		book.setId(1);
		book.setName("Spring Cloud Function");
		// no input,outputs book
		Supplier<Book> supplier = () -> book;
		return supplier;
	}

	// it takes an argument and returns nothing.
	@Override
	public Consumer<Book> displayBook() {
		Consumer<Book> consumer = (input) -> System.out.println(input);
		return consumer;
	}

	@Override
	public Function<String, String> uppercase() {
		return v -> v.toUpperCase();
	}

}
