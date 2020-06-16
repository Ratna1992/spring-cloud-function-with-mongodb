package com.ratna.cloud.function.service;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ratna.cloud.function.model.Book;

@FunctionalSpringBootTest
@RunWith(SpringRunner.class)
class ConsumerImplTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void testAccept() {
		Consumer<Book> consumer = catalog.lookup(Consumer.class, "consumerImpl");
		Book b1 = new Book();
		b1.setId(1);
		b1.setName("Springs");
		consumer.accept(b1);
	}

}
