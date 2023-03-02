package com.learn.realtime.springboot.inventorylisting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 *  1. controller
 *  2. service
 *  3. config
 *  4. repository reactive
 *  5. clients  kafka mq webclient
 *  6. model
 *  7. utils
 *  8. constants
 *  9. exceptions
 *  10. dao
 *
 *
 *
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan without arguments tells Spring to scan the current package and all of its sub-packages.
 *
 *
 * Understand the usage in future
 * @EnableAsync
 * @EnableScheduling
 * @EnableCaching
 *
 *
 */
@SpringBootApplication // important
public class InventoryListingApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryListingApplication.class, args); // important
	}

}
