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
import peyto.ide.data.AddMessageData;
import peyto.ide.data.UpdateMessageData;

@AutoConfigureMockMvc
@SpringBootTest
class MessageControllerTest {

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
	void testAddMessage() throws Exception {
		long applicationId = 1;
		AddMessageData data = new AddMessageData();
		data.setApplicationId(applicationId);
		data.setMessageName("sample api");
		data.setMessageDescription("this is a sample api");
		ObjectMapper MAPPER = new ObjectMapper();
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/message")
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testGetMessageByApplicationId() throws Exception {
		long applicationId = 1;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/message?applicationId=%s", applicationId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testUpdateMessage() throws Exception {
		long messageId = 1;
		long applicationId = 1;
		UpdateMessageData data = new UpdateMessageData();
		data.setMessageId(messageId);
		data.setApplicationId(applicationId);
		data.setMessageName("hello api");
		data.setMessageDescription("this is a hello api");
		ObjectMapper MAPPER = new ObjectMapper();
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.put(String.format("/api/message/%s", messageId))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testRemoveMessage() throws Exception {
		long messageId = 1;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/message/%s",  messageId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

}
