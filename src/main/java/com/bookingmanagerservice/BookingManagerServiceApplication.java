package com.bookingmanagerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Booking Manager Service Spring Boot application.
 * This class serves as the entry point for the application.
 */
@SpringBootApplication // Marks this application as a Spring Boot application and enables auto-configuration, component scanning, and other features.
public class BookingManagerServiceApplication {

	/**
	 * Main method which serves as the entry point for the Spring Boot application.
	 * This method will launch the Spring Boot application using the specified configuration.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookingManagerServiceApplication.class, args); // Launches the Spring Boot application.
	}

}
