package com.ratna.cloud.function.service;

import java.util.function.Consumer;

import com.ratna.cloud.function.model.Book;

public class ConsumerImpl implements Consumer<Book> {

	@Override
	public void accept(Book t) {
		System.out.println(t);
	}

}
