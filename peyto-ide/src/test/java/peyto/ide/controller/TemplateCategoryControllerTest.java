package peyto.ide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;
import peyto.ide.data.AddMessageFieldData;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.dto.TemplateCategoryDto;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
class TemplateCategoryControllerTest {

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
		TemplateCategoryDto	dto = new TemplateCategoryDto();
		dto.setCategoryCode("TEST");
		dto.setCategoryName("TEST");

		ObjectNode objectNode = MAPPER.convertValue(dto, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post(String.format("/api/template-category"))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void getItems() throws Exception {
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/template-category")));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void getItem() throws Exception {
		long id = 6;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/template-category/%s", id)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void getItemByCategoryCode() throws Exception {
		String categoryCode = "TEST";
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/template-category/categoryCode?categoryCode=%s", categoryCode)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void update() throws Exception {
		TemplateCategoryDto	dto = new TemplateCategoryDto();
		dto.setId(6);
		dto.setCategoryCode("TEST1");
		dto.setCategoryName("TEST1");
		dto.setDescription("TEST1");
		ObjectNode objectNode = MAPPER.convertValue(dto, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.put(String.format("/api/template-category/%s", dto.getId()))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void remove() throws Exception {
		long id = 6;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/template-category/%s", id)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

}