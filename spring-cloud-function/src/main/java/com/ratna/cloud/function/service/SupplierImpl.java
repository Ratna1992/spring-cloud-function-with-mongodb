package com.ratna.cloud.function.service;

import java.util.function.Supplier;

import com.ratna.cloud.function.model.Book;

public class SupplierImpl implements Supplier<Book> {

	@Override
	public Book get() {
		Book book = new Book();
		book.setId(1);
		book.setName("Spring Cloud Function");
		return book;
	}

}
