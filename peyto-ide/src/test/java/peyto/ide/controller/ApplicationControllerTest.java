package peyto.ide.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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
import peyto.ide.data.AddApplicationData;
import peyto.ide.data.UpdateApplicationData;

@AutoConfigureMockMvc
@SpringBootTest
class ApplicationControllerTest {

	@Autowired
	private MockMvc mockMvc;

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
	void testAddApplication() throws Exception {
		AddApplicationData data = new AddApplicationData();
		data.setApplicationName("sample app");
		data.setApplicationDescription("this is a sample application");
		ObjectMapper MAPPER = new ObjectMapper();
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/application")
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testGetApplications() throws Exception {
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/application"));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testUpdateApplication() throws Exception {
		long applicationId = 2;
		UpdateApplicationData data = new UpdateApplicationData();
		data.setApplicatoinId(applicationId);
		data.setApplicationName("hello app");
		data.setApplicationDescription("this is a hello application");
		ObjectMapper MAPPER = new ObjectMapper();
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.put(String.format("/api/application/%s", applicationId))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testRemoveApplication() throws Exception {
		long applicationId = 2;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/application/%s",  applicationId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}



}
