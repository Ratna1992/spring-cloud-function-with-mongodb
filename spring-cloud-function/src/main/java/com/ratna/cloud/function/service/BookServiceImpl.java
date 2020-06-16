package com.ratna.cloud.function.service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratna.cloud.function.exceptions.BookNotFoundException;
import com.ratna.cloud.function.model.Book;
import com.ratna.cloud.function.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository repository;

	@Override
	public Function<Book, String> saveBook() throws BookNotFoundException {
		return input -> repository.save(input).getName() + " save to book_store";
	}

	@Override
	public Supplier<List<Book>> getAllBooks() throws BookNotFoundException {
		return () -> repository.findAll();
	}

}
