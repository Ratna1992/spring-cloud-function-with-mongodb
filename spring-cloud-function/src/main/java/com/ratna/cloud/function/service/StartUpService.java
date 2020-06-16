package com.ratna.cloud.function.service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.ratna.cloud.function.model.Book;

public interface StartUpService {

	Function<String, String> uppercase();

	Function<Book, Integer> length();

	Supplier<Book> getBook();

	Consumer<Book> displayBook();

}
