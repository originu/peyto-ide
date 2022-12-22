package peyto.ide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import peyto.ide.dto.TemplateDto;

@AutoConfigureMockMvc
@SpringBootTest
class TemplateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper MAPPER = new ObjectMapper();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void contextLoads() {
	}

	@Test
	void add() throws Exception {
		TemplateDto	dto = new TemplateDto();
		dto.setCategoryCode("TEST");
		dto.setRevisionName("TEST");
		dto.setContent("test code");

		ObjectNode objectNode = MAPPER.convertValue(dto, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post(String.format("/api/template"))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void getItemsByCategoryCode() throws Exception {
		String categoryCode = "TEST";
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/template?categoryCode=%s", categoryCode)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void getItem() throws Exception {
		long id = 3;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/template/%s", id)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void update() throws Exception {
		TemplateDto	dto = new TemplateDto();
		dto.setId(4);
		dto.setCategoryCode("TEST");
		dto.setRevisionName("TEST1");
		dto.setContent("test code1");

		ObjectNode objectNode = MAPPER.convertValue(dto, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.put(String.format("/api/template/%s", dto.getId()))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void remove() throws Exception {
		long id = 4;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/template/%s", id)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

}