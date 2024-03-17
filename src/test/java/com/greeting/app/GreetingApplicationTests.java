package com.greeting.app;

import com.greeting.app.services.WhenService;
import com.greeting.app.services.WhomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingApplicationTests {
	private static final String NAME_TEST = "TEST";
	private static final String NAME_DEFAULT = "WORLD";
	private static final String ZONE_UTC_PLUS_ONE = "UTC+1";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WhomService whomService;
    @Autowired
    private WhenService whenService;

	@Test
	void whomServiceTest() {
		assertEquals(NAME_DEFAULT, whomService.whom(Optional.empty()));
		assertEquals(NAME_TEST, whomService.whom(Optional.of(NAME_TEST)));
	}

	@Test
	void whenServiceTest() {
		assertEquals(Instant.now().atZone(ZoneId
				.of(ZONE_UTC_PLUS_ONE))
				.format(DateTimeFormatter.ISO_LOCAL_DATE),
				whenService.when());
	}

	@Test
	void requestWithNoNameParameter() throws Exception {
		this.mockMvc.perform(get("/greet"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(createResponseString(NAME_DEFAULT))));
	}

	@Test
	void requestWithNameParameter() throws Exception {
		this.mockMvc.perform(get("/greet?name={name}", NAME_TEST))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(createResponseString(NAME_TEST))));
	}

	private String createResponseString(String name){
		return String.format("{\"whom\":\"%s\",\"when\":\"%s\"}", name, whenService.when());
	}

}

