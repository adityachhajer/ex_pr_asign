package com.example.expense.tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ExpenseTrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"port=3306",
		"db_name=expense_tracker",
		"username=root",
		"password=root"})
@ActiveProfiles("test")
class ExpenseTrackerApplicationTests {

	@Test
	void contextLoads() {
	}

}
