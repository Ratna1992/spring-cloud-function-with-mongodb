package com.ratna.cloud.function.service;

import static org.junit.Assert.assertEquals;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FunctionalSpringBootTest
@RunWith(SpringRunner.class)
class FunctionImplTest {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	void testApply() throws Exception {

		Function<String, Integer> function = catalog.lookup(Function.class, "functionImpl");
		assertEquals(new Integer(5), function.apply("hello"));
	}

}
