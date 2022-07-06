package com.example.AMSProject;

import com.example.AMSProject.controller.AdvertisementController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AmsProjectApplicationTests {

	@Autowired
	private AdvertisementController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}






/*	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:8080/advertisement", String.class)).contains("Hello, World");
	}
		@Autowired
	private TestRestTemplate restTemplate;

*/

}
