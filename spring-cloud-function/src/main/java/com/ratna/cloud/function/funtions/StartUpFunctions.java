package com.ratna.cloud.function.funtions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.ratna.cloud.function.model.Book;
import com.ratna.cloud.function.service.StartUpService;

@Component
public class StartUpFunctions {

	@Autowired
	StartUpService startUpService;

	@Bean
	public Function<Book, Integer> length() {
		return startUpService.length();
	}

	@Bean
	public Supplier<Book> getBook() {
		return startUpService.getBook();
	}

	@Bean
	public Consumer<Book> displayBook() {
		return startUpService.displayBook();
	}

	@Bean
	public Function<String, String> uppercase() {
		return startUpService.uppercase();
	}

}
