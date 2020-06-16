package com.ratna.cloud.function.service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.ratna.cloud.function.model.Book;

public interface BookService {

	Function<Book, String> saveBook();

	Supplier<List<Book>> getAllBooks();

}
